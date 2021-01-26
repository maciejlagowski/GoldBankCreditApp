package io.github.maciejlagowski.goldbank.view;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.util.List;

public class WaitView implements View {

    public List<Node> createContent() {
        return List.of(
                createWaitAnimation()
        );
    }

    public Node createWaitAnimation() {
        Pane pane = new FlowPane();
        pane.getChildren().add(new ProgressIndicator());
        pane.getChildren().add(new Label("   Please wait.."));
        return pane;
    }
}
