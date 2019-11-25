package io.github.maciejlagowski.prz.project.model.database.repository;

import io.github.maciejlagowski.prz.project.model.database.entity.Credit;

public class CreditRepository implements CrudRepository<Credit> {

    @Override
    public Class getEntityType() {
        return Credit.class;
    }
}
