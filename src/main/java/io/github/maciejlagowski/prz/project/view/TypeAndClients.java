package io.github.maciejlagowski.prz.project.view;

import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import io.github.maciejlagowski.prz.project.controller.TypeAndClientsController;
import io.github.maciejlagowski.prz.project.model.enums.CreditType;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.LinkedList;
import java.util.List;

public class TypeAndClients implements View {

    private TypeAndClientsController controller = TypeAndClientsController.getInstance();

    public List<Node> createContent() {
        return new LinkedList<>(List.of(
                createTypeForm(),
                createClientsForm()
        ));
    }

    private Node createTypeForm() {
        List<String> types = CreditType.getAll();
        Form typeForm = Form.of(Group.of(
                Field.ofSingleSelectionType(
                        types, 1
                ).label("Type")
        )).title("Type of credit");
        FormRenderer formRenderer = new FormRenderer(typeForm);
        formRenderer.setPrefWidth(400);
        return formRenderer;
    }

    private Node createClientsForm() {
        return new Pane();
    }
}
