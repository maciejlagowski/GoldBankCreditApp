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

    Double calculateCapacity(CreditApplication application) {
        Double capacity = 0.0;
        for (Client client : application.getClients()) {
            capacity += calculateClientCapacity(client);
        }
        return capacity;
    }

    private Double calculateClientCapacity(Client client) {
        Double incomes = calculateClientIncomes(client.getIncomes());
        Double liabilities = calculateClientLiabilities(client.getCredits());
        liabilities += calculateBikLiabilities(new BikApi("123456789").getBikReport(client.getPesel()));
        return incomes - liabilities;
    }

    private Double calculateClientIncomes(List<Income> incomes) {
        return incomes.stream().mapToDouble(Income::getAmount).sum();
    }

    private Double calculateClientLiabilities(List<Credit> liabilities) {
        Double sum = 0.0;
        for (Credit liability : liabilities) {
            if (!liability.getIsPaidOff()) {
                sum += liability.getInstallment();
            }
        }
        return sum;
    }

    private Double calculateBikLiabilities(BikReport report) {
        Double sum = 0.0;
        for (BikLiability liability : report.getLiabilities()) {
            if (!liability.getIsPaidOff()) {
                sum += liability.getInstallment();
            }
        }
        return sum;
    }
}
