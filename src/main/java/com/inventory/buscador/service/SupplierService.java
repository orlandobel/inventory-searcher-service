package com.inventory.buscador.service;

import com.inventory.buscador.entity.Supplier;
import com.inventory.buscador.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(String id) {
        return supplierRepository.findById(id).orElse(null);
    }

    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Supplier updateSupplier(String id, Supplier supplier) {
        if (supplierRepository.existsById(id)) {
            supplier.setId(id);
            return supplierRepository.save(supplier);
        }
        return null;
    }

    public boolean deleteSupplier(String id) {
        if (supplierRepository.existsById(id)) {
            supplierRepository.deleteById(id);
            return true;
        }
        return false;
    }
}