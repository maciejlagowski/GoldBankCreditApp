package io.github.maciejlagowski.goldbank.controller;

import io.github.maciejlagowski.goldbank.model.database.entity.Client;
import io.github.maciejlagowski.goldbank.model.database.entity.CreditApplication;
import io.github.maciejlagowski.goldbank.model.enumeration.CreditType;
import io.github.maciejlagowski.goldbank.model.enumeration.Risk;
import io.github.maciejlagowski.goldbank.model.service.*;
import io.github.maciejlagowski.goldbank.view.credit.OfferView;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import lombok.Data;

@Data
public class TypeAndClientsController implements Controller {

    private final FrameController frameController = FrameController.getInstance();
    private final RiskService riskService = new RiskService();
    private final CreditService creditService = new CreditService();
    private final CapacityService capacityService = new CapacityService();
    private final LimitService limitService = new LimitService();
    private SimpleStringProperty forenameProperty = new SimpleStringProperty("");
    private SimpleStringProperty surnameProperty = new SimpleStringProperty("");
    private SimpleStringProperty addressProperty = new SimpleStringProperty("");
    private SimpleStringProperty peselProperty = new SimpleStringProperty("");
    private CreditType creditType;
    private ObservableList<Client> clientsFromDb;
    private ObservableList<Client> clientsChosen = FXCollections.observableArrayList();
    private ListView<Client> clientsFromDbListView;
    private ListView<Client> clientsChosenListView;

    @Override
    public void onNextButtonClick(ActionEvent event) {
        frameController.nextButton.setVisible(false);
        CreditApplication application = creditService.createCreditApplication(creditType, clientsChosen);
        if (application.getRisk().equals(Risk.DEFAULT)) {
            // TODO if risk is default then error
        } // TODO empty or null list cannot go further
        new BackgroundTaskRunnerService(() -> {
            OfferView offerView = new OfferView(application);
            Platform.runLater(() -> {
                FrameController.getInstance().changeView(offerView);
                displayInformationOnLeftPane(application);
            });
        }).start();
    }

    public Client onAddClientButtonClick() {
        Client client = clientsFromDbListView.getSelectionModel().getSelectedItem();
        if (!clientsChosen.contains(client)) {
            clientsChosen.add(client);
        }
        return client;
    }

    public Client onRemoveClientButtonClick() {
        Client client = clientsChosenListView.getSelectionModel().getSelectedItem();
        clientsChosen.remove(client);
        return client;
    }

    private String displayInformationOnLeftPane(CreditApplication application) {
        FlowPane pane = frameController.leftPane;
        pane.getChildren().clear();
        String information =
                "\nType of application: " + application.getType() +
                        "\n\nApplication date: " + application.getApplicationDate() +
                        "\n\nApplication risk: " + application.getRisk() +
                        "\n\nCredit limit: " + limitService.calculateLimit(application) +
                        "\n\nCapacity: " + capacityService.calculateCapacity(application);
        Label label = new Label(information);
        pane.getChildren().add(label);
        return information;
    }
}
