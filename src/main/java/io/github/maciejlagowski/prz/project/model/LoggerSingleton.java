package io.github.maciejlagowski.prz.project.model;

import org.apache.log4j.*;

import java.io.IOException;

public class LoggerSingleton {
    private static Logger instance;

    private LoggerSingleton() {
    }

    public static synchronized Logger getInstance() throws IOException {
        if (instance == null) {
            instance = createInstance();
        }
        return instance;
    }

    private synchronized static Logger createInstance() throws IOException {
        //TODO configurate logger
        Layout layout = new PatternLayout("[%p] %c - %m - DateOfLog: %d %n");
        Appender appender;
        appender = new FileAppender(layout, "src/main/resources/log.txt");
        BasicConfigurator.configure(appender);
        return Logger.getRootLogger();
    }
}
