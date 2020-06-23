package it.register.tech.exercise.one;

import it.register.tech.exercise.one.model.ExportFormat;
import it.register.tech.exercise.one.model.LogDetail;
import it.register.tech.exercise.one.model.LogSummary;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LogManager {

    private final LogImporter logImporter;
    private final LogExporter logExporter;
    private final LogDetailsMapper logDetailsMapper;

    public LogManager(LogImporter logImporter, LogDetailsMapper logDetailsMapper, LogExporter logExporter) {
        this.logImporter = logImporter;
        this.logDetailsMapper = logDetailsMapper;
        this.logExporter = logExporter;
    }

    public void summarizeAndPrintLogs(String fileImportPath, String fileExportPath, ExportFormat exportFormat) {
        try {
            List<LogDetail> logDetails = this.logImporter.importLogDetails(fileImportPath);
            List<LogSummary> logSummaries = logDetailsMapper.mapFrom(logDetails);

            List<LogSummary> sortedLogSummaries = sortBy(logSummaries,Comparator.comparingInt(LogSummary::getRequestNumber).reversed());

            switch (exportFormat) {
                case CSV:
                    logExporter.writeOnFileInCSVFormat(fileExportPath, sortedLogSummaries);
                    break;
                case JSON:
                    logExporter.writeOnFileInJsonFormat(fileExportPath, sortedLogSummaries);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<LogSummary> sortBy(List<LogSummary> logSummaries, Comparator<LogSummary> comparator) {
        return logSummaries.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}
