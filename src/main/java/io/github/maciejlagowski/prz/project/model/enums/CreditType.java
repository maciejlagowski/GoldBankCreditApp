package io.github.maciejlagowski.prz.project.model.enums;

import io.github.maciejlagowski.prz.project.model.tools.Helpers;

import java.util.LinkedList;
import java.util.List;

public enum CreditType {
    RETAIL, MORTGAGE, ENTREPRENEUR;

    public static List<String> getAll() {
        List<String> types = new LinkedList<>();
        for (CreditType type : CreditType.values()) {
            types.add(Helpers.capitalizeString(type.name()));
        }
        return types;
    }
}
