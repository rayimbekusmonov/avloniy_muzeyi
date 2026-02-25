package uz.rayimbek.avloniy_muzeyi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.rayimbek.avloniy_muzeyi.dto.request.GalleryRequest;
import uz.rayimbek.avloniy_muzeyi.dto.response.GalleryResponse;
import uz.rayimbek.avloniy_muzeyi.entity.Gallery;
import uz.rayimbek.avloniy_muzeyi.repository.GalleryRepository;

@Service
@RequiredArgsConstructor
public class GalleryService {

    private final GalleryRepository galleryRepository;

    public Page<GalleryResponse> getAll(int page, int size, String mediaType) {
        PageRequest pageable = PageRequest.of(page, size);

        if (mediaType != null && !mediaType.isBlank()) {
            Gallery.MediaType type = Gallery.MediaType.valueOf(mediaType.toUpperCase());
            return galleryRepository
                    .findAllByMediaTypeOrderByCreatedAtDesc(type, pageable)
                    .map(this::toResponse);
        }

        return galleryRepository
                .findAllByOrderByCreatedAtDesc(pageable)
                .map(this::toResponse);
    }

    public GalleryResponse getById(Long id) {
        Gallery gallery = galleryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Galereya elementi topilmadi: " + id));
        return toResponse(gallery);
    }

    public GalleryResponse create(GalleryRequest request) {
        Gallery gallery = Gallery.builder()
                .title(request.getTitle())
                .fileUrl(request.getFileUrl())
                .thumbnailUrl(request.getThumbnailUrl())
                .description(request.getDescription())
                .mediaType(request.getMediaType())
                .build();

        return toResponse(galleryRepository.save(gallery));
    }

    public GalleryResponse update(Long id, GalleryRequest request) {
        Gallery gallery = galleryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Galereya elementi topilmadi: " + id));

        gallery.setTitle(request.getTitle());
        gallery.setFileUrl(request.getFileUrl());
        gallery.setThumbnailUrl(request.getThumbnailUrl());
        gallery.setDescription(request.getDescription());
        gallery.setMediaType(request.getMediaType());

        return toResponse(galleryRepository.save(gallery));
    }

    public void delete(Long id) {
        if (!galleryRepository.existsById(id)) {
            throw new RuntimeException("Galereya elementi topilmadi: " + id);
        }
        galleryRepository.deleteById(id);
    }

    private GalleryResponse toResponse(Gallery gallery) {
        return GalleryResponse.builder()
                .id(gallery.getId())
                .title(gallery.getTitle())
                .fileUrl(gallery.getFileUrl())
                .thumbnailUrl(gallery.getThumbnailUrl())
                .description(gallery.getDescription())
                .mediaType(gallery.getMediaType())
                .createdAt(gallery.getCreatedAt())
                .build();
    }
}