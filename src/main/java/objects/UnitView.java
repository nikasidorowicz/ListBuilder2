package objects;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UnitView implements HierarchicalController<MainController> {

    private MainController parentController;

    @Override
    public MainController getParentController() {
        return parentController;
    }

    @Override
    public void setParentController(MainController parent) {

    }

    @FXML
    private TextField nameHeader;
    @FXML
    private Label pointsHeader;


    public void initialize() {

    }



}


