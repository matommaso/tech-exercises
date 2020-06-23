package it.register.tech.exercise.one;

import com.opencsv.exceptions.CsvValidationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LogImporter {

    public static final int INPUT_ROW_ELEMENTS_NUMBER = 4;
    public static final String SEMICOLON_DELIMITER = ";";

    public List<LogDetail> importLogDetails(String pathFileToImport) throws IOException, CsvValidationException {

        List<LogDetail> logDetails = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(pathFileToImport),
                StandardCharsets.UTF_8)) {

            String line;
            while ((line = br.readLine()) != null) {

                if (!removeWhiteSpace(line).isEmpty()) {
                    String[] values = removeWhiteSpace(line).split(SEMICOLON_DELIMITER);

                    if (values.length == INPUT_ROW_ELEMENTS_NUMBER) {
                        long timestamp = Long.parseLong(values[0]);
                        long bytes = Long.parseLong(values[1]);
                        int status = Integer.parseInt(values[2]);
                        String remoteAddress = values[3];

                        logDetails.add(new LogDetail(timestamp, bytes, status, remoteAddress));
                    } else {
                        throw new CsvValidationException();
                    }
                }
            }
        }
        return logDetails;
    }

    private String removeWhiteSpace(String input) {
        return input.replaceAll("\\s+", "");
    }
}
