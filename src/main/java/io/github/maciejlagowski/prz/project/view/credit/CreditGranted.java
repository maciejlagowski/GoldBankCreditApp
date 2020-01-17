package io.github.maciejlagowski.prz.project.view.credit;

import io.github.maciejlagowski.prz.project.view.View;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.LinkedList;
import java.util.List;

public class CreditGranted implements View {

//    private OfferController controller = OfferController.getInstance();

//    public CreditGranted(OfferGenerator offerGenerator) {
//        controller.setOfferGenerator(offerGenerator);
//    }

    public List<Node> createContent() {
        Pane pane = new VBox();
        pane.setMinHeight(400);
        pane.setMinWidth(800);
        pane.getChildren().addAll(new Label("Credit granted!"),
                createGetCreditButton());
        pane.setBackground(new Background(new BackgroundImage(new Image("/img/fireworks.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        return new LinkedList<>(List.of(
                pane
        ));
    }

    private Node createGetCreditButton() {
        Button button = new Button("Get credit");
//        button.setOnAction((event) -> controller.onGetCreditButtonClick());
        return button;
    }
}
