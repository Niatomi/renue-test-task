package ru.niatomi.configuration;

import lombok.Getter;
import ru.niatomi.Application;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author niatomi
 */
public class CSVResourceFileConfiguration {

    private static CSVResourceFileConfiguration csvResourceFileConfiguration;

    @Getter
    private static String fileName;

    private CSVResourceFileConfiguration() {}

    public static CSVResourceFileConfiguration getCsvResourceFileConfiguration() {
        if (csvResourceFileConfiguration == null) {
            csvResourceFileConfiguration = new CSVResourceFileConfiguration();
        }
        return csvResourceFileConfiguration;
    }

    public InputStream getFileAsInputStream() {
        return Application.class.getClassLoader().getResourceAsStream(fileName);
    }

    public static void setFileName(String fileName) {
        CSVResourceFileConfiguration.fileName = fileName;
    }

}
