package group3.mindfactory_booking.model;

public class Forløb {

    private int forløbID;
    private String forløbName;

    public Forløb(int forløbID, String forløbName) {
        this.forløbID = forløbID;
        this.forløbName = forløbName;
    }

    public int getForløbID() {
        return forløbID;
    }

    public void setForløbID(int forløbID) {
        this.forløbID = forløbID;
    }

    public String getForløbName() {
        return forløbName;
    }

    public void setForløbName(String forløbName) {
        this.forløbName = forløbName;
    }
}
