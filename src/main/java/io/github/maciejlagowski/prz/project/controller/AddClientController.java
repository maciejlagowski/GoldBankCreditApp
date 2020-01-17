package io.github.maciejlagowski.prz.project.controller;

import com.dlsc.formsfx.model.structure.Form;
import io.github.maciejlagowski.prz.project.model.database.entity.Client;
import io.github.maciejlagowski.prz.project.model.database.entity.Income;
import io.github.maciejlagowski.prz.project.model.database.repository.ClientRepository;
import io.github.maciejlagowski.prz.project.model.tools.BackgroundTaskRunner;
import io.github.maciejlagowski.prz.project.view.Error;
import io.github.maciejlagowski.prz.project.view.client.AddIncome;
import io.github.maciejlagowski.prz.project.view.client.ManageClients;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class AddClientController extends Controller {

    private SimpleStringProperty forenameProperty = new SimpleStringProperty("");
    private SimpleStringProperty surnameProperty = new SimpleStringProperty("");
    private SimpleStringProperty addressProperty = new SimpleStringProperty("");
    private SimpleStringProperty peselProperty = new SimpleStringProperty("");
    private Form clientForm;
    private ObservableList<Income> incomes = FXCollections.observableArrayList();
    private Stage stage;
    private ListView<Income> incomeListView;
    private Label incomesSum = new Label("Incomes sum: 0.0");

    public void onAddClientButtonClick() throws Error {
        clientForm.persist();
        try {
            Client client = new Client(null, forenameProperty.get(), surnameProperty.get(),
                    addressProperty.get(), Long.parseLong(peselProperty.get()), incomes, new LinkedList<>());
            new BackgroundTaskRunner(() -> {
                ClientRepository clientRepository = new ClientRepository();
                clientRepository.createRecord(client);
                List<Client> clients = clientRepository.readAllRecords();
                Platform.runLater(() -> FrameController.getInstance().changeView(new ManageClients(clients)));
            }).start();
        } catch (NumberFormatException e) {
            throw new Error("Data not valid");
        }
    }

    public void addIncomeButtonClicked() {
        new AddIncome().showStage();
        recalculateIncomesSum();
    }

    public void recalculateIncomesSum() {
        Double sum = 0.0;
        for (Income income : incomes) {
            sum += income.getAmount();
        }
        incomesSum.setText("Incomes sum: " + sum);
    }

    public void removeIncomeButtonClicked() {
        incomes.remove(incomeListView.getSelectionModel().getSelectedItem());
        recalculateIncomesSum();
    }
}
