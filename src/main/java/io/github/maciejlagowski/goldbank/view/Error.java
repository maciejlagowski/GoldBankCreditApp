package io.github.maciejlagowski.goldbank.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Error extends Throwable {

    private final String message;

    public void showStage() {
        Stage stage = new Stage();
        VBox pane = new VBox();
        pane.setSpacing(10);
        pane.setMinWidth(300);
        Button button = new Button("OK");
        button.setOnAction((event) -> stage.close());
        Label label = new Label("Error: " + message);
        label.setTextFill(Color.web("#FF0000"));
        pane.getChildren().addAll(label, button);
        stage.setScene(new Scene(pane));
        stage.showAndWait();
    }
}
