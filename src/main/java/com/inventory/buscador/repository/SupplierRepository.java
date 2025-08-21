package com.inventory.buscador.repository;

import com.inventory.buscador.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, String> {
    List<Supplier> findByNameContainingIgnoreCase(String name);
    List<Supplier> findByCountry(String country);
    List<Supplier> findByCity(String city);
}