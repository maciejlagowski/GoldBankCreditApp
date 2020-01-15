package io.github.maciejlagowski.prz.project.model.tools;

import com.google.common.base.CaseFormat;

public class Helpers {
    public static String capitalizeString(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    public static String camelCaseToUpperSnakeCase(String input) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, input);
    }
}
