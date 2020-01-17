package io.github.maciejlagowski.prz.project.controller;

import io.github.maciejlagowski.prz.project.model.credit.offer.OfferGenerator;
import io.github.maciejlagowski.prz.project.model.database.entity.Credit;
import io.github.maciejlagowski.prz.project.model.database.entity.CreditApplication;
import io.github.maciejlagowski.prz.project.model.database.repository.ClientRepository;
import io.github.maciejlagowski.prz.project.model.database.repository.CreditRepository;
import io.github.maciejlagowski.prz.project.model.tools.Helpers;
import io.github.maciejlagowski.prz.project.view.Wait;
import io.github.maciejlagowski.prz.project.view.credit.CreditGranted;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import lombok.Data;

@Data
public class OfferController {

    private static OfferController instance;

    private OfferGenerator offerGenerator;
    private Slider creditAmount;
    private Slider installmentAmount;
    private Label creditAmountLabel;
    private Label installmentAmountLabel;


    private OfferController() {
    }

    public static synchronized OfferController getInstance() {
        if (instance == null) {
            instance = new OfferController();
        }
        return instance;
    }

    public void onGetCreditButtonClick() {
        Platform.runLater(() -> FrameController.getInstance().changeView(new Wait()));
        new Thread(() -> {
            //TODO markup, plannedRepaymentDate
            CreditApplication application = offerGenerator.getApplication();
            Credit credit = new Credit(null, Helpers.roundDouble(creditAmount.getValue()), 0.0,
                    null, application.getApplicationDate(), null,
                    null, false, application.getType(),
                    Helpers.roundDouble(installmentAmount.getValue()), false);
            new CreditRepository().createRecord(credit);
            application.getClients().forEach(client -> {
                client.getCredits().add(credit);
                new ClientRepository().updateRecord(client);
            });
            Platform.runLater(() -> FrameController.getInstance().changeView(new CreditGranted()));
        }).start();
    }
}
