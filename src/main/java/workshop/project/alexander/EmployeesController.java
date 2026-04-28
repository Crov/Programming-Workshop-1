package workshop.project.alexander;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import workshop.project.HomeController;

import java.time.LocalDate;

public class EmployeesController {

    @FXML private TextField nameField, emailField, salaryField, phoneField;
    @FXML private DatePicker datePicker;
    @FXML private RadioButton maleRadio, femaleRadio;
    @FXML private ToggleGroup genderGroup;
    @FXML private ChoiceBox<String> departmentBox;
    @FXML private CheckBox fulltimeCheckBox;
    @FXML private Label formMessage;

    @FXML private TableView<Employee> tableView;
    @FXML private TableColumn<Employee, String> nameCol, emailCol, dobCol, genderCol, deptCol, salaryCol, phoneCol, fulltimeCol;

    @FXML
    public void initialize() {
        departmentBox.getItems().addAll("IT", "HR", "Finance", "Marketing", "Operations", "Administration");
        departmentBox.getSelectionModel().selectFirst();

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        dobCol.setCellValueFactory(new PropertyValueFactory<>("dob"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        deptCol.setCellValueFactory(new PropertyValueFactory<>("department"));
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        fulltimeCol.setCellValueFactory(new PropertyValueFactory<>("fulltime"));

        tableView.setItems(EmployeesStore.getEmployees());

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) populateForm(newSelection);
        });
    }

    @FXML
    protected void addEmployee(ActionEvent event) {
        if (!validateForm()) return;

        String gender = ((RadioButton) genderGroup.getSelectedToggle()).getText();
        String fulltime = fulltimeCheckBox.isSelected() ? "Yes" : "No";

        Employee newEmp = new Employee(
                nameField.getText().trim(), emailField.getText().trim(), datePicker.getValue().toString(),
                gender, departmentBox.getValue(), salaryField.getText().trim(), phoneField.getText().trim(), fulltime
        );

        EmployeesStore.getEmployees().add(newEmp);
        clearForm();
        showMessage("Employee added successfully!", Color.GREEN);
    }

    @FXML
    protected void updateEmployee(ActionEvent event) {
        Employee selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showMessage("Select an employee to update", Color.RED);
            return;
        }
        if (!validateForm()) return;

        selected.setName(nameField.getText().trim());
        selected.setEmail(emailField.getText().trim());
        selected.setDob(datePicker.getValue().toString());
        selected.setGender(((RadioButton) genderGroup.getSelectedToggle()).getText());
        selected.setDepartment(departmentBox.getValue());
        selected.setSalary(salaryField.getText().trim());
        selected.setPhone(phoneField.getText().trim());
        selected.setFulltime(fulltimeCheckBox.isSelected() ? "Yes" : "No");

        tableView.refresh();
        clearForm();
        showMessage("Employee updated successfully!", Color.GREEN);
    }

    @FXML
    protected void deleteEmployee(ActionEvent event) {
        Employee selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            EmployeesStore.getEmployees().remove(selected);
            clearForm();
            showMessage("Employee deleted!", Color.GREEN);
        } else {
            showMessage("Select an employee to delete", Color.RED);
        }
    }

    @FXML
    protected void goBackToHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/workshop/project/home.fxml"));
            Parent root = loader.load();

            HomeController controller = loader.getController();
            controller.setupAccess("Alex");

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1200, 700);
            stage.setTitle("Home Page");
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean validateForm() {
        if (nameField.getText().trim().isEmpty() || emailField.getText().trim().isEmpty() ||
                salaryField.getText().trim().isEmpty() || phoneField.getText().trim().isEmpty() || datePicker.getValue() == null) {
            showMessage("Please fill all fields", Color.RED);
            return false;
        }
        return true;
    }

    private void clearForm() {
        nameField.clear(); emailField.clear(); salaryField.clear(); phoneField.clear();
        datePicker.setValue(null); maleRadio.setSelected(true);
        departmentBox.getSelectionModel().selectFirst(); fulltimeCheckBox.setSelected(true);
        tableView.getSelectionModel().clearSelection();
    }

    private void populateForm(Employee e) {
        nameField.setText(e.getName());
        emailField.setText(e.getEmail());
        if (e.getDob() != null && !e.getDob().isEmpty()) {
            datePicker.setValue(LocalDate.parse(e.getDob()));
        }
        if ("Male".equals(e.getGender())) maleRadio.setSelected(true);
        else femaleRadio.setSelected(true);
        departmentBox.setValue(e.getDepartment());
        salaryField.setText(e.getSalary());
        phoneField.setText(e.getPhone());
        fulltimeCheckBox.setSelected("Yes".equals(e.getFulltime()));
    }

    private void showMessage(String msg, Color color) {
        formMessage.setText(msg);
        formMessage.setTextFill(color);
    }
}