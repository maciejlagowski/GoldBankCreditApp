package io.github.maciejlagowski.prz.project.view;

import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import io.github.maciejlagowski.prz.project.controller.RequestedAmountController;
import javafx.scene.Node;

import java.util.LinkedList;
import java.util.List;

public class RequestedAmount implements View {

    private RequestedAmountController controller = RequestedAmountController.getInstance();

    public List<Node> createContent() {
        return new LinkedList<>(List.of(
                createAmountForm()
        ));
    }

    private Node createAmountForm() {
        Form amountForm = Form.of(Group.of(
                Field.ofDoubleType(controller.getRequestedAmountProperty())
                        .label("Requested\n  Amount")
        )).title("Requested Amount");
        FormRenderer formRenderer = new FormRenderer(amountForm);
        formRenderer.setPrefWidth(700);
        controller.setRequestedAmountForm(amountForm);
        return formRenderer;
    }
}
