package io.github.maciejlagowski.prz.project.controller;

import com.dlsc.formsfx.model.structure.Form;
import io.github.maciejlagowski.prz.project.model.database.entity.Client;
import io.github.maciejlagowski.prz.project.model.database.entity.Income;
import io.github.maciejlagowski.prz.project.model.database.repository.ClientRepository;
import io.github.maciejlagowski.prz.project.model.tools.BackgroundTaskRunner;
import io.github.maciejlagowski.prz.project.view.Error;
import io.github.maciejlagowski.prz.project.view.client.ManageClients;
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

public class AddClientController {

    private static AddClientController instance;
    @Getter
    private SimpleStringProperty forenameProperty = new SimpleStringProperty("");
    @Getter
    private SimpleStringProperty surnameProperty = new SimpleStringProperty("");
    @Getter
    private SimpleStringProperty addressProperty = new SimpleStringProperty("");
    @Getter
    private SimpleStringProperty peselProperty = new SimpleStringProperty("");
    @Setter
    private Form clientForm;
    @Getter
    private ObservableList<Income> incomes = FXCollections.observableArrayList();


    @Getter
    @Setter
    private Stage stage;
    @Getter
    @Setter
    private ListView<Income> incomeListView;
    @Getter
    private Label incomesSum = new Label("Incomes sum: 0.0");


    private AddClientController() {
    }

    public static synchronized AddClientController getInstance() {
        if (instance == null) {
            instance = new AddClientController();
        }
        return instance;
    }

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

    public void recalculateIncomesSum() {
        Double sum = 0.0;
        for (Income income : incomes) {
            sum += income.getAmount();
        }
        incomesSum.setText("Incomes sum: " + sum);
    }
}
