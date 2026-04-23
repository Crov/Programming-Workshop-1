package workshop.project;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

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

    @FXML public void initialize() {
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

    @FXML void addEmployee() {
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

    @FXML void updateEmployee() {
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

    @FXML void deleteEmployee() {
        Employee selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            EmployeesStore.getEmployees().remove(selected);
            clearForm();
            showMessage("Employee deleted!", Color.GREEN);
        } else {
            showMessage("Select an employee to delete", Color.RED);
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
    }

    private void populateForm(Employee e) { /* Logic to fill fields from selected Employee */ }

    private void showMessage(String msg, Color color) {
        formMessage.setText(msg);
        formMessage.setTextFill(color);
    }
}
