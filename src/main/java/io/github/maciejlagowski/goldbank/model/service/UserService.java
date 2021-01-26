package io.github.maciejlagowski.goldbank.model.service;

import org.mindrot.jbcrypt.BCrypt;

public class UserService {

    public boolean login(String username, String password) {
        try {   //TODO from DB, now only simulates sleep
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final String SALT = BCrypt.gensalt();
        final String USERNAME = "admin";
        final String PASSWORD = BCrypt.hashpw("admin", SALT);
        return username.equals(USERNAME) && BCrypt.checkpw(password, PASSWORD);
    }
}
