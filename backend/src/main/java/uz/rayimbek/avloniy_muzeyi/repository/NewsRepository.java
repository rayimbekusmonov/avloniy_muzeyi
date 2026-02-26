package uz.rayimbek.avloniy_muzeyi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.rayimbek.avloniy_muzeyi.entity.News;

import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    Page<News> findAllByPublishedTrueOrderByCreatedAtDesc(Pageable pageable);
    Page<News> findAllByPublishedTrueAndCategoryOrderByCreatedAtDesc(News.Category category, Pageable pageable);
    Page<News> findAllByOrderByCreatedAtDesc(Pageable pageable);
    @Query("SELECT n FROM News n LEFT JOIN FETCH n.author WHERE n.published = true ORDER BY n.createdAt DESC")
    Page<News> findAllPublishedWithAuthor(Pageable pageable);
    Optional<News> findBySlug(String slug);
    boolean existsBySlug(String slug);
}