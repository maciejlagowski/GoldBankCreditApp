package io.github.maciejlagowski.prz.project.controller;

import javafx.event.ActionEvent;

public interface Controller {
    default void onNextButtonClick(ActionEvent event) {
    }
}
