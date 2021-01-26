package io.github.maciejlagowski.goldbank.controller;

import io.github.maciejlagowski.goldbank.model.database.entity.Credit;
import io.github.maciejlagowski.goldbank.model.database.entity.CreditApplication;
import io.github.maciejlagowski.goldbank.model.database.repository.ClientRepository;
import io.github.maciejlagowski.goldbank.model.database.repository.CreditRepository;
import io.github.maciejlagowski.goldbank.model.service.BackgroundTaskRunnerService;
import io.github.maciejlagowski.goldbank.model.service.CapacityService;
import io.github.maciejlagowski.goldbank.model.service.CreditService;
import io.github.maciejlagowski.goldbank.model.service.LimitService;
import io.github.maciejlagowski.goldbank.view.credit.CreditGrantedView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class OfferController implements Controller {

    private final CreditRepository creditRepository = new CreditRepository();
    private final ClientRepository clientRepository = new ClientRepository();
    private final CreditService creditService = new CreditService();
    private final CapacityService capacityService = new CapacityService();
    private final LimitService limitService = new LimitService();
    private final CreditApplication creditApplication;
    private Slider creditAmount;
    private Slider installmentAmount;
    private Label creditAmountLabel;
    private Label installmentAmountLabel;
    private Double fullCreditCost;
    private Label fullCreditCostLabel;
    private Label repaymentPeriodLabel;
    private int repaymentPeriod;
    private Button getCreditButton;
    private Label errorLabel;

    public Credit onGetCreditButtonClick() {
        Credit credit = Credit.builder()
                .amountTaken((double) Math.round(creditAmount.getValue()))
                .amountRepaid(0.0)
                .fullCreditCost((double) Math.round(fullCreditCost))
                .takeDate(creditApplication.getApplicationDate())
                .plannedRepaymentDate(getPlannedRepaymentDate())
                .isPaidOff(false)
                .type(creditApplication.getType())
                .installment(installmentAmount.getValue())
                .isDefault(false)
                .build();
        new BackgroundTaskRunnerService(() -> {
            creditRepository.save(credit);
            creditApplication.getClients().forEach(client -> {
                client.getCredits().add(credit);
                clientRepository.update(client);
            });
        }).start(new CreditGrantedView());
        return credit;
    }

    public long onInstallmentAmountSliderChange() {
        if (Double.isNaN(installmentAmount.getValue())) {
            installmentAmount.setValue(installmentAmount.getMax());
        }
        long installment = Math.round(installmentAmount.getValue());
        installmentAmountLabel.setText("Installment amount: " + installment + " PLN");
        recalculateRepaymentPeriod();
        return installment;
    }

    public double onCreditAmountSliderChange() {
        if (Double.isNaN(creditAmount.getValue())) {
            creditAmount.setValue(creditAmount.getMax());
        }
        creditAmountLabel.setText("Credit amount: " + Math.round(creditAmount.getValue()) + " PLN");
        fullCreditCost = creditService.calculateFullCreditCost(creditAmount.getValue(), creditApplication.getType());
        fullCreditCostLabel.setText("Full credit cost: " + Math.round(fullCreditCost) + " PLN");
        installmentAmount.setMax(creditService.calculateMaxInstallment(creditAmount.getValue(), capacityService.calculateCapacity(creditApplication)));
        installmentAmount.setMin(creditService.calculateMinInstallment(creditAmount.getValue()));
        if (installmentAmount.getMax() <= installmentAmount.getMin()) {
            getCreditButton.setVisible(false);
            errorLabel.setVisible(true);
        } else {
            getCreditButton.setVisible(true);
            errorLabel.setVisible(false);
        }
        recalculateRepaymentPeriod();
        return fullCreditCost;
    }

    public int recalculateRepaymentPeriod() {
        repaymentPeriod = creditService.calculateRepaymentPeriod(fullCreditCost, installmentAmount.getValue());
        repaymentPeriodLabel.setText("Repayment period: " + repaymentPeriod + " months");
        return repaymentPeriod;
    }

    public LocalDate getPlannedRepaymentDate() {
        LocalDate applicationDate = creditApplication.getApplicationDate();
        return applicationDate.plusMonths(repaymentPeriod);
    }

    public double getMinInstallmentAmount() {
        return creditService.calculateMinInstallment(creditAmount.getValue());
    }

    public double getMaxInstallmentAmount() {
        return creditService.calculateMaxInstallment(creditAmount.getValue(), capacityService.calculateCapacity(creditApplication));
    }

    public double getLimit() {
        return limitService.calculateLimit(creditApplication);
    }

    public double calculateFullCreditCost() {
        return creditService.calculateFullCreditCost(creditAmount.getValue(), creditApplication.getType());
    }
}
