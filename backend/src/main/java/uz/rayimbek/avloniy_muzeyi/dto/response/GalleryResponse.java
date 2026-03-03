package uz.rayimbek.avloniy_muzeyi.dto.response;

import lombok.Builder;
import lombok.Data;
import uz.rayimbek.avloniy_muzeyi.entity.Gallery;

import java.time.LocalDateTime;

@Data
@Builder
public class GalleryResponse {
    private Long id;
    private String title;
    private String fileUrl;
    private String thumbnailUrl;
    private String description;
    private Gallery.MediaType mediaType;
    private LocalDateTime createdAt;
}