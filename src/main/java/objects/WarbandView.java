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
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.io.IOException;


public class WarbandView implements HierarchicalController<MainController>{

    public ComboBox <String> factionCommanders;
    public ComboBox  <String> factionWarriors;
    public ComboBox <Integer> number;
    public TextField sumPoints;
    public TextField sumModels;
    public Button add;
    public Button addChange;
    public Pane pane;
    public Button proba;
    private MainController parentController;


    String factionId = "faction1";
    String gameVersion = "testVersion";

    private Faction faction = new Faction(gameVersion, factionId);
    Warband warband = new Warband(faction);

    public WarbandView() throws Exception {
    }

    public void initialize () throws Exception {

//        String factionId = "faction1";
//        String gameVersion = "testVersion";
//        faction = new Faction(gameVersion, factionId);
//        Warband warband = new Warband(faction);

//        ObservableList<String> commanders;
//        commanders = FXCollections.observableArrayList();
        factionCommanders.getItems().addAll(warband.getFaction().getAvailableCommanders());
        factionWarriors.getItems().addAll(warband.getFaction().getFactionWarriors());


        ObservableList<Integer> options = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12);
        number.getItems().addAll(options);

    }


    public void addChange(ActionEvent actionEvent) throws Exception {
        warband.chooseCommander(factionCommanders.getValue());
        addChange.setText("Change");
        BorderPane newLoadedPane = FXMLLoader.load(getClass().getClassLoader().getResource("UnitView.fxml"));
        pane.getChildren().add(newLoadedPane);
    }

    public void add(ActionEvent actionEvent) throws Exception {

    }

    public void chooseCommander(ActionEvent actionEvent) {
        String output =  (String) factionCommanders.getValue();
        System.out.println(output);
    }



    @Override
    public MainController getParentController() {
        return parentController;
    }

    @Override
    public void setParentController(MainController parentController) {
        this.parentController = parentController;

    }


    public void loadU(ActionEvent actionEvent) throws IOException {
        BorderPane newLoadedPane = FXMLLoader.load(getClass().getClassLoader().getResource("UnitView.fxml"));
        pane.getChildren().add(newLoadedPane);


       // FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml)
    }
}
