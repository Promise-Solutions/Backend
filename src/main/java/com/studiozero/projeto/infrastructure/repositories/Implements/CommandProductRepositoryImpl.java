package com.studiozero.projeto.infrastructure.repositories.Implements;

import com.studiozero.projeto.domain.entities.CommandProduct;
import com.studiozero.projeto.domain.repositories.CommandProductRepository;
import com.studiozero.projeto.infrastructure.repositories.JpaCommandProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommandProductRepositoryImpl implements CommandProductRepository {
    private final JpaCommandProductRepository jpaCommandProductRepository;

    @Autowired
    public CommandProductRepositoryImpl(JpaCommandProductRepository jpaCommandProductRepository) {
        this.jpaCommandProductRepository = jpaCommandProductRepository;
    }

    @Override
    public List<CommandProduct> findAllByCommand_Id(Integer commandId) {
        return jpaCommandProductRepository.findAll().stream()
                .filter(cp -> cp.getCommand() != null && commandId.equals(cp.getCommand().getId())).toList();
    }

    @Override
    public boolean existsByProduct_IdAndCommand_Id(Integer productId, Integer commandId) {
        return jpaCommandProductRepository.findAll().stream()
                .anyMatch(cp -> cp.getProduct() != null && productId.equals(cp.getProduct().getId()) &&
                        cp.getCommand() != null && commandId.equals(cp.getCommand().getId()));
    }

    @Override
    public CommandProduct findByProduct_IdAndCommand_Id(Integer productId, Integer commandId) {
        return jpaCommandProductRepository.findAll().stream()
                .filter(cp -> cp.getProduct() != null && productId.equals(cp.getProduct().getId()) &&
                        cp.getCommand() != null && commandId.equals(cp.getCommand().getId()))
                .findFirst().orElse(null);
    }

    @Override
    public CommandProduct findById(Integer id) {
        return jpaCommandProductRepository.findById(id).orElse(null);
    }

    @Override
    public void save(CommandProduct commandProduct) {
        jpaCommandProductRepository.save(commandProduct);
    }

    @Override
    public void deleteById(Integer id) {
        jpaCommandProductRepository.deleteById(id);
    }
}
