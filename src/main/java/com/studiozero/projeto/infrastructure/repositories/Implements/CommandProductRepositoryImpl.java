package com.studiozero.projeto.infrastructure.repositories.Implements;

import com.studiozero.projeto.domain.entities.CommandProduct;
import com.studiozero.projeto.domain.repositories.CommandProductRepository;
import com.studiozero.projeto.infrastructure.entities.CommandProductEntity;
import com.studiozero.projeto.infrastructure.mappers.CommandProductEntityMapper;
import com.studiozero.projeto.infrastructure.repositories.jpa.JpaCommandProductRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CommandProductRepositoryImpl implements CommandProductRepository {
    private final JpaCommandProductRepository jpaCommandProductRepository;

    @Override
    public List<CommandProduct> findAllByCommand_Id(Integer commandId) {
        return jpaCommandProductRepository.findAll().stream()
                .map(CommandProductEntityMapper::toDomain)
                .filter(cp -> cp.getCommand() != null && commandId.equals(cp.getCommand().getId()))
                .toList();
    }

    @Override
    public List<CommandProduct> findAll() {
        return jpaCommandProductRepository.findAll().stream()
                .map(CommandProductEntityMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByProduct_IdAndCommand_Id(Integer productId, Integer commandId) {
        return jpaCommandProductRepository.findAll().stream()
                .map(CommandProductEntityMapper::toDomain)
                .anyMatch(cp -> cp.getProduct() != null && productId.equals(cp.getProduct().getId()) &&
                        cp.getCommand() != null && commandId.equals(cp.getCommand().getId()));
    }

    @Override
    public CommandProduct findByProduct_IdAndCommand_Id(Integer productId, Integer commandId) {
        return jpaCommandProductRepository.findAll().stream()
                .map(CommandProductEntityMapper::toDomain)
                .filter(cp -> cp.getProduct() != null && productId.equals(cp.getProduct().getId()) &&
                        cp.getCommand() != null && commandId.equals(cp.getCommand().getId()))
                .findFirst().orElse(null);
    }

    @Override
    public CommandProduct findById(Integer id) {
        return jpaCommandProductRepository.findById(id)
                .map(CommandProductEntityMapper::toDomain)
                .orElse(null);
    }

    @Override
    public void save(CommandProduct commandProduct) {
        CommandProductEntity entity = CommandProductEntityMapper.toEntity(commandProduct);
        jpaCommandProductRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        jpaCommandProductRepository.deleteById(id);
    }
}
