package com.group5.ArtExpress.service;




import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.JsonPatchOperation;
import com.github.fge.jsonpatch.ReplaceOperation;
import com.group5.ArtExpress.customException.ArtistNotEnabled;
import com.group5.ArtExpress.customException.ArtistNotFoundException;
import com.group5.ArtExpress.customException.ArtworkNotFoundException;
import com.group5.ArtExpress.data.models.Artwork;
import com.group5.ArtExpress.data.models.Genre;
import com.group5.ArtExpress.dto.requestDto.*;
import com.group5.ArtExpress.dto.responseDto.*;

//import com.group5.ArtExpress.dto.requestDto.SendMailRequest;
//import com.group5.ArtExpress.emailService.BrevoMailService;

import com.group5.ArtExpress.emailService.EmailService;
import com.group5.ArtExpress.emailService.EmailVerificationService;
import com.group5.ArtExpress.confirmation.ArtistConfirmation;
import com.group5.ArtExpress.customException.TokenWasNotFoundException;

import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.repository.ArtistConfirmationRepo;
import com.group5.ArtExpress.repository.ArtistRepo;
import com.group5.ArtExpress.repository.ArtworkRepository;
import com.group5.ArtExpress.repository.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import static com.group5.ArtExpress.utils.Mapper.map;
import static java.util.Arrays.stream;

@Service
public class ArtistServiceImpl implements ArtistService{
    @Autowired
    private EmailService emailService;


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ArtistRepo artistRepo;

    @Autowired
    private ArtistConfirmationRepo artistConfirmationRepo;

    @Autowired
    private EmailVerificationService emailVerificationService;

    @Autowired
    private ArtworkRepository artworkRepository;

    @Autowired
    private GenreRepository genreRepository;

//    @Autowired
//    private BrevoMailService brevoMailService;

    @Override
    public Artist register(ArtistRequest request) {
        emailVerificationService.verifyEmailFormat(request.getEmail());
        emailVerificationService.ifArtistEmailAlreadyExist(request.getEmail());
        Artist artist = modelMapper.map(request, Artist.class);
        map(request, artist);
        Artist newArtist = artistRepo.save(artist);

        ArtistConfirmation artistConfirmation = new ArtistConfirmation(newArtist);
        artistConfirmationRepo.save(artistConfirmation);

        emailService.sendSimpleMailMessage(artist.getFirstName(),artist.getEmail(),artistConfirmation.getToken());
        return newArtist;

    }


    @Override
    public Boolean verifyToken(String token) {

        ArtistConfirmation artistConfirmation = artistConfirmationRepo.findByToken(token);
        System.out.println(artistConfirmation);
        if(artistConfirmation == null) throw new TokenWasNotFoundException("Could not find token");
        Artist artist = artistRepo.findByEmailIgnoreCase(artistConfirmation.getArtist().getEmail());
        artist.setEnabled(true);
        artistRepo.save(artist);
        return Boolean.TRUE;

    }

    @Override
    public MessageResponse login(LoginRequest loginRequest) {
        Artist artist = emailVerificationService.findArtistEmail(loginRequest.getEmail());
        if(artist.getPassword().equals(loginRequest.getPassword())){
            if(artist.isEnabled()) {
                artist.setLocked(false);
                return new MessageResponse("Login successful.",
                                           200);
            }
            else return new MessageResponse("Account not verified. Please verify your email.", 401);
        }
        else return new MessageResponse("Login Unsuccessful, Account does not exist.", 401);
    }

    public UploadArtResponse uploadArt(UploadArtRequest uploadArtRequest) {
        Optional<Artist> foundArtist = artistRepo.findByBusinessName(uploadArtRequest.getArtist());
        if (foundArtist.isEmpty()) throw new ArtistNotFoundException("Artist not found!");

        boolean isUnlocked = !foundArtist.get().isLocked();
        
        if (foundArtist.get().isEnabled() && isUnlocked) {
            Artwork artwork = getArtwork(uploadArtRequest, foundArtist);
            artworkRepository.save(artwork);
            return getUploadArtResponse(artwork);
        }
        throw new ArtistNotEnabled("Not enabled");
    }

    private static UploadArtResponse getUploadArtResponse(Artwork artwork) {
        UploadArtResponse uploadArtResponse = new UploadArtResponse();
        uploadArtResponse.setMessage("Upload successful");
        uploadArtResponse.setStatusCode(200);
//        uploadArtResponse.setUploadDateTime(artwork.getUploadDateTime());

        return uploadArtResponse;
    }

