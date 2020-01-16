package io.github.maciejlagowski.prz.project.model.credit.offer;

import io.github.maciejlagowski.prz.project.model.database.entity.CreditApplication;
import lombok.Getter;

public class OfferGenerator {

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

        //TODO jakos ten suwak
        return this;
    }

    private Double calculateMargin() {
        return 0.0;
    }

    public Double calculateMinInstallment(Double creditAmount) {
        Integer period = application.getRequestedPeriod();
        return creditAmount / period;

    }

    public Double calculateMaxInstallment(Double creditAmount) {
        return creditAmount / 3;
    }
}
