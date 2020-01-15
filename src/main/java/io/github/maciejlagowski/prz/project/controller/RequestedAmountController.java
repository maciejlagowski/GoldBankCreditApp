package io.github.maciejlagowski.prz.project.controller;

import com.dlsc.formsfx.model.structure.Form;
import io.github.maciejlagowski.prz.project.model.credit.Application;
import io.github.maciejlagowski.prz.project.view.Wait;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import lombok.Getter;
import lombok.Setter;

public class RequestedAmountController implements Controller {

    private static RequestedAmountController instance;
    @Getter
    private SimpleDoubleProperty requestedAmountProperty = new SimpleDoubleProperty(0.0);
    @Setter
    private Form requestedAmountForm;

    private RequestedAmountController() {
    }

    public static synchronized RequestedAmountController getInstance() {
        if (instance == null) {
            instance = new RequestedAmountController();
        }
        return instance;
    }

    public void onNextButtonClick() {
        requestedAmountForm.persist();
        Platform.runLater(() -> FrameController.getInstance().changeView(new Wait()));
        new Thread(() -> {
            Application.getApplicationInstance()
                    .setRequestedAmount(requestedAmountProperty.get())
                    .calculateRisk();
//            Platform.runLater(() -> FrameController.getInstance().changeView(null));
            System.out.println(Application.getApplicationInstance());
        }).start();
    }
}
