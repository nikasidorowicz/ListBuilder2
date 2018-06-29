package controllers;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import lombok.Getter;
import lombok.Setter;
import objects.ArmyList;
import objects.Components;
import objects.Faction;

import java.io.IOException;
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
    private Button addWarbandButton;
    @FXML
    private TabPane warbandsPane;

    private Components components;
    private ArmyList armyList;

    public ListBuilderController() {
        String gameVersion = "testVersion";
        setComponents(new Components(gameVersion));
    }

    public void initialize() throws IOException {
        setupPointsLimitListener();
        updateSideOfConflictComboBox();
        setupSideOfConflictListener();

        // Load list summary
        Text testSummary = new Text("No side of conflict!");
        testSummary.setFill(Color.RED);
        getListSummary().getChildren().add(testSummary);
    }

    // If anything is done here, it overrides ChangeListener of sideOfConflict combo box. Function is bound to fxml.
    @FXML
    public void changeSideOfConflict() {
//        getListSummary().getChildren().add(new Text("HOW ABOUT THAT..."));
    }

    private void setupSideOfConflictListener() {
        getPointsLimit().setText("0");
        getSideOfConflict().valueProperty().addListener((observable, oldValue, newValue) -> {
            int points = Integer.parseInt(getPointsLimit().getText());
            setArmyList(new ArmyList(newValue, points, getComponents()));
            updateSummary();

            updateAvailableAlliesComboBox();

            getListSummary().getChildren().add(new Text(String.format("%s -> %s\n", oldValue, newValue)));
        });
    }

    // https://stackoverflow.com/questions/7555564/what-is-the-recommended-way-to-make-a-numeric-textfield-in-javafx
    private void setupPointsLimitListener() {
        // TODO: do something about numbers that start with 0 (regex?)
        getPointsLimit().textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*"))
                getPointsLimit().setText(newValue.replaceAll("[^\\d]", ""));
            if (newValue.equals(""))
                getPointsLimit().setText("0");
            if (getArmyList() != null) {
                getArmyList().setPointsLimit(Integer.parseInt(newValue));
                updateSummary();
            }
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

    private void updateSummary() {
        Text summary = new Text(getArmyList().getSummary());
        getListSummary().getChildren().clear();
        getListSummary().getChildren().add(summary);
    }

    @FXML
    private void addWarband(ActionEvent actionEvent) throws IOException {
        String sideOfConflict = getSideOfConflict().getValue();
        Faction faction = getComponents().getFactionMap().get(sideOfConflict).get(getAvailableAllies().getValue());
        getArmyList().addWarband(faction);

        // Add warband tab
        // TODO: fit tab to enclosing pane...
        Tab warbandTab = FXMLLoader.load(getClass().getResource("../Warband.fxml"));
        String warbandName = String.format("Warband %s", getArmyList().getWarbands().size());
        warbandTab.setText(warbandName);
        getWarbandsPane().getTabs().add(warbandTab);
    }

    @Override
    public ListBuilderController getParentController() {
        return this;
    }

    @Override
    public void setParentController(HierarchicalController parent) {

    }


}
