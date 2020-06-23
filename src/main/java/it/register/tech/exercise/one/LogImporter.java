package it.register.tech.exercise.one;

import com.opencsv.exceptions.CsvValidationException;
import it.register.tech.exercise.one.model.LogDetail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class LogImporter {

    public static final int INPUT_ROW_ELEMENTS_NUMBER = 4;
    public static final String SEMICOLON_DELIMITER = ";";

    public List<LogDetail> importLogDetails(String pathFileToImport) throws IOException {

        List<LogDetail> logDetails = Files.lines(Paths.get(pathFileToImport))
                .map(line-> removeWhiteSpace(line))
                .filter(line -> !line.isEmpty())
                .map(line -> parse(line))
                .filter(logDetail -> logDetail.getStatus()!=-1)
                .collect(Collectors.toList());
        return logDetails;
    }

    private LogDetail parse(String line) {

        try {
            String[] values = removeWhiteSpace(line).split(SEMICOLON_DELIMITER);

            if (values.length == INPUT_ROW_ELEMENTS_NUMBER) {
                long timestamp = Long.parseLong(values[0]);
                long bytes = Long.parseLong(values[1]);
                int status = Integer.parseInt(values[2]);
                String remoteAddress = values[3];

                return new LogDetail(timestamp, bytes, status, remoteAddress);
            } else {
                throw new CsvValidationException();
            }
        } catch (CsvValidationException e) {
            e.printStackTrace();
           return new LogDetail(0, 0, -1, "Error");
        }
    }

    private String removeWhiteSpace(String input) {
        return input.replaceAll("\\s+", "");
    }
}
