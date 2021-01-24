package io.github.maciejlagowski.prz.project.controller;

import io.github.maciejlagowski.prz.project.model.database.entity.Client;
import io.github.maciejlagowski.prz.project.model.database.repository.ClientRepository;
import io.github.maciejlagowski.prz.project.model.service.BackgroundTaskRunnerService;
import io.github.maciejlagowski.prz.project.view.client.ManageClientsView;
import io.github.maciejlagowski.prz.project.view.credit.TypeAndClientsView;
import javafx.application.Platform;

import java.util.List;

public class HomeController implements Controller {

    private final ClientRepository clientRepository = new ClientRepository();
    private final FrameController frameController = FrameController.getInstance();

    public void onApplyForACreditButtonClick() {
        FrameController.getInstance().nextButton.setVisible(true);
        new BackgroundTaskRunnerService(() -> {
            List<Client> clients = clientRepository.getAll();
            Platform.runLater(() -> frameController.changeView(new TypeAndClientsView(clients)));
        }).start();
    }

    public void onManageCustomersButtonClick() {
        new BackgroundTaskRunnerService(() -> {
            List<Client> clients = clientRepository.getAll();
            Platform.runLater(() -> frameController.changeView(new ManageClientsView(clients)));
        }).start();
    }
}
