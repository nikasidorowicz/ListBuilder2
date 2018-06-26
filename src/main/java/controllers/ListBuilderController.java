package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import objects.Components;

@Data
public class ListBuilderController implements HierarchicalController {

    @FXML
    protected TextFlow listSummary;

    @FXML
    private ChoiceBox<String> sideOfConflict;
    @FXML
    private TextField pointsLimit;

    private Components components;

    public ListBuilderController() {
        String gameVersion = "testVersion";
        setComponents(new Components(gameVersion));

        // Load list summary pane
//        FXMLLoader listSummaryLoader = new FXMLLoader(getClass().getResource("ListSummary.fxml"));

        Text testSummary = new Text("THIS IS SOME SUMMARY");
        testSummary.setFill(Color.RED);
        testSummary.setFont(Font.font("Helvetica", FontPosture.ITALIC, 40));
    }

    public void initialize() {
        Text testSummary = new Text("THIS IS SOME SUMMARY");
        listSummary.getChildren().add(testSummary);
    }

    @Override
    public ListBuilderController getParentController() {
        return this;
    }

    @Override
    public void setParentController(HierarchicalController parent) {

    }
}
