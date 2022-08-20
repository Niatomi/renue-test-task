package ru.niatomi;

import ru.niatomi.configuration.CSVResourceFileConfiguration;
import ru.niatomi.configuration.ColumnChoiceConfiguration;
import ru.niatomi.service.FindService;
import ru.niatomi.service.Impl.FindServiceImpl;

/**
 * @author niatomi
 */
public class Application {

    private static final ColumnChoiceConfiguration COLUMN_CHOICE_CONFIGURATION =
            ColumnChoiceConfiguration.getColumnChoiceConfiguration();
    private static final CSVResourceFileConfiguration CSV_RESOURCE_FILE_CONFIGURATION =
            CSVResourceFileConfiguration.getCsvResourceFileConfiguration();
    private static final String DATASET_NAME = "airports.csv";

    public static void main(String[] args) {
        configureApp(args);
        FindService findService = new FindServiceImpl();
        findService.initService();
    }

    private static void configureApp(String[] args) {
        COLUMN_CHOICE_CONFIGURATION.setColumnNumberAndSetPropertyName(args);
        CSV_RESOURCE_FILE_CONFIGURATION.setFileName(DATASET_NAME);
    }

}
