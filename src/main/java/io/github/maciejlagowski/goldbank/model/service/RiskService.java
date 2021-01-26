package io.github.maciejlagowski.goldbank.model.service;

import io.github.maciejlagowski.goldbank.model.bik.BikLiability;
import io.github.maciejlagowski.goldbank.model.bik.BikReport;
import io.github.maciejlagowski.goldbank.model.database.entity.Client;
import io.github.maciejlagowski.goldbank.model.database.entity.Income;
import io.github.maciejlagowski.goldbank.model.enumeration.Risk;

import java.util.LinkedList;
import java.util.List;

public class RiskService {

    private final BikService bikService = new BikService();

    public Risk calculateClientsRisk(List<Client> clients) {
        List<Risk> clientsRisk = new LinkedList<>();
        if (clients.isEmpty()) {
            return Risk.DEFAULT;
        }
        clients.forEach(client -> clientsRisk.add(calculateOneClientRisk(client)));
        return getBestRisk(clientsRisk);
    }

    private Risk calculateOneClientRisk(Client client) {
        List<Risk> clientRisks = new LinkedList<>();
        clientRisks.add(calculateIncomesRisk(client.getIncomes()));
        //TODO credit history
        clientRisks.add(calculateBikRisk(bikService.getBikReport(client)));
        return calculateAverageRisk(clientRisks);
    }


    public Risk calculateIncomesRisk(List<Income> incomes) {
        List<Risk> incomeRisks = new LinkedList<>();
        if (incomes.isEmpty()) {
            return Risk.DEFAULT;
        }
        incomes.forEach(income -> incomeRisks.add(calculateOneIncomeRisk(income)));
        return getBestRisk(incomeRisks);
    }

    public Risk calculateOneIncomeRisk(Income income) {
        List<Risk> riskList = new LinkedList<>();
        riskList.add(income.getIndustry().getIndustryRisk());
        riskList.add(calculateIncomeAmountToAverageRisk(income.getAmount()));
        return calculateAverageRisk(riskList);
    }

    public Risk calculateIncomeAmountToAverageRisk(Double incomeAmount) {
        final double AVERAGE_COUNTRY_INCOME = 2500.0;
        final double HIGH_INCOME_MULTIPLIER = 2.0;
        final double LOW_INCOME_MULTIPLIER = 0.5;
        if (incomeAmount > AVERAGE_COUNTRY_INCOME * HIGH_INCOME_MULTIPLIER) {
            return Risk.LOW;
        } else if (incomeAmount < AVERAGE_COUNTRY_INCOME * LOW_INCOME_MULTIPLIER) {
            return Risk.HIGH;
        } else {
            return Risk.MEDIUM;
        }
    }

    public Risk calculateAverageRisk(List<Risk> riskList) {
        double avg = 0.0;
        for (Risk risk : riskList) {
            if (risk.equals(Risk.DEFAULT)) {
                return Risk.DEFAULT;
            }
            avg += risk.getNumericRisk();
        }
        avg /= riskList.size();
        return Risk.getRisk((int) Math.round(avg));
    }

    public Risk getBestRisk(List<Risk> riskList) {
        Risk risk = Risk.HIGH;
        for (Risk riskFromList : riskList) {
            if (riskFromList.equals(Risk.DEFAULT)) {
                return Risk.DEFAULT;
            }
            if (riskFromList.getNumericRisk() < risk.getNumericRisk()) {
                risk = riskFromList;
            }
        }
        return risk;
    }

    public Risk calculateBikRisk(BikReport bikReport) {
        List<BikLiability> liabilities = bikReport.getLiabilities();
        if (liabilities.isEmpty()) {
            return Risk.HIGH;
        }
        if (isBikDefault(liabilities)) {
            return Risk.DEFAULT;
        }
        return calculateBikLiabilitiesRisk(liabilities);
    }

    public boolean isBikDefault(List<BikLiability> liabilities) {
        for (BikLiability liability : liabilities) {
            if (liability.getIsDefault()) {
                return true;
            }
        }
        return false;
    }

    public Risk calculateBikLiabilitiesRisk(List<BikLiability> liabilities) {
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
