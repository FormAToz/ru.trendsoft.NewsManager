package ru.trendsoft.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GenericGenerator(name = "primaryIncrement", strategy = "increment")
    @GeneratedValue(generator = "primaryIncrement")
    private long id;
    private String name;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<NewsItem> newsItemList;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<NewsItem> getNewsItemList() {
        return newsItemList;
    }
}
