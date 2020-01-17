package io.github.maciejlagowski.prz.project.controller;

import com.dlsc.formsfx.model.structure.Form;
import io.github.maciejlagowski.prz.project.model.tools.BackgroundTaskRunner;
import io.github.maciejlagowski.prz.project.view.Home;
import io.github.maciejlagowski.prz.project.view.Login;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;

public class LoginController extends Controller {

    @Getter
    private SimpleStringProperty usernameProperty = new SimpleStringProperty("");
    @Getter
    private SimpleStringProperty passwordProperty = new SimpleStringProperty("");
    @Setter
    private Form loginForm;

    public void onLoginButtonClick() {
        loginForm.persist();
        new BackgroundTaskRunner(() -> {
            if (login(usernameProperty.get(), passwordProperty.get())) {
                Platform.runLater(() -> FrameController.getInstance().changeView(new Home()));
                FrameController.getInstance().backToMenuButton.setVisible(true);
            } else {
                Platform.runLater(() -> FrameController.getInstance().changeView(new Login("Not valid credentials")));
            }
        }).start();
    }

    private boolean login(String formUsername, String formPassword) {
        try {   //TODO from DB, now only simulates sleep
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final String SALT = BCrypt.gensalt();
        final String USERNAME = "admin";
        final String PASSWORD = BCrypt.hashpw("admin", SALT);
        return formUsername.equals(USERNAME) && BCrypt.checkpw(formPassword, PASSWORD);
    }
}
