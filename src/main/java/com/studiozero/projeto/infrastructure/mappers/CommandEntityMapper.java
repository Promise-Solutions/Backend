package com.studiozero.projeto.infrastructure.mappers;

import com.studiozero.projeto.domain.entities.Command;
import com.studiozero.projeto.infrastructure.entities.CommandEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommandEntityMapper {

    public static CommandEntity toEntity(Command command) {
        if (command == null) return null;
        return new CommandEntity(
                command.getId(),
                command.getCommandNumber(),
                command.getOpeningDateTime(),
                command.getClosingDateTime(),
                command.getDiscount(),
                command.getTotalValue(),
                ClientEntityMapper.toEntity(command.getClient()),
                EmployeeEntityMapper.toEntity(command.getEmployee()),
                command.getIsInternal(),
                command.getStatus()
        );
    }

    public static Command toDomain(CommandEntity entity) {
        if (entity == null) return null;
        return new Command(
                entity.getId(),
                entity.getCommandNumber(),
                entity.getOpeningDateTime(),
                entity.getClosingDateTime(),
                entity.getDiscount(),
                entity.getTotalValue(),
                ClientEntityMapper.toDomain(entity.getClient()),
                EmployeeEntityMapper.toDomain(entity.getEmployee()),
                entity.getIsInternal(),
                entity.getStatus()
        );
    }

    public static List<Command> toDomainList(List<CommandEntity> entities) {
        if (entities == null) return null;
        return entities.stream().map(CommandEntityMapper::toDomain).toList();
    }

    public static List<CommandEntity> toEntityList(List<Command> commands) {
        if (commands == null) return null;
        return commands.stream().map(CommandEntityMapper::toEntity).toList();
    }

}
