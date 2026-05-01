module workshop.project.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens workshop.project to javafx.fxml;
    exports workshop.project;
    exports workshop.project.alexander;
    opens workshop.project.alexander to javafx.fxml, javafx.base;
    opens workshop.project.carl to javafx.fxml, javafx.base;
}