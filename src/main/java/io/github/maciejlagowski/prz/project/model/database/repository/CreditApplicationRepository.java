package io.github.maciejlagowski.prz.project.model.database.repository;

import io.github.maciejlagowski.prz.project.model.database.entity.CreditApplication;

public class CreditApplicationRepository implements CrudRepository<CreditApplication> {

    @Override
    public Class getEntityType() {
        return CreditApplication.class;
    }
}
