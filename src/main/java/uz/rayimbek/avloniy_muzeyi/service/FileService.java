package uz.rayimbek.avloniy_muzeyi.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class FileService {

    @Value("${app.upload.dir:uploads/}")
    private String uploadDir;

    private static final long MAX_FILE_SIZE = 50 * 1024 * 1024; // 50MB

    private static final List<String> ALLOWED_TYPES = List.of(
            "application/pdf",
            "audio/mpeg",
            "audio/mp3",
            "video/mp4",
            "image/jpeg",
            "image/png"
    );

    public String saveFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Fayl bo'sh bo'lishi mumkin emas");
        }

        String contentType = file.getContentType();
        if (!ALLOWED_TYPES.contains(contentType)) {
            throw new IllegalArgumentException("Ruxsat etilmagan fayl turi: " + contentType);
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("Fayl hajmi 50MB dan oshmasligi kerak");
        }

        String originalName = StringUtils.cleanPath(file.getOriginalFilename());
        String extension = getExtension(originalName);
        String fileName = UUID.randomUUID() + "." + extension;

        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(uploadPath);

        Path targetPath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        log.info("Fayl saqlandi: {}", fileName);
        return fileName;
    }

    public void deleteFile(String fileName) throws IOException {
        Path filePath = Paths.get(uploadDir)
                .toAbsolutePath()
                .normalize()
                .resolve(fileName);

        if (Files.exists(filePath)) {
            Files.delete(filePath);
            log.info("Fayl o'chirildi: {}", fileName);
        }
    }

    private String getExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex < 0) {
            throw new IllegalArgumentException("Fayl kengaytmasi yo'q");
        }
        return fileName.substring(dotIndex + 1).toLowerCase();
    }
}