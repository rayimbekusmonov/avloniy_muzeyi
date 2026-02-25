import { api, Page, NewsItem, GalleryItem, ResourceItem, AuthResponse } from './api';

// Auth
export const authService = {
    login: (username: string, password: string) =>
        api.post<AuthResponse>('/api/auth/login', { username, password }),
};

// News
export const newsService = {
    getAll: (page = 0, size = 10, category?: string) => {
        const params = new URLSearchParams({ page: String(page), size: String(size) });
        if (category) params.append('category', category);
        return api.get<Page<NewsItem>>(`/api/news?${params}`);
    },
    getBySlug: (slug: string) =>
        api.get<NewsItem>(`/api/news/${slug}`),
    create: (data: {
        title: string;
        content: string;
        excerpt: string;
        imageUrl: string;
        category: string;
        published: boolean
    }) =>
        api.post<NewsItem>('/api/news', data),
    update: (id: number, data: {
        title: string;
        content: string;
        excerpt: string;
        imageUrl: string;
        category: string;
        published: boolean
    }) =>
        api.put<NewsItem>(`/api/news/${id}`, data),
    delete: (id: number) =>
        api.delete<void>(`/api/news/${id}`),
};

// Gallery
export const galleryService = {
    getAll: (page = 0, size = 12, mediaType?: string) => {
        const params = new URLSearchParams({ page: String(page), size: String(size) });
        if (mediaType) params.append('mediaType', mediaType);
        return api.get<Page<GalleryItem>>(`/api/gallery?${params}`);
    },
    getById: (id: number) =>
        api.get<GalleryItem>(`/api/gallery/${id}`),
    create: (data: Partial<GalleryItem>) =>
        api.post<GalleryItem>('/api/gallery', data),
    update: (id: number, data: Partial<GalleryItem>) =>
        api.put<GalleryItem>(`/api/gallery/${id}`, data),
    delete: (id: number) =>
        api.delete<void>(`/api/gallery/${id}`),
};

// Resources
export const resourceService = {
    getAll: (page = 0, size = 10, type?: string, search?: string) => {
        const params = new URLSearchParams({ page: String(page), size: String(size) });
        if (type) params.append('type', type);
        if (search) params.append('search', search);
        return api.get<Page<ResourceItem>>(`/api/resources?${params}`);
    },
    getById: (id: number) =>
        api.get<ResourceItem>(`/api/resources/${id}`),
    create: (data: Partial<ResourceItem>) =>
        api.post<ResourceItem>('/api/resources', data),
    update: (id: number, data: Partial<ResourceItem>) =>
        api.put<ResourceItem>(`/api/resources/${id}`, data),
    delete: (id: number) =>
        api.delete<void>(`/api/resources/${id}`),
};