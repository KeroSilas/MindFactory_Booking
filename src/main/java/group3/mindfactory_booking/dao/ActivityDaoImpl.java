package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.Activity;

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
            ps.setString(2, activity.getActivityName());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("cannot insert record: " + e.getMessage());
        }
    }

    @Override
    public void deleteActivity(int activityID) {
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Activities WHERE activityID = ?");
            ps.setInt(1, activityID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }


    public List<Activity> getAllActivity() {

        List<Activity> activities = new ArrayList<>();
        try(Connection con = databaseConnector.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Activities;");
            ResultSet rs = ps.executeQuery();

            Activity activity;
            while (rs.next()) {
                int activityID = rs.getInt(1);
                String activityName = rs.getString(2);

                activity = new Activity(activityID, activityName);
                activities.add(activity);
            }

        } catch (SQLException e) {
            System.err.println("cannot access AllActivities (ActivityDaoImpl) " + e.getMessage());
        }
        return activities;

    }
}
