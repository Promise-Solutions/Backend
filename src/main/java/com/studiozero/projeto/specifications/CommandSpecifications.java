package com.studiozero.projeto.specifications;

import com.studiozero.projeto.entities.Command;
import com.studiozero.projeto.enums.Status;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class CommandSpecifications {

    public static Specification<Command> hasClient(UUID clientId) {
        return (root, query, cb) ->
                cb.equal(root.get("client").get("id"), clientId);
    }

    public static Specification<Command> hasStatus(Status status) {
        return (root, query, cb) ->
                cb.equal(root.get("status"), status);
    }
}
