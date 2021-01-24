package io.github.maciejlagowski.prz.project.model.service;

import io.github.maciejlagowski.prz.project.model.database.entity.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ClientFilterService {

    public ObservableList<Client> filterByAddress(String address, List<Client> clients) {
        ObservableList<Client> fxClients = FXCollections.observableArrayList();
        clients.forEach(client -> {
            String clientAddress = client.getAddress().toLowerCase();
            if (clientAddress.contains(address.toLowerCase())) {
                fxClients.add(client);
            }
        });
        return fxClients;
    }

    public ObservableList<Client> filterByForename(String forename, List<Client> clients) {
        ObservableList<Client> fxClients = FXCollections.observableArrayList();
        clients.forEach(client -> {
            String clientForename = client.getForename().toLowerCase();
            if (clientForename.contains(forename.toLowerCase())) {
                fxClients.add(client);
            }
        });
        return fxClients;
    }

    public ObservableList<Client> filterBySurname(String surname, List<Client> clients) {
        ObservableList<Client> fxClients = FXCollections.observableArrayList();
        clients.forEach(client -> {
            String clientSurname = client.getSurname().toLowerCase();
            if (clientSurname.contains(surname.toLowerCase())) {
                fxClients.add(client);
            }
        });
        return fxClients;
    }

    public ObservableList<Client> filterByPesel(String pesel, List<Client> clients) {
        ObservableList<Client> fxClients = FXCollections.observableArrayList();
        clients.forEach(client -> {
            String clientPesel = client.getPesel().toString();
            if (clientPesel.contains(pesel)) {
                fxClients.add(client);
            }
        });
        return fxClients;
    }
}
