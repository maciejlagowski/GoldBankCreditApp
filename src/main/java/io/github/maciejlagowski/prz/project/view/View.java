package io.github.maciejlagowski.prz.project.view;

import io.github.maciejlagowski.prz.project.controller.Controller;
import javafx.scene.Node;

import java.util.LinkedList;
import java.util.List;

public interface View {
    default List<Node> createContent() {
        return new LinkedList<>();
    }

    default Controller getController() {
        throw new RuntimeException("Class " + this.getClass() + " does not have a controller");
    }
}
