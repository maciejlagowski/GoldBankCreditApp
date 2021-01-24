package io.github.maciejlagowski.prz.project.controller;

import com.dlsc.formsfx.model.structure.Form;
import io.github.maciejlagowski.prz.project.model.database.entity.Income;
import io.github.maciejlagowski.prz.project.model.database.repository.IncomeRepository;
import io.github.maciejlagowski.prz.project.model.enumeration.Industry;
import io.github.maciejlagowski.prz.project.view.client.AddClientView;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;

public class AddIncomeController implements Controller {

    private final FrameController frameController = FrameController.getInstance();
    private final IncomeRepository incomeRepository = new IncomeRepository();
    @Getter
    private final SimpleDoubleProperty amountProperty = new SimpleDoubleProperty(0.0);
    @Getter
    private final SimpleStringProperty companyNameProperty = new SimpleStringProperty("");
    @Setter
    private Form incomeForm;
    @Setter
    private Industry companyIndustryProperty;

    public Income onAddIncomeButtonClick() {
        incomeForm.persist();
        Income income = Income.builder()
                .amount(amountProperty.get())
                .companyName(companyNameProperty.get())
                .industry(companyIndustryProperty)
                .build();
        income = incomeRepository.save(income);
        AddClientView view = (AddClientView) frameController.getActualView();
        view.getController().getIncomes().add(income);
        return income;
    }
}
