package objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;

public class DataContainer {
    @Getter @Setter protected ObservableList<Unit> units;


    public DataContainer() {
        units = FXCollections.observableArrayList();
    }



}
