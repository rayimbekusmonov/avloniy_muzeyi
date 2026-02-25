package uz.rayimbek.avloniy_muzeyi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import uz.rayimbek.avloniy_muzeyi.entity.News;

@Data
public class NewsRequest {

    @NotBlank(message = "Sarlavha bo'sh bo'lishi mumkin emas")
    private String title;

    @NotBlank(message = "Kontent bo'sh bo'lishi mumkin emas")
    private String content;

    private String excerpt;
    private String imageUrl;

    @NotNull(message = "Kategoriya bo'sh bo'lishi mumkin emas")
    private News.Category category;

    private Boolean published = false;
}