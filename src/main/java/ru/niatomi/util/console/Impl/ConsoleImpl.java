package ru.niatomi.util.console.Impl;

import ru.niatomi.model.AirportData;
import ru.niatomi.util.console.Console;

import java.util.List;

/**
 * @author niatomi
 */
public class ConsoleImpl implements Console {
    @Override
    public void close() {
        System.exit(0);
    }

    @Override
    public void printData(List<AirportData> printableData, long timeOfSearch) {
        printableData.forEach(System.out::println);
        System.out.println("Колличество найденных строк: " + printableData.size());
        System.out.println("Время затраченное на поиск: " + timeOfSearch + " мс");
        System.out.println();
    }

    @Override
    public void messageUser() {
        System.out.println("Для выхода из приложения введите !quit");
        System.out.println("Введите строку:");
    }
}
