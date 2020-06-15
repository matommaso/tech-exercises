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


public class LogManagerTest {

    LogManager logManagerTest = new LogManager();
    String pathImportFileTest = "src/test/resources/logfiles/requests.log";
    LogDetail expectedLogDetail01 = LogDetailProvider.getLogDetail(1591391957001L, 101L, 400, "127.0.0.1");
    LogDetail expectedLogDetail02 =  LogDetailProvider.getLogDetail(1591391957002L, 102L, 401, "127.0.0.1");
    LogDetail expectedLogDetailWithStatusOK =  LogDetailProvider.getLogDetail(1591391957003L, 102L, 200, "127.0.0.1");

    @Test
    public void shouldImportLogDetailsFromFile() {
        List<LogDetail> actualLogDetails = logManagerTest.importLogDetails(pathImportFileTest);
        assertThat(actualLogDetails, containsInAnyOrder(expectedLogDetail01, expectedLogDetail02));
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
    public void shouldPrintLogSummariesOnFile() throws IOException {

        String filePath = "src/test/resources/reports/ipaddr.csv";

        List<LogSummary> logSummariesTest = Arrays.asList(new LogSummary("127.0.0.1", 2, 1, 203, 1));

        logManagerTest.saveOnFile(filePath, logSummariesTest);

        List<String> lines = Files.readAllLines(Paths.get(filePath));

        assertThat(lines, containsInAnyOrder("127.0.0.1,2,1,203,1"));

    }
}