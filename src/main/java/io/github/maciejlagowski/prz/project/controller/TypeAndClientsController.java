package io.github.maciejlagowski.prz.project.controller;

import io.github.maciejlagowski.prz.project.model.credit.Application;
import io.github.maciejlagowski.prz.project.model.database.entity.Client;
import io.github.maciejlagowski.prz.project.model.enums.CreditType;
import io.github.maciejlagowski.prz.project.view.AddClient;
import io.github.maciejlagowski.prz.project.view.RequestedAmount;
import io.github.maciejlagowski.prz.project.view.Wait;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;

public class TypeAndClientsController implements Controller {

    private static TypeAndClientsController instance;


    @Getter
    private SimpleStringProperty forenameProperty = new SimpleStringProperty("");
    @Getter
    private SimpleStringProperty surnameProperty = new SimpleStringProperty("");
    @Getter
    private SimpleStringProperty addressProperty = new SimpleStringProperty("");
    @Getter
    private SimpleStringProperty peselProperty = new SimpleStringProperty("");
    @Setter
    private CreditType creditType;

    @Getter
    @Setter
    private ObservableList<Client> clients = FXCollections.observableArrayList();
    //TODO usuwanie klientow z listy

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
                            .setClients(clients)
            );
            Platform.runLater(() -> FrameController.getInstance().changeView(new RequestedAmount()));
        }).start();
    }

    public void onAddClientButtonClick() {
        new AddClient().showStage();
    }
}
