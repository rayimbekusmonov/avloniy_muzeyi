package uz.rayimbek.avloniy_muzeyi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.rayimbek.avloniy_muzeyi.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Page<Contact> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<Contact> findAllByReadFalseOrderByCreatedAtDesc(Pageable pageable);
    long countByReadFalse();
}