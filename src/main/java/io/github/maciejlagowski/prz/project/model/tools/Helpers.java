package io.github.maciejlagowski.prz.project.model.tools;

public class Helpers {
    public static String capitalizeString(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}
