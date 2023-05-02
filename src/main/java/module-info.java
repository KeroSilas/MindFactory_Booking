module group3.mindfactory_booking {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.naming;
    requires com.microsoft.sqlserver.jdbc;
    requires MaterialFX;


    opens group3.mindfactory_booking to javafx.fxml;
    exports group3.mindfactory_booking;
    exports group3.mindfactory_booking.dao;
    opens group3.mindfactory_booking.dao to javafx.fxml;
    exports group3.mindfactory_booking.model;
    opens group3.mindfactory_booking.model to javafx.fxml;
    exports group3.mindfactory_booking.controllers;
    opens group3.mindfactory_booking.controllers to javafx.fxml;
}