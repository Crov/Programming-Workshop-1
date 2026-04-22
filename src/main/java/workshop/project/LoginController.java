package workshop.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginController {
    private static final Map<String, String> users = new HashMap<>();
    static {
        users.put("Carl", "123");
        users.put("Alex", "123");
    }

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label loginText;

    @FXML
    protected void Login() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            loginText.setText("Enter your username and password");
            return;
        }

        String user = users.get(username);
        if (user == null) {
            loginText.setText("Username not found");
            return;
        }

        if (!user.equals(password)) {
            loginText.setText("Incorrect password");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();

            Stage homeStage = new Stage();
            Scene scene = new Scene(root, 600, 400);
            homeStage.setTitle("Home Page");
            homeStage.setResizable(true);
            homeStage.setMaximized(true);
            homeStage.setScene(scene);
            homeStage.show();

            Stage loginStage = (Stage) usernameField.getScene().getWindow();
            loginStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            loginText.setText("Error: Couldn't load Home Page");
        }
    }
}
