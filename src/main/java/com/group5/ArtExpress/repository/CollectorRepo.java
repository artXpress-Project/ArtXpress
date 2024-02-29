package com.group5.ArtExpress.repository;

import com.group5.ArtExpress.data.models.Collector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectorRepo extends JpaRepository<Collector, Long> {
    Collector findByEmailIgnoreCase(String email);
    Boolean existsByEmail(String email);
}
