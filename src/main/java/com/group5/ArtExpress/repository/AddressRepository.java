package com.group5.ArtExpress.repository;

import com.group5.ArtExpress.data.models.DeliveryAddress;
import jakarta.mail.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<DeliveryAddress,Long> {
}
