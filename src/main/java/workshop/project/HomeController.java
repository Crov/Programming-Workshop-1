package workshop.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HomeController {
    @FXML private Label welcomeLabel;
    @FXML private Button booksButton;
    @FXML private Button employeesButton;

    private String currentUser;

    public void setupAccess(String username){
        this.currentUser = username;
        welcomeLabel.setText("Welcome to the Dashboard, " + username + "!");

        if (username.equals("Carl")) {
            booksButton.setDisable(false);
            employeesButton.setDisable(true);
        } else if (username.equals("Alexander")) {
            booksButton.setDisable(true);
            employeesButton.setDisable(false);
        }
    }
    @FXML protected void openBooks() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("book.fxml"));

            Scene scene = new Scene(loader.load(), 900, 600);

            Stage stage = new Stage();
            stage.setMaximized(true);
            stage.setScene(scene);
            stage.setTitle("Book Management");
            stage.show();

            Stage homeStage = (Stage) welcomeLabel.getScene().getWindow();
            homeStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML protected void openEmployees() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("employees.fxml"));
            Stage employeeStage = new Stage();
            employeeStage.setTitle("Manage Employees");
            employeeStage.setScene(new Scene(loader.load(), 900, 600));
            employeeStage.show();

            Stage homeStage = (Stage) welcomeLabel.getScene().getWindow();
            homeStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML protected void logout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root, 350, 400);
            stage.setTitle("Login");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.centerOnScreen();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
