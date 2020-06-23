package it.register.tech.exercise.one;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

class LogExporterTest {

    LogExporter logExporterTest = new LogExporter();

    @Test
    public void shouldWriteLogSummariesOnFileInCSVFormat() throws IOException {

        String filePath = "src/test/resources/reports/ipaddr.csv";

        List<LogSummary> logSummariesTest = Arrays.asList(
                new LogSummary("127.0.0.1", 2, 2 / 20D, 200, 200 / 2000D),
                new LogSummary("127.0.0.2", 8, 8 / 20D, 800, 800 / 2000D),
                new LogSummary("127.0.0.3", 10, 10 / 20D, 1000, 1000 / 2000D));

        logExporterTest.writeOnFileInCSVFormat(filePath, logSummariesTest);

        List<String> lines = Files.readAllLines(Paths.get(filePath));

        assertThat(lines, containsInAnyOrder("127.0.0.3,10,0.5,1000,0.5", "127.0.0.2,8,0.4,800,0.4", "127.0.0.1,2,0.1,200,0.1"));
    }

    @Test
    public void shouldWriteLogSummariesOnFileInJsonFormat() throws IOException {

        String filePath = "src/test/resources/reports/ipaddr.csv";
        List<LogSummary> logSummariesTest = Arrays.asList(
                new LogSummary("127.0.0.1", 2, 2 / 20D, 200, 200 / 2000D));

        logExporterTest.writeOnFileInJsonFormat(filePath, logSummariesTest);
        String result = String.join("", Files.readAllLines(Paths.get(filePath)));

        String expectedResult = "[{\"remoteAddress\":\"127.0.0.1\",\"requestNumber\":2,\"percentageRequest\":0.1,\"totalBytes\":200,\"percentageTotalBytes\":0.1}]";
        assertThat(result, equalTo(expectedResult));
    }
}