package io.github.maciejlagowski.prz.project.controller;

import com.dlsc.formsfx.model.structure.Form;
import io.github.maciejlagowski.prz.project.view.Home;
import io.github.maciejlagowski.prz.project.view.Login;
import io.github.maciejlagowski.prz.project.view.Wait;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;

public class LoginController implements Controller {

    private static LoginController instance;
    @Getter
    private SimpleStringProperty usernameProperty = new SimpleStringProperty("");
    @Getter
    private SimpleStringProperty passwordProperty = new SimpleStringProperty("");
    @Setter
    private Form loginForm;

    private LoginController() {
    }

    public static synchronized LoginController getInstance() {
        if (instance == null) {
            instance = new LoginController();
        }
        return instance;
    }

    public void onLoginButtonClick() {
        loginForm.persist();
        Platform.runLater(() -> FrameController.getInstance().changeView(new Wait()));
        new Thread(() -> {
            if (login(usernameProperty.get(), passwordProperty.get())) {
                Platform.runLater(() -> FrameController.getInstance().changeView(new Home()));
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
