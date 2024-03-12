package com.group5.ArtExpress.dto.requestDto;

import com.group5.ArtExpress.data.models.DeliveryAddress;
import lombok.Data;

@Data
public class OrderRequest {
    private Long artistId;
    private DeliveryAddress deliveryAddress;
}
