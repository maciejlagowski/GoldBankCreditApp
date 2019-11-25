package io.github.maciejlagowski.prz.project.model.database.repository;

import io.github.maciejlagowski.prz.project.model.database.entity.Client;

public class ClientRepository implements CrudRepository<Client> {

    @Override
    public Class getEntityType() {
        return Client.class;
    }
}
