package io.github.maciejlagowski.prz.project.model.database.repository;

import io.github.maciejlagowski.prz.project.model.database.entity.Account;
import org.hibernate.Session;

public class AccountRepository {

    private Session session = DbmsOperations.getSession();

    public void createRecord(Account account) {
        try {
            session.beginTransaction();
            session.save(account);
            session.getTransaction().commit();
            System.out.println("Success!");
        } catch (Exception sqlException) {
            //TODO some logger
            if(session.getTransaction() != null) {
                session.getTransaction().rollback();
                System.err.println("Creating account is being rolled back");
            }
            System.err.println("xx");
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }


}
