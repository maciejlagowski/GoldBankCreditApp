package io.github.maciejlagowski.prz.project.view;

import io.github.maciejlagowski.prz.project.controller.TypeAndClientsController;
import io.github.maciejlagowski.prz.project.model.database.entity.Client;
import io.github.maciejlagowski.prz.project.model.enums.CreditType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TypeAndClients implements View {

    private TypeAndClientsController controller = TypeAndClientsController.getInstance();
    private ObservableList<Client> clients = controller.getClients();

    public List<Node> createContent() {
        return new LinkedList<>(List.of(
                createTypeForm(),
                createClientsList(),
                createAddClientButton()
        ));
    }

    private Node createTypeForm() {
        ChoiceBox<CreditType> choiceBox = new ChoiceBox<>();
        choiceBox.setItems(FXCollections.observableArrayList(Arrays.asList(CreditType.values())));
        choiceBox.setOnAction((event) -> controller.setCreditType(choiceBox.getValue()));
        return choiceBox;
    }

    private Node createClientsList() {
        ListView<Client> listView = new ListView<>();
        listView.setMaxWidth(500);
        listView.setMaxHeight(200);
        listView.setItems(clients);
        return listView;
    }

    private Node createAddClientButton() {
        Button addClientButton = new Button("Add client");
        addClientButton.setOnAction((event) -> controller.onAddClientButtonClick());
        return addClientButton;
    }
}
