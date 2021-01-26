package io.github.maciejlagowski.goldbank.view.client;

import io.github.maciejlagowski.goldbank.controller.ManageClientsController;
import io.github.maciejlagowski.goldbank.model.database.entity.Client;
import io.github.maciejlagowski.goldbank.model.service.ClientFilterService;
import io.github.maciejlagowski.goldbank.view.Error;
import io.github.maciejlagowski.goldbank.view.View;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lombok.Getter;

import java.util.List;

public class ManageClientsView implements View {

    @Getter
    private final ManageClientsController controller = new ManageClientsController();
    private ListView<Client> listView;

    public ManageClientsView(List<Client> clients) {
        controller.setClients(clients);
    }

    public List<Node> createContent() {
        return List.of(
                createClientsList(),
                createButtons(),
                createSearchFields()
        );
    }

    public Node createClientsList() {
        ListView<Client> listView = new ListView<>();
        listView.setItems(FXCollections.observableArrayList(controller.getClients()));
        this.listView = listView;
        return listView;
    }

    public Node createButtons() {
        Button createButton = new Button("Create");
        Button readButton = new Button("Read");
        Button deleteButton = new Button("Delete");
        createButton.setOnAction((event) -> {
            controller.onCreateButtonClick();
            listView.setItems(FXCollections.observableArrayList(controller.getClients()));
        });
        readButton.setOnAction((event) -> {
            try {
                controller.onReadButtonClick(getSelectedClient());
            } catch (Error error) {
                error.showStage();
            }
        });
        deleteButton.setOnAction((event) -> {
            try {
                controller.onDeleteButtonClick(getSelectedClient());
            } catch (Error error) {
                error.showStage();
            }
        });
        FlowPane pane = new FlowPane();
        pane.setHgap(10.0);
        pane.getChildren().addAll(createButton, readButton, deleteButton);
        return pane;
    }

    public Node createSearchFields() {
        Label forenameLabel = new Label("Forename");
        Label surnameLabel = new Label("Surname");
        Label addressLabel = new Label("Address");
        Label peselLabel = new Label("PESEL");
        TextField forenameField = new TextField();
        TextField surnameField = new TextField();
        TextField peselField = new TextField();
        TextField addressField = new TextField();
        List<Client> clients = controller.getClients();
        ClientFilterService filter = new ClientFilterService();
        forenameField.setOnKeyTyped((event) -> listView.setItems(filter.filterByForename(forenameField.getText(), clients)));
        surnameField.setOnKeyTyped((event) -> listView.setItems(filter.filterBySurname(surnameField.getText(), clients)));
        peselField.setOnKeyTyped((event) -> listView.setItems(filter.filterByPesel(peselField.getText(), clients)));
        addressField.setOnKeyTyped((event) -> listView.setItems(filter.filterByAddress(addressField.getText(), clients)));
        Pane pane = new VBox();
        pane.getChildren().addAll(forenameLabel, forenameField,
                surnameLabel, surnameField,
                addressLabel, addressField,
                peselLabel, peselField);
        return pane;
    }

    public Client getSelectedClient() throws Error {
        Client client = listView.getSelectionModel().getSelectedItem();
        if (client != null) {
            return client;
        } else {
            throw new Error("You must select a client");
        }
    }
}
