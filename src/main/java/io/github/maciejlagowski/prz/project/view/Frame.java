package io.github.maciejlagowski.prz.project.view;

import io.github.maciejlagowski.prz.project.controller.FrameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Frame {

    public void showStage() {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            Parent root = fxmlLoader.load(getClass().getResource("/fxml/frame.fxml").openStream());
            stage.setTitle("GoldBank Credit App");
            stage.setScene(new Scene(root));
            stage.setMinWidth(1040);
            stage.setMinHeight(720);
            stage.setResizable(false);
            FrameController.setInstance(fxmlLoader.getController());
            stage.show();
        } catch (IOException e) {
            //TODO logger
        }
    }
}
