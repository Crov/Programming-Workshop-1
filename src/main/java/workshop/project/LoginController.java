package workshop.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label loginText;

    @FXML protected void Login() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            loginText.setText("Enter your username and password");
            return;
        }

        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                Parent root = loader.load();

                HomeController controller = loader.getController();
                controller.setupAccess(username);

                Stage stage = (Stage) loginText.getScene().getWindow();
                Scene scene = new Scene(root, 1200, 700);
                stage.setTitle("Home Dashboard");
                stage.setScene(scene);
                stage.centerOnScreen();
            } else {
                loginText.setText("Invalid username or password.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            loginText.setText("Database connection error. Is MySQL running?");
        } catch (Exception e) {
            e.printStackTrace();
            loginText.setText("Error loading home dashboard.");
        }
    }
}