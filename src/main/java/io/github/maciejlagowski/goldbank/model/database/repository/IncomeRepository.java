package io.github.maciejlagowski.goldbank.model.database.repository;

import io.github.maciejlagowski.goldbank.model.database.entity.Income;

public class IncomeRepository implements CrudRepository<Income> {

    @Override
    public Class getEntityType() {
        return Income.class;
    }
}
