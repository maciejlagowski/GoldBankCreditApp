package io.github.maciejlagowski.prz.project.model.service;

import io.github.maciejlagowski.prz.project.model.database.entity.Client;
import io.github.maciejlagowski.prz.project.model.database.entity.CreditApplication;
import io.github.maciejlagowski.prz.project.model.enumeration.CreditType;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class CreditService {

    private final RiskService riskService = new RiskService();

    public CreditApplication createCreditApplication(CreditType creditType, ObservableList<Client> clientsChosen) {
        return CreditApplication.builder()
                .applicationDate(LocalDate.now())
                .type(creditType)
                .clients(clientsChosen)
                .risk(riskService.calculateClientsRisk(clientsChosen))
                .build();
    }

    public double calculateFullCreditCost(double creditAmount, CreditType creditType) {
        final double MARKUP_FOR_MORTGAGE = 1.042;
        final double MARKUP_FOR_ENTREPRENEUR = 1.052;
        final double MARKUP_FOR_RETAIL = 1.064;
        switch (creditType) {
            case MORTGAGE:
                return creditAmount * MARKUP_FOR_MORTGAGE;
            case RETAIL:
                return creditAmount * MARKUP_FOR_RETAIL;
            case ENTREPRENEUR:
                return creditAmount * MARKUP_FOR_ENTREPRENEUR;
            default:
                return 0.0;
        }
    }

    public int calculateRepaymentPeriod(double fullCreditCost, double installmentAmount) {
        return (int) Math.floor(fullCreditCost / installmentAmount) + 1;
    }

    public double calculateMinInstallment(double creditAmount) {
        final int MAXIMUM_CREDIT_PERIOD_IN_MONTHS = 120;
        return (creditAmount / MAXIMUM_CREDIT_PERIOD_IN_MONTHS) + 1;
    }

    public double calculateMaxInstallment(double creditAmount, double capacity) {
        final int MINIMUM_CREDIT_PERIOD_IN_MONTHS = 3;
        return Math.min(creditAmount / MINIMUM_CREDIT_PERIOD_IN_MONTHS, capacity);
    }
}
