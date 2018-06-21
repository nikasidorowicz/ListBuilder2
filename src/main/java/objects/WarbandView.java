package objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;


public class WarbandView implements HierarchicalController<MainController>{

    public ComboBox <String> factionCommanders;
    public ComboBox  <String> factionWarriors;
    public ComboBox <Integer> number;
    public TextField sumPoints;
    public TextField sumModels;
    public Button add;
    public Button addChange;
    public AnchorPane pane;
    private MainController parentController;


//
//    @BeforeAll
//    public void initFaction() {
//        String gameVersion = "testVersion";
//        String factionId = "faction1";
//        try {
//            faction = new Faction(gameVersion, factionId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    Warband warband =  new Warband(faction);


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
        parentController.loadUnit(actionEvent);
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


}
