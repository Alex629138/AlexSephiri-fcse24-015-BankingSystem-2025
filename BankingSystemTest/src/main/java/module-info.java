module com.ooadassignment.bankingsystemtest {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires javafx.graphics;
    requires java.desktop;

    opens com.ooadassignment.bankingsystemtest to javafx.fxml;
    opens com.ooadassignment.bankingsystemtest.controller to javafx.fxml;
    exports com.ooadassignment.bankingsystemtest;
    exports com.ooadassignment.bankingsystemtest.controller;
}