package ru.trendsoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.trendsoft.api.request.AddNewsItemRequest;
import ru.trendsoft.entity.Category;
import ru.trendsoft.entity.NewsItem;
import ru.trendsoft.repository.NewsItemRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NewsItemService {
    @Autowired
    private NewsItemRepository newsItemRepository;
    @Autowired
    private CategoryService categoryService;

    public NewsItemService() {
    }

    public void addItem(AddNewsItemRequest request) {
        Category category = categoryService.getCategoryByName(request.getCategory());
        NewsItem newsItem = new NewsItem(request.getName(), request.getContent(), LocalDateTime.now(), category);
        newsItemRepository.save(newsItem);
    }

    public NewsItem getById(long id) {
        return newsItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Новость с id = " + id + "не существует!"));
    }

    public List<NewsItem> getNewsByCategoryName(String categoryName) {
        if (categoryService.existsByName(categoryName)) {
            Category category = categoryService.getCategoryByName(categoryName);
            return newsItemRepository.findByCategory(category);
        } else {
            return (List<NewsItem>) newsItemRepository.findAll();
        }
    }

    public List<NewsItem> getNewsByQuery(String query) {
        return newsItemRepository.findAllByQuery(query);
    }

    public boolean existsById(long id) {
        return newsItemRepository.existsById(id);
    }

    public void save(NewsItem newsItem) {
        newsItemRepository.save(newsItem);
    }

    public void delete(NewsItem newsItem) {
        newsItemRepository.delete(newsItem);
    }
}
