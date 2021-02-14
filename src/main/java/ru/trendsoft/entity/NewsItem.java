package ru.trendsoft.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "news_items")
public class NewsItem {
    @Id
    @GenericGenerator(name = "primaryIncrement", strategy = "increment")
    @GeneratedValue(generator = "primaryIncrement")
    private long id;
    private String name;
    private String content;
    @CreationTimestamp
    @Column(columnDefinition = "timestamp with time zone")
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public NewsItem() {
    }

    public NewsItem(String name, String content, LocalDateTime date, Category category) {
        this.name = name;
        this.content = content;
        this.date = date;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
