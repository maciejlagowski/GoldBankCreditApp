package io.github.maciejlagowski.prz.project.view.client;

import io.github.maciejlagowski.prz.project.controller.ManageClientsController;
import io.github.maciejlagowski.prz.project.model.database.entity.Client;
import io.github.maciejlagowski.prz.project.model.tools.filter.Filter;
import io.github.maciejlagowski.prz.project.model.tools.filter.FilterEnum;
import io.github.maciejlagowski.prz.project.view.Error;
import io.github.maciejlagowski.prz.project.view.View;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.LinkedList;
import java.util.List;

public class ManageClients implements View {

    private ManageClientsController controller = ManageClientsController.getInstance();
    private ListView<Client> listView;

    public ManageClients(List<Client> clients) {
        controller.setClients(clients);
    }

    public List<Node> createContent() {
        return new LinkedList<>(List.of(
                createClientsList(),
                createButtons(),
                createSearchFields()
        ));
    }

    private Node createClientsList() {
        ListView<Client> listView = new ListView<>();
        listView.setItems(FXCollections.observableArrayList(controller.getClients()));
        this.listView = listView;
        return listView;
    }

    private Node createButtons() {
        Button createButton = new Button("Create");
        Button readButton = new Button("Read");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");
        Button saveAndBackToMenuButton = new Button("Save and back to menu");
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
        updateButton.setOnAction((event) -> {
            try {
                controller.onUpdateButtonClick(getSelectedClient());
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
        saveAndBackToMenuButton.setOnAction((event) -> controller.onSaveButtonClick());
        FlowPane pane = new FlowPane();
        pane.setHgap(10.0);
        pane.getChildren().addAll(createButton, readButton, updateButton, deleteButton);
        return pane;
    }

    private Node createSearchFields() {
        Label forenameLabel = new Label("Forename");
        Label surnameLabel = new Label("Surname");
        Label addressLabel = new Label("Address");
        Label peselLabel = new Label("PESEL");
        TextField forenameField = new TextField();
        TextField surnameField = new TextField();
        TextField peselField = new TextField();
        TextField addressField = new TextField();
        List<Client> clients = controller.getClients();
        forenameField.setOnKeyTyped((event) -> listView.setItems(Filter.filterClients(forenameField.getText(), FilterEnum.FORENAME, clients)));
        surnameField.setOnKeyTyped((event) -> listView.setItems(Filter.filterClients(surnameField.getText(), FilterEnum.SURNAME, clients)));
        peselField.setOnKeyTyped((event) -> listView.setItems(Filter.filterClients(peselField.getText(), FilterEnum.PESEL, clients)));
        addressField.setOnKeyTyped((event) -> listView.setItems(Filter.filterClients(addressField.getText(), FilterEnum.ADDRESS, clients)));
        Pane pane = new VBox();
        pane.getChildren().addAll(forenameLabel, forenameField,
                surnameLabel, surnameField,
                addressLabel, addressField,
                peselLabel, peselField);
        return pane;
    }

    private Client getSelectedClient() throws Error {
        Client client = listView.getSelectionModel().getSelectedItem();
        if (client != null) {
            return client;
        } else {
            throw new Error("You must select a client");
        }
    }
}
