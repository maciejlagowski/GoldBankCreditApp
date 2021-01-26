package io.github.maciejlagowski.goldbank.view.client;

import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import io.github.maciejlagowski.goldbank.controller.AddIncomeController;
import io.github.maciejlagowski.goldbank.model.enumeration.Industry;
import io.github.maciejlagowski.goldbank.view.View;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;

public class AddIncomeView implements View {

    private final AddIncomeController controller = new AddIncomeController();
    private Stage stage;

    public void showStage() {
        stage = new Stage();
        Pane pane = new VBox();
        pane.getChildren().addAll(createIncomeForm(), createCompanyIndustryForm(), createAddIncomeButton());
        stage.setScene(new Scene(pane));
        stage.showAndWait();
    }

    public Node createAddIncomeButton() {
        Button addIncomeButton = new Button("Add income");
        addIncomeButton.setOnAction((event) -> {
            controller.onAddIncomeButtonClick();
            stage.close();
        });
        return addIncomeButton;
    }

    public Node createIncomeForm() {
        Form incomeForm = Form.of(Group.of(
                Field.ofStringType(controller.getCompanyNameProperty())
                        .label("Company \nname")
                        .required("You have to provide company name"),
                Field.ofDoubleType(controller.getAmountProperty())
                        .label("Amount")
                        .required("You have to provide income amount [monthly]")
        )).title("Income");
        FormRenderer formRenderer = new FormRenderer(incomeForm);
        formRenderer.setPrefWidth(700);
        controller.setIncomeForm(incomeForm);
        return formRenderer;
    }

    public Node createCompanyIndustryForm() {
        ChoiceBox<Industry> choiceBox = new ChoiceBox<>();
        choiceBox.setItems(FXCollections.observableArrayList(Arrays.asList(Industry.values())));
        choiceBox.setOnAction((event) -> controller.setCompanyIndustryProperty(choiceBox.getValue()));
        choiceBox.setValue(Industry.AEROSPACE);
        return choiceBox;
    }
}
