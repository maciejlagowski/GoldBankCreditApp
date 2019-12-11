package io.github.maciejlagowski.prz.project.model.credit.risk;

import io.github.maciejlagowski.prz.project.model.database.entity.Income;
import io.github.maciejlagowski.prz.project.model.enums.Risk;

import java.util.LinkedList;
import java.util.List;

public class Incomes {

    private final Double AVERAGE_COUNTRY_INCOME = 2500.0;
    private final Double HIGH_INCOME_MULTIPLIER = 2.0;
    private final Double LOW_INCOME_MULTIPLIER = 0.5;

    public Risk calculateRisk(List<Income> incomes) {
        List<Risk> incomeRisks = new LinkedList<>();
        incomes.forEach(income -> incomeRisks.add(calculateIncomeRisk(income)));
        return Risk.getBestRisk(incomeRisks);
    }

    private Risk calculateIncomeRisk(Income income) {
        List<Risk> riskList = new LinkedList<>();
        riskList.add(income.getIndustry().getIndustryRisk());
        riskList.add(calculateIncomeAmountToAverageRisk(income.getAmount()));
        return Risk.calculateAverageRisk(riskList);
    }

    private Risk calculateIncomeAmountToAverageRisk(Double incomeAmount) {
        if (incomeAmount > AVERAGE_COUNTRY_INCOME * HIGH_INCOME_MULTIPLIER) {
            return Risk.LOW;
        } else if (incomeAmount < AVERAGE_COUNTRY_INCOME * LOW_INCOME_MULTIPLIER) {
            return Risk.HIGH;
        } else {
            return Risk.MEDIUM;
        }
    }
}