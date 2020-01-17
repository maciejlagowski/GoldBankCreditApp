package io.github.maciejlagowski.prz.project.view;

import io.github.maciejlagowski.prz.project.model.tools.Helpers;

public enum ViewEnum {
    FRAME, HOME, LOGIN, TYPE_AND_CLIENTS, WAIT, MANAGE_CLIENTS, OFFER, CREDIT_GRANTED;

    private static ViewEnum actualView;

    public synchronized static ViewEnum getActualView() {
        return actualView;
    }

    public synchronized static void setActualView(View view) {
        String snakeCase = Helpers.camelCaseToUpperSnakeCase(view.getClass().getSimpleName());
        actualView = ViewEnum.valueOf(snakeCase);
    }
}
