package com.studiozero.projeto.domain.repositories;

import com.studiozero.projeto.domain.entities.CommandProduct;
import java.util.List;

public interface CommandProductRepository {

    List<CommandProduct> findAllByCommand_Id(Integer fkComanda);

    List<CommandProduct> findAll();

    boolean existsByProduct_IdAndCommand_Id(Integer idProduct, Integer idCommand);

    CommandProduct findByProduct_IdAndCommand_Id(Integer idProduct, Integer idCommand);

    CommandProduct findById(Integer id);

    void save(CommandProduct commandProduct);

    void deleteById(Integer id);

}
