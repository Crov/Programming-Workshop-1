package workshop.project.alexander;

import javafx.beans.property.SimpleStringProperty;

public class Employee {
    private int id;
    private final SimpleStringProperty name, email, dob, gender, department, salary, phone, fulltime;

    public Employee(int id, String name, String email, String dob, String gender, String department, String salary, String phone, String fulltime) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.dob = new SimpleStringProperty(dob);
        this.gender = new SimpleStringProperty(gender);
        this.department = new SimpleStringProperty(department);
        this.salary = new SimpleStringProperty(salary);
        this.phone = new SimpleStringProperty(phone);
        this.fulltime = new SimpleStringProperty(fulltime);
    }

    public Employee(String name, String email, String dob, String gender, String department, String salary, String phone, String fulltime) {
        this(0, name, email, dob, gender, department, salary, phone, fulltime);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name.get(); }
    public void setName(String name) { this.name.set(name); }
    public SimpleStringProperty nameProperty() { return name; }

    public String getEmail() { return email.get(); }
    public void setEmail(String email) { this.email.set(email); }
    public SimpleStringProperty emailProperty() { return email; }

    public String getDob() { return dob.get(); }
    public void setDob(String dob) { this.dob.set(dob); }
    public SimpleStringProperty dobProperty() { return dob; }

    public String getGender() { return gender.get(); }
    public void setGender(String gender) { this.gender.set(gender); }
    public SimpleStringProperty genderProperty() { return gender; }

    public String getDepartment() { return department.get(); }
    public void setDepartment(String department) { this.department.set(department); }
    public SimpleStringProperty departmentProperty() { return department; }

    public String getSalary() { return salary.get(); }
    public void setSalary(String salary) { this.salary.set(salary); }
    public SimpleStringProperty salaryProperty() { return salary; }

    public String getPhone() { return phone.get(); }
    public void setPhone(String phone) { this.phone.set(phone); }
    public SimpleStringProperty phoneProperty() { return phone; }

    public String getFulltime() { return fulltime.get(); }
    public void setFulltime(String fulltime) { this.fulltime.set(fulltime); }
    public SimpleStringProperty fulltimeProperty() { return fulltime; }
}