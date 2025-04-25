package com.studiozero.projeto.specifications;

import com.studiozero.projeto.entities.SubJob;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class SubJobSpecifications {

    public static Specification<SubJob> hasClient(UUID clientId) {
        return (root, query, cb) ->
                cb.equal(root.get("job").get("client").get("id"), clientId);
    }
}
