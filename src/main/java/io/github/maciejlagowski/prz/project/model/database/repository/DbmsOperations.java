package io.github.maciejlagowski.prz.project.model.database.repository;

import io.github.maciejlagowski.prz.project.model.database.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

class DbmsOperations {
    private static SessionFactory sessionFactory;

    private DbmsOperations() {}

    synchronized static Session getSession() {
        if (sessionFactory == null) {
            Configuration config = new Configuration();
            config.addAnnotatedClass(Account.class);
            config.addAnnotatedClass(Client.class);
            config.addAnnotatedClass(Credit.class);
            config.addAnnotatedClass(CreditApplication.class);
            config.addAnnotatedClass(Income.class);
            config.configure("/hibernate.cfg.xml");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
            sessionFactory = config.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory.openSession();
    }
}
