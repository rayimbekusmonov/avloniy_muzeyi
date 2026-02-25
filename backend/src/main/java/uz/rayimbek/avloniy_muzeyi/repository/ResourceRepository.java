package uz.rayimbek.avloniy_muzeyi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.rayimbek.avloniy_muzeyi.entity.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    Page<Resource> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<Resource> findAllByResourceTypeOrderByCreatedAtDesc(Resource.ResourceType resourceType, Pageable pageable);
    Page<Resource> findAllByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrderByCreatedAtDesc(
            String title, String author, Pageable pageable);
}