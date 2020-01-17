package io.github.maciejlagowski.prz.project.controller;

import io.github.maciejlagowski.prz.project.model.database.entity.Client;
import io.github.maciejlagowski.prz.project.model.database.repository.ClientRepository;
import io.github.maciejlagowski.prz.project.view.Wait;
import io.github.maciejlagowski.prz.project.view.client.ManageClients;
import io.github.maciejlagowski.prz.project.view.credit.TypeAndClients;
import javafx.application.Platform;

import java.util.List;

public class HomeController {

    private static HomeController instance;

    private HomeController() {
    }

    public static synchronized HomeController getInstance() {
        if (instance == null) {
            instance = new HomeController();
        }
        return instance;
    }

    public void onApplyForACreditButtonClick() {
        Platform.runLater(() -> FrameController.getInstance().changeView(new Wait()));
        new Thread(() -> {
            List<Client> clients = new ClientRepository().readAllRecords();
            Platform.runLater(() -> FrameController.getInstance().changeView(new TypeAndClients(clients)));
        }).start();
    }

    public void onManageCustomersButtonClick() {
        Platform.runLater(() -> FrameController.getInstance().changeView(new Wait()));
        new Thread(() -> {
            List<Client> clients = new ClientRepository().readAllRecords();
            Platform.runLater(() -> FrameController.getInstance().changeView(new ManageClients(clients)));
        }).start();
    }
}
