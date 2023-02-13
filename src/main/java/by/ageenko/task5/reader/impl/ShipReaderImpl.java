package by.ageenko.task5.reader.impl;

import by.ageenko.task5.entity.Ship;
import by.ageenko.task5.exception.ShipException;
import by.ageenko.task5.reader.ShipReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ShipReaderImpl implements ShipReader {
    static Logger logger = LogManager.getLogger();
    private static final String DEFAULT_FILENAME = "data//EmptyFile";
    private static final String SPACE_SEPARATOR = "\\s+";
    @Override
    public Ship shipReader(String filename) throws ShipException {
        List<String> tempList;
        String [] strTemp;
        Path path = Paths.get(filename);
        if (!Files.exists(path)) {
            logger.log(Level.INFO, "file {} not exist", filename);
            filename = DEFAULT_FILENAME;
        }
        try (Stream<String> stream = Files.lines(path)) {
            tempList = stream.toList();
            strTemp = tempList.get(0).split(SPACE_SEPARATOR);
        }
        catch (IOException e) {
            throw new ShipException("File is empty");
        }
        return new Ship(Integer.parseInt(strTemp[0]), Ship.StateShip.valueOf(strTemp[1]));
    }

    @Override
    public List<Ship> shipReaderToList(String filename) throws ShipException {
        List<String> tempList;
        List<Ship> shipList = new ArrayList<>();
        String [] strTemp;
        Path path = Paths.get(filename);
        if (!Files.exists(path)) {
            logger.log(Level.INFO, "file {} not exist", filename);
            filename = DEFAULT_FILENAME;
        }
        try (Stream<String> stream = Files.lines(path)) {
            tempList = stream.toList();
            for (int i = 0; i < tempList.size(); i++) {
                strTemp = tempList.get(i).split(SPACE_SEPARATOR);
                shipList.add(new Ship(Integer.parseInt(strTemp[0]), Ship.StateShip.valueOf(strTemp[1])));
            }
        }
        catch (IOException e) {
            throw new ShipException("File is empty");
        }
        return shipList;
    }
}
