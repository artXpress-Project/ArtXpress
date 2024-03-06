package com.group5.ArtExpress.dto.requestDto;

import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.data.models.Comment;
import com.group5.ArtExpress.data.models.Genre;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UploadArtRequest {
    private List<MultipartFile> artWork;
    private String title;
    private String genre;
    private String description;
    private String artist;
    private String medium;
    private String size;
    private BigDecimal price;
    private LocalDateTime uploadDateTime;
}
//@RestController
//@RequestMapping("/api/art")
//public class ArtController {
//
//    @Autowired
//    private ArtService artService; // Assuming you have a service to handle business logic




