package group3.mindfactory_booking.dao;
import group3.mindfactory_booking.model.File;

import java.util.List;

public interface FileDao
{


    public void saveFile(File file);

    public void deleteFile(int fileID);

    List<File> getAllFiles();
}
