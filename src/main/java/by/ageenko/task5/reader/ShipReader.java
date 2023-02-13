package by.ageenko.task5.reader;

import by.ageenko.task5.entity.Ship;
import by.ageenko.task5.exception.ShipException;

import java.util.List;

public interface ShipReader {
     Ship shipReader (String filename) throws ShipException;
     List<Ship> shipReaderToList (String filename) throws ShipException;
}
