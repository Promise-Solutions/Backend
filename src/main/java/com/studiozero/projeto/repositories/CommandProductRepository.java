package com.studiozero.projeto.repositories;

import com.studiozero.projeto.entities.CommandProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandProductRepository extends JpaRepository<CommandProduct, Integer> {
    List<CommandProduct> findAllByCommand_Id(Integer fkComanda);
}
