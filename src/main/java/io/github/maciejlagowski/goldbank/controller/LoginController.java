package io.github.maciejlagowski.goldbank.controller;

import com.dlsc.formsfx.model.structure.Form;
import io.github.maciejlagowski.goldbank.model.service.BackgroundTaskRunnerService;
import io.github.maciejlagowski.goldbank.model.service.UserService;
import io.github.maciejlagowski.goldbank.view.HomeView;
import io.github.maciejlagowski.goldbank.view.LoginView;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;

public class LoginController implements Controller {

    private final UserService userService = new UserService();
    private final FrameController frameController = FrameController.getInstance();
    @Getter
    private final SimpleStringProperty usernameProperty = new SimpleStringProperty("");
    @Getter
    private final SimpleStringProperty passwordProperty = new SimpleStringProperty("");
    @Setter
    private Form loginForm;

    public String onLoginButtonClick() {
        loginForm.persist();
        String username = usernameProperty.get();
        String password = passwordProperty.get();
        new BackgroundTaskRunnerService(() -> {
            if (userService.login(username, password)) {
                Platform.runLater(() -> {
                    frameController.changeView(new HomeView());
                    frameController.loggedAsLabel.setText("Logged as: " + usernameProperty.get());
                    frameController.backToMenuButton.setVisible(true);
                    frameController.logoutLink.setVisible(true);
                });
            } else {
                Platform.runLater(() -> frameController.changeView(new LoginView("Not valid credentials")));
            }
        }).start();
        return username;
    }
}
