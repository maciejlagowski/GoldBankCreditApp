package io.github.maciejlagowski.prz.project.model.credit;

import io.github.maciejlagowski.prz.project.model.credit.risk.RiskCalc;
import io.github.maciejlagowski.prz.project.model.database.entity.Client;
import io.github.maciejlagowski.prz.project.model.database.entity.CreditApplication;
import io.github.maciejlagowski.prz.project.model.enums.CreditType;

import java.util.Date;
import java.util.List;

public class Application {

    private static Application applicationInstance;

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

    @Override
    @Deprecated
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(creditApplication.getApplicationDate())
                .append(creditApplication.getClients())
                .append(creditApplication.getId())
                .append(creditApplication.getRequestedAmount())
                .append(creditApplication.getRisk())
                .append(creditApplication.getType());
        return stringBuilder.toString();
    }

    public Application setCreditType(CreditType creditType) {
        creditApplication.setType(creditType);
        return this;
    }

    public Application setClients(List<Client> clients) {
        creditApplication.setClients(clients);
        return this;
    }

    public Application setRequestedAmount(Double amount) {
        creditApplication.setRequestedAmount(amount);
        return this;
    }

    public Application calculateRisk() {
        creditApplication.setRisk(new RiskCalc().calculateRisk(creditApplication.getClients()));
        return this;
    }

    public CreditApplication getCreditApplication() {
        return creditApplication;
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
