package objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;

public class DataContainer {
    @Getter @Setter protected ObservableList<Unit> unites;
    //@Getter @Setter protected ObservableList <Model> models;

    public DataContainer() {
        unites = FXCollections.observableArrayList();
        //models = FXCollections.observableArrayList();
    }



}
