package it.register.tech.exercise.one;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;


public class LogManagerTest {

    LogManager logManagerTest = new LogManager();
    String pathImportFileTest = "src/test/resources/logfiles/requests.log";
    LogDetail expectedLogDetail01 = new LogDetail(1591391957001L, 101L, 400, "127.0.0.1");
    LogDetail expectedLogDetail02 = new LogDetail(1591391957002L, 102L, 401, "127.0.0.1");
    LogDetail expectedLogDetailWithStatusOK = new LogDetail(1591391957003L, 102L, 200, "127.0.0.1");

    @Test
    public void shouldImportLogDetailsFromFile() {
        List<LogDetail> actualLogDetails = logManagerTest.importLogDetails(pathImportFileTest);
        assertThat(actualLogDetails, containsInAnyOrder(expectedLogDetail01, expectedLogDetail02, expectedLogDetailWithStatusOK));
    }

    @Test
    public void shouldImportLogDetailsFromFileWithEmptyLine() {
        String pathImportFileTestWithEmptyLine = "src/test/resources/logfiles/requestsWithEmptyLine.log";
        List<LogDetail> actualLogDetails = logManagerTest.importLogDetails(pathImportFileTestWithEmptyLine);
        assertThat(actualLogDetails, containsInAnyOrder(expectedLogDetail01, expectedLogDetail02, expectedLogDetailWithStatusOK));
    }

    @Test
    public void shouldImportLogDetailsFromFileWithWhiteSpace() {
        String pathImportFileTestWithWhiteSpace = "src/test/resources/logfiles/requestsWithWhiteSpace.log";
        List<LogDetail> actualLogDetails = logManagerTest.importLogDetails(pathImportFileTestWithWhiteSpace);
        assertThat(actualLogDetails, containsInAnyOrder(expectedLogDetail01, expectedLogDetail02, expectedLogDetailWithStatusOK));
    }

    @Test
    public void shouldMapLogDetailsInLogSummary() {

        List<LogDetail> logDetails = new ArrayList<LogDetail>();
        logDetails.add(expectedLogDetail01);
        logDetails.add(expectedLogDetail02);

        List<LogSummary> actualLogSummaries = logManagerTest.mapFrom(logDetails);

        LogSummary logSummary01 = new LogSummary("127.0.0.1", 2, 1, 203, 1);

        assertThat(actualLogSummaries, containsInAnyOrder(logSummary01));
    }

    @Test
    public void shouldMapLogDetailsInLogSummaryWithNotOkStatus() {

        List<LogDetail> logDetails = new ArrayList<LogDetail>();
        logDetails.add(expectedLogDetail01);
        logDetails.add(expectedLogDetail02);
        logDetails.add(expectedLogDetailWithStatusOK);

        List<LogSummary> actualLogSummaries = logManagerTest.mapFrom(logDetails);

        LogSummary logSummary01 = new LogSummary("127.0.0.1", 2, 1, 203, 1);

        assertThat(actualLogSummaries, containsInAnyOrder(logSummary01));
    }

    @Test
    public void shouldWriteLogSummariesOnFileInCSVFormat() throws IOException {

        String filePath = "src/test/resources/reports/ipaddr.csv";

        List<LogSummary> logSummariesTest = Arrays.asList(
                new LogSummary("127.0.0.1", 2, 2 / 20D, 200, 200 / 2000D),
                new LogSummary("127.0.0.2", 8, 8 / 20D, 800, 800 / 2000D),
                new LogSummary("127.0.0.3", 10, 10 / 20D, 1000, 1000 / 2000D));

        logManagerTest.writeOnFileInCSVFormat(filePath, logSummariesTest);

        List<String> lines = Files.readAllLines(Paths.get(filePath));

        assertThat(lines, containsInAnyOrder("127.0.0.3,10,0.5,1000,0.5", "127.0.0.2,8,0.4,800,0.4", "127.0.0.1,2,0.1,200,0.1"));
    }

    @Test
    public void shouldWriteLogSummariesOnFileInJsonFormat() throws IOException {

        String filePath = "src/test/resources/reports/ipaddr.csv";
        List<LogSummary> logSummariesTest = Arrays.asList(
                new LogSummary("127.0.0.1", 2, 2 / 20D, 200, 200 / 2000D));

        logManagerTest.writeOnFileInJsonFormat(filePath, logSummariesTest);
        String result = String.join("", Files.readAllLines(Paths.get(filePath)));

        String expectedResult = "[{\"remoteAddress\":\"127.0.0.1\",\"requestNumber\":2,\"percentageRequest\":0.1,\"totalBytes\":200,\"percentageTotalBytes\":0.1}]";
        assertThat(result, equalTo(expectedResult));
    }
}