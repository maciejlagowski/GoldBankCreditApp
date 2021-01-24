package io.github.maciejlagowski.prz.project.controller;

import com.dlsc.formsfx.model.structure.Form;
import io.github.maciejlagowski.prz.project.model.database.entity.Client;
import io.github.maciejlagowski.prz.project.model.database.entity.Income;
import io.github.maciejlagowski.prz.project.model.database.repository.ClientRepository;
import io.github.maciejlagowski.prz.project.model.service.BackgroundTaskRunnerService;
import io.github.maciejlagowski.prz.project.view.Error;
import io.github.maciejlagowski.prz.project.view.client.AddIncomeView;
import io.github.maciejlagowski.prz.project.view.client.ManageClientsView;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class AddClientController implements Controller {

    private final FrameController frameController = FrameController.getInstance();
    private final ClientRepository clientRepository = new ClientRepository();

    private final SimpleStringProperty forenameProperty = new SimpleStringProperty("");
    private final SimpleStringProperty surnameProperty = new SimpleStringProperty("");
    private final SimpleStringProperty addressProperty = new SimpleStringProperty("");
    private final SimpleStringProperty peselProperty = new SimpleStringProperty("");
    private final ObservableList<Income> incomes = FXCollections.observableArrayList();
    private final Label incomesSum = new Label("Incomes sum: 0.0");

    @Setter
    private Form clientForm;
    @Setter
    private Stage stage;
    @Setter
    private ListView<Income> incomeListView;

    public Client onAddClientButtonClick() throws Error {
        clientForm.persist();
        Client client = Client.builder()
                .forename(forenameProperty.get())
                .surname(surnameProperty.get())
                .address(addressProperty.get())
                .pesel(Long.parseLong(peselProperty.get()))
                .incomes(incomes)
                .credits(new LinkedList<>())
                .build();
        new BackgroundTaskRunnerService(() -> {
            clientRepository.save(client);
            List<Client> clients = clientRepository.getAll();
            Platform.runLater(() -> frameController.changeView(new ManageClientsView(clients)));
        }).start();
        return client;
    }

    public double addIncomeButtonClicked() {
        new AddIncomeView().showStage();
        return recalculateIncomesSum();
    }

    public double removeIncomeButtonClicked() {
        incomes.remove(incomeListView.getSelectionModel().getSelectedItem());
        return recalculateIncomesSum();
    }

    public double recalculateIncomesSum() {
        double sum = 0.0;
        for (Income income : incomes) {
            sum += income.getAmount();
        }
        incomesSum.setText("Incomes sum: " + sum);
        return sum;
    }
}
