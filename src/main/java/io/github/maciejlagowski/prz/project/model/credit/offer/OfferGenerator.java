package io.github.maciejlagowski.prz.project.model.credit.offer;

import io.github.maciejlagowski.prz.project.model.database.entity.CreditApplication;
import io.github.maciejlagowski.prz.project.model.enums.Risk;
import io.github.maciejlagowski.prz.project.view.Error;
import lombok.Getter;

public class OfferGenerator {

    @Getter
    private CreditApplication application;
    @Getter
    private Double limit;
    @Getter
    private Double capacity;

    public OfferGenerator(CreditApplication application) {
        this.application = application;
    }

    public OfferGenerator generateOffers() throws Error {
        capacity = new Capacity().calculateCapacity(application);
        if (capacity <= 0.0) {
            application.setRisk(Risk.DEFAULT);
            throw new Error("Capacity of application is negative.");
        }
        limit = new Limit().calculateLimit(application);
        return this;
    }

    public double calculateFullCreditCost(Double creditAmount) {
        final double MARKUP_FOR_MORTGAGE = 1.042;
        final double MARKUP_FOR_ENTREPRENEUR = 1.052;
        final double MARKUP_FOR_RETAIL = 1.064;
        switch (application.getType()) {
            case MORTGAGE:
                return creditAmount * MARKUP_FOR_MORTGAGE;
            case RETAIL:
                return creditAmount * MARKUP_FOR_RETAIL;
            case ENTREPRENEUR:
                return creditAmount * MARKUP_FOR_ENTREPRENEUR;
        }
        throw new IllegalArgumentException("Invalid credit type: " + application.getType());
    }

    public int calculateRepaymentPeriod(Double fullCreditCost, Double installmentAmount) {
        return (int) Math.floor(fullCreditCost / installmentAmount) + 1;
    }

    public Double calculateMinInstallment(Double creditAmount) {
        final int MAXIMUM_CREDIT_PERIOD_IN_MONTHS = 120;
        return (creditAmount / MAXIMUM_CREDIT_PERIOD_IN_MONTHS) + 1;
    }

    public Double calculateMaxInstallment(Double creditAmount) {
        final int MINIMUM_CREDIT_PERIOD_IN_MONTHS = 3;
        return Math.min(creditAmount / MINIMUM_CREDIT_PERIOD_IN_MONTHS, capacity);
    }
}
