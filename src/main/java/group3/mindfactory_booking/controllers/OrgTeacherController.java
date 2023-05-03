package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.BookingApplication;
import group3.mindfactory_booking.dao.ActivityDao;
import group3.mindfactory_booking.dao.ActivityDaoImpl;
import group3.mindfactory_booking.dao.EquipmentDao;
import group3.mindfactory_booking.dao.EquipmentDaoImpl;
import group3.mindfactory_booking.model.Activity;
import group3.mindfactory_booking.model.singleton.Booking;
import group3.mindfactory_booking.model.Equipment;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class OrgTeacherController {

    private Booking booking;
    private ActivityDao activityDao;
    private EquipmentDao equipmentDao;

    @FXML
    private MFXComboBox<Activity> aktivitetCB;

    @FXML
    private MFXRadioButton annesofieRB;

    @FXML
    private ToggleGroup assistanceType;

    @FXML
    private MFXRadioButton læringsRB;

    @FXML
    private MFXButton næsteBtn;

    @FXML
    private MFXButton tilbageBtn;

    @FXML
    private MFXComboBox<Equipment> udstyrCB;

    @FXML
    private MFXListView<Equipment> udstyrLV;

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
    void handleSlet(ActionEvent event) {

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

    @FXML
    void handleTilføj(ActionEvent event) {
    }

    @FXML
    void handleTilføj() {
        udstyrLV.getItems().add(udstyrCB.getSelectionModel().getSelectedItem());
    }

    public void initialize() {
        booking = Booking.getInstance();
        //activityDao = new ActivityDaoImpl();
        //equipmentDao = new EquipmentDaoImpl();

        annesofieRB.setUserData("Anne-Sofie");
        læringsRB.setUserData("Lærings konsulent");

        assistanceType.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue))
                booking.setAssistance(newValue.getUserData().toString());
        });

        aktivitetCB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(!oldValue.equals(newValue))
                booking.getActivity().setActivityID(newValue.getActivityID());
        });

        udstyrLV.getItems().addListener((ListChangeListener<Equipment>) c -> {
            booking.setEquipmentList(udstyrLV.getItems());
        });

        //udstyrCB.getItems().addAll(equipmentDao.getAllEquipment());

        //aktivitetCB.getItems().addAll(activityDao.getAllActivity());
    }

}

