package io.github.maciejlagowski.prz.project.view.credit;

import io.github.maciejlagowski.prz.project.controller.TypeAndClientsController;
import io.github.maciejlagowski.prz.project.model.database.entity.Client;
import io.github.maciejlagowski.prz.project.model.enums.CreditType;
import io.github.maciejlagowski.prz.project.model.tools.filter.Filter;
import io.github.maciejlagowski.prz.project.model.tools.filter.FilterEnum;
import io.github.maciejlagowski.prz.project.view.View;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TypeAndClients implements View {

    private TypeAndClientsController controller = TypeAndClientsController.getInstance();

    public TypeAndClients(List<Client> clients) {
        controller.setClientsFromDb(FXCollections.observableArrayList(clients));
    }

    public List<Node> createContent() {
        return new LinkedList<>(List.of(
                createTypeForm(),
                createClientsFromDbList(),
                createClientsChosenList(),
                createAddClientButton(),
                createRemoveClientButton(),
                createSearchFields()
        ));
    }

    private Node createTypeForm() {
        ChoiceBox<CreditType> choiceBox = new ChoiceBox<>();
        choiceBox.setItems(FXCollections.observableArrayList(Arrays.asList(CreditType.values())));
        choiceBox.setOnAction((event) -> controller.setCreditType(choiceBox.getValue()));
        choiceBox.setValue(CreditType.RETAIL);
        return choiceBox;
    }

    private Node createClientsFromDbList() {
        ListView<Client> listView = new ListView<>();
        listView.setMaxWidth(500);
        listView.setMaxHeight(200);
        listView.setItems(controller.getClientsFromDb());
        controller.setClientsFromDbListView(listView);
        return listView;
    }

    private Node createClientsChosenList() {
        ListView<Client> listView = new ListView<>();
        listView.setMaxWidth(500);
        listView.setMaxHeight(200);
        listView.setItems(controller.getClientsChosen());
        controller.setClientsChosenListView(listView);
        return listView;
    }

    private Node createAddClientButton() {
        Button addClientButton = new Button("Add client");
        addClientButton.setOnAction((event) -> controller.onAddClientButtonClick());
        return addClientButton;
    }

    private Node createRemoveClientButton() {
        Button removeClientButton = new Button("Remove client");
        removeClientButton.setOnAction((event) -> controller.onRemoveClientButtonClick());
        return removeClientButton;
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
        List<Client> clients = controller.getClientsFromDb();
        ListView<Client> listView = controller.getClientsFromDbListView();
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
}
