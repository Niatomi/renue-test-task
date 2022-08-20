package ru.niatomi.util.console;

import ru.niatomi.model.AirportData;

import java.util.List;

/**
 * @author niatomi
 */
public interface Console {

    void close();

    void printData(List<AirportData> printableData, long timeOfSearch);

    void messageUser();

}
