package uz.rayimbek.avloniy_muzeyi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.rayimbek.avloniy_muzeyi.dto.request.LoginRequest;
import uz.rayimbek.avloniy_muzeyi.dto.response.AuthResponse;
import uz.rayimbek.avloniy_muzeyi.entity.User;
import uz.rayimbek.avloniy_muzeyi.repository.UserRepository;
import uz.rayimbek.avloniy_muzeyi.security.JwtService;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Foydalanuvchi topilmadi"));

        String token = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(token)
                .username(user.getUsername())
                .role(user.getRole().name())
                .build();
    }

    // Birinchi admin yaratish uchun (faqat bir marta ishlatiladi)
    public void createAdmin(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Bu username allaqachon mavjud");
        }

        User admin = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(User.Role.ADMIN)
                .build();

        userRepository.save(admin);
    }
}