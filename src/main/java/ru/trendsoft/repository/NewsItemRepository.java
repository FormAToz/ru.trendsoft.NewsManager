package ru.trendsoft.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.trendsoft.entity.Category;
import ru.trendsoft.entity.NewsItem;

import java.util.List;

@Repository
public interface NewsItemRepository extends CrudRepository<NewsItem, Long> {
    List<NewsItem> findByCategory(Category category);

    boolean existsById(long id);

    @Query("from NewsItem i where lower(i.name) like %?1% or lower(i.content) like %?1%")
    List<NewsItem> findAllByQuery(String query);
}
