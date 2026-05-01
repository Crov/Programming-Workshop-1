package workshop.project.alexander;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import workshop.project.DatabaseConnection;

import java.sql.*;

public class EmployeesStore {

    public static ObservableList<Employee> getEmployeesFromDB() {
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();
        String query = "SELECT * FROM employees";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Employee emp = new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("dob"),
                        rs.getString("gender"),
                        rs.getString("department"),
                        String.valueOf(rs.getDouble("salary")),
                        rs.getString("phone"),
                        rs.getString("fulltime")
                );
                employeeList.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public static void addEmployeeToDB(Employee emp) {
        String query = "INSERT INTO employees (name, email, dob, gender, department, salary, phone, fulltime) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, emp.getName());
            stmt.setString(2, emp.getEmail());
            stmt.setDate(3, Date.valueOf(emp.getDob()));
            stmt.setString(4, emp.getGender());
            stmt.setString(5, emp.getDepartment());
            stmt.setDouble(6, Double.parseDouble(emp.getSalary()));
            stmt.setString(7, emp.getPhone());
            stmt.setString(8, emp.getFulltime());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateEmployeeInDB(Employee emp) {
        String query = "UPDATE employees SET name=?, email=?, dob=?, gender=?, department=?, salary=?, phone=?, fulltime=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, emp.getName());
            stmt.setString(2, emp.getEmail());
            stmt.setDate(3, Date.valueOf(emp.getDob()));
            stmt.setString(4, emp.getGender());
            stmt.setString(5, emp.getDepartment());
            stmt.setDouble(6, Double.parseDouble(emp.getSalary()));
            stmt.setString(7, emp.getPhone());
            stmt.setString(8, emp.getFulltime());
            stmt.setInt(9, emp.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteEmployeeFromDB(Employee emp) {
        String query = "DELETE FROM employees WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, emp.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}