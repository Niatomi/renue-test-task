package ru.niatomi.util.resource;

import ru.niatomi.model.AirportData;

import java.util.List;

/**
 * @author niatomi
 */
public interface CSVParser {

    List<AirportData> parseCSVWithQuery(String query);

}