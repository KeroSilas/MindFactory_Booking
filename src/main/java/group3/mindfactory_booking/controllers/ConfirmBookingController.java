package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.model.Booking;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ConfirmBookingController {

    private Booking booking;

    @FXML
    private Label afgangLabel, ankomstLabel, assistanceLabel, deltagereLabel, efternavnLabel,
            emailLabel, fornavnLabel, forplejningLabel, fraLabel, orgLabel, slutDatoLabel,
            specielUdstyrLabel, startDatoLabel, stillingLabel, telefonLabel, tilLabel, transportLabel;

    public void initialize() {
        booking = Booking.getInstance();

        // Update labels every 2 seconds
        // This is a workaround for the fact that the labels are not updated once the view is loaded into memory from the NavigationController
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);

                    afgangLabel.setText(booking.getTransportDeparture());
                    ankomstLabel.setText(booking.getTransportArrival());
                    assistanceLabel.setText(booking.getAssistance());
                    deltagereLabel.setText(String.valueOf(booking.getParticipants()));
                    efternavnLabel.setText(booking.getLastName());
                    emailLabel.setText(booking.getEmail());
                    fornavnLabel.setText(booking.getFirstName());
                    telefonLabel.setText(booking.getPhone());
                    transportLabel.setText(booking.getTransportType());
                    forplejningLabel.setText("?");
                    orgLabel.setText("?");
                    specielUdstyrLabel.setText("?");
                    stillingLabel.setText("?");
                    slutDatoLabel.setText(String.format("%s/%s/%s",
                            booking.getEndDate().getDayOfMonth(),
                            booking.getEndDate().getMonthValue(),
                            booking.getEndDate().getYear()));
                    startDatoLabel.setText(String.format("%s/%s/%s",
                            booking.getStartDate().getDayOfMonth(),
                            booking.getStartDate().getMonthValue(),
                            booking.getStartDate().getYear()));
                    fraLabel.setText(String.format("%s:%s",
                            booking.getStartTime().getHour(),
                            booking.getStartTime().getMinute()));
                    tilLabel.setText(String.format("%s:%s",
                            booking.getEndTime().getHour(),
                            booking.getEndTime().getMinute()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        // Doesn't work sometimes, don't know why
        Platform.runLater(() -> thread.start());
    }

}
