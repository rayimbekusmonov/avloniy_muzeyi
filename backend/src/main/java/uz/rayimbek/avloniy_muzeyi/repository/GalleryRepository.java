package uz.rayimbek.avloniy_muzeyi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.rayimbek.avloniy_muzeyi.entity.Gallery;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {
    Page<Gallery> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<Gallery> findAllByMediaTypeOrderByCreatedAtDesc(Gallery.MediaType mediaType, Pageable pageable);
}