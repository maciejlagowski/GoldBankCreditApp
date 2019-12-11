package io.github.maciejlagowski.prz.project;

import io.github.maciejlagowski.prz.project.controller.FrameController;
import io.github.maciejlagowski.prz.project.view.Frame;
import io.github.maciejlagowski.prz.project.view.Login;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        System.out.println("dupa");
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        new Frame().showStage();
        FrameController.getInstance().changeView(new Login());
        System.out.println("dupa");
    }
}
