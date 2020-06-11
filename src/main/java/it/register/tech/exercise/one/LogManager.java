package it.register.tech.exercise.one;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

public class LogManager { //TODO: check name
    public List<LogDetail> importLogDetails(String pathImportFileTest) {//TODO: check name

        String cvsSplitBy = ";";
        List<LogDetail> logDetails = new ArrayList<LogDetail>();

        try (BufferedReader br = new BufferedReader(new FileReader(pathImportFileTest))) {
            String row;
            while ((row = br.readLine()) != null) {
                String[] logDetailArray = preProcess(row).split(cvsSplitBy);
                logDetails.add(new LogDetail(logDetailArray[0], logDetailArray[1], logDetailArray[2], logDetailArray[3]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return logDetails;
    }


    public List<LogSummary> mapFrom(List<LogDetail> logDetails) {

        List<LogSummary> logSummaries = new ArrayList<LogSummary>();

        int totalRequests = logDetails.size();
        int totalBytes = logDetails.stream().map(logDetail -> Integer.parseInt(logDetail.getBytes())).reduce(0, (a, b) -> a + b);

        Map<String, Set<LogDetail>> logSummariesMap = logDetails.stream().collect(groupingBy(LogDetail::getRemoteAddress, toSet())); //TODO: set or list?

        for (Map.Entry<String, Set<LogDetail>> entry : logSummariesMap.entrySet()) {
            String remoteAddress = entry.getKey();
            int requestNumber = entry.getValue().size();
            int bytes = entry.getValue().stream().map(logDetail -> Integer.parseInt(logDetail.getBytes())).reduce(0, (a, b) -> a + b);


            logSummaries.add(new LogSummary(remoteAddress,requestNumber,requestNumber/totalRequests,bytes,bytes/totalBytes));
        }


        return logSummaries;
    }

    private String preProcess(String row) {
        return row.toLowerCase().replace(" ", "");
    }
}
