package io.github.maciejlagowski.prz.project.view.credit;

import io.github.maciejlagowski.prz.project.controller.OfferController;
import io.github.maciejlagowski.prz.project.model.database.entity.CreditApplication;
import io.github.maciejlagowski.prz.project.view.View;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;

import java.util.List;

public class OfferView implements View {

    private final OfferController controller;

    public OfferView(CreditApplication creditApplication) {
        controller = new OfferController(creditApplication);
    }

    public List<Node> createContent() {
        return List.of(
                createCreditAmountSlider(),
                createCreditAmountLabel(),
                createInstallmentAmountSlider(),
                createInstallmentAmountLabel(),
                createFullCreditCostLabel(),
                createRepaymentPeriodLabel(),
                createGetCreditButton(),
                createErrorLabel()
        );
    }

    public Node createErrorLabel() {
        Label label = new Label("Installment is bigger than capacity");
        label.setTextFill(Color.web("FF0000"));
        label.setVisible(false);
        controller.setErrorLabel(label);
        return label;
    }

    public Node createGetCreditButton() {
        Button button = new Button("Get credit");
        button.setOnAction((event) -> controller.onGetCreditButtonClick());
        controller.setGetCreditButton(button);
        return button;
    }

    public Node createCreditAmountLabel() {
        Label label = new Label("Credit amount: " + Math.round(controller.getCreditAmount().getValue()));
        controller.setCreditAmountLabel(label);
        return label;
    }

    public Node createInstallmentAmountLabel() {
        Label label = new Label("Installment amount: " + Math.round(controller.getInstallmentAmount().getValue()));
        controller.setInstallmentAmountLabel(label);
        return label;
    }

    public Node createCreditAmountSlider() {
        Slider creditAmount = new Slider();
        creditAmount.setMax(controller.getLimit());
        creditAmount.setMin(0.0);
        creditAmount.setValue(1.0);
        creditAmount.valueProperty().addListener((event) -> controller.onCreditAmountSliderChange());
        controller.setCreditAmount(creditAmount);
        return creditAmount;
    }

    public Node createInstallmentAmountSlider() {
        Slider installmentAmount = new Slider();
        installmentAmount.setMin(controller.getMinInstallmentAmount());
        installmentAmount.setMax(controller.getMaxInstallmentAmount());
        installmentAmount.setValue(installmentAmount.getMin());
        installmentAmount.valueProperty().addListener((event) -> controller.onInstallmentAmountSliderChange());
        controller.setInstallmentAmount(installmentAmount);
        return installmentAmount;
    }

    public Node createFullCreditCostLabel() {
        controller.setFullCreditCost(controller.calculateFullCreditCost());
        Label label = new Label("Full credit cost: " + Math.round(controller.getFullCreditCost()) + " PLN");
        controller.setFullCreditCostLabel(label);
        return label;
    }

    public Node createRepaymentPeriodLabel() {
        Label label = new Label("Repayment period: " + 0 + " months");
        controller.setRepaymentPeriodLabel(label);
        return label;
    }
}
