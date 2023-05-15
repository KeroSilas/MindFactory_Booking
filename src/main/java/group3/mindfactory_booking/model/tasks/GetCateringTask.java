package group3.mindfactory_booking.model.tasks;

import group3.mindfactory_booking.dao.CateringDao;
import group3.mindfactory_booking.dao.CateringDaoImpl;
import group3.mindfactory_booking.model.Catering;
import javafx.concurrent.Task;

import java.util.List;

public class GetCateringTask extends Task<List<Catering>> {

    private final CateringDao cateringDao;

    public GetCateringTask() {
        cateringDao = new CateringDaoImpl();
    }

    @Override
    public List<Catering> call() {
        return cateringDao.getCatering();
    }
}
