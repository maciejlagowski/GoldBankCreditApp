package io.github.maciejlagowski.prz.project.model.database.repository;

import io.github.maciejlagowski.prz.project.model.database.entity.Income;

public class IncomeRepository implements CrudRepository<Income> {

    @Override
    public Class getEntityType() {
        return Income.class;
    }
}
