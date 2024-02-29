package com.group5.ArtExpress.utils;

import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.data.models.Location;
import com.group5.ArtExpress.dto.requestDto.ArtistRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Mapper {

    public static void map(ArtistRequest request, Artist artist) {
        Location location = new Location();
        location.setState(request.getState());
        location.setLga(request.getLga());
        location.setAddress(request.getAddress());
        artist.setLocation(location);
        artist.setEnabled(false);
        artist.setDateTime(LocalDateTime.now());
        artist.setArtworks(new ArrayList<>());
    }
}
