package it.register.tech.exercise.one;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class LogManager {

    public static final int STATUS_OK = 200;

    private final LogImporter logImporter;
    private final LogExporter logExporter;

    public LogManager() {
        this.logImporter = new LogImporter();
        this.logExporter = new LogExporter();
    }

    public void SummarizeAndPrintLogs(String fileImportPath, String fileExportPath, ExportFormat exportFormat) {
        try {
            List<LogDetail> logDetails = this.logImporter.importLogDetails(fileImportPath);
            List<LogSummary> logSummaries = mapFrom(logDetails);

            switch (exportFormat) {
                case CSV:
                    logExporter.writeOnFileInCSVFormat(fileExportPath, logSummaries);
                    break;
                case JSON:
                    logExporter.writeOnFileInJsonFormat(fileExportPath, logSummaries);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
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
}
