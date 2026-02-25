package uz.rayimbek.avloniy_muzeyi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import uz.rayimbek.avloniy_muzeyi.entity.enums.MediaType;

import java.time.LocalDateTime;

@Entity
@Table(name = "media")
@Getter @Setter @NoArgsConstructor
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "media_url", nullable = false)
    private String mediaUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MediaType type;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;      // ✅ Video preview rasmi

    @Column(name = "duration_seconds")
    private Integer durationSeconds;  // ✅ Audio/video davomiyligi

    @CreationTimestamp
    private LocalDateTime createdAt;
}