package io.github.maciejlagowski.prz.project.view.client;

import io.github.maciejlagowski.prz.project.model.database.entity.Client;
import io.github.maciejlagowski.prz.project.model.database.entity.Credit;
import io.github.maciejlagowski.prz.project.model.database.entity.Income;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReadClient {

    private Client client;

    public void showStage() {
        Stage stage = new Stage();
        Pane pane = new FlowPane();
        Button button = new Button("Close");
        button.setOnAction((event) -> stage.close());
        pane.getChildren().add(createClientInformation());
        pane.getChildren().add(button);
        stage.setScene(new Scene(pane));
        stage.showAndWait();
    }

    private Node createClientInformation() {
        Pane pane = new VBox();
        ListView<Credit> creditListView = new ListView<>();
        ListView<Income> incomeListView = new ListView<>();
        creditListView.setItems(FXCollections.observableArrayList(client.getCredits()));
        incomeListView.setItems(FXCollections.observableArrayList(client.getIncomes()));
        creditListView.setMaxHeight(100);
        incomeListView.setMaxHeight(100);
        pane.getChildren().addAll(
                new Label("Forename: " + client.getForename()),
                new Label("Surname: " + client.getSurname()),
                new Label("Address: " + client.getAddress()),
                new Label("PESEL: " + client.getPesel()),
                new Label("Credits:"),
                creditListView,
                new Label("Incomes:"),
                incomeListView,
                createSumLabel()
        );
        return pane;
    }

    private Label createSumLabel() {
        Double sumCredits = 0.0;
        Double sumIncomes = 0.0;
        for (Income income : client.getIncomes()) {
            sumIncomes += income.getAmount();
        }
        for (Credit credit : client.getCredits()) {
            sumCredits += credit.getInstallment();
        }
        return new Label("Incomes sum: " + sumIncomes + "; Credits sum: " + sumCredits +
                "; Difference: " + (sumIncomes - sumCredits) + " [monthly]");
    }
}
