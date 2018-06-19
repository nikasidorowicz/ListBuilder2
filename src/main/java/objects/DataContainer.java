package objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;

public class DataContainer {
    @Getter @Setter protected ObservableList<Figure> figures;
    //@Getter @Setter protected ObservableList <Model> models;

    public DataContainer() {
        figures = FXCollections.observableArrayList();
        //models = FXCollections.observableArrayList();
    }



}
