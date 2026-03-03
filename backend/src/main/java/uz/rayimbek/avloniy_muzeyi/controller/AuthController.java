package uz.rayimbek.avloniy_muzeyi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.rayimbek.avloniy_muzeyi.dto.request.LoginRequest;
import uz.rayimbek.avloniy_muzeyi.dto.response.AuthResponse;
import uz.rayimbek.avloniy_muzeyi.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    // Birinchi adminni yaratish — deploy qilgandan keyin o'chirib tashlang!
    @PostMapping("/setup")
    public ResponseEntity<String> setup() {
        authService.createAdmin("admin", "Admin@12345");
        return ResponseEntity.ok("Admin yaratildi!");
    }
}