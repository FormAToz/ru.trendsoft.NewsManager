package ru.trendsoft.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.trendsoft.entity.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    boolean existsByNameIgnoreCase(String name);

    @Query("select c.name from Category c")
    List<String> findAllCategoryNames();

    Optional<Category> findByNameIgnoreCase(String name);
}
