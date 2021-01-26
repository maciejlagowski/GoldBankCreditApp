package io.github.maciejlagowski.goldbank.model.service;

import io.github.maciejlagowski.goldbank.model.database.entity.Income;
import io.github.maciejlagowski.goldbank.model.enumeration.Industry;
import io.github.maciejlagowski.goldbank.model.enumeration.Risk;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class RiskServiceTest {

    private final RiskService riskService = new RiskService();

    @Test
    public void incomesTest() {
        List<Income> incomes = new LinkedList<>();
        incomes.add(new Income(1L, 2000.0, "TestCompany", Industry.COMPUTER));
        //avg(2000 - medium; COMPUTER - low) => medium
        incomes.add(new Income(1L, 1000.0, "TestCompany2", Industry.FOOD));
        //avg(1000 - high; FOOD - high) => high
        Risk risk = riskService.calculateIncomesRisk(incomes);
        //best of(medium; high) => medium
        Assert.assertEquals(Risk.MEDIUM, risk);
    }
}
