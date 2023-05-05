package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.BookingApplication;
import group3.mindfactory_booking.model.Equipment;
import group3.mindfactory_booking.model.singleton.Booking;
import group3.mindfactory_booking.model.tasks.SaveBookingTask;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;

public class ConfirmBookingController {

    @FXML private MFXProgressSpinner progressSpinner;
    @FXML private MFXButton bekræftBtn, tilbageBtn;
    @FXML private Label afdelingLabel, afgangLabel, ankomstLabel, assistanceLabel, deltagereLabel, efternavnLabel, emailLabel,
            fornavnLabel, telefonLabel, transportLabel, stillingLabel, organisationLabel, forløbLabel, datoLabel,
            fraLabel, tilLabel, aktivitetLabel, udstyrLabel, forplejningLabel;


    @FXML
    void handleBekræft() {
        progressSpinner.setVisible(true);
        bekræftBtn.setDisable(true);

        SaveBookingTask saveBookingTask = new SaveBookingTask();
        saveBookingTask.setOnSucceeded(e -> {
            if (saveBookingTask.getValue()) {
                goToHome();
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
    }

    @FXML
    void handleTilbage() {
        previousPage();
    }

    public void initialize() {
        Booking booking = Booking.getInstance();

        afgangLabel.setText(booking.getTransportDeparture());
        ankomstLabel.setText(booking.getTransportArrival());
        assistanceLabel.setText(booking.getAssistance());
        deltagereLabel.setText(String.valueOf(booking.getParticipants()));
        efternavnLabel.setText(booking.getLastName());
        emailLabel.setText(booking.getEmail());
        fornavnLabel.setText(booking.getFirstName());
        telefonLabel.setText(booking.getPhone());
        transportLabel.setText(booking.getTransportType());
        afdelingLabel.setText(booking.getAfdeling());
        stillingLabel.setText(booking.getPosition());

        if (booking.getCatering() != null)
            forplejningLabel.setText(booking.getCatering().getPackageName());

        if (booking.getOrganization() != null)
            organisationLabel.setText(booking.getOrganization().getOrganizationName());

        if (booking.getÅbenSkoleForløb() != null)
            forløbLabel.setText(booking.getÅbenSkoleForløb().getForløbName());

        if (booking.getActivity() != null)
            aktivitetLabel.setText(booking.getActivity().getActivityName());

        // https://www.baeldung.com/java-list-to-string
        udstyrLabel.setText(booking.getEquipmentList().stream()
                .map(Equipment::getEquipmentName)
                .collect(Collectors.joining(", ")));

        datoLabel.setText(String.format("%s/%s/%s",
                booking.getDate().getDayOfMonth(),
                booking.getDate().getMonthValue(),
                booking.getDate().getYear()));
        fraLabel.setText(String.format("%s:00", booking.getStartTime().getHour()));
        tilLabel.setText(String.format("%s:00", booking.getEndTime().getHour()));
    }

    private void goToHome() {
        Stage stage;
        Parent root;

        stage = (Stage) tilbageBtn.getScene().getWindow();
        try {
            root = FXMLLoader.load(Objects.requireNonNull(BookingApplication.class.getResource("homepage-view.fxml")));
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
            root = FXMLLoader.load(Objects.requireNonNull(BookingApplication.class.getResource("informationOgDato-3-view.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
