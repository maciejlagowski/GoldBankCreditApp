package io.github.maciejlagowski.prz.project.view.credit;

import io.github.maciejlagowski.prz.project.view.View;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.List;

public class CreditGrantedView implements View {

    public List<Node> createContent() {
        Pane pane = new VBox();
        pane.setMinHeight(400);
        pane.setMinWidth(800);
        pane.setBackground(new Background(new BackgroundImage(new Image("/img/fireworks.png"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        pane.getChildren().add(new Label("Credit granted!"));
        return List.of(pane);
    }
}
