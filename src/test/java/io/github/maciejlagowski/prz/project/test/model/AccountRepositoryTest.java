package io.github.maciejlagowski.prz.project.test.model;

import io.github.maciejlagowski.prz.project.model.database.entity.Account;
import io.github.maciejlagowski.prz.project.model.database.entity.Client;
import io.github.maciejlagowski.prz.project.model.database.repository.AccountRepository;
import io.github.maciejlagowski.prz.project.model.database.repository.CrudRepository;
import org.junit.Assert;
import org.junit.Test;

public class AccountRepositoryTest {

    //TODO remember that this tests resets the table!!
    @Test
    public void testCreateAndReadRecord() {
        Client client = new Client(Long.parseLong("0"), "ForenameTest", "SurnameTest", "AddressTest", Long.parseLong("88051697473"), null, null);
        Account account = new Account(Long.parseLong("1"), Long.parseLong("1"), 15.20);
        CrudRepository<Account> repo = new AccountRepository();
        ((AccountRepository) repo).resetTable();
        repo.createRecord(account);
        Account accountFromDb = repo.findRecordById(Long.parseLong("1"));
        Assert.assertEquals(account, accountFromDb);
    }
}
