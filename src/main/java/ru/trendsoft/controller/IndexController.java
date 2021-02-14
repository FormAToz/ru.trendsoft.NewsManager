package ru.trendsoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.trendsoft.service.CategoryService;
import ru.trendsoft.service.NewsItemService;

@Controller
@RequestMapping("/")
public class IndexController {
    private final CategoryService categoryService;
    private final NewsItemService newsItemService;

    public IndexController(CategoryService categoryService, NewsItemService newsItemService) {
        this.categoryService = categoryService;
        this.newsItemService = newsItemService;
    }

    @GetMapping
    public String get(Model model, String category) {
        model.addAttribute("categoryNames", categoryService.getAllCategoryNames());
        model.addAttribute("news", newsItemService.getNewsByCategoryName(category));
        return "index";
    }
}
