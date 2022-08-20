package ru.niatomi.util.resource.Impl;

import lombok.NoArgsConstructor;
import ru.niatomi.configuration.CSVResourceFileConfiguration;
import ru.niatomi.configuration.ColumnChoiceConfiguration;
import ru.niatomi.model.AirportData;
import ru.niatomi.util.cache.Cache;
import ru.niatomi.util.cache.Impl.LRUCache;
import ru.niatomi.util.resource.CSVParser;

import java.io.*;
import java.util.*;

/**
 * @author niatomi
 */
@NoArgsConstructor
public class CSVParserImpl implements CSVParser {

    private static final ColumnChoiceConfiguration COLUMN_CHOICE_CONFIGURATION =
            ColumnChoiceConfiguration.getColumnChoiceConfiguration();

    private static final CSVResourceFileConfiguration CSV_RESOURCE_FILE_CONFIGURATION =
            CSVResourceFileConfiguration.getCsvResourceFileConfiguration();

    private static final Cache<String, String> cache = new LRUCache<>();
    private static final String delimiter = ",";

    public List<AirportData> parseCSVWithQuery(String query) {
        InputStream resource = CSV_RESOURCE_FILE_CONFIGURATION.getFileAsInputStream();
        List<AirportData> parsedData = new LinkedList<>();
        query = query.toLowerCase().replace("\\", "/");
        String line;
        int fieldNumber = COLUMN_CHOICE_CONFIGURATION.getFieldNumber();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resource))) {
            while ((line = bufferedReader.readLine()) != null) {
                String checkingColumn = line.replace("\\", "/").split(delimiter)[fieldNumber];
                if (checkingColumn.toLowerCase().startsWith(query, checkingColumn.startsWith("\"") ? 1 : 0)) {
                    if (cache.containsKey(checkingColumn)) {
                        AirportData airportData = new AirportData(checkingColumn, cache.get(checkingColumn).get());
                        bufferedReader.skip(line.length() + 1);
                        parsedData.add(airportData);
                    } else {
                        cache.put(checkingColumn, line);
                        AirportData airportData = new AirportData(checkingColumn, line);
                        parsedData.add(airportData);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Файл " + CSV_RESOURCE_FILE_CONFIGURATION.getFileName() + " не может быть прочтён");
        }
        return parsedData;
    }

}
