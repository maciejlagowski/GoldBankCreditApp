package io.github.maciejlagowski.goldbank.controller;

import io.github.maciejlagowski.goldbank.model.database.entity.Client;
import io.github.maciejlagowski.goldbank.model.database.repository.ClientRepository;
import io.github.maciejlagowski.goldbank.model.service.BackgroundTaskRunnerService;
import io.github.maciejlagowski.goldbank.view.View;
import io.github.maciejlagowski.goldbank.view.client.AddClientView;
import io.github.maciejlagowski.goldbank.view.client.ManageClientsView;
import io.github.maciejlagowski.goldbank.view.client.ReadClientView;
import javafx.application.Platform;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ManageClientsController implements Controller {

    private final ClientRepository clientRepository = new ClientRepository();
    private final FrameController frameController = FrameController.getInstance();
    @Setter
    @Getter
    private List<Client> clients;

    public View onCreateButtonClick() {
        AddClientView addClientView = new AddClientView();
        frameController.setActualView(addClientView);
        addClientView.showStage();
        return addClientView;
    }

    public ReadClientView onReadButtonClick(Client client) {
        ReadClientView readClientView = new ReadClientView(client);
        readClientView.showStage();
        return readClientView;
    }

    public void onDeleteButtonClick(Client client) {
        new BackgroundTaskRunnerService(() -> {
            clientRepository.delete(client);
            List<Client> clients = clientRepository.getAll();
            Platform.runLater(() -> frameController.changeView(new ManageClientsView(clients)));
        }).start();
    }
}
