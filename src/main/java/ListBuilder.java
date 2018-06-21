
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import objects.Unit;
import objects.UnitFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ListBuilder extends Application {
    public static void main(String[] args) {
        launch(args);
    }

//    public static UnitFactory factory;
//    String gameVersion = "testVersion";
//    String factionId = "faction1";

    @Override
    public void start(Stage primaryStage) throws Exception {
//        factory = new UnitFactory(gameVersion, factionId);
//        Unit hero = factory.getUnit("hero1");

        Parent root = FXMLLoader.load(getClass().getResource("WarbandView.fxml"));
        primaryStage.setTitle("ListBuilder");
        primaryStage.setScene(new Scene(root, 3000, 2750));
        primaryStage.show();

    }

}
