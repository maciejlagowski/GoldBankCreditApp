package io.github.maciejlagowski.prz.project.model.credit;

import io.github.maciejlagowski.prz.project.model.credit.risk.RiskCalc;
import io.github.maciejlagowski.prz.project.model.database.entity.Client;
import io.github.maciejlagowski.prz.project.model.database.entity.CreditApplication;
import io.github.maciejlagowski.prz.project.model.enums.CreditType;
import lombok.Getter;

import java.util.Date;
import java.util.List;

public class Application {

    private static Application applicationInstance;

    @Getter
    private CreditApplication creditApplication;

    public Application() {
        creditApplication = new CreditApplication();
        creditApplication.setApplicationDate(new Date(System.currentTimeMillis()));
    }

    public static synchronized Application getApplicationInstance() {
        return applicationInstance;
    }

    public static synchronized void setApplicationInstance(Application applicationInstance) {
        Application.applicationInstance = applicationInstance;
    }

    public Application setCreditType(CreditType creditType) {
        creditApplication.setType(creditType);
        return this;
    }

    public Application setClients(List<Client> clients) {
        creditApplication.setClients(clients);
        return this;
    }

    public Application setRequestedPeriod(Integer period) {
        creditApplication.setRequestedPeriod(period);
        return this;
    }

    public Application calculateRisk() {
        creditApplication.setRisk(new RiskCalc().calculateRisk(creditApplication.getClients()));
        return this;
    }

    public CreditApplication generateApplication() {
//        if (application.getRisk().equals(Risk.DEFAULT)) {
//            TODO show default error view
//        } else {
//            List<Credit> offers = new OfferGenerator(application).generateOffers();
//            application.setCredit( /*TODO choose offer view */ null);
//        }
//        return application;
        return null;
    }
}
