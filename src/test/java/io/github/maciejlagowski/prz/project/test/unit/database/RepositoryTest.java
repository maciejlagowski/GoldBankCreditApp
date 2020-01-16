package io.github.maciejlagowski.prz.project.test.unit.database;

import io.github.maciejlagowski.prz.project.model.database.entity.Client;
import io.github.maciejlagowski.prz.project.model.database.entity.Credit;
import io.github.maciejlagowski.prz.project.model.database.entity.CreditApplication;
import io.github.maciejlagowski.prz.project.model.database.entity.Income;
import io.github.maciejlagowski.prz.project.model.database.repository.*;
import io.github.maciejlagowski.prz.project.model.enums.CreditType;
import io.github.maciejlagowski.prz.project.model.enums.Industry;
import io.github.maciejlagowski.prz.project.model.enums.Risk;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RepositoryTest {

    private CrudRepository<Client> clientRepo = new ClientRepository();
    private CrudRepository<Credit> creditRepo = new CreditRepository();
    private CrudRepository<CreditApplication> creditApplicationRepo = new CreditApplicationRepository();
    private CrudRepository<Income> incomeRepo = new IncomeRepository();

    @Test
    public void test1Income() {
        Income income = new Income(1L, 2000.0, "CompanyTest", Industry.COMPUTER);
        incomeRepo.createRecord(income);
        Income incomeFromDb = incomeRepo.findRecordById(1L);
        Assert.assertEquals(income.getId(), incomeFromDb.getId());
        Assert.assertEquals(income.getAmount(), incomeFromDb.getAmount());
        Assert.assertEquals(income.getCompanyName(), incomeFromDb.getCompanyName());
        Assert.assertEquals(income.getIndustry(), incomeFromDb.getIndustry());
    }

    @Test
    public void test2Client() {
        List<Income> incomes = incomeRepo.readAllRecords();
        Client client = new Client(1L, "ForenameTest", "SurnameTest",
                "AddressTest", 88051697473L, incomes, new LinkedList<>());
        clientRepo.createRecord(client);
        Client clientFromDb = clientRepo.findRecordById(1L);
        Assert.assertEquals(client.getId(), clientFromDb.getId());
        Assert.assertEquals(client.getForename(), clientFromDb.getForename());
        Assert.assertEquals(client.getSurname(), clientFromDb.getSurname());
        Assert.assertEquals(client.getAddress(), clientFromDb.getAddress());
        Assert.assertEquals(client.getPesel(), clientFromDb.getPesel());
        Assert.assertEquals(client.getIncomes().get(0), clientFromDb.getIncomes().get(0));
    }

    @Test
    public void test3Credit() {
        Date date = new Date(System.currentTimeMillis());
        Date date1 = new Date(System.currentTimeMillis() + 50000);
        Credit credit = new Credit(1L, 200000.0, 110000.0, 4.3,
                date, date1, null, false, CreditType.MORTGAGE, 200.0, false);
        creditRepo.createRecord(credit);
        Credit creditFromDb = creditRepo.findRecordById(1L);
        Assert.assertEquals(credit.getId(), creditFromDb.getId());
        Assert.assertEquals(credit.getAmountTaken(), creditFromDb.getAmountTaken());
        Assert.assertEquals(credit.getAmountRepaid(), creditFromDb.getAmountRepaid());
        Assert.assertEquals(credit.getMarkup(), creditFromDb.getMarkup());
        Assert.assertEquals(credit.getActualRepaymentDate(), creditFromDb.getActualRepaymentDate());
        Assert.assertEquals(credit.getIsPaidOff(), creditFromDb.getIsPaidOff());
        Assert.assertEquals(credit.getType(), creditFromDb.getType());
    }

    @Test
    public void test4CreditApplication() {
        Client client = clientRepo.findRecordById(1L);
        List<Client> clients = new LinkedList<>();
        clients.add(client);
        CreditApplication application = new CreditApplication(1L,
                new Date(System.currentTimeMillis()), clients, 12, Risk.MEDIUM, null, CreditType.MORTGAGE);
        creditApplicationRepo.createRecord(application);
        CreditApplication applicationFromDb = creditApplicationRepo.findRecordById(1L);
        Assert.assertEquals(application.getId(), applicationFromDb.getId());
//        Assert.assertEquals(application.getApplicationDate(), applicationFromDb.getApplicationDate());
        //TODO date format is different
        Assert.assertEquals(application.getRequestedPeriod(), applicationFromDb.getRequestedPeriod());
        Assert.assertEquals(application.getRisk(), applicationFromDb.getRisk());
    }
}
