package io.github.maciejlagowski.goldbank.controller;

import javafx.event.ActionEvent;

public interface Controller {
    default void onNextButtonClick(ActionEvent event) {
    }
}
