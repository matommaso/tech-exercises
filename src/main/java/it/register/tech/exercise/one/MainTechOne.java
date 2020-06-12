package it.register.tech.exercise.one;

import java.io.IOException;
import java.util.List;

public class MainTechOne {
    public static void main(String[] args) {

       try {
           String pathImportFileTest = "src/test/resources/logfiles/requests.log";
           String filePath = "src/test/resources/reports/ipaddr.csv";
           LogManager logManager = new LogManager();

           List<LogDetail> logDetails = logManager.importLogDetails(pathImportFileTest);
           List<LogSummary> logSummaries = logManager.mapFrom(logDetails);
           logManager.saveOnFile(filePath, logSummaries);

    } catch (  Exception e) {
        e.printStackTrace();
    }

    }
}
