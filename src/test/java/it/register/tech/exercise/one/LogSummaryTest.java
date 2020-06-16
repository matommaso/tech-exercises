package it.register.tech.exercise.one;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class LogSummaryTest {

    @Test
    void shouldCreateACSVString() {
        LogSummary logSummary = new LogSummary("127.0.0.1", 2, 1, 203, 1);

        String actualLogSummaryInCSV = logSummary.toCSV();

        assertThat(actualLogSummaryInCSV, equalTo("127.0.0.1,2,1.0,203,1.0"));
    }
}