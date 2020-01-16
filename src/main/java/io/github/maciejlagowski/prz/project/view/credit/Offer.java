package io.github.maciejlagowski.prz.project.view.credit;

import io.github.maciejlagowski.prz.project.controller.OfferController;
import io.github.maciejlagowski.prz.project.model.credit.offer.OfferGenerator;
import io.github.maciejlagowski.prz.project.view.View;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.FlowPane;

import java.util.LinkedList;
import java.util.List;

public class Offer implements View {

    private OfferController controller = OfferController.getInstance();

    public Offer(OfferGenerator offerGenerator) {
        controller.setOfferGenerator(offerGenerator);
    }

    public List<Node> createContent() {
        return new LinkedList<>(List.of(
                createSliders(),
                createGetCreditButton()
        ));
    }

    private Node createGetCreditButton() {
        Button button = new Button("Get credit");
        button.setOnAction((event) -> controller.onGetCreditButtonClick());
        return button;
    }

    private Node createSliders() {
        Slider creditAmount = new Slider();
        creditAmount.setMax(controller.getOfferGenerator().getLimit());
        System.out.println("limit: " + controller.getOfferGenerator().getLimit());
        creditAmount.setMin(0.0);
        creditAmount.setValue(1.0);
        Slider installmentAmount = new Slider();
        installmentAmount.setMin(controller.getOfferGenerator().calculateMinInstallment(creditAmount.getValue()));
        installmentAmount.setMax(controller.getOfferGenerator().calculateMaxInstallment(creditAmount.getValue()));
        installmentAmount.setValue(installmentAmount.getMin());
        FlowPane flowPane = new FlowPane();
        Label label = new Label("creditAmount: " + creditAmount.getValue());
        Label label2 = new Label("installmentAmount: " + installmentAmount.getValue());
        creditAmount.valueProperty().addListener((event) -> {
            label.setText(String.valueOf(creditAmount.getValue()));
            if (Double.isNaN(creditAmount.getValue())) {
                creditAmount.setValue(creditAmount.getMax());
            }
            installmentAmount.setMax(controller.getOfferGenerator().calculateMaxInstallment(creditAmount.getValue()));
        });
        installmentAmount.valueProperty().addListener((event) -> {
            label2.setText(String.valueOf(installmentAmount.getValue()));
            if (Double.isNaN(installmentAmount.getValue())) {
                installmentAmount.setValue(installmentAmount.getMax());
            }
        });
        controller.setCreditAmount(creditAmount);
        controller.setInstallmentAmount(installmentAmount);
        flowPane.getChildren().addAll(creditAmount, installmentAmount, label, label2);
        return flowPane;
    }
}
