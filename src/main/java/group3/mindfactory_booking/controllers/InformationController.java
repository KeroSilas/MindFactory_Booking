package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.BookingApplication;
import group3.mindfactory_booking.model.BookingTime;
import group3.mindfactory_booking.model.singleton.Booking;
import group3.mindfactory_booking.model.tasks.ReceiveTimesTask;
import group3.mindfactory_booking.model.tasks.SaveBookingEquipmentTask;
import group3.mindfactory_booking.model.tasks.SaveBookingTask;
import group3.mindfactory_booking.model.tasks.SendEmailTask;
import io.github.palexdev.materialfx.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InformationController {

    private Booking booking;
    private List<BookingTime> bookedTimes;
    private final ObservableList<LocalDate> dateList = FXCollections.observableArrayList();
    private final ObservableList<LocalTime> startTimeList = FXCollections.observableArrayList();
    private final ObservableList<LocalTime> endTimeList = FXCollections.observableArrayList();
    private boolean isHalfDayEarly;

    @FXML private MFXProgressSpinner progressSpinner;
    @FXML private MFXComboBox<LocalDate> datoCB;
    @FXML private MFXComboBox<LocalTime> fraCB, tilCB;
    @FXML private MFXButton bekræftBtn, tilbageBtn;
    @FXML private MFXTextField efternavnTF, emailTF, fornavnTF, telefonTF;
    @FXML private TextArea beskedTA;
    @FXML private Label alertLabel, fraLabel, tilLabel, specialLabel;

    @FXML
    void handleBekræft() {
        if (isInputValid()) {
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
                    startTimeList.clear();
                    endTimeList.clear();
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
                if (oldValue == null || newValue.size() != oldValue.size()) { // This is to prevent the dateList from being cleared every 5 seconds, if nothing has changed. But it still needs to be populated the first time
                    dateList.clear();
                    for (int i = 0; i < 365; i++) {
                        dateList.add(LocalDate.now().plusDays((1 + i)));
                    }
                }

                // Remove the dates that are already booked if the booking is a wholeday booking
                Iterator<LocalDate> dateIterator = dateList.iterator();
                int sameDayCounter = 0;
                while (dateIterator.hasNext()) {
                    LocalDate ld = dateIterator.next();
                    for (BookingTime bt : bookedTimes) {
                        if (bt.isWholeDay() && bt.getStartDate().equals(ld)) {
                            dateIterator.remove();
                            break;
                        }
                        if (bt.getStartDate().equals(ld)) {
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

        datoCB.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != oldValue) {
                fraCB.getSelectionModel().clearSelection();
                tilCB.getSelectionModel().clearSelection();
                startTimeList.clear();
                endTimeList.clear();

                fraCB.setDisable(false);
                fraLabel.setDisable(false);
                tilCB.setDisable(true);
                tilLabel.setDisable(true);

                // If there already is a booking on the selected date and it is a halfday booking before 12, then don't add the hours for the first half of the day
                // If there already is a booking on the selected date and it is a halfday booking after 12, then don't add the hours for the second half of the day
                // Otherwise show all the hours
                for (BookingTime bt : bookedTimes) {
                    if (bt.getStartDate().equals(newValue) && !bt.isHalfDayEarly()) {

                        startTimeList.clear();
                        for (int i = 7; i < 12; i++) {
                            startTimeList.add(LocalTime.of(i, 0));
                            isHalfDayEarly = true;
                        }
                        break;

                    } else if (bt.getStartDate().equals(newValue) && bt.isHalfDayEarly()) {

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
                tilLabel.setDisable(false);

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
            if (newValue.isAfter(LocalTime.of(18, 0))) {
                specialLabel.setVisible(true);
            } else {
                specialLabel.setVisible(false);
            }
        });
    }

    private void importToBooking() {
        booking.setStartDate(datoCB.getValue());
        booking.setStartTime(fraCB.getValue());
        booking.setEndTime(tilCB.getValue());
        booking.setMessageToAS(beskedTA.getText());
        booking.setLastName(efternavnTF.getText());
        booking.setEmail(emailTF.getText());
        booking.setFirstName(fornavnTF.getText());
        booking.setPhone(telefonTF.getText());
    }

    private boolean isInputValid() {
        boolean success = true;

        if (fornavnTF.getText().isEmpty()) {
            fornavnTF.setStyle("-fx-border-color: red");
            success = false;
        } else {
            fornavnTF.setStyle("-fx-border-color: lightgrey");
        }

        if (efternavnTF.getText().isEmpty()) {
            efternavnTF.setStyle("-fx-border-color: red");
            success = false;
        } else {
            efternavnTF.setStyle("-fx-border-color: lightgrey");
        }

        // https://stackoverflow.com/questions/60282362/regex-pattern-for-email
        if (emailTF.getText().isEmpty() || !emailTF.getText().matches("^[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*$")) {
            emailTF.setStyle("-fx-border-color: red");
            success = false;
        } else if (booking.getBookingType().equals("Skole") && !emailTF.getText().substring(emailTF.getText().length() - 11).equals("skoletdr.dk")) {
            emailTF.setStyle("-fx-border-color: red");
            success = false;
        } else {
            emailTF.setStyle("-fx-border-color: lightgrey");
        }

        if (telefonTF.getText().isEmpty() || !telefonTF.getText().matches("^[0-9]+$")) {
            telefonTF.setStyle("-fx-border-color: red");
            success = false;
        } else {
            telefonTF.setStyle("-fx-border-color: lightgrey");
        }

        if (datoCB.getValue() == null) {
            datoCB.setStyle("-fx-border-color: red");
            success = false;
        } else {
            datoCB.setStyle("-fx-border-color: lightgrey");
        }

        if (fraCB.getValue() == null) {
            fraCB.setStyle("-fx-border-color: red");
            success = false;
        } else {
            fraCB.setStyle("-fx-border-color: lightgrey");
        }

        if (tilCB.getValue() == null) {
            tilCB.setStyle("-fx-border-color: red");
            success = false;
        } else {
            tilCB.setStyle("-fx-border-color: lightgrey");
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
            root = FXMLLoader.load(Objects.requireNonNull(BookingApplication.class.getResource(Objects.equals(booking.getBookingType(), "Skole") ? "skole-2-view.fxml" : "virksomhed-2-view.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
