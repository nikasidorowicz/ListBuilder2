package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import lombok.Getter;
import lombok.Setter;
import objects.Components;

public class ListBuilderController implements HierarchicalController {

    @FXML
    private TextFlow listSummary;

    @FXML
    private ChoiceBox<String> sideOfConflict;
    @FXML
    private TextField pointsLimit;

    @Getter
    @Setter
    private Components components;

    public ListBuilderController() {
        String gameVersion = "testVersion";
        setComponents(new Components(gameVersion));
//        pointsLimit = "0";
    }

    @Override
    public ListBuilderController getParentController() {
        return this;
    }

    @Override
    public void setParentController(HierarchicalController parent) {

    }
}
