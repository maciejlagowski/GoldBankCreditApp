package io.github.maciejlagowski.prz.project.model.database.repository;

import io.github.maciejlagowski.prz.project.model.LoggerSingleton;
import io.github.maciejlagowski.prz.project.model.database.entity.Account;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.util.LinkedList;
import java.util.List;

public class AccountRepository implements CrudRepository<Account> {

    //TODO clean and repair all of it

//    private Logger logger = LoggerSingleton.getInstance();

    @Override
    public void createRecord(Account account) {
        Session session = DbmsOperations.getSession();
        try {
            session.beginTransaction();
            session.save(account);
            session.getTransaction().commit();
//            logger.info("Create account transaction committed successfully, id: " + account.getId());
        } catch (Exception sqlException) {
            if(session.getTransaction() != null) {
                session.getTransaction().rollback();
//                logger.error("Error in creating account transaction, rolling back changes. id: " + account.getId());
            }
            sqlException.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }

    @Override
    public List readAllRecords() {
        Session session = DbmsOperations.getSession();
        List accounts = new LinkedList<>();
        try {
            session.beginTransaction();
            accounts = session.createQuery("FROM Account").list();
        } catch (Exception sqlException) {
            if(session.getTransaction() != null) {
                session.getTransaction().rollback();
//                logger.error("Error in reading accounts transaction, rolling back.");
            }
            sqlException.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return accounts;
    }

    @Override
    public Account findRecordById(Long id) {
        Session session = DbmsOperations.getSession();
        Account foundAccount = null;
        try {
            session.beginTransaction();
            foundAccount = session.load(Account.class, id);
//            session.getTransaction().commit();
        } catch (Exception sqlException) {
            if(session.getTransaction() != null) {
//                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(session != null) {
//                session.close();
            }
        }
        return foundAccount;
    }

    @Override
    public void updateRecord(Account object) {
        //TODO
    }

    @Override
    public void deleteRecordById(Long id) {
        //TODO
    }

    @Override
    public void deleteRecord(Account object) {
        //TODO
    }

    @Override
    public void deleteAllRecords() {
        Session session = DbmsOperations.getSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("DELETE FROM Account");
            query.executeUpdate();
            session.getTransaction().commit();
//            logger.info("\nSuccessfully Deleted All Records From The Database Table!\n");
        } catch(Exception sqlException) {
            if(session.getTransaction() != null) {
//                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }


    /**
     * temp method to test
     */
    @Deprecated
    public void resetTable() {
        Session session = DbmsOperations.getSession();
        try {
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("DROP TABLE Account");
            query.executeUpdate();
            query = session.createSQLQuery("CREATE TABLE Account (" +
                    "    id int(100) NOT NULL AUTO_INCREMENT," +
                    "    client int(100) NOT NULL," +
                    "    balance double NOT NULL DEFAULT 0," +
                    "    PRIMARY KEY(id))");
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception sqlException) {
            if(session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }
}
