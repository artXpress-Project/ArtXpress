package com.group5.ArtExpress.service;

import com.group5.ArtExpress.customException.ArtworkNotFoundException;
import com.group5.ArtExpress.customException.GenreDoesNotExistException;
import com.group5.ArtExpress.customException.IdNotFoundException;
import com.group5.ArtExpress.customException.NoListOfArtworkWithThatParticularGenre;
import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.data.models.Artwork;
import com.group5.ArtExpress.data.models.Collector;
import com.group5.ArtExpress.data.models.Genre;
import com.group5.ArtExpress.dto.ArtworkDto;
import com.group5.ArtExpress.dto.requestDto.ArtworkRequest;
import com.group5.ArtExpress.dto.responseDto.MessageResponse;
import com.group5.ArtExpress.repository.ArtWorksRepository;
import com.group5.ArtExpress.repository.CollectorRepo;
import com.group5.ArtExpress.repository.GenreRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ArtworkServiceImpl implements ArtworkService{
    @Autowired
    private GenreRepo genreRepo;
    @Autowired
   private ArtWorksRepository artworkRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CollectorRepo collectorRepo;


    @Override
    public Artwork uploadArtwork(ArtworkRequest request, Artist artist) {
        Genre genre = genreRepo.save(request.getGenre());
        Artwork artwork = modelMapper.map(request,Artwork.class);
        artwork.setGenre(genre);
        artwork.setComments(new ArrayList<>());
        artwork.setArtist(artist);
        artwork.setEmail(artist.getEmail());
        artwork.setUploadDateTime(LocalDateTime.now());
        artwork.setUploadDateTime(LocalDate.now());


        return artworkRepository.save(artwork);
    }

    @Override
    public Artwork updateArtwork(Long id, ArtworkRequest update) {
        Artwork artwork = findArtWorkById(id);
        if(artwork.getTitle() != null) artwork.setTitle(update.getTitle());
        if(artwork.getMedium() != null) artwork.setMedium(update.getMedium());
        if(artwork.getPrice() != null) artwork.setPrice(update.getPrice());
        if(artwork.getImages() != null) artwork.setImages(update.getImages());
        if(artwork.getDescription() != null) artwork.setDescription(update.getDescription());
        if(artwork.getSize() != null) artwork.setSize(update.getSize());
        Genre genre = genreRepo.findGenreByGenreName(update.getGenre().getGenreName());
        if(genre != null) artwork.setGenre(update.getGenre());
        artwork.setUploadDateTime(LocalDate.now());
        return artworkRepository.save(artwork);

    }

    @Override
    public MessageResponse deleteArtwork(Long artWorkId) {
        Artwork artwork = findArtWorkById(artWorkId);
        artworkRepository.delete(artwork);
        return new MessageResponse("Deleted Successfully",200);

    }

    @Override
    public List<Artwork> findArtWorkByArtist(Long artistId) {
        List<Artwork> artwork = artworkRepository.findArtworkByArtistId(artistId);
        if (artwork == null) throw  new ArtworkNotFoundException("Artwork with the id " + artistId + " was not found");
        else return artwork;
    }

    @Override
    public Artwork findArtWorkById(Long id) {
        return artworkRepository.findById(id).orElseThrow(()-> new IdNotFoundException("Artwork with this " + id + "not found"));
    }

    @Override
    public List<Artwork> searchArtwork(String genreName) {
//        return artworkRepository.findByGenreName(genreName);
        List<Artwork> artwork = findAllArtwork();
        List<Artwork> genreType = new ArrayList<>();
        for(Artwork art: artwork){
            if (art.getGenre().getGenreName().equals(genreName)){
                genreType.add(art);}
        }
        if(!genreType.isEmpty()){
            return genreType;
        }else throw new GenreDoesNotExistException("GenreType of " + genreName + " does not exist");

    }

    @Override
    public List<Artwork> findAllArtwork() {
        return artworkRepository.findAll();

    }

    @Override
    public List<Artwork> findArtworkByArtistBusinessName(String businessName) {
        List<Artwork> artwork = artworkRepository.findArtworkByArtistBusinessName(businessName);
        if(artwork == null) throw new ArtworkNotFoundException("No artwork with business name " + businessName + "exist");
        else return artwork;
    }

    @Override
    public ArtworkDto addToFavourite(Long artworkId, Collector collector) {
        Artwork artwork = findArtWorkById(artworkId);
        ArtworkDto artworkDto = new ArtworkDto();
        artworkDto.setTitle(artwork.getTitle());
        artworkDto.setImages(artwork.getImages());
        artworkDto.setDescription(artwork.getDescription());
        artworkDto.setId(artworkId);

        boolean isFavourite = false;

        List<ArtworkDto> favourites = collector.getFavourite();
        for( ArtworkDto favour: favourites){
            if(favour.getId().equals(artworkId)){
                isFavourite = true;
                break;
            }
        }
        if(isFavourite){
            favourites.removeIf(favour-> favour.getId().equals(artworkId));
        }else{
            favourites.add(artworkDto);
        }
        collectorRepo.save(collector);
        return artworkDto;
    }

    @Override
    public Artwork updateAvailabilityStatus(Long artworkId) {
        Artwork artwork = findArtWorkById(artworkId);
        artwork.setAvailable(!artwork.isAvailable());
        return artworkRepository.save(artwork);
    }


}
