package io.github.maciejlagowski.prz.project.view.clients;

import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import io.github.maciejlagowski.prz.project.controller.AddClientController;
import io.github.maciejlagowski.prz.project.model.database.entity.Income;
import io.github.maciejlagowski.prz.project.view.Error;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddClient {

    private AddClientController controller = AddClientController.getInstance();

    public void showStage() {
        Stage stage = new Stage();
        Pane pane = new VBox();
        pane.getChildren().addAll(createClientForm(), createIncomesList(), controller.getIncomesSum(), createButtons());
        stage.setScene(new Scene(pane));
        controller.setStage(stage);
        stage.showAndWait();
    }

    private Node createButtons() {
        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(10);
        Button addClientButton = new Button("Add client");
        addClientButton.setOnAction((event) -> {
            try {
                controller.onAddClientButtonClick();
                controller.getStage().close();
            } catch (Error error) {
                error.showStage();
            }
        });
        Button addIncomeButton = new Button("Add income");
        addIncomeButton.setOnAction((event) -> {
            new AddIncome().showStage();
            controller.recalculateIncomesSum();
        });
        Button removeIncomeButton = new Button("Remove income");
        removeIncomeButton.setOnAction((event) -> {
            controller.getIncomes().remove(controller.getIncomeListView().getSelectionModel().getSelectedItem());
            controller.recalculateIncomesSum();
        });
        flowPane.getChildren().addAll(addIncomeButton, removeIncomeButton, addClientButton);
        return flowPane;
    }

    private Node createClientForm() {
        Form clientForm = Form.of(Group.of(
                Field.ofStringType(controller.getForenameProperty())
                        .label("Forename")
                        .required("You have to provide client's forename"),
                Field.ofStringType(controller.getSurnameProperty())
                        .label("Surname")
                        .required("You have to provide client's surname"),
                Field.ofStringType(controller.getAddressProperty())
                        .label("Address")
                        .required("You have to provide client's home address"),
                Field.ofStringType(controller.getPeselProperty())
                        .label("PESEL")
                        .required("You have to provide client's PESEL or other ID form")
        )).title("Client");
        FormRenderer formRenderer = new FormRenderer(clientForm);
        formRenderer.setPrefWidth(700);
        controller.setClientForm(clientForm);
        return formRenderer;
    }

    private Node createIncomesList() {
        ListView<Income> incomeListView = new ListView<>();
        incomeListView.setItems(controller.getIncomes());
        controller.setIncomeListView(incomeListView);
        return incomeListView;
    }
}