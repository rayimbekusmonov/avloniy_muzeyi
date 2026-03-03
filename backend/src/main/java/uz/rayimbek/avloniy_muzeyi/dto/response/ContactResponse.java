package uz.rayimbek.avloniy_muzeyi.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ContactResponse {
    private Long id;
    private String name;
    private String phone;
    private String telegram;
    private String subject;
    private String message;
    private Boolean read;
    private LocalDateTime createdAt;
}