package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.Forløb;

import java.util.List;

public interface CateringDao {

    public void saveCatering(Catering catering);

    public void deleteCatering(int cateringID);

    List<Catering> getAllCatering();
}
