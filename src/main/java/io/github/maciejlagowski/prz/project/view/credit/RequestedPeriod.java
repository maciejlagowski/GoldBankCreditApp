package io.github.maciejlagowski.prz.project.view.credit;

import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import io.github.maciejlagowski.prz.project.controller.RequestedPeriodController;
import io.github.maciejlagowski.prz.project.view.View;
import javafx.scene.Node;

import java.util.LinkedList;
import java.util.List;

public class RequestedPeriod implements View {

    private RequestedPeriodController controller = RequestedPeriodController.getInstance();

    public List<Node> createContent() {
        return new LinkedList<>(List.of(
                createPeriodForm()
        ));
    }

    private Node createPeriodForm() {
        Form periodForm = Form.of(Group.of(
                Field.ofIntegerType(controller.getRequestedPeriodProperty())
                        .label("Requested\n  Period")
        )).title("Requested Period");
        FormRenderer formRenderer = new FormRenderer(periodForm);
        formRenderer.setPrefWidth(700);
        controller.setRequestedAmountForm(periodForm);
        return formRenderer;
    }
}