    private Artwork getArtwork(UploadArtRequest uploadArtRequest, Optional<Artist> foundArtist) {
        Genre genre = getGenre(uploadArtRequest);
        Artwork artwork = new Artwork();
        artwork.setArtist(foundArtist.get());
        mapArtwork(uploadArtRequest, artwork, genre);
        return artwork;
    }

    private Genre getGenre(UploadArtRequest uploadArtRequest) {
        Genre genre = new Genre();
        genre.setGenreName(uploadArtRequest.getGenre());
        genreRepository.save(genre);
        return genre;
    }

    private static void mapArtwork(UploadArtRequest uploadArtRequest, Artwork artwork, Genre genre) {
        artwork.setTitle(uploadArtRequest.getTitle());
        artwork.setDescription(uploadArtRequest.getDescription());
        artwork.setMedium(uploadArtRequest.getMedium());
        artwork.setPrice(uploadArtRequest.getPrice());
        artwork.setSize(uploadArtRequest.getSize());
        artwork.setGenre(genre);
//        artwork.setUploadDateTime(LocalDateTime.now());
        artwork.setImageLinks(uploadArtRequest.getImageLink());
    }

    public MessageResponse logout(LogoutRequest logoutRequest) {
        Artist artist = emailVerificationService.findArtistEmail(logoutRequest.getEmail());
            if(artist.isEnabled()) {
                artist.setLocked(true);
                artistRepo.save(artist);
                return new MessageResponse("Logout successful",
                                        200);
            }else return new MessageResponse( "message: " + "Artist is not logged in yet",
                                            401);
    }

    @Override
    public UpdateArtworkResponse updateUpload(Long artworkId, UpdateUploadRequest request) {
        ObjectMapper mapper = new ObjectMapper();
        List<JsonPatchOperation> operations = new ArrayList<>(buildJsonPatchOperations(request));

        JsonPatch updatePatch = new JsonPatch(operations);

        Artwork foundArtwork = artworkRepository.findById(artworkId)
                                .orElseThrow(()-> getArtworkNotFoundException(artworkId));

        JsonNode artworkNode = mapper.convertValue(foundArtwork, JsonNode.class);
        JsonNode updatedNode = applyPatch(updatePatch, artworkNode);

        var updatedArtwork = mapper.convertValue(updatedNode, Artwork.class);
        artworkRepository.save(updatedArtwork);
        return new UpdateArtworkResponse("Artwork details updated successfully", "201");
    }

    private JsonNode applyPatch(JsonPatch updatePatch, JsonNode artworkNode) {
        try {
            JsonNode updatedNode = updatePatch.apply(artworkNode);
            return updatedNode;
        } catch (JsonPatchException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    private ArtworkNotFoundException getArtworkNotFoundException(Long artworkId) {
        return new ArtworkNotFoundException(
                String.format("Artwork with id %d not found", artworkId)
        );
    }

    private List<ReplaceOperation> buildJsonPatchOperations(UpdateUploadRequest request) {
        Field[] fields = request.getClass().getDeclaredFields();
        return stream(fields).filter((field)-> isFieldNonNull(request, field))
                .map((field)-> buildReplaceOperation(request, field))
                .toList();
    }

    private static ReplaceOperation buildReplaceOperation(UpdateUploadRequest request, Field field) {
        String path = "/";
        try {
            if (field.getName().equals("businessName")) {
                path += "artist/";
            } else if (field.getName().equals("genreName")) {
                path += "genre/";
            }

            return new ReplaceOperation(new JsonPointer(path),
                    new TextNode(String.valueOf(field.get(request))));
        } catch (JsonPointerException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isFieldNonNull(UpdateUploadRequest request, Field field) {
        try {
            field.setAccessible(true);
            return field.get(request) != null;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UpdateUploadResponse getArtwork(long artworkId) {
        Artwork foundArtwork = artworkRepository.findById(artworkId)
                .orElseThrow(()-> new ArtworkNotFoundException(
                        String.format("Artwork with id %d not found", artworkId)
                ));
        return buildArtworkResponse(foundArtwork);
    }

    private UpdateUploadResponse buildArtworkResponse(Artwork foundArtwork) {
        UpdateUploadResponse response = new UpdateUploadResponse();
        response.setArtist(foundArtwork.getArtist().getBusinessName());
        response.setPrice(foundArtwork.getPrice());
        response.setDescription(foundArtwork.getDescription());
        response.setGenre(foundArtwork.getGenre().getGenreName());
        response.setMedium(foundArtwork.getMedium());
        response.setSize(foundArtwork.getSize());
        response.setTitle(foundArtwork.getTitle());
//        response.setUploadDateTime(foundArtwork.getUploadDateTime());

        return response;
    }

}
