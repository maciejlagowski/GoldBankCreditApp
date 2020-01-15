package io.github.maciejlagowski.prz.project.view;

import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import io.github.maciejlagowski.prz.project.controller.AddClientController;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AddClient {

    private AddClientController controller = AddClientController.getInstance();

    public void showStage() {
        Stage stage = new Stage();
        Pane pane = new FlowPane();
        Button button = new Button("Add client");
        button.setOnAction((event) -> {
            controller.onAddClientButtonClick();
            stage.close();
        });
        pane.getChildren().add(createClientForm());
        pane.getChildren().add(button);
        stage.setScene(new Scene(pane));
        stage.showAndWait();
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
}
