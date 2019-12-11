package io.github.maciejlagowski.prz.project.model.credit.risk;

import io.github.maciejlagowski.prz.project.model.bik.BikApi;
import io.github.maciejlagowski.prz.project.model.database.entity.Client;
import io.github.maciejlagowski.prz.project.model.enums.Risk;

import java.util.LinkedList;
import java.util.List;

public class RiskCalc {

    public Risk calculateRisk(List<Client> clients) {
        List<Risk> clientsRisk = new LinkedList<>();
        clients.forEach(client -> clientsRisk.add(calculateClientRisk(client)));
        return Risk.getBestRisk(clientsRisk);
    }

    private Risk calculateClientRisk(Client client) {
        List<Risk> clientRisks = new LinkedList<>();
        clientRisks.add(new Incomes().calculateRisk(client.getIncomes()));
        //TODO credit history (with default)
        clientRisks.add(new Bik().calculateRisk(new BikApi("123456789").getBikReport(client.getPesel())));
        return Risk.calculateAverageRisk(clientRisks);
    }
}
