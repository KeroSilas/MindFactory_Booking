package group3.mindfactory_booking.model.tasks;

import group3.mindfactory_booking.dao.ActivityDao;
import group3.mindfactory_booking.dao.ActivityDaoImpl;
import group3.mindfactory_booking.model.Activity;
import javafx.concurrent.Task;

import java.util.List;


public class FetchActivitiesTask extends Task<List<Activity>> {

    private final ActivityDao activityDao;

    public FetchActivitiesTask() {
        activityDao = new ActivityDaoImpl();
    }

    @Override
    public List<Activity> call() {
        return activityDao.getAllActivity();
    }
}
