package io.github.maciejlagowski.prz.project.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {

    private static Stage actualStage;

    public void changeView(View view) {
        if (actualStage != null) {
            actualStage.close();
        }
        actualStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            Parent root = fxmlLoader.load(getClass().getResource("/fxml/" + view.getName() + ".fxml").openStream());
            actualStage.setTitle("GoldBank Credit App - " + view.getName());
            actualStage.setScene(new Scene(root));
            actualStage.show();
        } catch (IOException e) {
            //TODO logger
        }
    }
}
