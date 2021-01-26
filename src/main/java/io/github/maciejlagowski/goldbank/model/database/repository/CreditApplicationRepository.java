package io.github.maciejlagowski.goldbank.model.database.repository;

import io.github.maciejlagowski.goldbank.model.database.entity.CreditApplication;

public class CreditApplicationRepository implements CrudRepository<CreditApplication> {

    @Override
    public Class getEntityType() {
        return CreditApplication.class;
    }
}
