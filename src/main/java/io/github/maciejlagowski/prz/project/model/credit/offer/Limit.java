package io.github.maciejlagowski.prz.project.model.credit.offer;


import io.github.maciejlagowski.prz.project.model.database.entity.CreditApplication;
import io.github.maciejlagowski.prz.project.model.enums.Risk;

class Limit {

    Double calculateLimit(CreditApplication application) {
        switch (application.getType()) {
            case MORTGAGE:
                return calculateLimitMortgage(application.getRisk());
            case RETAIL:
                return calculateLimitRetail(application.getRisk());
            case ENTREPRENEUR:
                return calculateLimitEntrepreneur(application.getRisk());
        }
        throw new IllegalArgumentException("Type \"" + application.getType() + "\" is not valid application type");
    }

    private Double calculateLimitMortgage(Risk risk) {
        switch (risk) {
            case LOW:
                return 600000.0;
            case MEDIUM:
                return 250000.0;
            case HIGH:
                return 120000.0;
        }
        throw new IllegalArgumentException("Risk \"" + risk + "\" is not valid risk type");
    }

    private Double calculateLimitRetail(Risk risk) {
        switch (risk) {
            case LOW:
                return 100000.0;
            case MEDIUM:
                return 40000.0;
            case HIGH:
                return 8000.0;
        }
        throw new IllegalArgumentException("Risk \"" + risk + "\" is not valid risk type");
    }

    private Double calculateLimitEntrepreneur(Risk risk) {
        switch (risk) {
            case LOW:
                return 250000.0;
            case MEDIUM:
                return 120000.0;
            case HIGH:
                return 50000.0;
        }
        throw new IllegalArgumentException("Risk \"" + risk + "\" is not valid risk type");
    }
}
