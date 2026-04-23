package workshop.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmployeesStore {
    private static final ObservableList<Employee> employees = FXCollections.observableArrayList();

    static {
        // Load initial dummy data
        employees.add(new Employee("Alexander Tannous", "alexandertannous@email.com", "2006-06-11", "Male", "IT", "5500", "70112233", "Yes"));
        employees.add(new Employee("Joseph Aoun", "josephaoun@email.com", "1964-01-10", "Male", "Administration", "7000", "70223344", "Yes"));
    }

    public static ObservableList<Employee> getEmployees() {
        return employees;
    }
}
