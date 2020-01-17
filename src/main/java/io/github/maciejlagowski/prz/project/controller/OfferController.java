package io.github.maciejlagowski.prz.project.controller;

import io.github.maciejlagowski.prz.project.model.credit.offer.OfferGenerator;
import io.github.maciejlagowski.prz.project.model.database.entity.Credit;
import io.github.maciejlagowski.prz.project.model.database.entity.CreditApplication;
import io.github.maciejlagowski.prz.project.model.database.repository.ClientRepository;
import io.github.maciejlagowski.prz.project.model.database.repository.CreditRepository;
import io.github.maciejlagowski.prz.project.model.tools.BackgroundTaskRunner;
import io.github.maciejlagowski.prz.project.model.tools.Helpers;
import io.github.maciejlagowski.prz.project.view.credit.CreditGranted;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import lombok.Data;

import java.util.Date;

@Data
public class OfferController extends Controller {

    private OfferGenerator offerGenerator;
    private Slider creditAmount;
    private Slider installmentAmount;
    private Label creditAmountLabel;
    private Label installmentAmountLabel;
    private Double fullCreditCost;
    private Label fullCreditCostLabel;
    private Label repaymentPeriodLabel;
    private int repaymentPeriod;

    public void onGetCreditButtonClick() {
        new BackgroundTaskRunner(() -> {
            CreditApplication application = offerGenerator.getApplication();
            Credit credit = new Credit(null, Helpers.roundDouble(creditAmount.getValue()), 0.0,
                    Helpers.roundDouble(fullCreditCost), application.getApplicationDate(), getPlannedRepaymentDate(),
                    null, false, application.getType(),
                    Helpers.roundDouble(installmentAmount.getValue()), false);
            new CreditRepository().createRecord(credit);
            application.getClients().forEach(client -> {
                client.getCredits().add(credit);
                new ClientRepository().updateRecord(client);
            });
        }).start(new CreditGranted());
    }

    public void onInstallmentAmountSliderChange() {
        if (Double.isNaN(installmentAmount.getValue())) {
            installmentAmount.setValue(installmentAmount.getMax());
        }
        installmentAmountLabel.setText("Installment amount: " + Math.round(installmentAmount.getValue()) + " PLN");
        recalculateRepaymentPeriod();
    }

    public void onCreditAmountSliderChange() {
        if (Double.isNaN(creditAmount.getValue())) {
            creditAmount.setValue(creditAmount.getMax());
        }
        creditAmountLabel.setText("Credit amount: " + Math.round(creditAmount.getValue()) + " PLN");
        fullCreditCost = offerGenerator.calculateFullCreditCost(creditAmount.getValue());
        fullCreditCostLabel.setText("Full credit cost: " + Math.round(fullCreditCost) + " PLN");
        installmentAmount.setMax(offerGenerator.calculateMaxInstallment(creditAmount.getValue()));
        installmentAmount.setMin(offerGenerator.calculateMinInstallment(creditAmount.getValue()));
        recalculateRepaymentPeriod();
    }

    private void recalculateRepaymentPeriod() {
        repaymentPeriod = offerGenerator.calculateRepaymentPeriod(fullCreditCost, installmentAmount.getValue());
        repaymentPeriodLabel.setText("Repayment period: " + repaymentPeriod + " months");
    }

    private Date getPlannedRepaymentDate() {
        Date applicationDate = offerGenerator.getApplication().getApplicationDate();
        return new Date(applicationDate.getTime() + convertMonthsToMilis(repaymentPeriod));
    }

    private Long convertMonthsToMilis(int months) {
        return months * 2592000000L;
    }
}
