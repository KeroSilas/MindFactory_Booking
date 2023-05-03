package group3.mindfactory_booking.model;

public class Activity {

    int activityID;
    String activityName;

    public Activity(int activityID, String activityName) {
        this.activityID = activityID;
        this.activityName = activityName;
    }

    public int getActivityID() {
        return activityID;
    }

    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    @Override
    public String toString() {
        return getActivityName();
    }
}
