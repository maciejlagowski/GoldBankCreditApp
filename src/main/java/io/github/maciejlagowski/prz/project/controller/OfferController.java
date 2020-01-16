package io.github.maciejlagowski.prz.project.controller;

import io.github.maciejlagowski.prz.project.model.credit.Application;
import io.github.maciejlagowski.prz.project.model.credit.offer.OfferGenerator;
import io.github.maciejlagowski.prz.project.model.database.entity.Credit;
import io.github.maciejlagowski.prz.project.model.database.entity.CreditApplication;
import io.github.maciejlagowski.prz.project.model.database.repository.ClientRepository;
import io.github.maciejlagowski.prz.project.model.database.repository.CreditRepository;
import io.github.maciejlagowski.prz.project.view.Wait;
import javafx.application.Platform;
import javafx.scene.control.Slider;
import lombok.Data;

@Data
public class OfferController {

    private static OfferController instance;

    private OfferGenerator offerGenerator;
    private Slider creditAmount;
    private Slider installmentAmount;


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
            CreditApplication application = Application.getApplicationInstance().getCreditApplication();
            Credit credit = new Credit(null, creditAmount.getValue(), 0.0,
                    null, application.getApplicationDate(), null,
                    null, false, application.getType(),
                    installmentAmount.getValue(), false);
            new CreditRepository().createRecord(credit);
            application.getClients().forEach(client -> {
                ClientRepository repo = new ClientRepository();
//                repo.deleteRecord(client);
                client.getCredits().add(credit);
                repo.updateRecord(client);
            });
        }).start();
    }
}
