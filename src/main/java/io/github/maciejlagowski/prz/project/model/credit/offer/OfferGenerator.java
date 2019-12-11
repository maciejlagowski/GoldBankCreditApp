package io.github.maciejlagowski.prz.project.model.credit.offer;

import io.github.maciejlagowski.prz.project.model.database.entity.Credit;
import io.github.maciejlagowski.prz.project.model.database.entity.CreditApplication;

import java.util.LinkedList;
import java.util.List;

public class OfferGenerator {

    private CreditApplication application;
    private List<Credit> offers = new LinkedList<>();

    public OfferGenerator(CreditApplication application) {
        this.application = application;
    }

    public List<Credit> generateOffers() {
        Double limit = new Limit().calculateLimit(application);
        Double capacity = new Capacity().calculateCapacity(application);

        //TODO jakos ten suwak
        return null;
    }

    private Double calculateMargin() {
        return 0.0;
    }
}
