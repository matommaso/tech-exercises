package it.register.tech.exercise.one;

import com.opencsv.exceptions.CsvValidationException;
import it.register.tech.exercise.one.model.LogDetail;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

class LogImporterTest {

    LogImporter logImporterTest = new LogImporter();
    String fileImportPath = "src/test/resources/logfiles/requests.log";
    LogDetail expectedLogDetail01 = new LogDetail(1591391957001L, 101L, 400, "127.0.0.1");
    LogDetail expectedLogDetail02 = new LogDetail(1591391957002L, 102L, 401, "127.0.0.1");
    LogDetail expectedLogDetailWithStatusOK = new LogDetail(1591391957003L, 102L, 200, "127.0.0.1");

    @Test
    public void shouldImportLogDetailsFromFile() throws IOException, CsvValidationException {
        List<LogDetail> actualLogDetails = logImporterTest.importLogDetails(fileImportPath);
        assertThat(actualLogDetails, containsInAnyOrder(expectedLogDetail01, expectedLogDetail02, expectedLogDetailWithStatusOK));
    }

    @Test
    public void shouldImportLogDetailsFromFileWithEmptyLine() throws IOException {
        String pathImportFileTestWithEmptyLine = "src/test/resources/logfiles/requestsWithEmptyLine.log";
        List<LogDetail> actualLogDetails = logImporterTest.importLogDetails(pathImportFileTestWithEmptyLine);
        assertThat(actualLogDetails, containsInAnyOrder(expectedLogDetail01, expectedLogDetail02, expectedLogDetailWithStatusOK));
    }

    @Test
    public void shouldImportLogDetailsFromFileWithWhiteSpace() throws IOException {
        String pathImportFileTestWithWhiteSpace = "src/test/resources/logfiles/requestsWithWhiteSpace.log";
        List<LogDetail> actualLogDetails = logImporterTest.importLogDetails(pathImportFileTestWithWhiteSpace);
        assertThat(actualLogDetails, containsInAnyOrder(expectedLogDetail01, expectedLogDetail02, expectedLogDetailWithStatusOK));
    }


}