package ru.trendsoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.trendsoft.api.request.AddNewsItemRequest;
import ru.trendsoft.entity.Category;
import ru.trendsoft.entity.NewsItem;
import ru.trendsoft.service.CategoryService;
import ru.trendsoft.service.NewsItemService;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/news")
public class NewsItemController {
    private final CategoryService categoryService;
    private final NewsItemService newsItemService;

    public NewsItemController(CategoryService categoryService, NewsItemService newsItemService) {
        this.categoryService = categoryService;
        this.newsItemService = newsItemService;
    }

    @PostMapping("/add")
    public String add(AddNewsItemRequest request) {
        newsItemService.addItem(request);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String get(Model model) {
        model.addAttribute("categoryNames", categoryService.getAllCategoryNames());
        return "news/news_add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        if (newsItemService.existsById(id)) {
            NewsItem newsItem = newsItemService.getById(id);
            model.addAttribute("categoryNames", categoryService.getAllCategoryNames());
            model.addAttribute("news", newsItem);
            return "news/news_edit";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable long id, AddNewsItemRequest request) {
        NewsItem newsItem = newsItemService.getById(id);
        Category category = categoryService.getCategoryByName(request.getCategory());
        newsItem.setName(request.getName());
        newsItem.setContent(request.getContent());
        newsItem.setDate(LocalDateTime.now());
        newsItem.setCategory(category);
        newsItemService.save(newsItem);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        NewsItem newsItem = newsItemService.getById(id);
        newsItemService.delete(newsItem);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String search(String query, Model model) {
        model.addAttribute("categoryNames", categoryService.getAllCategoryNames());
        model.addAttribute("news", newsItemService.getNewsByQuery(query.toLowerCase()));
        return "index";
    }
}
