package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.Catering;

import java.util.List;

public interface CateringDao {

    public void saveCatering(Catering catering);

    void deleteCatering(int cateringID);

    List<Catering> getAllCatering();
}
