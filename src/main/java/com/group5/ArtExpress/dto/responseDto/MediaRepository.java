package com.group5.ArtExpress.dto.responseDto;

import com.group5.ArtExpress.data.models.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {

}
