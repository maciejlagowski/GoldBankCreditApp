package io.github.maciejlagowski.prz.project.view.credit;

import io.github.maciejlagowski.prz.project.controller.OfferController;
import io.github.maciejlagowski.prz.project.model.credit.offer.OfferGenerator;
import io.github.maciejlagowski.prz.project.view.View;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.util.LinkedList;
import java.util.List;

public class Offer implements View {

    private OfferController controller = new OfferController();

    public Offer(OfferGenerator offerGenerator) {
        controller.setOfferGenerator(offerGenerator);
    }

    public List<Node> createContent() {
        return new LinkedList<>(List.of(
                createCreditAmountSlider(),
                createCreditAmountLabel(),
                createInstallmentAmountSlider(),
                createInstallmentAmountLabel(),
                createFullCreditCostLabel(),
                createRepaymentPeriodLabel(),
                createGetCreditButton()
        ));
    }

    private Node createGetCreditButton() {
        Button button = new Button("Get credit");
        button.setOnAction((event) -> controller.onGetCreditButtonClick());
        return button;
    }

    private Node createCreditAmountLabel() {
        Label label = new Label("Credit amount: " + Math.round(controller.getCreditAmount().getValue()));
        controller.setCreditAmountLabel(label);
        return label;
    }

    private Node createInstallmentAmountLabel() {
        Label label = new Label("Installment amount: " + Math.round(controller.getInstallmentAmount().getValue()));
        controller.setInstallmentAmountLabel(label);
        return label;
    }

    private Node createCreditAmountSlider() {
        Slider creditAmount = new Slider();
        OfferGenerator offerGenerator = controller.getOfferGenerator();
        creditAmount.setMax(offerGenerator.getLimit());
        creditAmount.setMin(0.0);
        creditAmount.setValue(1.0);
        creditAmount.valueProperty().addListener((event) -> controller.onCreditAmountSliderChange());
        controller.setCreditAmount(creditAmount);
        return creditAmount;
    }

    private Node createInstallmentAmountSlider() {
        Slider installmentAmount = new Slider();
        OfferGenerator offerGenerator = controller.getOfferGenerator();
        installmentAmount.setMin(offerGenerator.calculateMinInstallment(controller.getCreditAmount().getValue()));
        installmentAmount.setMax(offerGenerator.calculateMaxInstallment(controller.getCreditAmount().getValue()));
        installmentAmount.setValue(installmentAmount.getMin());
        installmentAmount.valueProperty().addListener((event) -> controller.onInstallmentAmountSliderChange());
        controller.setInstallmentAmount(installmentAmount);
        return installmentAmount;
    }

    private Node createFullCreditCostLabel() {
        controller.setFullCreditCost(controller.getOfferGenerator().calculateFullCreditCost(controller.getCreditAmount().getValue()));
        Label label = new Label("Full credit cost: " + Math.round(controller.getFullCreditCost()) + " PLN");
        controller.setFullCreditCostLabel(label);
        return label;
    }

    private Node createRepaymentPeriodLabel() {
        Label label = new Label("Repayment period: " + 0 + " months");
        controller.setRepaymentPeriodLabel(label);
        return label;
    }
}
