package ru.niatomi.configuration;

import lombok.Getter;

/**
 * @author niatomi
 */
public class ColumnChoiceConfiguration {

    private static ColumnChoiceConfiguration columnChoiceConfiguration;

    @Getter
    private static int fieldNumber;

    public static ColumnChoiceConfiguration getColumnChoiceConfiguration() {
        if (columnChoiceConfiguration == null) {
            columnChoiceConfiguration = new ColumnChoiceConfiguration();
        }
        return columnChoiceConfiguration;
    }

    private ColumnChoiceConfiguration() {}

    public void setColumnNumberAndSetPropertyName(String[] args) {
        int settingColumnNumber = checkConfigurationStringArgs(args);
        fieldNumber = settingColumnNumber - 1;
    }

    private int checkConfigurationStringArgs(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException(
                    "Номер колонки не указан, ожидается: java -jar airports-search-1.0.jar 1(вот эта цифра номер колонки)");
        }

        Integer column;
        try {
            column = Integer.parseInt(args[0]);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Номер колонки должен быть числом");
        }

        if (column <= 0 || column > 13) {
            throw new IllegalArgumentException("Номер колонки должен быть от 1 до " + 13);
        }

        return column;

    }

}
