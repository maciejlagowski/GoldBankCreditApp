package io.github.maciejlagowski.goldbank;

import io.github.maciejlagowski.goldbank.controller.FrameController;
import io.github.maciejlagowski.goldbank.view.Frame;
import io.github.maciejlagowski.goldbank.view.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        new Frame().showStage();
        FrameController.getInstance().changeView(new LoginView());
    }
}
