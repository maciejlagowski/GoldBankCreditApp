package io.github.maciejlagowski.prz.project.model.database.repository;

import io.github.maciejlagowski.prz.project.view.Error;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.LinkedList;
import java.util.List;

public interface CrudRepository <T> {

    Class getEntityType();

    default void createRecord(T object) {
        Session session = DbmsOperations.getSession();
        try {
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
        } catch (Exception sqlException) {
            if(session.getTransaction() != null) {
                session.getTransaction().rollback();
                new Error("Error in creating " + this.getEntityType().toString() + " transaction, rolling back changes.").showStage();
            }
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
                new Error("Error in reading all records transaction, rolling back.").showStage();
            }
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
        } catch (Exception sqlException) {
            if(session.getTransaction() != null) {
                new Error("Error in reading record").showStage();
                session.getTransaction().rollback();
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
                new Error("Error in updating record, rolling back.").showStage();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    default void deleteRecordById(Long id) {
        Session session = DbmsOperations.getSession();
        try {
            session.beginTransaction();
            session.delete(findRecordById(id));
            session.getTransaction().commit();
        } catch (Exception sqlException) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
                new Error("Error in deleting record, rolling back.").showStage();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    default void deleteRecord(T object) {
        Session session = DbmsOperations.getSession();
        try {
            session.beginTransaction();
            session.delete(object);
            session.getTransaction().commit();
        } catch (Exception sqlException) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
                new Error("Error in deleting record, rolling back.").showStage();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    default void deleteAllRecords() {
        Session session = DbmsOperations.getSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("DELETE FROM " + this.getEntityType().toString());
            query.executeUpdate();
            session.getTransaction().commit();
        } catch(Exception sqlException) {
            if(session.getTransaction() != null) {
                new Error("Error in deleting all records transaction, rolling back.").showStage();
                session.getTransaction().rollback();
            }
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }
}
