package com.studiozero.projeto.repositories;

import com.studiozero.projeto.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT SUM(p.quantity * p.unitValue) FROM Product p")
    Double getTotalStockValue();
}
