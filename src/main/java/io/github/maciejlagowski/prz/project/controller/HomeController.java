package io.github.maciejlagowski.prz.project.controller;

import io.github.maciejlagowski.prz.project.model.database.entity.Client;
import io.github.maciejlagowski.prz.project.model.database.repository.ClientRepository;
import io.github.maciejlagowski.prz.project.model.tools.BackgroundTaskRunner;
import io.github.maciejlagowski.prz.project.view.client.ManageClients;
import io.github.maciejlagowski.prz.project.view.credit.TypeAndClients;
import javafx.application.Platform;

import java.util.List;

public class HomeController extends Controller {

    public void onApplyForACreditButtonClick() {
        FrameController.getInstance().nextButton.setVisible(true);
        new BackgroundTaskRunner(() -> {
            List<Client> clients = new ClientRepository().readAllRecords();
            Platform.runLater(() -> FrameController.getInstance().changeView(new TypeAndClients(clients)));
        }).start();
    }

    public void onManageCustomersButtonClick() {
        new BackgroundTaskRunner(() -> {
            List<Client> clients = new ClientRepository().readAllRecords();
            Platform.runLater(() -> FrameController.getInstance().changeView(new ManageClients(clients)));
        }).start();
    }
}
