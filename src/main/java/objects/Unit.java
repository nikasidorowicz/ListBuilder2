package objects;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import objects.attributes.Wargear;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Data
public class Unit {

    private String name;
    private int points;
    private int unitCount;

    private List<Figure> figures;
    @FXML
    private List<Wargear> options;
    private List<Wargear> bought;



    public String getNameHeader() {
        return String.format("%s(%s)", getName(), getRaces());
    }

    public String getPointsHeader() {
        if (getTotalPoints() != getPoints())
            return String.format("%s(%s)", getPoints(), getTotalPoints());
        else
            return String.valueOf(getPoints());
    }

    private List<String> getRacesList() {
        List<String> races = new ArrayList<>();
        for (Figure figure : getFigures()) races.add(figure.getRace());
        return races;
    }

    private String getRaces() {
        return String.join(", ", getRacesList());
    }

    public int getTotalPoints() {
        int points = 0;
        for (Wargear option : getBought())
            points += option.getPoints();
        return getPoints() + points;
    }

    public void buyOption(String name) throws Exception {
        for (Wargear option : getOptions())
            if (option.getName().equals(name)) {
                getBought().add(option);
                return;
            }
        throw new Exception("Wrong option name!");
    }

    public void removeOption(String name) throws Exception {
        for (Wargear option : getBought())
            if (option.getName().equals(name)) {
                getBought().remove(option);
                return;
            }
        throw new Exception("Wrong option name!");
    }


    @FXML
    private TextField nameHeader;
    @FXML
    private TextField pointsHeader;
    @FXML
    private TextField wargear;
    @FXML
    private TextField specialRules;

    @FXML
    private CheckBox option;
    @FXML
    private TableView<Figure> tablicaFig;


    public void initialize() throws ParseException {
        nameHeader.setText(this.getNameHeader());
        pointsHeader.setText(this.getPointsHeader());
        tablicaFig.setItems((ObservableList<Figure>) figures);
        //wargear.setText(this.);



        for (TableColumn<Figure, ?> figureTableColumn : tablicaFig.getColumns()) {
            if ("".equals(figureTableColumn.getId())) {
                TableColumn<Figure, String> nameColumn = (TableColumn<Figure, String>) figureTableColumn;
                nameColumn.setCellValueFactory(new PropertyValueFactory<>(""));
                //nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            }
            else if ("move".equals(figureTableColumn.getId())) {
                TableColumn<Figure, String> moveColumn = (TableColumn<Figure, String>) figureTableColumn;
                moveColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                //moveColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            }
            else if ("fight".equals(figureTableColumn.getId())) {
                TableColumn<Figure, String> fightColumn = (TableColumn<Figure, String>) figureTableColumn;
                fightColumn.setCellValueFactory(new PropertyValueFactory<>("fight"));
                //fightColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            }
            else if ("strenght".equals(figureTableColumn.getId())) {
                TableColumn<Figure, String> strenghtColumn = (TableColumn<Figure, String>) figureTableColumn;
                strenghtColumn.setCellValueFactory(new PropertyValueFactory<>("strenght"));
                //strenghtColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            }
            else if ("defence".equals(figureTableColumn.getId())) {
                TableColumn<Figure, String> defenceColumn = (TableColumn<Figure, String>) figureTableColumn;
                defenceColumn.setCellValueFactory(new PropertyValueFactory<>("defence"));
                //defenceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            }
            else if ("attack".equals(figureTableColumn.getId())) {
                TableColumn<Figure, String> attackColumn = (TableColumn<Figure, String>) figureTableColumn;
                attackColumn.setCellValueFactory(new PropertyValueFactory<>("attack"));
                //attackColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            }
            else if ("wound".equals(figureTableColumn.getId())) {
                TableColumn<Figure, String> woundColumn = (TableColumn<Figure, String>) figureTableColumn;
                woundColumn.setCellValueFactory(new PropertyValueFactory<>("wound"));
                //woundColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            }
            else if ("courage".equals(figureTableColumn.getId())) {
                TableColumn<Figure, String> courageColumn = (TableColumn<Figure, String>) figureTableColumn;
                courageColumn.setCellValueFactory(new PropertyValueFactory<>("courage"));
                //courageColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            }
            else if ("might".equals(figureTableColumn.getId())) {
                TableColumn<Figure, String> mightColumn = (TableColumn<Figure, String>) figureTableColumn;
                mightColumn.setCellValueFactory(new PropertyValueFactory<>("might"));
                //mightColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            }
            else if ("will".equals(figureTableColumn.getId())) {
                TableColumn<Figure, String> willColumn = (TableColumn<Figure, String>) figureTableColumn;
                willColumn.setCellValueFactory(new PropertyValueFactory<>("will"));
                //willColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            }
            else if ("fate".equals(figureTableColumn.getId())) {
                TableColumn<Figure, String> fateColumn = (TableColumn<Figure, String>) figureTableColumn;
                fateColumn.setCellValueFactory(new PropertyValueFactory<>("fate"));
                //fateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            }
        }
    }



}