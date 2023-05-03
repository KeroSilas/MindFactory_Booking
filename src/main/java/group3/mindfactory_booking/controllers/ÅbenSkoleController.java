package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.BookingApplication;
import group3.mindfactory_booking.dao.ForløbDaoImpl;
import group3.mindfactory_booking.model.singleton.Booking;
import group3.mindfactory_booking.model.Forløb;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ÅbenSkoleController {

    private Booking booking;
    private ForløbDaoImpl forløbDao;

    @FXML
    private MFXTextField afgangTF;

    @FXML
    private MFXTextField ankomstTF;

    @FXML
    private MFXComboBox<Forløb> forløbCB;

    @FXML
    private MFXButton næsteBtn;

    @FXML
    private MFXButton tilbageBtn;

    @FXML
    private MFXComboBox<String> transportCB;

    @FXML
    void handleNæste(ActionEvent event) {
        Stage stage;
        Parent root;

        stage = (Stage) næsteBtn.getScene().getWindow();
        try {
            root = FXMLLoader.load(Objects.requireNonNull(BookingApplication.class.getResource("informationOgDato-3-view.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void handleTilbage(ActionEvent event) {
        Stage stage;
        Parent root;

        stage = (Stage) tilbageBtn.getScene().getWindow();
        try {
            root = FXMLLoader.load(Objects.requireNonNull(BookingApplication.class.getResource("bookingType-1-view.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        booking = Booking.getInstance();
        //forløbDao = new ForløbDaoImpl();

        //forløbCB.getItems().addAll(forløbDao.getAllForløb());
        transportCB.getItems().addAll("Lejet bus", "Offentlig transport");
    }

}

