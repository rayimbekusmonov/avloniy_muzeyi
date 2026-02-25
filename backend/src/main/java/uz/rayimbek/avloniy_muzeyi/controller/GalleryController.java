package uz.rayimbek.avloniy_muzeyi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.rayimbek.avloniy_muzeyi.dto.request.GalleryRequest;
import uz.rayimbek.avloniy_muzeyi.dto.response.GalleryResponse;
import uz.rayimbek.avloniy_muzeyi.service.GalleryService;

@RestController
@RequestMapping("/api/gallery")
@RequiredArgsConstructor
public class GalleryController {

    private final GalleryService galleryService;

    // PUBLIC
    @GetMapping
    public ResponseEntity<Page<GalleryResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(required = false) String mediaType
    ) {
        return ResponseEntity.ok(galleryService.getAll(page, size, mediaType));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GalleryResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(galleryService.getById(id));
    }

    // ADMIN ONLY
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GalleryResponse> create(@Valid @RequestBody GalleryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(galleryService.create(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GalleryResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody GalleryRequest request
    ) {
        return ResponseEntity.ok(galleryService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        galleryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}