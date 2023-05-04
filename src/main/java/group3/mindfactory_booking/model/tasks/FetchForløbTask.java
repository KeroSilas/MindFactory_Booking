package group3.mindfactory_booking.model.tasks;

import group3.mindfactory_booking.dao.ForløbDao;
import group3.mindfactory_booking.dao.ForløbDaoImpl;
import group3.mindfactory_booking.model.Forløb;
import javafx.concurrent.Task;

import java.util.List;


public class FetchForløbTask extends Task<List<Forløb>> {

    private final ForløbDao forløbDao;

    public FetchForløbTask() {
        forløbDao = new ForløbDaoImpl();
    }

    @Override
    public List<Forløb> call() {
        return forløbDao.getAllForløb();
    }
}
