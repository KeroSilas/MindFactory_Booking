package group3.mindfactory_booking.dao;
import group3.mindfactory_booking.model.singleton.Booking;
import group3.mindfactory_booking.model.File;

import java.util.List;

public interface BookingFilesDao {

    public void addBookingFiles(int bookingID, int fileID);

    public void deleteBookingFiles(int fileID);

    List<File> getAllFilesOnBookingFile(Booking booking);


}
