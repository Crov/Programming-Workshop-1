package workshop.project.alexander;

public class Employee {
    private String name, email, dob, gender, department, salary, phone, fulltime;

    public Employee(String name, String email, String dob, String gender, String department, String salary, String phone, String fulltime) {
        this.name = name; this.email = email; this.dob = dob; this.gender = gender;
        this.department = department; this.salary = salary; this.phone = phone; this.fulltime = fulltime;
    }
    public String getName() { return name; } public void setName(String name) { this.name = name; }
    public String getEmail() { return email; } public void setEmail(String email) { this.email = email; }
    public String getDob() { return dob; } public void setDob(String dob) { this.dob = dob; }
    public String getGender() { return gender; } public void setGender(String gender) { this.gender = gender; }
    public String getDepartment() { return department; } public void setDepartment(String department) { this.department = department; }
    public String getSalary() { return salary; } public void setSalary(String salary) { this.salary = salary; }
    public String getPhone() { return phone; } public void setPhone(String phone) { this.phone = phone; }
    public String getFulltime() { return fulltime; } public void setFulltime(String fulltime) { this.fulltime = fulltime; }
}
