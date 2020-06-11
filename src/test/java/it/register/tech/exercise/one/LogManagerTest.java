package it.register.tech.exercise.one;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;


public class LogManagerTest {

    LogManager logManagerTest = new LogManager();
    String pathImportFileTest = "src/test/resources/logfiles/requests.log";
    LogDetail expectedLogDetail01 = new LogDetail("1591391957001", "101", "ok", "127.0.0.1");
    LogDetail expectedLogDetail02 = new LogDetail("1591391957002", "102", "ok", "127.0.0.1");

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
}