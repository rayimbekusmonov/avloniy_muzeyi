package uz.rayimbek.avloniy_muzeyi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import uz.rayimbek.avloniy_muzeyi.entity.enums.ResourceType;

import java.time.LocalDateTime;

@Entity
@Table(name = "resources")
@Getter @Setter @NoArgsConstructor
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "cover_image_url")
    private String coverImageUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResourceType type;

    @CreationTimestamp
    private LocalDateTime createdAt;
}