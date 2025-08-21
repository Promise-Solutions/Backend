package com.studiozero.projeto.infrastructure.repositories;

import com.studiozero.projeto.domain.entities.CommandProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCommandProductRepository extends JpaRepository<CommandProduct, Integer> {
}
