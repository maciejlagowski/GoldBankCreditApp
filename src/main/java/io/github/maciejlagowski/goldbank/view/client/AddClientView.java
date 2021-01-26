package io.github.maciejlagowski.goldbank.view.client;

import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import io.github.maciejlagowski.goldbank.controller.AddClientController;
import io.github.maciejlagowski.goldbank.model.database.entity.Income;
import io.github.maciejlagowski.goldbank.view.Error;
import io.github.maciejlagowski.goldbank.view.View;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;

public class AddClientView implements View {

    @Getter
    private final AddClientController controller = new AddClientController();

    public void showStage() {
        Stage stage = new Stage();
        Pane pane = new VBox();
        pane.getChildren().addAll(createClientForm(), createIncomesList(), controller.getIncomesSum(), createButtons());
        stage.setScene(new Scene(pane));
        controller.setStage(stage);
        stage.showAndWait();
    }

    public Node createButtons() {
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
        addIncomeButton.setOnAction((event) -> controller.addIncomeButtonClicked());
        Button removeIncomeButton = new Button("Remove income");
        removeIncomeButton.setOnAction((event) -> controller.removeIncomeButtonClicked());
        flowPane.getChildren().addAll(addIncomeButton, removeIncomeButton, addClientButton);
        return flowPane;
    }

    public Node createClientForm() {
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

    public Node createIncomesList() {
        ListView<Income> incomeListView = new ListView<>();
        incomeListView.setItems(controller.getIncomes());
        controller.setIncomeListView(incomeListView);
        return incomeListView;
    }
}
