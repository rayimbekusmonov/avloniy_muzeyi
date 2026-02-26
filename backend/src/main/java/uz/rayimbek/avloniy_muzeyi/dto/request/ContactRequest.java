package uz.rayimbek.avloniy_muzeyi.dto.request;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContactRequest {

    @NotBlank(message = "Ism bo'sh bo'lishi mumkin emas")
    private String name;

    private String phone;
    private String telegram;
    @AssertTrue(message = "Telefon yoki Telegram kiritish majburiy")
    public boolean isContactValid() {
        return (phone != null && !phone.isBlank()) || (telegram != null && !telegram.isBlank());
    }

    @NotBlank(message = "Mavzu bo'sh bo'lishi mumkin emas")
    private String subject;

    @NotBlank(message = "Xabar bo'sh bo'lishi mumkin emas")
    private String message;
}