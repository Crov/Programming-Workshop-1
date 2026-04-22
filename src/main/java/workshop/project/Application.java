package workshop.project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 350, 400);
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
}
