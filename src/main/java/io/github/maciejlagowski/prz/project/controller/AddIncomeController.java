package io.github.maciejlagowski.prz.project.controller;

import com.dlsc.formsfx.model.structure.Form;
import io.github.maciejlagowski.prz.project.model.database.entity.Income;
import io.github.maciejlagowski.prz.project.model.database.repository.IncomeRepository;
import io.github.maciejlagowski.prz.project.model.enums.Industry;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;

public class AddIncomeController {

    private static AddIncomeController instance;
    @Getter
    private SimpleDoubleProperty amountProperty = new SimpleDoubleProperty(0.0);
    @Getter
    private SimpleStringProperty companyNameProperty = new SimpleStringProperty("");
    @Setter
    private Form incomeForm;
    @Setter
    private Industry companyIndustryProperty;


    private AddIncomeController() {
    }

    public static synchronized AddIncomeController getInstance() {
        if (instance == null) {
            instance = new AddIncomeController();
        }
        return instance;
    }

    public void onAddIncomeButtonClick() {
        incomeForm.persist();
        Income income = new Income(null, amountProperty.get(), companyNameProperty.get(), companyIndustryProperty);
        new IncomeRepository().createRecord(income);
        AddClientController.getInstance().getIncomes().add(income);
    }
}
