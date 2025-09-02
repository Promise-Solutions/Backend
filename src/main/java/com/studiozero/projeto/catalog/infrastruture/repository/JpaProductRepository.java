package com.studiozero.projeto.catalog.infrastruture.repository;

import com.studiozero.projeto.catalog.infrastruture.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, Integer> {
}
