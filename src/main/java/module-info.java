module workshop.project.project {
    requires javafx.controls;
    requires javafx.fxml;


    opens workshop.project to javafx.fxml;
    exports workshop.project;
}