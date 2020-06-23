package it.register.tech.exercise.one;

import com.opencsv.exceptions.CsvValidationException;
import it.register.tech.exercise.one.model.ExportFormat;
import it.register.tech.exercise.one.model.LogDetail;
import it.register.tech.exercise.one.model.LogSummary;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;


public class LogManagerTest {

    LogImporter logImporterMock = mock(LogImporter.class);
    LogDetailsMapper logDetailsMapperMock = mock(LogDetailsMapper.class);
    LogExporter logExporterMock = mock(LogExporter.class);

    LogManager logManagerTest = new LogManager(logImporterMock, logDetailsMapperMock, logExporterMock);

    String fileImportPathTest = "fileImportPathTest";
    String fileExportPathTest = "fileExportPathTest";

    LogDetail expectedLogDetail01 = new LogDetail(1591391957001L, 101L, 400, "127.0.0.1");
    LogDetail expectedLogDetail02 = new LogDetail(1591391957002L, 102L, 401, "127.0.0.1");
    LogDetail expectedLogDetailWithStatusOK = new LogDetail(1591391957003L, 102L, 200, "127.0.0.1");
    LogDetail expectedLogDetail03 = new LogDetail(1591391957009L, 109L, 401, "127.0.0.9");


    @Test
    public void shouldSummarizeLogsAndCallCSVPrinter() throws IOException {

        ExportFormat exportFormat = ExportFormat.CSV;

        List<LogDetail> logDetails = new ArrayList<>();
        logDetails.add(expectedLogDetail01);
        logDetails.add(expectedLogDetail03);
        logDetails.add(expectedLogDetail02);
        logDetails.add(expectedLogDetailWithStatusOK);

        List<LogSummary> logSummaries = new ArrayList<>();
        logSummaries.add(new LogSummary("127.0.0.9", 1, 0.33, 109, 0.35));
        logSummaries.add(new LogSummary("127.0.0.1", 2, 0.67, 203, 0.65));


        List<LogSummary> sortedLogSummaries = new ArrayList<>();
        sortedLogSummaries.add(new LogSummary("127.0.0.1", 2, 0.67, 203, 0.65));
        sortedLogSummaries.add(new LogSummary("127.0.0.9", 1, 0.33, 109, 0.35));

        when(logImporterMock.importLogDetails(fileImportPathTest)).thenReturn(logDetails);
        when(logDetailsMapperMock.mapFrom(logDetails)).thenReturn(logSummaries);


        logManagerTest.summarizeAndPrintLogs(fileImportPathTest, fileExportPathTest, exportFormat);

        verify(logExporterMock).writeOnFileInCSVFormat(fileExportPathTest, sortedLogSummaries);
    }

    @Test
    public void shouldSummarizeLogsAndCallJsonPrinter() throws IOException {

        ExportFormat exportFormat = ExportFormat.JSON;

        List<LogDetail> logDetails = new ArrayList<>();
        logDetails.add(expectedLogDetail01);
        logDetails.add(expectedLogDetail03);
        logDetails.add(expectedLogDetail02);
        logDetails.add(expectedLogDetailWithStatusOK);

        List<LogSummary> logSummaries = new ArrayList<>();
        logSummaries.add(new LogSummary("127.0.0.9", 1, 0.33, 109, 0.35));
        logSummaries.add(new LogSummary("127.0.0.1", 2, 0.67, 203, 0.65));

        List<LogSummary> sortedLogSummaries = new ArrayList<>();
        sortedLogSummaries.add(new LogSummary("127.0.0.1", 2, 0.67, 203, 0.65));
        sortedLogSummaries.add(new LogSummary("127.0.0.9", 1, 0.33, 109, 0.35));

        when(logImporterMock.importLogDetails(fileImportPathTest)).thenReturn(logDetails);
        when(logDetailsMapperMock.mapFrom(logDetails)).thenReturn(logSummaries);

        logManagerTest.summarizeAndPrintLogs(fileImportPathTest, fileExportPathTest, exportFormat);

        verify(logExporterMock).writeOnFileInJsonFormat(fileExportPathTest, sortedLogSummaries);
    }
}