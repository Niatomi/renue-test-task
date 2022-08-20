package ru.niatomi.service.Impl;

import ru.niatomi.model.AirportData;
import ru.niatomi.service.FindService;
import ru.niatomi.util.console.Console;
import ru.niatomi.util.console.Impl.ConsoleImpl;
import ru.niatomi.util.resource.CSVParser;
import ru.niatomi.util.resource.Impl.CSVParserImpl;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author niatomi
 */
public class FindServiceImpl implements FindService {

    private static final Console console = new ConsoleImpl();
    private static final CSVParser parser = new CSVParserImpl();

    @Override
    public void initService() {
        while (true) {
            console.messageUser();
            Scanner scanner = new Scanner(System.in);
            String command = scanner.next();

            if (command.equals("!quit")) {
                console.close();
            } else {
                long time1 = System.currentTimeMillis();
                List<AirportData> airportData = parser.parseCSVWithQuery(command);
                airportData = airportData.stream().sorted(Comparator.comparing(AirportData::getKey)).collect(Collectors.toList());
                long time2 = System.currentTimeMillis();
                console.printData(airportData, time2 - time1);
                airportData.clear();
            }
        }
    }
}
