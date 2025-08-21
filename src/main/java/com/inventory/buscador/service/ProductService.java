package com.inventory.buscador.service;

import com.inventory.buscador.entity.Product;
import com.inventory.buscador.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product getProductBySku(String sku) {
        return productRepository.findBySku(sku).orElse(null);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(String id, Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            return productRepository.save(product);
        }
        return null;
    }

    public boolean deleteProduct(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Product> searchProducts(String name, String category, String supplierId, String sku) {
        return productRepository.findProductsByFilters(name, category, supplierId, sku);
    }

    public Product updateStock(String id, Integer newStock) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setCurrentStock(newStock);
            return productRepository.save(product);
        }
        return null;
    }
}