package io.github.maciejlagowski.prz.project.model.database.repository;

import io.github.maciejlagowski.prz.project.view.Error;
import org.hibernate.Session;

import java.util.LinkedList;
import java.util.List;

public interface CrudRepository <T> {

    Class getEntityType();

    default T save(T object) {
        Session session = DbmsOperations.getSession();
        try {
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
        } catch (Exception sqlException) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
                new Error("Error in creating " + this.getEntityType().toString() + " transaction, rolling back changes.").showStage();
            }
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return object;
    }

    default List<T> getAll() {
        Session session = DbmsOperations.getSession();
        List<T> records = new LinkedList<>();
        try {
            session.beginTransaction();
            records = session.createQuery("FROM " + this.getEntityType().getName()).list();
        } catch (Exception sqlException) {
            if (session.getTransaction() != null) {
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

    default T getById(Long id) {
        Session session = DbmsOperations.getSession();
        T foundRecord = null;
        try {
            session.beginTransaction();
            foundRecord = (T) session.get(this.getEntityType(), id);
        } catch (Exception sqlException) {
            if (session.getTransaction() != null) {
                new Error("Error in reading record").showStage();
                session.getTransaction().rollback();
            }
        }
        return foundRecord;
    }

    default T update(T object) {
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
        return object;
    }

    default Long deleteById(Long id) {
        Session session = DbmsOperations.getSession();
        try {
            session.beginTransaction();
            session.delete(getById(id));
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
        return id;
    }

    default T delete(T object) {
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
        return object;
    }
}
