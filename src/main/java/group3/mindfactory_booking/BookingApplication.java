package group3.mindfactory_booking;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class BookingApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BookingApplication.class.getResource("homepage-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.getIcons().add(new Image("file:src/main/resources/group3/mindfactory_booking/images/MF_POSITVE_LOGO_WHITE.png"));
        stage.setWidth(850);
        stage.setHeight(850);
        stage.setMinWidth(850);
        stage.setMinHeight(850);
        stage.setTitle("Mind Factory Booking");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}