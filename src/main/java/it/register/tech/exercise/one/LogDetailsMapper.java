package it.register.tech.exercise.one;

import it.register.tech.exercise.one.model.LogDetail;
import it.register.tech.exercise.one.model.LogSummary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class LogDetailsMapper {
    public static final int STATUS_OK = 200;

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
