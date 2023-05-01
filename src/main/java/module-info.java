module group3.mindfactory_booking {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;


    opens group3.mindfactory_booking to javafx.fxml;
    exports group3.mindfactory_booking;
}