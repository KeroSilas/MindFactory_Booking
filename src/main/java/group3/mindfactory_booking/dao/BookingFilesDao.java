package group3.mindfactory_booking.dao;
import group3.mindfactory_booking.model.Booking;
import group3.mindfactory_booking.model.File;

import java.util.List;

public interface BookingFilesDao {

    public void addBookingFiles(int bookingID, int fileID);

    public void deleteBookingFiles(File file);

    List<File> getAllFilesOnBookingFile(Booking booking);


}
