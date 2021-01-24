package io.github.maciejlagowski.prz.project.model.service;


import io.github.maciejlagowski.prz.project.model.database.entity.CreditApplication;
import io.github.maciejlagowski.prz.project.model.enumeration.Risk;

public class LimitService {

    public double calculateLimit(CreditApplication application) {
        switch (application.getType()) {
            case MORTGAGE:
                return getLimitMortgage(application.getRisk());
            case RETAIL:
                return getLimitRetail(application.getRisk());
            case ENTREPRENEUR:
                return getLimitEntrepreneur(application.getRisk());
            default:
                return 0.0;
        }
    }

    public double getLimitMortgage(Risk risk) {
        switch (risk) {
            case LOW:
                return 600000.0;
            case MEDIUM:
                return 250000.0;
            case HIGH:
                return 120000.0;
            default:
                return 0.0;
        }
    }

    public double getLimitRetail(Risk risk) {
        switch (risk) {
            case LOW:
                return 100000.0;
            case MEDIUM:
                return 40000.0;
            case HIGH:
                return 8000.0;
            default:
                return 0.0;
        }
    }

    public double getLimitEntrepreneur(Risk risk) {
        switch (risk) {
            case LOW:
                return 250000.0;
            case MEDIUM:
                return 120000.0;
            case HIGH:
                return 50000.0;
            default:
                return 0.0;
        }
    }
}
