package com.studiozero.projeto.infrastructure.repositories.jpa;

import com.studiozero.projeto.infrastructure.entities.CommandProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCommandProductRepository extends JpaRepository<CommandProductEntity, Integer> {
}
