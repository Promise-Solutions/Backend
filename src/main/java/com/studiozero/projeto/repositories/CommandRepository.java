package com.studiozero.projeto.repositories;

import com.studiozero.projeto.entities.Command;
import com.studiozero.projeto.enums.Status;
import com.studiozero.projeto.projections.MonthlyStatsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommandRepository extends JpaRepository<Command, Integer>, JpaSpecificationExecutor<Command> {

    boolean existsByClient_Id(UUID clientId);

    List<Command> findAllByStatus(Status status);

    @Query(value = """
                SELECT
                    YEAR(c.data_hora_fechamento) AS ano,
                    MONTH(c.data_hora_fechamento) AS mes,
                    SUM(c.valor_total) AS total
                FROM comanda c
                WHERE c.status = 'CLOSED' AND c.data_hora_fechamento IS NOT NULL
                GROUP BY ano, mes
                ORDER BY ano, mes
            """, nativeQuery = true)
    List<MonthlyStatsProjection> findMonthlyClosedCommands();

    @Query("SELECT c FROM Command c WHERE c.client.id = :clientId AND c.status = :status")
    List<Command> findAllByClient_IdAndStatus(UUID clientId, Status status);
}
