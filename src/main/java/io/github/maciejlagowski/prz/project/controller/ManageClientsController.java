package io.github.maciejlagowski.prz.project.controller;

import io.github.maciejlagowski.prz.project.model.database.entity.Client;
import io.github.maciejlagowski.prz.project.view.client.AddClient;
import io.github.maciejlagowski.prz.project.view.client.ReadClient;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ManageClientsController {

    private static ManageClientsController instance;

    @Setter
    @Getter
    private List<Client> clients;

    private ManageClientsController() {
    }

    public static synchronized ManageClientsController getInstance() {
        if (instance == null) {
            instance = new ManageClientsController();
        }
        return instance;
    }

    public void onCreateButtonClick() {
        new AddClient().showStage();
    }

    public void onReadButtonClick(Client client) {
        new ReadClient(client).showStage();
    }

    public void onUpdateButtonClick(Client client) {

    }

    public void onDeleteButtonClick(Client client) {

    }

    public void onSaveButtonClick() {
//        new ClientRepository().
    }
}
