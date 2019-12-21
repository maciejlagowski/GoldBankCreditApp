package io.github.maciejlagowski.prz.project.model.credit.offer;

import io.github.maciejlagowski.prz.project.model.bik.BikApi;
import io.github.maciejlagowski.prz.project.model.bik.BikLiability;
import io.github.maciejlagowski.prz.project.model.bik.BikReport;
import io.github.maciejlagowski.prz.project.model.database.entity.Client;
import io.github.maciejlagowski.prz.project.model.database.entity.Credit;
import io.github.maciejlagowski.prz.project.model.database.entity.CreditApplication;
import io.github.maciejlagowski.prz.project.model.database.entity.Income;

import java.util.List;

class Capacity {

    double calculateCapacity(CreditApplication application) {
        double capacity = 0.0;
        for (Client client : application.getClients()) {
            capacity += calculateClientCapacity(client);
        }
        return capacity;
    }

    private double calculateClientCapacity(Client client) {
        double incomes = calculateClientIncomes(client.getIncomes());
        double liabilities = calculateClientLiabilities(client.getCredits());
        liabilities += calculateBikLiabilities(new BikApi("123456789").getBikReport(client.getPesel()));
        return incomes - liabilities;
    }

    private double calculateClientIncomes(List<Income> incomes) {
        return incomes.stream().mapToDouble(Income::getAmount).sum();
    }

    private double calculateClientLiabilities(List<Credit> liabilities) {
        double sum = 0.0;
        for (Credit liability : liabilities) {
            if (!liability.getIsPaidOff()) {
                sum += liability.getInstallment();
            }
        }
        return sum;
    }

    private double calculateBikLiabilities(BikReport report) {
        double sum = 0.0;
        for (BikLiability liability : report.getLiabilities()) {
            if (!liability.getIsPaidOff()) {
                sum += liability.getInstallment();
            }
        }
        return sum;
    }
}
