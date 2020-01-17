package io.github.maciejlagowski.prz.project.controller;

import io.github.maciejlagowski.prz.project.model.credit.Application;
import io.github.maciejlagowski.prz.project.model.credit.offer.OfferGenerator;
import io.github.maciejlagowski.prz.project.model.database.entity.Client;
import io.github.maciejlagowski.prz.project.model.database.entity.CreditApplication;
import io.github.maciejlagowski.prz.project.model.enums.CreditType;
import io.github.maciejlagowski.prz.project.model.tools.BackgroundTaskRunner;
import io.github.maciejlagowski.prz.project.view.Error;
import io.github.maciejlagowski.prz.project.view.Home;
import io.github.maciejlagowski.prz.project.view.credit.Offer;
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
public class TypeAndClientsController extends Controller {

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
        FrameController.getInstance().nextButton.setVisible(false);
        new BackgroundTaskRunner(() -> {
            try {
                Application application = new Application()
                        .setCreditType(creditType)
                        .setClients(clientsChosen)
                        .calculateRisk();
                OfferGenerator offerGenerator = new OfferGenerator(application.getCreditApplication()).generateOffers();
                Offer offer = new Offer(offerGenerator);
                Platform.runLater(() -> {
                    FrameController.getInstance().changeView(offer);
                    displayInformationOnLeftPane(offerGenerator);
                });
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

    private void displayInformationOnLeftPane(OfferGenerator offerGenerator) {
        FlowPane pane = FrameController.getInstance().leftPane;
        pane.getChildren().clear();
        CreditApplication application = offerGenerator.getApplication();
        String information = "\nType of application: " + application.getType() +
                "\n\nApplication date: " + application.getApplicationDate() +
                "\n\nApplication risk: " + application.getRisk() +
                "\n\nCredit limit: " + offerGenerator.getLimit() +
                "\n\nCapacity: " + offerGenerator.getCapacity();
        Label label = new Label(information);
        pane.getChildren().add(label);
    }
}
