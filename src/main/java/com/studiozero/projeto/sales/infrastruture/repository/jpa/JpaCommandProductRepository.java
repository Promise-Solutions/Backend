package com.studiozero.projeto.sales.infrastruture.repository.jpa;

import com.studiozero.projeto.sales.infrastruture.entity.CommandProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCommandProductRepository extends JpaRepository<CommandProductEntity, Integer> {
}
