package objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;
import lombok.var;

import java.awt.event.MouseEvent;
import java.text.ParseException;



public class UnitView {


//    public static UnitFactory factory;
//    String gameVersion = "testVersion";
//    String factionId = "faction1";
    @Getter @Setter private Unit unit;


    @FXML private TextField nameHeader;
    @FXML private TextField pointsHeader;
//    @FXML private CheckBox o1;
//    @FXML private CheckBox o2;
//    @FXML private CheckBox o3;
//    @FXML private CheckBox o4;
//    @FXML private CheckBox o5;
//    @FXML private CheckBox o6;
//    @FXML private CheckBox o7;
//    @FXML private CheckBox o8;
//    @FXML private CheckBox o9;
//    @FXML private CheckBox o10;

    // ObservableList<CheckBox> checkboxes;
    @FXML private TextField wargear;
    @FXML private TextField w1;
    @FXML private TextField w2;
    @FXML private TextField w3;
    @FXML private TextField w4;
    @FXML private TextField w5;
    @FXML private TextField w6;
    @FXML private TextField w7;
    @FXML private TextField w8;
    @FXML private TextField w9;
    @FXML private TextField w10;

//
//    @FXML private TextField specialRules;
//    @FXML private TextField sr1;
//    @FXML private TextField sr2;
//    @FXML private TextField sr3;
//    @FXML private TextField sr4;
//    @FXML private TextField sr5;
//    @FXML private TextField sr6;
//    @FXML private TextField sr7;
//    @FXML private TextField sr8;
//    @FXML private TextField sr9;
//    @FXML private TextField sr10;


    @FXML private ChoiceBox<String> hm;
    @FXML private ComboBox<String> dajesz;
    @FXML private TextField attributeName;
    @FXML private Button baton;

    @FXML private TableView<Model> tablicaModeli;


    public void initialize() throws ParseException {

//        factory = new UnitFactory(gameVersion, factionId);
//        Unit unit = factory.getUnit("hero1");

        nameHeader.setText(unit.getNameHeader());
        pointsHeader.setText(unit.getPointsHeader());

        ObservableList<Model> models;
        models = FXCollections.observableArrayList();
        unit.getFigures().forEach((x) -> models.add(x.getModel()));
        System.out.println(models);
        tablicaModeli.setItems(models);

        ObservableList<String> tere;
        tere = FXCollections.observableArrayList();
        unit.getOptions().forEach((x, y) -> tere.add(x + "  (" + Integer.toString(y) + "points) "));
        dajesz.getItems().addAll(tere);

        wargear.setText("Wargear: ");
        ObservableList<TextField> w;
        w = FXCollections.observableArrayList();
        w.addAll(w1, w2, w3, w4, w5, w6, w7, w8, w9, w10);
        w.forEach((x) -> x.setVisible(false));

        ObservableList<String> wargears;
        wargears = FXCollections.observableArrayList(unit.getAllWargear());

        for (int i = 0; i < wargears.size(); i++) {
            w.get(i).setText(wargears.get(i));
            w.get(i).setVisible(true);
        }


//        specialRules.setText("Special Rules: ");
//        ObservableList<TextField> sr;
//        sr = FXCollections.observableArrayList();
//        sr.addAll(sr1,sr2,sr3,sr4,sr5,sr6,sr7,sr8,sr9,sr10);
//        sr.forEach((x)-> x.setVisible(false));

        //ObservableList<String> specialRulesList;
        //specialRulesList = FXCollections.observableArrayList(unit.getFigures());

        for (int i = 0; i < wargears.size(); i++) {
            w.get(i).setText(wargears.get(i));
            w.get(i).setVisible(true);
        }


        //unit.getAllWargear().forEach((x)-> System.out.println(x));
        // wargears = unit.getAllWargear();


        for (TableColumn<Model, ?> modelTableColumn : tablicaModeli.getColumns()) {
            if ("name".equals(modelTableColumn.getId())) {
                TableColumn<Model, String> nameColumn = (TableColumn<Model, String>) modelTableColumn;
                nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                //nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            } else if ("move".equals(modelTableColumn.getId())) {
                TableColumn<Model, String> moveColumn = (TableColumn<Model, String>) modelTableColumn;
                moveColumn.setCellValueFactory(new PropertyValueFactory<>("move"));
                //moveColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            } else if ("fight".equals(modelTableColumn.getId())) {
                TableColumn<Model, String> fightColumn = (TableColumn<Model, String>) modelTableColumn;
                fightColumn.setCellValueFactory(new PropertyValueFactory<>("fight"));
                //fightColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            } else if ("strength".equals(modelTableColumn.getId())) {
                TableColumn<Model, String> strenghtColumn = (TableColumn<Model, String>) modelTableColumn;
                strenghtColumn.setCellValueFactory(new PropertyValueFactory<>("strength"));
                //strenghtColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            } else if ("defence".equals(modelTableColumn.getId())) {
                TableColumn<Model, String> defenceColumn = (TableColumn<Model, String>) modelTableColumn;
                defenceColumn.setCellValueFactory(new PropertyValueFactory<>("defence"));
                //defenceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            } else if ("attacks".equals(modelTableColumn.getId())) {
                TableColumn<Model, String> attackColumn = (TableColumn<Model, String>) modelTableColumn;
                attackColumn.setCellValueFactory(new PropertyValueFactory<>("attacks"));
                //attackColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            } else if ("wounds".equals(modelTableColumn.getId())) {
                TableColumn<Model, String> woundColumn = (TableColumn<Model, String>) modelTableColumn;
                woundColumn.setCellValueFactory(new PropertyValueFactory<>("wounds"));
                //woundColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            } else if ("courage".equals(modelTableColumn.getId())) {
                TableColumn<Model, String> courageColumn = (TableColumn<Model, String>) modelTableColumn;
                courageColumn.setCellValueFactory(new PropertyValueFactory<>("courage"));
                //courageColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            } else if ("might".equals(modelTableColumn.getId())) {
                TableColumn<Model, String> mightColumn = (TableColumn<Model, String>) modelTableColumn;
                mightColumn.setCellValueFactory(new PropertyValueFactory<>("might"));
                //mightColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            } else if ("will".equals(modelTableColumn.getId())) {
                TableColumn<Model, String> willColumn = (TableColumn<Model, String>) modelTableColumn;
                willColumn.setCellValueFactory(new PropertyValueFactory<>("will"));
                //willColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            } else if ("fate".equals(modelTableColumn.getId())) {
                TableColumn<Model, String> fateColumn = (TableColumn<Model, String>) modelTableColumn;
                fateColumn.setCellValueFactory(new PropertyValueFactory<>("fate"));
                //fateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            }
        }

    }

    @FXML
    public void dzialaj(ActionEvent actionEvent) {
//        endEdit(false);
        System.out.print("Udalo sie!");
        attributeName.setText(nameHeader.getText());
    }
}

