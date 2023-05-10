package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.BookingApplication;
import group3.mindfactory_booking.model.BookingTime;
import group3.mindfactory_booking.model.singleton.Booking;
import group3.mindfactory_booking.model.tasks.*;
import io.github.palexdev.materialfx.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TidOgDatoController {

    private Booking booking;
    private List<BookingTime> bookedTimes;
    private final ObservableList<LocalDate> dateList = FXCollections.observableArrayList();
    private final ObservableList<LocalTime> startTimeList = FXCollections.observableArrayList();
    private final ObservableList<LocalTime> endTimeList = FXCollections.observableArrayList();
    private boolean isHalfDayEarly;

    @FXML private MFXProgressSpinner progressSpinner;
    @FXML private MFXComboBox<LocalDate> datoCB;
    @FXML private MFXComboBox<LocalTime> fraCB, tilCB;
    @FXML private MFXListView<BookingTime> tidOgDatoLV;
    @FXML private MFXButton bekræftBtn, tilbageBtn, tilføjBtn, sletBtn;
    @FXML private Label alertLabel;

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
            alertLabel.setVisible(false);
            importToBooking();

            SaveBookingTask saveBookingTask = new SaveBookingTask();
            saveBookingTask.setOnSucceeded(e -> {
                if (saveBookingTask.getValue()) {
                    System.out.println("Booking successfully saved");

                    // Send email and save booking equipment in a new thread
                    // This only happens when the booking is saved successfully
                    ExecutorService executorService = Executors.newCachedThreadPool();
                    SendEmailTask sendEmailTask = new SendEmailTask();
                    SaveBookingEquipmentTask saveBookingEquipmentTask = new SaveBookingEquipmentTask();
                    executorService.submit(sendEmailTask);
                    executorService.submit(saveBookingEquipmentTask);
                    executorService.shutdown();

                    nextPage();
                } else {
                    System.out.println("Booking failed to save");

                    // If the booking fails to save, the user is notified
                    alertLabel.setVisible(true);

                    // Clear the ListView and ComboBoxes
                    datoCB.getSelectionModel().clearSelection();
                    fraCB.getSelectionModel().clearSelection();
                    tilCB.getSelectionModel().clearSelection();
                    tidOgDatoLV.getItems().clear();
                    startTimeList.clear();
                    endTimeList.clear();

                    // Disable the buttons again
                    tilføjBtn.setDisable(true);
                    sletBtn.setDisable(true);
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

        // Add the ObservableLists to the ComboBoxes
        datoCB.setItems(dateList);
        fraCB.setItems(startTimeList);
        tilCB.setItems(endTimeList);

        // Gets the already booked times from the database every 5 seconds
        ReceiveTimesTask receiveTimesTask = new ReceiveTimesTask();
        receiveTimesTask.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                bookedTimes = newValue;

                // Clear the dateList and add all the dates from today to 365 days from now
                // This is added inside the listener because a booking time can be deleted, and that needs to be added back to the dateList
                dateList.clear();
                for (int i = 0; i < 365; i++) {
                    dateList.add(LocalDate.now().plusDays((1 + i)));
                }

                // Remove the dates that are already booked if the booking is a wholeday booking
                // Binged hard on this one. I'm sorry.
                Iterator<LocalDate> dateIterator = dateList.iterator();
                int sameDayCounter = 0;
                while (dateIterator.hasNext()) {
                    LocalDate ld = dateIterator.next();
                    for (BookingTime bt : bookedTimes) {
                        if (bt.isWholeDay() && bt.getDate().equals(ld)) {
                            dateIterator.remove();
                            break;
                        }
                        if (bt.getDate().equals(ld)) {
                            sameDayCounter++;
                            if (sameDayCounter == 2) {
                                dateIterator.remove();
                                break;
                            }
                        }
                    }
                    sameDayCounter = 0;
                }
            }
        });
        Thread thread = new Thread(receiveTimesTask);
        thread.setDaemon(true);
        thread.start();

        // Spaghetti code incoming
        datoCB.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != oldValue) {
                fraCB.getSelectionModel().clearSelection();
                tilCB.getSelectionModel().clearSelection();
                startTimeList.clear();
                endTimeList.clear();

                fraCB.setDisable(false);
                tilCB.setDisable(true);

                // If there already is a booking on the selected date and it is a halfday booking before 12, then don't add the hours for the first half of the day
                // If there already is a booking on the selected date and it is a halfday booking after 12, then don't add the hours for the second half of the day
                // Otherwise show all the hours
                for (BookingTime bt : bookedTimes) {
                    if (bt.getDate().equals(newValue) && !bt.isHalfDayEarly()) {

                        startTimeList.clear();
                        for (int i = 7; i < 12; i++) {
                            startTimeList.add(LocalTime.of(i, 0));
                            isHalfDayEarly = true;
                        }
                        break;

                    } else if (bt.getDate().equals(newValue) && bt.isHalfDayEarly()) {

                        startTimeList.clear();
                        for (int i = 12; i < 23; i++) {
                            startTimeList.add(LocalTime.of(i, 0));
                            isHalfDayEarly = false;
                        }
                        break;

                    } else {

                        startTimeList.clear();
                        for (int i = 7; i < 23; i++) {
                            startTimeList.add(LocalTime.of(i, 0));
                        }
                    }
                }

                if (bookedTimes.isEmpty()) {
                    startTimeList.clear();
                    for (int i = 7; i < 23; i++) {
                        startTimeList.add(LocalTime.of(i, 0));
                    }
                }
            }
        });

        // If the selected time is a halfday booking before 12, then don't add the hours for the first half of the day
        fraCB.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != oldValue) {
                tilCB.setDisable(false);

                endTimeList.clear();

                // Since we already declared the boolean isHalfDayEarly in the listener for the date ComboBox, we can use it here, instead of having to loop through the bookedTimes again
                try {
                    int hour = newValue.plusHours(1).getHour();
                    if (isHalfDayEarly) {
                        for (int i = hour; i <= 12; i++) {
                            endTimeList.add(LocalTime.of(i, 0));
                        }
                    } else {
                        for (int i = hour; i < 24; i++) {
                            endTimeList.add(LocalTime.of(i, 0));
                        }
                    }
                } catch (NullPointerException e) {
                    System.out.println("No value selected");
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
        // If the booking is a wholeday booking, then set the wholeDay boolean to true
        boolean isWholeDay = false;
        if (fraCB.getValue().isBefore(LocalTime.of(12, 0)) && tilCB.getValue().isAfter(LocalTime.of(12, 0))) {
            isWholeDay = true;
        }

        // If the booking is a halfday booking before 12, then set the halfDayEarly boolean to true
        boolean isHalfDayEarly = false;
        if (tilCB.getValue().isBefore(LocalTime.of(12, 0))) {
            isHalfDayEarly = true;
        }

        // Set the wholeDay and halfDayEarly booleans for all the BookingTimes in the ListView
        for (BookingTime bookingTime : tidOgDatoLV.getItems()) {
            bookingTime.setWholeDay(isWholeDay);
            bookingTime.setHalfDayEarly(isHalfDayEarly);
        }

        // Get the BookingTimes from the ListView and add them to the Booking singleton
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