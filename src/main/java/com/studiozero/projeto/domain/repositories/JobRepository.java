package com.studiozero.projeto.domain.repositories;

import com.studiozero.projeto.domain.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {}
