package group3.mindfactory_booking.controllers;

import group3.mindfactory_booking.BookingApplication;
import group3.mindfactory_booking.model.singleton.Booking;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FolkeskoleController {

    private Booking booking;

    @FXML private MFXTextField afgangTF, ankomstTF, fagTF, stillingTF;
    @FXML private MFXComboBox<String> forløbCB, transportCB, skoleCB;
    @FXML private MFXButton næsteBtn, tilbageBtn;
    @FXML private MFXRadioButton jaRB, nejRB;
    @FXML private VBox åbenSkolePane;

    @FXML
    void handleNæste() {
        if (isInputValid()) {
            importToBooking();
            nextPage();
        } else {
            System.out.println("Input is not valid");
        }
    }

    @FXML
    void handleTilbage() {
        previousPage();
        booking.setBookingType(null);
    }

    public void initialize() {
        booking = Booking.getInstance();

        jaRB.setOnAction(e -> {
            if (jaRB.isSelected()) {
                åbenSkolePane.setVisible(true);
                åbenSkolePane.setMinHeight(Region.USE_COMPUTED_SIZE);
                åbenSkolePane.setPrefHeight(Region.USE_COMPUTED_SIZE);
            } else {
                åbenSkolePane.setVisible(false);
                åbenSkolePane.setMinHeight(0);
                åbenSkolePane.setPrefHeight(0);
            }
        });

        nejRB.setOnAction(e -> {
            if (nejRB.isSelected()) {
                åbenSkolePane.setVisible(false);
                åbenSkolePane.setMinHeight(0);
                åbenSkolePane.setPrefHeight(0);
            } else {
                åbenSkolePane.setVisible(true);
                åbenSkolePane.setMinHeight(Region.USE_COMPUTED_SIZE);
                åbenSkolePane.setPrefHeight(Region.USE_COMPUTED_SIZE);
            }
        });

        skoleCB.getItems().addAll("Tønder Gymnasium", "Det Blå Gymnasium", "EUC Syd", "Tønder Kommune - skole");
        forløbCB.getItems().addAll("Idéfabrikken", "Digital fabrikken med laserskærer", "Robot på job", "Robotten ryder op", "Naturisme ved Vadehavet", "Skab sikkerhed i Vadehavet");
        transportCB.getItems().addAll("Jeg kommer i lejet bus", "Jeg kommer i offentlig transport");
    }

    private void importToBooking() {
        booking.setOrganization(skoleCB.getSelectionModel().getSelectedItem());
        booking.setAfdeling(fagTF.getText());
        booking.setPosition(stillingTF.getText());

        if (jaRB.isSelected()) {
            booking.setÅbenSkoleForløb(forløbCB.getSelectionModel().getSelectedItem());
            booking.setTransportDeparture(afgangTF.getText());
            booking.setTransportArrival(ankomstTF.getText());
            booking.setTransportType(transportCB.getSelectionModel().getSelectedItem());
        } else {
            booking.setÅbenSkoleForløb("Ingen");
            booking.setTransportDeparture("Ikke valgt");
            booking.setTransportArrival("Ikke valgt");
            booking.setTransportType("Ikke valgt");
        }
    }

    private boolean isInputValid() {
        boolean success = true;

        if (skoleCB.getSelectionModel().getSelectedItem() == null) {
            skoleCB.setStyle("-fx-border-color: red");
            success = false;
        } else {
            skoleCB.setStyle("-fx-border-color: lightgrey");
        }

        if (fagTF.getText().isEmpty()) {
            fagTF.setStyle("-fx-border-color: red");
            success = false;
        } else {
            fagTF.setStyle("-fx-border-color: lightgrey");
        }

        if (stillingTF.getText().isEmpty()) {
            stillingTF.setStyle("-fx-border-color: red");
            success = false;
        } else {
            stillingTF.setStyle("-fx-border-color: lightgrey");
        }

        if (jaRB.isSelected()) {
            if (forløbCB.getSelectionModel().getSelectedItem() == null) {
                forløbCB.setStyle("-fx-border-color: red");
                success = false;
            } else {
                forløbCB.setStyle("-fx-border-color: lightgrey");
            }

            if (transportCB.getSelectionModel().getSelectedItem() == null) {
                transportCB.setStyle("-fx-border-color: red");
                success = false;
            } else {
                transportCB.setStyle("-fx-border-color: lightgrey");
            }

            if (afgangTF.getText().isEmpty()) {
                afgangTF.setStyle("-fx-border-color: red");
                success = false;
            } else {
                afgangTF.setStyle("-fx-border-color: lightgrey");
            }

            if (ankomstTF.getText().isEmpty()) {
                ankomstTF.setStyle("-fx-border-color: red");
                success = false;
            } else {
                ankomstTF.setStyle("-fx-border-color: lightgrey");
            }
        }

        return success;
    }

    private void previousPage() {
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

    private void nextPage() {
        Stage stage;
        Parent root;

        stage = (Stage) næsteBtn.getScene().getWindow();
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

