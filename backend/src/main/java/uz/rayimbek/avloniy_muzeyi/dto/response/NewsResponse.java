package uz.rayimbek.avloniy_muzeyi.dto.response;

import lombok.Builder;
import lombok.Data;
import uz.rayimbek.avloniy_muzeyi.entity.News;

import java.time.LocalDateTime;

@Data
@Builder
public class NewsResponse {
    private Long id;
    private String title;
    private String slug;
    private String content;
    private String excerpt;
    private String imageUrl;
    private News.Category category;
    private Boolean published;
    private String authorUsername;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}