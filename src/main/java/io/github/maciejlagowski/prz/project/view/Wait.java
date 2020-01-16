package io.github.maciejlagowski.prz.project.view;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.util.LinkedList;
import java.util.List;

public class Wait implements View {

    public List<Node> createContent() {
        return new LinkedList<>(List.of(
                createWaitAnimation()
        ));
    }

    private Node createWaitAnimation() {
        Pane pane = new FlowPane();
        pane.getChildren().add(new ProgressIndicator());
        pane.getChildren().add(new Label("   Please wait.."));
        return pane;
    }
}
