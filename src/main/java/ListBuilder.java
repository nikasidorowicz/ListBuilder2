import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import objects.Components;

import java.io.IOException;

public class ListBuilder extends Application {

    private String gameVersion = "testVersion";
    private Components components;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
        components = new Components(gameVersion);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        BorderPane root = FXMLLoader.load(getClass().getResource("ListBuilder.fxml"));
        primaryStage.setTitle("ListBuilder");
        Scene scene = new Scene(root, 400, 400);
//        scene.getStylesheets()

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
