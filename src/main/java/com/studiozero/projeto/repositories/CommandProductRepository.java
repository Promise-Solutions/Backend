package com.studiozero.projeto.repositories;

import com.studiozero.projeto.entities.CommandProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandProductRepository extends JpaRepository<CommandProduct, Integer> {}
