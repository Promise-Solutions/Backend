package com.studiozero.projeto.domain.repositories;

import com.studiozero.projeto.domain.entities.CommandProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandProductRepository extends JpaRepository<CommandProduct, Integer> {}
