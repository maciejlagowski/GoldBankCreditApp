package io.github.maciejlagowski.goldbank.model.database.repository;

import io.github.maciejlagowski.goldbank.model.database.entity.Client;

public class ClientRepository implements CrudRepository<Client> {

    @Override
    public Class getEntityType() {
        return Client.class;
    }
}
