package io.github.maciejlagowski.prz.project.controller;

import io.github.maciejlagowski.prz.project.view.View;
import io.github.maciejlagowski.prz.project.view.ViewEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import lombok.Setter;

public class FrameController implements Controller {

    @Setter
    private static FrameController instance;
    @FXML
    public FlowPane contentPane;
    @FXML
    public Button nextButton;

    public synchronized static FrameController getInstance() {
        return instance;
    }

    public void onNextButtonClick(ActionEvent event) {
        //TODO interfejs onNextButton
        switch (ViewEnum.getActualView()) {
            case TYPE_AND_CLIENTS:
                TypeAndClientsController.getInstance().onNextButtonClick();
                break;
            case REQUESTED_AMOUNT:
                RequestedAmountController.getInstance().onNextButtonClick();
                break;
            default:
                System.err.println("Button should be inactive here!");
        }
    }

    public void changeView(View view) {
        contentPane.getChildren().clear();
        contentPane.getChildren().addAll(view.createContent());
        ViewEnum.setActualView(view);
    }
}
