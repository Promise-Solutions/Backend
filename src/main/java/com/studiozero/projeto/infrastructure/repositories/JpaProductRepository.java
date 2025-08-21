package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<Product, Integer> {
}
