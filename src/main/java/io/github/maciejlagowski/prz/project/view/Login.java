package io.github.maciejlagowski.prz.project.view;

import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.model.structure.PasswordField;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import io.github.maciejlagowski.prz.project.controller.LoginController;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
public class Login implements View {

    private LoginController controller = LoginController.getInstance();
    private String message = "";

    public Login(String message) {
        this.message = message;
    }

    public List<Node> createContent() {
        return new LinkedList<>(List.of(
                createLoginForm(),
                createErrorMessage(),
                createLoginButton()
        ));
    }

    private Node createErrorMessage() {
        Label label = new Label(message);
        label.setTextFill(Color.web("#FF0000"));
        return label;
    }

    private Node createLoginForm() {
        Form loginForm = Form.of(Group.of(
                Field.ofStringType(controller.getUsernameProperty())
                        .label("Username")
                        .required("This field cannot be empty"),
                PasswordField.ofStringType(controller.getPasswordProperty())
                        .label("Password")
                        .required("This field cannot be empty")
        )).title("Login");
        FormRenderer renderer = new FormRenderer(loginForm);
        renderer.setPrefWidth(500);
        controller.setLoginForm(loginForm);
        return renderer;
    }

    private Node createLoginButton() {
        Button loginButton = new Button("Login");
        loginButton.setOnAction(event -> controller.onLoginButtonClick());
        loginButton.setPrefSize(400, 70);
        loginButton.setFont(new Font(35));
        return loginButton;
    }
}
