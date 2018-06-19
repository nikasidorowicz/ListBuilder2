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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: add copies support for cleaner Warband view.
@Data
public class Unit {

    private String id;
    private String name;
    private int points;
    private int figuresCount;
    private int unitCount;

    private List<Figure> figures;
    private Map<String, Integer> options;
    private Map<String, Integer> bought;
    private Mount mount;
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
        for (int optionCost : getBought().values())
            points += optionCost;
        return getPoints() + points;
    }

    public List<Model> getModels() {
        List<Model> models = new ArrayList<>();
        for (Figure figure : getFigures())
            models.add(figure.getModel());
        if (getMount() != null)
            models.add(getMount().getModel());
        return models;
    }

    // TODO: handle problem of multiple figures with different wargear.
    public List<String> getAllWargear() {
        List<String> wargear = new ArrayList<>();
        wargear.addAll(getFigures().get(0).getWargears());
        wargear.addAll(getBought().keySet());
        return wargear;
    }

    public void buyOption(String id) throws Exception {
        for (Map.Entry<String, Integer> option : options.entrySet())
            if (option.getKey().equals(id)) {
                getBought().put(option.getKey(), option.getValue());
                return;
            }
        throw new Exception("Wrong option name!");
    }

    public void removeOption(String name) throws Exception {
        for (String optionName : getBought().keySet())
            if (optionName.equals(name)) {
                getBought().remove(optionName);
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
    public void clear() {
        setMount(null);
        setBought(new HashMap<>());
    }

}



}