package dev.rohit.productservice.repositories;

import dev.rohit.productservice.models.Product;
import dev.rohit.productservice.repositories.projections.ProductWithTitleAndId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product entity);

    Product findByIdIs(Long id);

    @Query(value = "SELECT * FROM product ORDER BY id DESC", nativeQuery = true)
    List<Product> findAllOrderById();

    // HQL
    @Query("select p from Product p where p.category.title = :title and p.id = :id")
    Product getProductWithASpecificTitleAndId(@Param("title") String title, @Param("id") Long id);

    // Native SQL query
    @Query(value = "select * from product where id = :id and title = :title", nativeQuery = true)
    Product getProductWithSomeTitleAndId(@Param("title") String title,
                                         @Param("id") Long id);


    @Query("select p.id, p.title from Product p where p.title = :title and p.id = :id")
    ProductWithTitleAndId getProductWithASpecificTitleAndId2(@Param("title") String title, @Param("id") Long id);

    Page<Product> findByTitleContaining(String title, Pageable pageable);
}
