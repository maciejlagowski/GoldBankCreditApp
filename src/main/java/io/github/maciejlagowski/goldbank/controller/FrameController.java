package io.github.maciejlagowski.goldbank.controller;

import io.github.maciejlagowski.goldbank.view.HomeView;
import io.github.maciejlagowski.goldbank.view.LoginView;
import io.github.maciejlagowski.goldbank.view.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import lombok.Getter;
import lombok.Setter;

public class FrameController implements Controller {

    @Setter
    private static FrameController instance;
    @FXML
    public FlowPane contentPane;
    @FXML
    public Button nextButton;
    @FXML
    public Button backToMenuButton;
    @FXML
    public Label loggedAsLabel;
    @FXML
    public Hyperlink logoutLink;
    @Getter
    @Setter
    private View actualView;
    @FXML
    public FlowPane leftPane;

    public synchronized static FrameController getInstance() {
        return instance;
    }

    public void onNextButtonClick(ActionEvent event) {
        actualView.getController().onNextButtonClick(event);
    }

    public View logoutLinkClicked(ActionEvent event) {
        View loginView = new LoginView();
        logoutLink.setVisible(false);
        loggedAsLabel.setText("Not logged");
        backToMenuButton.setVisible(false);
        return changeView(loginView);
    }

    public View changeView(View view) {
        contentPane.getChildren().clear();
        contentPane.getChildren().addAll(view.createContent());
        actualView = view;
        return view;
    }

    public View onBackToMenuButtonClick(ActionEvent event) {
        nextButton.setVisible(false);
        return changeView(new HomeView());
    }
}
