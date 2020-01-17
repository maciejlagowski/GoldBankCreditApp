package io.github.maciejlagowski.prz.project.model.credit.offer;

import io.github.maciejlagowski.prz.project.model.database.entity.CreditApplication;
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

    public OfferGenerator generateOffers() {
        limit = new Limit().calculateLimit(application);
        capacity = new Capacity().calculateCapacity(application);
        return this;
    }

    private Double calculateMargin(Double creditAmount, Double installmentAmount) {
        //TODO
        return 0.0;
    }

    private int calculatePeriod() {
        return 0;
    }

    public Double calculateMinInstallment(Double creditAmount) {
        final int MAXIMUM_CREDIT_PERIOD_IN_MONTHS = 120;
        return creditAmount / MAXIMUM_CREDIT_PERIOD_IN_MONTHS;

    }

    public Double calculateMaxInstallment(Double creditAmount) {
        final int MINIMUM_CREDIT_PERIOD_IN_MONTHS = 3;
        return creditAmount / MINIMUM_CREDIT_PERIOD_IN_MONTHS;
    }
}
