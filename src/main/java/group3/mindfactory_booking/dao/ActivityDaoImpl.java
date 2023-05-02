package group3.mindfactory_booking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityDaoImpl implements ActivityDao {

    private final DatabaseConnector databaseConnector;

    public ActivityDaoImpl() {
        databaseConnector = new DatabaseConnector();
    }

    @Override
    public void saveActivity(Activity activity) {
        try (Connection con = databaseConnector.getConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO Activity VALUES(?,?);");
            ps.setInt(1, activity.getActivityID());
            ps.setString(1, activity.getActivityName());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("cannot insert record: " + e.getMessage());
        }
    }

    @Override
    public void deleteActivity(int activityID) {


    }


    public List<Activity> getAllActivity() {

        List<Activity> caterings = new ArrayList<>();
        try(Connection con = databaseConnector.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Activity;");
            ResultSet rs = ps.executeQuery();

            Activity activity;
            while (rs.next()) {
                int activityID = rs.getInt(1);
                String activityName = rs.getString(2);

                activity = new Activity(activityID, activityName);
                caterings.add(activity);
            }

        } catch (SQLException e) {
            System.err.println("cannot access AllCaterings (CateringDaoImpl) " + e.getMessage());
        }
        return caterings;

    }
}
