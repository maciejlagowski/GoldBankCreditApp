package io.github.maciejlagowski.prz.project.controller;

import com.dlsc.formsfx.model.structure.Form;
import io.github.maciejlagowski.prz.project.model.database.entity.Client;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

public class AddClientController implements Controller {

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

    private AddClientController() {
    }

    public static synchronized AddClientController getInstance() {
        if (instance == null) {
            instance = new AddClientController();
        }
        return instance;
    }

    public void onAddClientButtonClick() {
        clientForm.persist();
        Client client = new Client(null, forenameProperty.get(), surnameProperty.get(),
                addressProperty.get(), Long.parseLong(peselProperty.get()), new LinkedList<>(), new LinkedList<>());
        TypeAndClientsController.getInstance().getClients().add(client);
    }
}
