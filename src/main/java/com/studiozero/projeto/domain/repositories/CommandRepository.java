package com.studiozero.projeto.domain.repositories;

import com.studiozero.projeto.domain.entities.Command;
import com.studiozero.projeto.application.enums.Status;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface CommandRepository {
    boolean existsByClientId(UUID clientId);

    List<Command> findAllByStatus(Status status);

    List<Command> findAllByClientIdAndStatus(UUID clientId, Status status);

    LocalDateTime findMaxOpeningDate();

    LocalDateTime findMaxClosingDate();

    Command findById(Integer id);

    void save(Command command);

    void deleteById(Integer id);
}
