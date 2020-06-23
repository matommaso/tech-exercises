package it.register.tech.exercise.one;

public class LogManagerMain {
    public static void main(String[] args) {


        String fileImportPath = "src/test/resources/logfiles/requests.log";
        String fileExportPath = "src/test/resources/reports/ipaddr.csv";
        LogManager logManager = new LogManager();
        logManager.SummarizeAndPrintLogs(fileImportPath,fileExportPath,ExportFormat.CSV);

    }
}

