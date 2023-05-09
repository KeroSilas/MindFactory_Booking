package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.BookingApplication;
import group3.mindfactory_booking.model.BookingTime;
import group3.mindfactory_booking.model.singleton.Booking;
import group3.mindfactory_booking.model.tasks.SaveBookingTask;
import io.github.palexdev.materialfx.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public class TidOgDatoController {

    private Booking booking;

    @FXML private MFXProgressSpinner progressSpinner;
    @FXML private MFXComboBox<LocalDate> datoCB;
    @FXML private MFXComboBox<LocalTime> fraCB, tilCB;
    @FXML private MFXListView<BookingTime> tidOgDatoLV;
    @FXML private MFXButton bekræftBtn, tilbageBtn, tilføjBtn, sletBtn;

    @FXML
    void handleTilføj() {
        if (isInputValid()) {
            BookingTime bookingTime = new BookingTime(datoCB.getValue(), fraCB.getValue(), tilCB.getValue());
            tidOgDatoLV.getItems().add(bookingTime);
        } else {
            System.out.println("Input is not valid");
        }
    }

    @FXML
    void handleSlet() {
        List<BookingTime> bookingTimes = tidOgDatoLV.getSelectionModel().getSelectedValues();
        for (BookingTime bookingTime : bookingTimes) {
            tidOgDatoLV.getItems().remove(bookingTime);
        }
    }

    @FXML
    void handleBekræft() {
        if (isInputValid() && isListViewValid()) {
            progressSpinner.setVisible(true);
            bekræftBtn.setDisable(true);
            importToBooking();

            SaveBookingTask saveBookingTask = new SaveBookingTask();
            saveBookingTask.setOnSucceeded(e -> {
                if (saveBookingTask.getValue()) {
                    nextPage();
                    System.out.println("Booking saved successfully");
                } else {
                    System.out.println("Booking failed to save");
                }
                progressSpinner.setVisible(false);
                bekræftBtn.setDisable(false);
            });
            Thread thread = new Thread(saveBookingTask);
            thread.setDaemon(true);
            thread.start();
        } else {
            System.out.println("Input is not valid");
        }
    }

    @FXML
    void handleTilbage() {
        previousPage();
    }

    public void initialize() {
        booking = Booking.getInstance();

        for (int i = 0; i < 365; i++) {
            datoCB.getItems().add(LocalDate.now().plusDays((1 + i)));
        }

        datoCB.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != oldValue) {
                fraCB.setDisable(false);

                fraCB.getItems().clear();
                for (int i = 7; i < 23; i++) {
                    fraCB.getItems().add(LocalTime.of(i, 0));
                }
            }
        });

        fraCB.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != oldValue) {
                tilCB.setDisable(false);

                tilCB.getItems().clear();
                int hour = newValue.plusHours(1).getHour();
                for (int i = hour; i < 24; i++) {
                    tilCB.getItems().add(LocalTime.of(i, 0));
                }
            }
        });

        tilCB.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != oldValue) {
                tilføjBtn.setDisable(false);
            }
        });

        tidOgDatoLV.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != oldValue) {
                sletBtn.setDisable(false);
            }
        });
    }

    private void importToBooking() {
        booking.setBookingTimesList(tidOgDatoLV.getItems());
    }

    private boolean isInputValid() {
        boolean success = true;

        if (datoCB.getSelectionModel().getSelectedItem() == null) {
            datoCB.setStyle("-fx-border-color: red");
            success = false;
        } else {
            datoCB.setStyle("-fx-border-color: lightgrey");
        }

        if (fraCB.getSelectionModel().getSelectedItem() == null) {
            fraCB.setStyle("-fx-border-color: red");
            success = false;
        } else {
            fraCB.setStyle("-fx-border-color: lightgrey");
        }

        if (tilCB.getSelectionModel().getSelectedItem() == null) {
            tilCB.setStyle("-fx-border-color: red");
            success = false;
        } else {
            tilCB.setStyle("-fx-border-color: lightgrey");
        }

        return success;
    }

    private boolean isListViewValid() {
        boolean success = true;

        if (tidOgDatoLV.getItems().isEmpty()) {
            tidOgDatoLV.setStyle("-fx-border-color: red");
            success = false;
        } else {
            tidOgDatoLV.setStyle("-fx-border-color: lightgrey");
        }

        return success;
    }

    private void nextPage() {
        Stage stage;
        Parent root;

        stage = (Stage) bekræftBtn.getScene().getWindow();
        try {
            root = FXMLLoader.load(Objects.requireNonNull(BookingApplication.class.getResource("kvittering-5-view.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void previousPage() {
        Stage stage;
        Parent root;

        stage = (Stage) tilbageBtn.getScene().getWindow();
        try {
            root = FXMLLoader.load(Objects.requireNonNull(BookingApplication.class.getResource("information-3-view.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}