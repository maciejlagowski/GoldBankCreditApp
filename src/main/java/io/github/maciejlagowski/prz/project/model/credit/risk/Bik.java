package io.github.maciejlagowski.prz.project.model.credit.risk;

import io.github.maciejlagowski.prz.project.model.bik.BikLiability;
import io.github.maciejlagowski.prz.project.model.bik.BikReport;
import io.github.maciejlagowski.prz.project.model.enums.Risk;

import java.util.List;

class Bik {

    Risk calculateRisk(BikReport bikReport) {
        List<BikLiability> liabilities = bikReport.getLiabilities();
        if (liabilities.isEmpty()) {
            return Risk.HIGH;
        }
        if (isDefault(liabilities)) {
            return Risk.DEFAULT;
        }
        return calculateLiabilitiesRisk(liabilities);
    }

    private boolean isDefault(List<BikLiability> liabilities) {
        boolean isDefault = false;
        for (BikLiability liability : liabilities) {
            if (liability.getIsDefault()) {
                isDefault = true;
                break;
            }
        }
        return isDefault;
    }

    private Risk calculateLiabilitiesRisk(List<BikLiability> liabilities) {
        int paidOff = 0;
        for (BikLiability liability : liabilities) {
            if (liability.getIsPaidOff()) {
                paidOff++;
            }
        }
        double paidToAll = paidOff / (double) liabilities.size();
        if (paidToAll > 0.7) {
            return Risk.LOW;
        } else if (paidToAll > 0.4) {
            return Risk.MEDIUM;
        } else {
            return Risk.HIGH;
        }
    }
}
