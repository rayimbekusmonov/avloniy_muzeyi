package uz.rayimbek.avloniy_muzeyi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.rayimbek.avloniy_muzeyi.entity.Quote;
import java.util.Optional;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    // Tasodifiy bitta hikmatni olish uchun (Main page uchun)
    @Query(value = "SELECT * FROM quote ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Optional<Quote> findRandomQuote();
}
// Qolgan NewsRepository, ResourceRepository va MediaRepository ham xuddi shunday JpaRepository'dan extend oladi.