package com.studiozero.projeto.specifications;

import com.studiozero.projeto.entities.Job;
import com.studiozero.projeto.enums.Status;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.UUID;

public class JobSpecifications {

    public static Specification<Job> hasClient(UUID clientId) {
        return (root, query, cb) ->
                cb.equal(root.get("client").get("id"), clientId);
    }

    public static Specification<Job> hasStatus(Status status) {
        return (root, query, cb) ->
                cb.equal(root.get("status"), status);
    }

    public static Specification<Job> hasSubJobsInMonth(YearMonth month) {
        LocalDate start = month.atDay(1);
        LocalDate end = month.atEndOfMonth();
        return (root, query, cb) -> {
            query.distinct(true);
            var join = root.join("subJobs");
            return cb.between(join.get("date"), start, end);
        };
    }

}
