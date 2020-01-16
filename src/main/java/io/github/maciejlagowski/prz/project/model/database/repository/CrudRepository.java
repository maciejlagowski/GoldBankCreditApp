package io.github.maciejlagowski.prz.project.model.database.repository;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.LinkedList;
import java.util.List;

public interface CrudRepository <T> {

    //TODO logger

    Class getEntityType();

    default void createRecord(T object) {
//        Logger logger = LoggerSingleton.getInstance();
        Session session = DbmsOperations.getSession();
        try {
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
//            logger.info("Create " + this.getEntityType().toString() + " transaction committed successfully");
        } catch (Exception sqlException) {
            if(session.getTransaction() != null) {
                session.getTransaction().rollback();
//                logger.error("Error in creating " + this.getEntityType().toString() + " transaction, rolling back changes.");
            }
            sqlException.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }

    default List<T> readAllRecords() {
        Session session = DbmsOperations.getSession();
        List<T> records = new LinkedList<>();
        try {
            session.beginTransaction();
            records = session.createQuery("FROM " + this.getEntityType().getName() ).list();
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
        return records;
    }

    default T findRecordById(Long id) {
        Session session = DbmsOperations.getSession();
        T foundRecord = null;
        try {
            session.beginTransaction();
            foundRecord = (T) session.get(this.getEntityType(), id);
//            session.getTransaction().commit();
        } catch (Exception sqlException) {
            if(session.getTransaction() != null) {
//                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(session != null && session.isOpen()) {
//                session.close();
            }
        }
        return foundRecord;
    }

    default void updateRecord(T object) {
        Session session = DbmsOperations.getSession();
        try {
            session.beginTransaction();
            session.update(object);
            session.getTransaction().commit();
        } catch (Exception sqlException) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    default void deleteRecordById(Long id) {
        //TODO
    }

    default void deleteRecord(T object) {
        //TODO
    }

    default void deleteAllRecords() {
        Session session = DbmsOperations.getSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("DELETE FROM " + this.getEntityType().toString());
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
}
