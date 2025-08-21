package com.inventory.buscador.repository;

import com.inventory.buscador.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findBySku(String sku);
    List<Product> findByCategory(String category);
    List<Product> findBySupplierId(String supplierId);
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByCurrentStockLessThanEqual(Integer stock);
    List<Product> findByUnitPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    @Query("SELECT p FROM Product p WHERE " +
            "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) OR " +
            "(:category IS NULL OR LOWER(p.category) LIKE LOWER(CONCAT('%', :category, '%'))) OR " +
            "(:supplierId IS NULL OR LOWER(p.supplierId) LIKE LOWER(CONCAT('%', :supplierId, '%'))) OR " +
            "(:sku IS NULL OR LOWER(p.sku) LIKE LOWER(CONCAT('%', :sku, '%')))")
    List<Product> findProductsByFilters(@Param("name") String name,
                                        @Param("category") String category,
                                        @Param("supplierId") String supplierId,
                                        @Param("sku") String sku);
}