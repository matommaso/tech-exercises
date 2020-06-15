package it.register.tech.exercise.one;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class LogManager {

    public static final char SEMICOLON_SEPARATOR = ';';
    public static final int STATUS_OK = 200;

    public List<LogDetail> importLogDetails(String pathFileToImport) {

        List<LogDetail> logDetails = new ArrayList<>();
        try {
            Reader reader = Files.newBufferedReader(Paths.get(pathFileToImport));
            CsvToBean<LogDetail> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(LogDetail.class)
                    .withSeparator(SEMICOLON_SEPARATOR)
                    .withIgnoreQuotations(true)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            logDetails = csvToBean.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return logDetails;
    }


    public List<LogSummary> mapFrom(List<LogDetail> logDetails) {

        List<LogDetail> filteredLogDetails = logDetails.stream().filter(logDetail -> logDetail.getStatus() !=(STATUS_OK)).collect(toList());

        int totalRequests = filteredLogDetails.size();
        long totalBytes = filteredLogDetails.stream().map(logDetail -> logDetail.getBytes()).reduce(Long::sum).get();

        Map<String, List<LogDetail>> logSummariesMap = filteredLogDetails.stream().collect(groupingBy(LogDetail::getRemoteAddress, toList()));

        List<LogSummary> logSummaries = new ArrayList<LogSummary>();
        for (Map.Entry<String, List<LogDetail>> entry : logSummariesMap.entrySet()) {
            String remoteAddress = entry.getKey();
            int requestNumber = entry.getValue().size();
            long bytes = entry.getValue().stream().map(logDetail -> logDetail.getBytes()).reduce(Long::sum).get();

            logSummaries.add(new LogSummary(remoteAddress, requestNumber, requestNumber / totalRequests, bytes, (int)(bytes / totalBytes)));
        }

        return logSummaries;
    }

    private String preProcess(String row) {
        return row.toLowerCase().replace(" ", "");
    }

    public void saveOnFile(String filePath, List<LogSummary> logSummariesTest) {

        try {
            List<String> lines = logSummariesTest.stream().map(logSummary -> logSummary.toCSV()).collect(toList());
            Files.write(Paths.get(filePath), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
