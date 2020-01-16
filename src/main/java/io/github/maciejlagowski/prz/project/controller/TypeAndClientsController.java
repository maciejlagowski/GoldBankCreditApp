package io.github.maciejlagowski.prz.project.controller;

import io.github.maciejlagowski.prz.project.model.credit.Application;
import io.github.maciejlagowski.prz.project.model.database.entity.Client;
import io.github.maciejlagowski.prz.project.model.enums.CreditType;
import io.github.maciejlagowski.prz.project.view.Wait;
import io.github.maciejlagowski.prz.project.view.credit.RequestedPeriod;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import lombok.Data;

@Data
public class TypeAndClientsController {

    private static TypeAndClientsController instance;

    private SimpleStringProperty forenameProperty = new SimpleStringProperty("");
    private SimpleStringProperty surnameProperty = new SimpleStringProperty("");
    private SimpleStringProperty addressProperty = new SimpleStringProperty("");
    private SimpleStringProperty peselProperty = new SimpleStringProperty("");
    private CreditType creditType;
    private ObservableList<Client> clientsFromDb;
    private ObservableList<Client> clientsChosen = FXCollections.observableArrayList();
    private ListView<Client> clientsFromDbListView;
    private ListView<Client> clientsChosenListView;

    private TypeAndClientsController() {
    }

    public static synchronized TypeAndClientsController getInstance() {
        if (instance == null) {
            instance = new TypeAndClientsController();
        }
        return instance;
    }

    public void onNextButtonClick() {
        Platform.runLater(() -> FrameController.getInstance().changeView(new Wait()));
        new Thread(() -> {
            Application.setApplicationInstance(
                    new Application()
                            .setCreditType(creditType)
                            .setClients(clientsChosen)
            );
            Platform.runLater(() -> FrameController.getInstance().changeView(new RequestedPeriod()));
        }).start();
    }

    public void onAddClientButtonClick() {
        Client client = clientsFromDbListView.getSelectionModel().getSelectedItem();
        if (!clientsChosen.contains(client)) {
            clientsChosen.add(client);
        }
    }

    public void onRemoveClientButtonClick() {
        Client client = clientsChosenListView.getSelectionModel().getSelectedItem();
        clientsChosen.remove(client);
    }
}
