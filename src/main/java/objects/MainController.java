package objects;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import lombok.Getter;

import java.io.IOException;

public class MainController implements HierarchicalController<MainController> {

    @Getter
    protected DataContainer dataContainer;

    public Pane pane;

    @Override
    public MainController getParentController() {
        return this;
    }

    @Override
    public void setParentController(MainController parent) {

    }


    private void loadIntoPane(String fxml) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        try {
            final BorderPane load = loader.load();
            pane.getChildren().clear();
            pane.getChildren().add(load);
            HierarchicalController<MainController> daneController = loader.getController();
            daneController.setParentController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public MainController() {
        dataContainer = new DataContainer();
    }


}
