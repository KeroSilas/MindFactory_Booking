package group3.mindfactory_booking.model.tasks;

import group3.mindfactory_booking.dao.ActivityDao;
import group3.mindfactory_booking.dao.ActivityDaoImpl;
import group3.mindfactory_booking.model.Activity;
import javafx.concurrent.Task;

import java.util.List;

public class GetActivitiesTask extends Task<List<Activity>> {

    private final ActivityDao activityDao;

    public GetActivitiesTask() {
        activityDao = new ActivityDaoImpl();
    }

    @Override
    public List<Activity> call() {
        return activityDao.getActivities();
    }
}