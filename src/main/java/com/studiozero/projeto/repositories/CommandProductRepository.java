package com.studiozero.projeto.repositories;

import com.studiozero.projeto.entities.CommandProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommandProductRepository extends JpaRepository<CommandProduct, Integer> {
    List<CommandProduct> findAllByCommand_Id(Integer fkComanda);

    boolean existsByProduct_IdAndCommand_Id(Integer idProduct, Integer idCommand);

    Optional<CommandProduct> findByProduct_IdAndCommand_Id(Integer idProduct, Integer idCommand);

}
