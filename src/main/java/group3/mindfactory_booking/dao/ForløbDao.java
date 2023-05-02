package group3.mindfactory_booking.dao;

import group3.mindfactory_booking.model.Equipment;
import group3.mindfactory_booking.model.Forløb;

import java.util.List;

public interface ForløbDao {

    public void saveForløb(Forløb forløb);

    public void deleteForløb(int forløbID);

    List<Forløb> getAllForløb();
}
