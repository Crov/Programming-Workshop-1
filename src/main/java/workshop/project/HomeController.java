package workshop.project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
        } else if (username.equals("Alex")) {
            booksButton.setDisable(true);
            employeesButton.setDisable(false);
        }
    }
    @FXML protected void openBooks() {
        System.out.println("Opening Books Management...");
    }
    @FXML protected void openEmployees() {
        System.out.println("Opening Employees Management...");
    }
}
