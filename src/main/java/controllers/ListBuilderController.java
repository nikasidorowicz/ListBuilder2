package controllers;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import lombok.Getter;
import lombok.Setter;
import objects.ArmyList;
import objects.Components;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ListBuilderController implements HierarchicalController {

    @FXML
    private TextFlow listSummary;
    @FXML
    private ChoiceBox<String> sideOfConflict;
    @FXML
    private TextField pointsLimit;
    @FXML
    private ChoiceBox<String> availableAllies;
    @FXML
    private Button addWarband;

    private Components components;
    private ArmyList armyList;

    public ListBuilderController() {
        String gameVersion = "testVersion";
        setComponents(new Components(gameVersion));

//        FXMLLoader listSummaryLoader = new FXMLLoader(getClass().getResource("ListSummary.fxml"));
    }

    public void initialize() {
        setPointsLimitListener();
        getPointsLimit().setText("0");
        updateSideOfConflictComboBox();

        // Setup side of conflict listener
        getSideOfConflict().valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                int points = Integer.parseInt(pointsLimit.toString());
                setArmyList(new ArmyList(newValue, 100, getComponents()));

                updateAvailableAlliesComboBox();

                getListSummary().getChildren().add(new Text(String.format("%s -> %s\n", oldValue, newValue)));
            }
        });

        // Load list summary
        Text testSummary = new Text("THIS IS SOME SUMMARY\n");
        testSummary.setFill(Color.RED);
        testSummary.setFont(Font.font("Helvetica", FontPosture.ITALIC, 20));
        getListSummary().getChildren().add(testSummary);
    }

    // If anything is done here, it overrides ChangeListener of sideOfConflict combo box. Function is bound to fxml.
    @FXML
    public void changeSideOfConflict() {
//        getListSummary().getChildren().add(new Text("HOW ABOUT THAT..."));
    }

    // https://stackoverflow.com/questions/7555564/what-is-the-recommended-way-to-make-a-numeric-textfield-in-javafx
    private void setPointsLimitListener() {
        // TODO: make something about numbers that start with 0 (regex?)
        getPointsLimit().textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*"))
                getPointsLimit().setText(newValue.replaceAll("[^\\d]", ""));
            if (newValue.equals(""))
                getPointsLimit().setText("0");
            getArmyList().setPointsLimit(Integer.parseInt(newValue));
        });
    }

    private void updateSideOfConflictComboBox() {
        Set<String> sideOfConflictSet = getComponents().getSidesOfConflict().keySet();
        List<String> sideOfConflictList = new ArrayList<>(sideOfConflictSet);
        getSideOfConflict().setItems(new ObservableListWrapper<>(sideOfConflictList));
    }

    private void updateAvailableAlliesComboBox() {
        Set<String> availableAlliesSet = getArmyList().getAvailableAllies();
        List<String> availableAlliesList = new ArrayList<>(availableAlliesSet);
        getAvailableAllies().setItems(new ObservableListWrapper<>(availableAlliesList));
    }

    @Override
    public ListBuilderController getParentController() {
        return this;
    }

    @Override
    public void setParentController(HierarchicalController parent) {

    }

}
