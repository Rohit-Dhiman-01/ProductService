package dev.rohit.productservice.repositories;

import dev.rohit.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query()
    Optional<Category> findByTitle(String title);

    Category save(Category category);

    Optional<Category> findById(Long id);
}
