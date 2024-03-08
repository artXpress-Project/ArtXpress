package com.group5.ArtExpress.service;

import com.group5.ArtExpress.customException.ArtworkNotFoundException;
import com.group5.ArtExpress.customException.IdNotFoundException;
import com.group5.ArtExpress.customException.NoListOfArtworkWithThatParticularGenre;
import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.data.models.Artwork;
import com.group5.ArtExpress.data.models.Genre;
import com.group5.ArtExpress.dto.requestDto.ArtworkRequest;
import com.group5.ArtExpress.dto.responseDto.MessageResponse;
import com.group5.ArtExpress.repository.ArtWorksRepository;
import com.group5.ArtExpress.repository.GenreRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArtworkServiceImpl implements ArtworkService{
    @Autowired
    private GenreRepo genreRepo;
    @Autowired
   private ArtWorksRepository artworkRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Artwork uploadArtwork(ArtworkRequest request, Artist artist) {
        Genre genre = genreRepo.save(request.getGenre());
        Artwork artwork = modelMapper.map(request,Artwork.class);
        artwork.setGenre(genre);
        artwork.setComments(new ArrayList<>());
        artwork.setArtist(artist);
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
    public Artwork findArtWorkByArtist(Long artistId) {
        Artwork artwork = artworkRepository.findArtworkByArtistId(artistId);
        if (artwork == null) throw  new ArtworkNotFoundException("Artwork with the id " + artistId + " was not found");
        else return artwork;
    }

    @Override
    public Artwork findArtWorkById(Long id) {
        return artworkRepository.findById(id).orElseThrow(()-> new IdNotFoundException("Artwork with this " + id + "not found"));
    }

    @Override
    public List<Artwork> searchArtwork(String genreName) {
        List<Artwork> list = artworkRepository.findByGenreName(genreName);
        if(list.isEmpty()) throw new NoListOfArtworkWithThatParticularGenre("No list of Artwork with that particular " + list);
        else return list;
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
}
