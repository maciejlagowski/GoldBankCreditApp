package io.github.maciejlagowski.prz.project.controller;

import io.github.maciejlagowski.prz.project.view.Home;
import io.github.maciejlagowski.prz.project.view.View;
import io.github.maciejlagowski.prz.project.view.ViewEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import lombok.Getter;
import lombok.Setter;

public class FrameController extends Controller {

    @Setter
    private static FrameController instance;
    @FXML
    public FlowPane contentPane;
    @FXML
    public Button nextButton;
    @FXML
    public Button backToMenuButton;
    @Getter
    @Setter
    private View actualView;

    public synchronized static FrameController getInstance() {
        return instance;
    }

    public void onNextButtonClick(ActionEvent event) {
        try {
            actualView.getController().onNextButtonClick(event);
        } catch (RuntimeException e) {
            System.err.println("Button should be inactive here");
        }
    }

    public void changeView(View view) {
        contentPane.getChildren().clear();
        contentPane.getChildren().addAll(view.createContent());
        actualView = view;
        ViewEnum.setActualView(view);
    }

    public void onBackToMenuButtonClick(ActionEvent event) {
        changeView(new Home());
        nextButton.setVisible(false);
    }
}
