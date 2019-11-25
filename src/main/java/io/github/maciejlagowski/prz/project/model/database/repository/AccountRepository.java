package io.github.maciejlagowski.prz.project.model.database.repository;

import io.github.maciejlagowski.prz.project.model.database.entity.Account;

public class AccountRepository implements CrudRepository<Account> {

    @Override
    public Class getEntityType() {
        return Account.class;
    }
}
