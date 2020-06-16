package it.register.tech.exercise.one;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.exceptions.CsvValidationException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class LogManager {

    public static final int ROW_ELEMENTS_NUMBER = 4; //TODO: check a better name
    public static final String SEMICOLON_DELIMITER = ";";
    public static final int STATUS_OK = 200;

//    public void createDailyReport(String pathFileToImport, String pathFileDailyReport) {
//
//        List<LogDetail> logDetails = importLogDetails(pathFileToImport);
//        List<LogSummary> logSummaries = mapFrom(logDetails);
//        Collections.sort(logSummaries, (ls1, ls2) -> ls2.getRequestNumber() - ls1.getRequestNumber());
//        writeOnFileInCSVFormat(pathFileDailyReport, logSummaries);
//    }
    
    public List<LogDetail> importLogDetails(String pathFileToImport) {

        List<LogDetail> logDetails = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(pathFileToImport),
                StandardCharsets.UTF_8)) {

            String line;
            while ((line = br.readLine()) != null) {

                if (!removeWhiteSpace(line).isEmpty()) {
                    String[] values = removeWhiteSpace(line).split(SEMICOLON_DELIMITER);

                    if (values.length == ROW_ELEMENTS_NUMBER) {
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return logDetails;
    }

    private String removeWhiteSpace(String input) {
        return input.replaceAll("\\s+", "");
    }

    public List<LogSummary> mapFrom(List<LogDetail> logDetails) {

        List<LogDetail> filteredLogDetails = filter(logDetails);

        int totalRequests = filteredLogDetails.size();
        long totalBytes = filteredLogDetails.stream().map(logDetail -> logDetail.getBytes()).reduce(Long::sum).orElseThrow();

        Map<String, List<LogDetail>> logSummariesMap = filteredLogDetails.stream().collect(groupingBy(LogDetail::getRemoteAddress, toList()));

        List<LogSummary> logSummaries = new ArrayList<>();
        for (Map.Entry<String, List<LogDetail>> entry : logSummariesMap.entrySet()) {
            String remoteAddress = entry.getKey();
            int requestNumber = entry.getValue().size();
            long bytes = entry.getValue().stream().map(logDetail -> logDetail.getBytes()).reduce(Long::sum).orElseThrow();

            logSummaries.add(new LogSummary(remoteAddress, requestNumber, (double) requestNumber / totalRequests, bytes, (bytes / totalBytes)));
        }

        return logSummaries;
    }

    private List<LogDetail> filter(List<LogDetail> logDetails) {
        return logDetails
                .stream()
                .filter(logDetail -> logDetail.getStatus() != (STATUS_OK))
                .collect(toList());
    }

    public void writeOnFileInCSVFormat(String summaryFilePath, List<LogSummary> logSummaries) {

        try {
            List<String> lines = logSummaries.stream().map(logSummary -> logSummary.toCSV()).collect(Collectors.toList());
            Files.write(Paths.get(summaryFilePath), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeOnFileInJsonFormat(String summaryFilePath, List<LogSummary> logSummaries) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(summaryFilePath), logSummaries);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
