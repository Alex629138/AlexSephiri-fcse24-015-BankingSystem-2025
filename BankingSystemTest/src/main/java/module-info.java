module com.ooadassignment.bankingsystemtest {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens com.ooadassignment.bankingsystemtest to javafx.fxml;
    exports com.ooadassignment.bankingsystemtest;
}