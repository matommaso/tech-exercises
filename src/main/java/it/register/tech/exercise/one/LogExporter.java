package it.register.tech.exercise.one;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.register.tech.exercise.one.model.LogSummary;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class LogExporter {

    public void writeOnFileInCSVFormat(String summaryFilePath, List<LogSummary> logSummaries) throws IOException {
        List<String> lines = logSummaries.stream().map(logSummary -> logSummary.toCSV()).collect(Collectors.toList());
        Files.write(Paths.get(summaryFilePath), lines);
    }

    public void writeOnFileInJsonFormat(String summaryFilePath, List<LogSummary> logSummaries) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(summaryFilePath), logSummaries);
    }
}
