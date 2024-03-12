package com.group5.ArtExpress.dto.requestDto;

import lombok.Data;

@Data
public class UpdateCartIteRequest {
    private Long cartItemId;
    private int quantity;

}
