package objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import lombok.Getter;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.text.ParseException;


public class WarbandView implements HierarchicalController<MainController>{

    public ComboBox <String> factionCommanders;
    public ComboBox  <String> factionWarriors;
    public ComboBox <Integer> number;
    public TextField sumPoints;
    public TextField sumModels;
    public Button add;
    public Button addChange;
    public BorderPane pane;
    private MainController parentController;


    public String gameVersion = "testVersion";
    public String factionId = "faction1";

    Faction faction = new Faction(gameVersion, factionId);
    Warband warband = new Warband(faction);

    public WarbandView() throws Exception {
    }

    public MainController getParentController() {
    return parentController;
}

    @Override
    public void setParentController(MainController parentController) {
        this.parentController = parentController;
        //table.getItems().addAll(parentController.getDataContainer().getCzlowieczeks());
    }

    @Getter
    protected DataContainer dataContainer;

    public DataContainer getDataContainer() {
        return dataContainer;
    }

    public void loadUnit(ActionEvent actionEvent) throws IOException {
        BorderPane newLoadedPane = FXMLLoader.load(getClass().getClassLoader().getResource("UnitView.fxml"));
        pane.getChildren().add(newLoadedPane);
        //parentController.loadUnitView(actionEvent);

    }


    public void initialize () throws ParseException {
        ObservableList<String> commanders;
        commanders = FXCollections.observableArrayList();
        factionCommanders.getItems().addAll(warband.getFaction().getAvailableCommanders());
        factionWarriors.getItems().addAll(warband.getFaction().getFactionWarriors());


        ObservableList<Integer> options = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12);
        number.getItems().addAll(options);

    }

    public void addChange(ActionEvent actionEvent) throws Exception {
        warband.chooseCommander(factionCommanders.getValue());
        addChange.setText("Change");
        loadUnit(actionEvent);


    }

    public void add(ActionEvent actionEvent) throws Exception {

    }

    public void chooseCommander(ActionEvent actionEvent) {
        String output =  (String) factionCommanders.getValue();
        System.out.println(output);
    }

}
