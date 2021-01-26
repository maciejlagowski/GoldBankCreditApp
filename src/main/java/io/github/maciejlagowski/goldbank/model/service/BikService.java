package io.github.maciejlagowski.goldbank.model.service;

import io.github.maciejlagowski.goldbank.model.bik.BikApi;
import io.github.maciejlagowski.goldbank.model.bik.BikReport;
import io.github.maciejlagowski.goldbank.model.database.entity.Client;

public class BikService {
    public BikReport getBikReport(Client client) {
        return new BikApi("123456789").getBikReport(client.getPesel());
    }
}
