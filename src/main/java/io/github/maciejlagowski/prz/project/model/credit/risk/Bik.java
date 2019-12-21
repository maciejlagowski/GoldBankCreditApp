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
        for (BikLiability liability : liabilities) {
            if (liability.getIsDefault()) {
                return true;
            }
        }
        return false;
    }

    private Risk calculateLiabilitiesRisk(List<BikLiability> liabilities) {
        final double PTA_RATIO_HIGH = 0.7;
        final double PTA_RATIO_MEDIUM = 0.4;
        int paidOff = 0;
        for (BikLiability liability : liabilities) {
            if (liability.getIsPaidOff()) {
                paidOff++;
            }
        }
        double paidToAll = paidOff / (double) liabilities.size();
        if (paidToAll > PTA_RATIO_HIGH) {
            return Risk.LOW;
        } else if (paidToAll > PTA_RATIO_MEDIUM) {
            return Risk.MEDIUM;
        } else {
            return Risk.HIGH;
        }
    }
}
