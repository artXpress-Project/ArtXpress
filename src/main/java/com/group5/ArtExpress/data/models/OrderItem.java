package com.group5.ArtExpress.data.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Artwork artwork;

    private int quantity;
    private Long totalPrice;

    @ElementCollection
    private List<String>  toolsUsed;
}
