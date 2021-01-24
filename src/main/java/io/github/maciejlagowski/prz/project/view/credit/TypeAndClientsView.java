package io.github.maciejlagowski.prz.project.view.credit;

import io.github.maciejlagowski.prz.project.controller.TypeAndClientsController;
import io.github.maciejlagowski.prz.project.model.database.entity.Client;
import io.github.maciejlagowski.prz.project.model.enumeration.CreditType;
import io.github.maciejlagowski.prz.project.model.service.ClientFilterService;
import io.github.maciejlagowski.prz.project.view.View;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class TypeAndClientsView implements View {

    @Getter
    private final TypeAndClientsController controller = new TypeAndClientsController();

    public TypeAndClientsView(List<Client> clients) {
        controller.setClientsFromDb(FXCollections.observableArrayList(clients));
    }

    public List<Node> createContent() {
        return List.of(
                createTypeForm(),
                createClientsFromDbList(),
                createClientsChosenList(),
                createAddClientButton(),
                createRemoveClientButton(),
                createSearchFields()
        );
    }

    public Node createTypeForm() {
        ChoiceBox<CreditType> choiceBox = new ChoiceBox<>();
        choiceBox.setItems(FXCollections.observableArrayList(Arrays.asList(CreditType.values())));
        choiceBox.setOnAction((event) -> controller.setCreditType(choiceBox.getValue()));
        choiceBox.setValue(CreditType.RETAIL);
        return choiceBox;
    }

    public Node createClientsFromDbList() {
        ListView<Client> listView = new ListView<>();
        listView.setMaxWidth(500);
        listView.setMaxHeight(200);
        listView.setItems(controller.getClientsFromDb());
        controller.setClientsFromDbListView(listView);
        return listView;
    }

    public Node createClientsChosenList() {
        ListView<Client> listView = new ListView<>();
        listView.setMaxWidth(500);
        listView.setMaxHeight(200);
        listView.setItems(controller.getClientsChosen());
        controller.setClientsChosenListView(listView);
        return listView;
    }

    public Node createAddClientButton() {
        Button addClientButton = new Button("Add client");
        addClientButton.setOnAction((event) -> controller.onAddClientButtonClick());
        return addClientButton;
    }

    public Node createRemoveClientButton() {
        Button removeClientButton = new Button("Remove client");
        removeClientButton.setOnAction((event) -> controller.onRemoveClientButtonClick());
        return removeClientButton;
    }

    public Node createSearchFields() { // TODO duplicate
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
}
