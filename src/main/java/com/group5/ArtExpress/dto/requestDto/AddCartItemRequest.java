package com.group5.ArtExpress.dto.requestDto;

import lombok.Data;

import java.util.List;
@Data
public class AddCartItemRequest {
    private Long artworkId;
    private int quantity;

    private List<String> toolsUsed;

}
