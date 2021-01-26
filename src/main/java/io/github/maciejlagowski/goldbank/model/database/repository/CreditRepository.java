package io.github.maciejlagowski.goldbank.model.database.repository;

import io.github.maciejlagowski.goldbank.model.database.entity.Credit;

public class CreditRepository implements CrudRepository<Credit> {

    @Override
    public Class getEntityType() {
        return Credit.class;
    }
}
