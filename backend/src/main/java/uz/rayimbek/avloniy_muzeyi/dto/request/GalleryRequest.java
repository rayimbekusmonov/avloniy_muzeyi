package uz.rayimbek.avloniy_muzeyi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import uz.rayimbek.avloniy_muzeyi.entity.Gallery;

@Data
public class GalleryRequest {

    @NotBlank(message = "Sarlavha bo'sh bo'lishi mumkin emas")
    private String title;

    @NotBlank(message = "Fayl URL bo'sh bo'lishi mumkin emas")
    private String fileUrl;

    private String thumbnailUrl;
    private String description;

    @NotNull(message = "Media turi bo'sh bo'lishi mumkin emas")
    private Gallery.MediaType mediaType;
}