package io.github.maciejlagowski.prz.project.view;

import io.github.maciejlagowski.prz.project.controller.HomeController;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

public class Home implements View {

    @Getter
    private HomeController controller = new HomeController();

    public List<Node> createContent() {
        return new LinkedList<>(List.of(
                createApplyForACreditButton(),
                createManageCustomersButton()
        ));
    }

    private Node createManageCustomersButton() {
        Button manageCustomersButton = new Button("Manage customers");
        manageCustomersButton.setOnAction(event -> controller.onManageCustomersButtonClick());
        manageCustomersButton.setPrefSize(400, 70);
        manageCustomersButton.setFont(new Font(35));
        return manageCustomersButton;
    }

    private Node createApplyForACreditButton() {
        Button applyForACreditButton = new Button("Apply for a credit");
        applyForACreditButton.setOnAction(event -> controller.onApplyForACreditButtonClick());
        applyForACreditButton.setPrefSize(400, 70);
        applyForACreditButton.setFont(new Font(35));
        return applyForACreditButton;
    }
}
