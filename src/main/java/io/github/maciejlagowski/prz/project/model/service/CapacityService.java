package io.github.maciejlagowski.prz.project.model.service;

import io.github.maciejlagowski.prz.project.model.bik.BikLiability;
import io.github.maciejlagowski.prz.project.model.bik.BikReport;
import io.github.maciejlagowski.prz.project.model.database.entity.Client;
import io.github.maciejlagowski.prz.project.model.database.entity.Credit;
import io.github.maciejlagowski.prz.project.model.database.entity.CreditApplication;
import io.github.maciejlagowski.prz.project.model.database.entity.Income;

import java.util.List;

public class CapacityService {

    private final BikService bikService = new BikService();

    public double calculateCapacity(CreditApplication application) {
        double capacity = 0.0;
        for (Client client : application.getClients()) {
            capacity += calculateClientCapacity(client, bikService.getBikReport(client));
        }
        return capacity;
    }

    public double calculateClientCapacity(Client client, BikReport bikReport) {
        double incomes = sumClientIncomes(client.getIncomes());
        double liabilities = sumBankInstallments(client.getCredits());
        liabilities += sumBikInstallments(bikReport);
        return incomes - liabilities;
    }

    public double sumClientIncomes(List<Income> incomes) {
        return incomes.stream().mapToDouble(Income::getAmount).sum();
    }

    public double sumBankInstallments(List<Credit> liabilities) {
        double sum = 0.0;
        for (Credit liability : liabilities) {
            if (!liability.getIsPaidOff()) {
                sum += liability.getInstallment();
            }
        }
        return sum;
    }

    public double sumBikInstallments(BikReport report) {
        double sum = 0.0;
        for (BikLiability liability : report.getLiabilities()) {
            if (!liability.getIsPaidOff()) {
                sum += liability.getInstallment();
            }
        }
        return sum;
    }
}
