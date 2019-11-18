package io.github.maciejlagowski.prz.project.model.database.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DbmsOperations {
    private static SessionFactory sessionFactory;

    private DbmsOperations() {}

    public static Session getSession() {
        if (sessionFactory == null) {
            Configuration config = new Configuration();
            config.configure("hibernate.cfg.xml");  //TODO this file
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
            sessionFactory = config.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory.openSession();
    }
}
