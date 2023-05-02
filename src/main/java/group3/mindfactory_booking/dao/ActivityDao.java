package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.Forløb;

import java.util.List;

public interface ActivityDao {


    public void saveActivity(Activity activity);

    void deleteActivity(int activityID);

    List<Activity> getAllActivity();
}
