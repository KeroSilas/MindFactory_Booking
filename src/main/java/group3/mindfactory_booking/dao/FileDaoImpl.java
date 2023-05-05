package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileDaoImpl implements FileDao{
    private final DatabaseConnector databaseConnector;

    public FileDaoImpl() {
        databaseConnector = DatabaseConnector.getInstance();
    }


    @Override
    public void saveFile(File file) {
        try (Connection con = databaseConnector.getConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO Files VALUES(?,?,?);");
            ps.setInt(1, file.getFileID());
            ps.setString(2, file.getFilePath());
            ps.setString(3, file.getFileName());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("cannot insert record: " + e.getMessage());
        }

    }


    @Override
    public void deleteFile(int fileID) {
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Files WHERE fileID = ?");
            ps.setInt(1, fileID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public List<File> getAllFiles() {
        List<File> files = new ArrayList<>();
        try(Connection con = databaseConnector.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Files;");
            ResultSet rs = ps.executeQuery();

            File file;
            while (rs.next()) {
                int fileID = rs.getInt(1);
                String filePath = rs.getString(2);
                String fileName = rs.getString(3);

                file = new File(fileID, filePath, fileName);
                files.add(file);
            }

        } catch (SQLException e) {
            System.err.println("cannot access AllFiles (FileDaoImpl) " + e.getMessage());
        }
        return files;
    }

}
