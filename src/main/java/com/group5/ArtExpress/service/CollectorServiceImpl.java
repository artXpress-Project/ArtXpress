package com.group5.ArtExpress.service;


import com.group5.ArtExpress.customException.ActionForbiddenAttempt;
import com.group5.ArtExpress.customException.IdNotFoundException;
import com.group5.ArtExpress.customException.LockException;
import com.group5.ArtExpress.data.models.*;
import com.group5.ArtExpress.dto.responseDto.MessageResponse;
import com.group5.ArtExpress.emailService.EmailService;
import com.group5.ArtExpress.emailService.EmailVerificationService;
import com.group5.ArtExpress.confirmation.CollectorConfirmation;
import com.group5.ArtExpress.customException.TokenWasNotFoundException;

import com.group5.ArtExpress.dto.requestDto.CollectorRequest;
import com.group5.ArtExpress.dto.requestDto.LoginRequest;
import com.group5.ArtExpress.repository.CartRepository;
import com.group5.ArtExpress.repository.CollectorConfirmationRepo;
import com.group5.ArtExpress.repository.CollectorRepo;
import com.group5.ArtExpress.dto.requestDto.LogoutRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;


public class CollectorServiceImpl implements CollectorService{
    @Autowired
    private EmailService emailService;
    @Autowired
    private CollectorRepo collectorRepo;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private EmailVerificationService emailVerificationService;

    @Autowired
    private CollectorConfirmationRepo collectorConfirmationRepo;
    @Autowired
    private ArtworkService artworkService;
    @Override
    public Collector registerCollector(CollectorRequest collectorRequest) {
        emailVerificationService.ifCollectorEmailAlreadyExist(collectorRequest.getEmail());
        emailVerificationService.verifyEmailFormat(collectorRequest.getEmail());
        DeliveryAddress address = new DeliveryAddress();
        address.setCity(collectorRequest.getCity());
        address.setCountry(collectorRequest.getCountry());
        address.setStreetAddress(collectorRequest.getStreetAddress());
        address.setPostalCode(collectorRequest.getPostalCode());
        address.setStateProvince(collectorRequest.getStateProvince());
        Collector collector = modelMapper.map(collectorRequest, Collector.class);
//        String encodedPassword = passwordEncoder.encode(collectorRequest.getPassword());
//        collector.setPassword(encodedPassword);
        List<DeliveryAddress> addresses = new ArrayList<>();
        addresses.add(address);
        collector.setAddresses(addresses);
        collector.setEnabled(false);
        collector.setDateTime(LocalDate.now());
        Collector collects = collectorRepo.save(collector);
        Cart cart = new Cart();
        cart.setCollector(collects);
        cartRepository.save(cart);



        CollectorConfirmation confirmation = new CollectorConfirmation(collects);
        collectorConfirmationRepo.save(confirmation);

        emailService.sendHtmlEmailWithEmbeddedFilesToCollector(collector.getFirstName(),collector.getEmail(),confirmation.getToken());
        return collects;
    }



    @Override
    public Boolean verifyToken(String token) {
        CollectorConfirmation confirmation = collectorConfirmationRepo.findByToken(token);
        if(confirmation == null) throw new TokenWasNotFoundException("Could not find token");
        Collector collector = collectorRepo.findByEmailIgnoreCase(confirmation.getCollector().getEmail());
        collector.setEnabled(true);
        collector.setLocked(true);
        collectorRepo.save(collector);
        return Boolean.TRUE;
    }

    @Override
    public MessageResponse login(LoginRequest loginRequest) {
        Collector collector = emailVerificationService.findCollectorEmail(loginRequest.getEmail());
        collector.setLocked(false);
        if( collector.getPassword().equals(loginRequest.getPassword())){
            if(collector.isEnabled()) {
                collectorRepo.save(collector);
                return new MessageResponse("Login successful. \n", 200);
            }
            else return new MessageResponse("Account not verified. Please verify your email.\n", 401);
        }
        else return new MessageResponse("Login Unsuccessful, Account does not exist.\n", 401);
    }

    @Override
    public MessageResponse logout(LogoutRequest logoutRequest) {
        Collector collector = emailVerificationService.findCollectorEmail(logoutRequest.getEmail());
        collector.setLocked(true);
        if(collector.isEnabled()) {
            collectorRepo.save(collector);
            return new MessageResponse("Logout successful",
                    200);
        }else return new MessageResponse( "message: " + "Artist is not logged in yet",
                401);

    }

    @Override
    public Collector findById(Long id) {
        Collector collector;
        collector = collectorRepo.findById(id)
                .orElseThrow(()-> new IdNotFoundException("Id " + id + " Does not Exist"));
        return collector;
    }



    @Override
    public List<Artwork> findArtworkByArtistBusinessName(String businessName) {
            return artworkService.findArtworkByArtistBusinessName(businessName);
    }

    @Override
    public List<Artwork> searchArtwork(String genre) {
            return artworkService.searchArtwork(genre);
    }

    @Override
    public List<Artwork> findAllArtwork() {
        return artworkService.findAllArtwork();
    }


}
