package uz.rayimbek.avloniy_muzeyi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.rayimbek.avloniy_muzeyi.dto.request.NewsRequest;
import uz.rayimbek.avloniy_muzeyi.dto.response.NewsResponse;
import uz.rayimbek.avloniy_muzeyi.entity.News;
import uz.rayimbek.avloniy_muzeyi.entity.User;
import uz.rayimbek.avloniy_muzeyi.repository.NewsRepository;
import uz.rayimbek.avloniy_muzeyi.repository.UserRepository;

import java.text.Normalizer;
import java.util.Objects;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    public Page<NewsResponse> getPublishedNews(int page, int size, String category) {
        Pageable pageable = PageRequest.of(page, size);

        if (category != null && !category.isBlank()) {
            News.Category cat = News.Category.valueOf(category.toUpperCase());
            return newsRepository.findAllPublishedWithAuthor(pageable).map(this::toResponse);
        }

        return newsRepository
                .findAllByPublishedTrueOrderByCreatedAtDesc(pageable)
                .map(this::toResponse);
    }

    public NewsResponse getNewsBySlug(String slug) {
        News news = newsRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Yangilik topilmadi: " + slug));
        return toResponse(news);
    }

    public NewsResponse create(NewsRequest request) {
        String username = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();
        User author = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Foydalanuvchi topilmadi"));

        String slug = generateUniqueSlug(request.getTitle());

        News news = News.builder()
                .title(request.getTitle())
                .slug(slug)
                .content(request.getContent())
                .excerpt(request.getExcerpt())
                .imageUrl(request.getImageUrl())
                .category(request.getCategory())
                .published(request.getPublished())
                .author(author)
                .build();

        return toResponse(newsRepository.save(news));
    }

    public NewsResponse update(Long id, NewsRequest request) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Yangilik topilmadi: " + id));

        news.setTitle(request.getTitle());
        news.setContent(request.getContent());
        news.setExcerpt(request.getExcerpt());
        news.setImageUrl(request.getImageUrl());
        news.setCategory(request.getCategory());
        news.setPublished(request.getPublished());

        return toResponse(newsRepository.save(news));
    }

    public void delete(Long id) {
        if (!newsRepository.existsById(id)) {
            throw new RuntimeException("Yangilik topilmadi: " + id);
        }
        newsRepository.deleteById(id);
    }

    // --- Helper methods ---

    private String generateUniqueSlug(String title) {
        String slug = toSlug(title);
        if (newsRepository.existsBySlug(slug)) {
            slug = slug + "-" + System.currentTimeMillis();
        }
        return slug;
    }

    public Page<NewsResponse> getAllForAdmin(int page, int size) {
        return newsRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(page, size))
                .map(this::toResponse);
    }

    private String toSlug(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized)
                .replaceAll("")
                .toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("-+", "-")
                .trim();
    }

    private NewsResponse toResponse(News news) {
        return NewsResponse.builder()
                .id(news.getId())
                .title(news.getTitle())
                .slug(news.getSlug())
                .content(news.getContent())
                .excerpt(news.getExcerpt())
                .imageUrl(news.getImageUrl())
                .category(news.getCategory())
                .published(news.getPublished())
                .authorUsername(news.getAuthor() != null ? news.getAuthor().getUsername() : null)
                .createdAt(news.getCreatedAt())
                .updatedAt(news.getUpdatedAt())
                .build();
    }
}