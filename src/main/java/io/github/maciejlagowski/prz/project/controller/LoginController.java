package io.github.maciejlagowski.prz.project.controller;

import com.dlsc.formsfx.model.structure.Form;
import io.github.maciejlagowski.prz.project.view.Home;
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
        if (login(usernameProperty.get(), passwordProperty.get())) {
            FrameController.getInstance().changeView(new Home());
        } else {
            //TODO msg on view not valid credentials
            System.err.println("not valid cred");
        }
    }

    private boolean login(String formUsername, String formPassword) {
        final String SALT = BCrypt.gensalt();
        final String USERNAME = "admin";
        final String PASSWORD = BCrypt.hashpw("admin", SALT);
        return formUsername.equals(USERNAME) && BCrypt.checkpw(formPassword, PASSWORD);
    }
}
