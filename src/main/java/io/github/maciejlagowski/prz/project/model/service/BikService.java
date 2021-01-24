package io.github.maciejlagowski.prz.project.model.service;

import io.github.maciejlagowski.prz.project.model.bik.BikApi;
import io.github.maciejlagowski.prz.project.model.bik.BikReport;
import io.github.maciejlagowski.prz.project.model.database.entity.Client;

public class BikService {
    public BikReport getBikReport(Client client) {
        return new BikApi("123456789").getBikReport(client.getPesel());
    }
}
