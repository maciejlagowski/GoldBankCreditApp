package io.github.maciejlagowski.prz.project.model.credit;

import io.github.maciejlagowski.prz.project.model.credit.risk.RiskCalc;
import io.github.maciejlagowski.prz.project.model.database.entity.Client;
import io.github.maciejlagowski.prz.project.model.database.entity.CreditApplication;
import io.github.maciejlagowski.prz.project.model.enums.CreditType;
import io.github.maciejlagowski.prz.project.model.enums.Risk;
import io.github.maciejlagowski.prz.project.view.Error;
import lombok.Getter;

import java.util.Date;
import java.util.List;

public class Application {

    @Getter
    private CreditApplication creditApplication;

    public Application() {
        creditApplication = new CreditApplication();
        creditApplication.setApplicationDate(new Date(System.currentTimeMillis()));
    }

    public Application setCreditType(CreditType creditType) {
        creditApplication.setType(creditType);
        return this;
    }

    public Application setClients(List<Client> clients) {
        creditApplication.setClients(clients);
        return this;
    }

    public Application calculateRisk() throws Error {
        creditApplication.setRisk(new RiskCalc().calculateRisk(creditApplication.getClients()));
        if (creditApplication.getRisk().equals(Risk.DEFAULT)) {
            throw new Error("Application get an default credit risk, cannot continue.");
        }
        return this;
    }
}
