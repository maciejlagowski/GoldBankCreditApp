package io.github.maciejlagowski.prz.project.controller;

import io.github.maciejlagowski.prz.project.model.database.entity.Client;
import io.github.maciejlagowski.prz.project.model.database.repository.ClientRepository;
import io.github.maciejlagowski.prz.project.model.tools.BackgroundTaskRunner;
import io.github.maciejlagowski.prz.project.view.client.AddClient;
import io.github.maciejlagowski.prz.project.view.client.ManageClients;
import io.github.maciejlagowski.prz.project.view.client.ReadClient;
import javafx.application.Platform;
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
        new BackgroundTaskRunner(() -> {
            ClientRepository repo = new ClientRepository();
            repo.deleteRecord(client);
            Platform.runLater(() -> FrameController.getInstance().changeView(new ManageClients(repo.readAllRecords())));
        }).start();
    }
}
