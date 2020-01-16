package io.github.maciejlagowski.prz.project.model.tools.filter;

import io.github.maciejlagowski.prz.project.model.database.entity.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class Filter {
    public static ObservableList<Client> filterClients(String filter, FilterEnum filterEnum, List<Client> clients) {
        ObservableList<Client> fxClients = FXCollections.observableArrayList();
        clients.forEach(client -> {
            String clientString = "";
            switch (filterEnum) {
                case PESEL:
                    clientString = client.getPesel().toString();
                    break;
                case ADDRESS:
                    clientString = client.getAddress();
                    break;
                case SURNAME:
                    clientString = client.getSurname();
                    break;
                case FORENAME:
                    clientString = client.getForename();
                    break;
            }
            clientString = clientString.toLowerCase();
            if (clientString.contains(filter.toLowerCase())) {
                fxClients.add(client);
            }
        });
        return fxClients;
    }
}
