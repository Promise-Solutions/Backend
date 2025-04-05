package com.studiozero.projeto.domain.repositories;

import com.studiozero.projeto.domain.entities.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandRepository extends JpaRepository<Command, Integer> {}
