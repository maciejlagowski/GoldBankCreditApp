package io.github.maciejlagowski.prz.project.controller;

import io.github.maciejlagowski.prz.project.model.credit.Application;
import io.github.maciejlagowski.prz.project.model.credit.offer.OfferGenerator;
import io.github.maciejlagowski.prz.project.model.database.entity.Client;
import io.github.maciejlagowski.prz.project.model.enums.CreditType;
import io.github.maciejlagowski.prz.project.model.tools.BackgroundTaskRunner;
import io.github.maciejlagowski.prz.project.view.Error;
import io.github.maciejlagowski.prz.project.view.Home;
import io.github.maciejlagowski.prz.project.view.credit.Offer;
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
        new BackgroundTaskRunner(() -> {
            try {
                Application application = new Application()
                        .setCreditType(creditType)
                        .setClients(clientsChosen)
                        .calculateRisk();
                Offer offer = new Offer(new OfferGenerator(application.getCreditApplication()).generateOffers());
                Platform.runLater(() -> FrameController.getInstance().changeView(offer));
            } catch (Error error) {
                Platform.runLater(error::showStage);
                Platform.runLater(() -> FrameController.getInstance().changeView(new Home()));
            }
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
