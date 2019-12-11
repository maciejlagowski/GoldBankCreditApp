package io.github.maciejlagowski.prz.project.model.credit;

import io.github.maciejlagowski.prz.project.model.credit.offer.OfferGenerator;
import io.github.maciejlagowski.prz.project.model.credit.risk.RiskCalc;
import io.github.maciejlagowski.prz.project.model.database.entity.Credit;
import io.github.maciejlagowski.prz.project.model.database.entity.CreditApplication;
import io.github.maciejlagowski.prz.project.model.enums.Risk;

import java.util.Date;
import java.util.List;

public class Application {

    public CreditApplication generateApplication() {
        CreditApplication application = new CreditApplication();
        application.setApplicationDate(new Date(System.currentTimeMillis()));
        application.setType(/*TODO get type from view*/ null);
        application.setClients( /*TODO get client list from view */ null);
        application.setRequestedAmount( /*TODO get amount from view */ null);
        application.setRisk(new RiskCalc().calculateRisk(application.getClients()));
        if (application.getRisk().equals(Risk.DEFAULT)) {
            //TODO show default error view
        } else {
            List<Credit> offers = new OfferGenerator(application).generateOffers();
            application.setCredit( /*TODO choose offer view */ null);
        }
        return application;
    }
}
