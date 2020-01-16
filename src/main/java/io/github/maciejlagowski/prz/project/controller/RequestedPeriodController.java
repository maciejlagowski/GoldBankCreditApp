package io.github.maciejlagowski.prz.project.controller;

import com.dlsc.formsfx.model.structure.Form;
import io.github.maciejlagowski.prz.project.model.credit.Application;
import io.github.maciejlagowski.prz.project.model.credit.offer.OfferGenerator;
import io.github.maciejlagowski.prz.project.view.Wait;
import io.github.maciejlagowski.prz.project.view.credit.Offer;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import lombok.Getter;
import lombok.Setter;

public class RequestedPeriodController {

    private static RequestedPeriodController instance;
    @Getter
    private SimpleIntegerProperty requestedPeriodProperty = new SimpleIntegerProperty(0);
    @Setter
    private Form requestedAmountForm;

    private RequestedPeriodController() {
    }

    public static synchronized RequestedPeriodController getInstance() {
        if (instance == null) {
            instance = new RequestedPeriodController();
        }
        return instance;
    }

    public void onNextButtonClick() {
        requestedAmountForm.persist();
        Platform.runLater(() -> FrameController.getInstance().changeView(new Wait()));
        new Thread(() -> {
            Application application = Application.getApplicationInstance()
                    .setRequestedPeriod(requestedPeriodProperty.get())
                    .calculateRisk();
            Offer offer = new Offer(new OfferGenerator(application.getCreditApplication()).generateOffers());
            Platform.runLater(() -> FrameController.getInstance().changeView(offer));
        }).start();
    }
}
