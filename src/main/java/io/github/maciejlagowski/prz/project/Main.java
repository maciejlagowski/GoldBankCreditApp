package io.github.maciejlagowski.prz.project;

import io.github.maciejlagowski.prz.project.controller.FrameController;
import io.github.maciejlagowski.prz.project.view.Frame;
import io.github.maciejlagowski.prz.project.view.Home;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        new Frame().showStage();
//        FrameController.getInstance().changeView(new Login());
        FrameController.getInstance().changeView(new Home());
//        FrameController.getInstance().changeView(new TypeAndClients());
    }
}
