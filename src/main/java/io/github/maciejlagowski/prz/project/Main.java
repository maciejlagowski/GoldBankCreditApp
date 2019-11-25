package io.github.maciejlagowski.prz.project;

import io.github.maciejlagowski.prz.project.view.Home;
import io.github.maciejlagowski.prz.project.view.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        new ViewHandler().changeView(new Home());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
