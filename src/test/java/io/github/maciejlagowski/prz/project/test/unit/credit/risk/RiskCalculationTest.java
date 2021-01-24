package io.github.maciejlagowski.prz.project.test.unit.credit.risk;

import io.github.maciejlagowski.prz.project.model.database.entity.Income;
import io.github.maciejlagowski.prz.project.model.enumeration.Industry;
import io.github.maciejlagowski.prz.project.model.enumeration.Risk;
import io.github.maciejlagowski.prz.project.model.service.RiskService;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class RiskCalculationTest {

    @Test
    public void incomesTest() {
        List<Income> incomes = new LinkedList<>();
        incomes.add(new Income(1L, 2000.0, "TestCompany", Industry.COMPUTER));
        //avg(2000 - medium; COMPUTER - low) => medium
        incomes.add(new Income(1L, 1000.0, "TestCompany2", Industry.FOOD));
        //avg(1000 - high; FOOD - high) => high
        Risk risk = new RiskService().calculateIncomesRisk(incomes);
        //best of(medium; high) => medium
        Assert.assertEquals(Risk.MEDIUM, risk);
    }
}
