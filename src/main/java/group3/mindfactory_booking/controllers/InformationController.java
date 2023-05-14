package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.BookingApplication;
import group3.mindfactory_booking.model.BookingTime;
import group3.mindfactory_booking.model.singleton.Booking;
import group3.mindfactory_booking.model.tasks.GetBookingTimesTask;
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
        GetBookingTimesTask getBookingTimesTask = new GetBookingTimesTask();
        getBookingTimesTask.valueProperty().addListener((observable, oldValue, newValue) -> {
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

                Iterator<LocalDate> dateIterator = dateList.iterator();
                while (dateIterator.hasNext()) {
                    LocalDate ld = dateIterator.next();
                    for (BookingTime bt : bookedTimes) {
                        if (bt.isWholeDay() && bt.getStartDate().equals(ld)) {
                            dateIterator.remove();
                            break;
                        }
                    }
                }

            }
        });
        Thread thread = new Thread(getBookingTimesTask);
        thread.setDaemon(true);
        thread.start();

        datoCB.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                fraCB.getSelectionModel().clearSelection();
                tilCB.getSelectionModel().clearSelection();
                startTimeList.clear();
                endTimeList.clear();

                fraCB.setDisable(false);
                fraLabel.setDisable(false);
                tilCB.setDisable(true);
                tilLabel.setDisable(true);

                for (int i = 7; i < 23; i++) {
                    startTimeList.add(LocalTime.of(i,0));
                }
                Iterator<LocalTime> startTimeIterator = startTimeList.iterator();
                while (startTimeIterator.hasNext()) {
                    LocalTime lt = startTimeIterator.next();
                    for (BookingTime bt : bookedTimes) {
                        if (datoCB.getValue().equals(bt.getStartDate())) {
                            if ((lt.isAfter(bt.getStartTime()) || lt.equals(bt.getStartTime())) && (lt.isBefore(bt.getEndTime()) || lt.equals(bt.getEndTime()))) {
                                startTimeIterator.remove();
                                break;
                            }
                        }
                    }
                }

            }
        });

        // If the selected time is a halfday booking before 12, then don't add the hours for the first half of the day
        fraCB.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                tilCB.getSelectionModel().clearSelection();
                endTimeList.clear();

                tilCB.setDisable(false);
                tilLabel.setDisable(false);

                // I heard you like spaghetti, so I put spaghetti in your spaghetti
                int hour = newValue.plusHours(1).getHour();
                for (int i = hour; i < 24; i++) {
                    endTimeList.add(LocalTime.of(i,0));
                }
                Iterator<LocalTime> endTimeIterator = endTimeList.iterator();
                while (endTimeIterator.hasNext()) {
                    LocalTime lt = endTimeIterator.next();
                    for (BookingTime bt : bookedTimes) {
                        if (newValue.isBefore(bt.getStartTime()) && datoCB.getValue().equals(bt.getStartDate())) {
                            if ((lt.isAfter(bt.getStartTime()))) {
                                endTimeIterator.remove();
                                break;
                            }
                        }
                    }
                }

            }
        });

        tilCB.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                specialLabel.setVisible(newValue.isAfter(LocalTime.of(18, 0)));
            }
        });
    }

    private void importToBooking() {
        booking.setStartDate(datoCB.getValue());
        booking.setStartTime(fraCB.getValue());
        booking.setEndTime(tilCB.getValue());
        booking.setMessageToAS(beskedTA.getText());
        booking.getCustomer().setFirstName(fornavnTF.getText());
        booking.getCustomer().setLastName(efternavnTF.getText());
        booking.getCustomer().setEmail(emailTF.getText());
        booking.getCustomer().setPhone(telefonTF.getText());
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
