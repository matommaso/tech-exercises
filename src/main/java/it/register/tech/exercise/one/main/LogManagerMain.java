package it.register.tech.exercise.one.main;

import it.register.tech.exercise.one.LogDetailsMapper;
import it.register.tech.exercise.one.LogExporter;
import it.register.tech.exercise.one.LogImporter;
import it.register.tech.exercise.one.LogManager;
import it.register.tech.exercise.one.model.ExportFormat;

public class LogManagerMain {
    public static void main(String[] args) {

        String fileImportPath = "src/test/resources/logfiles/requests.log";
        String fileExportPath = "src/test/resources/reports/ipaddr.csv";
        LogManager logManager = new LogManager(new LogImporter(), new LogDetailsMapper(),new LogExporter());
        logManager.summarizeAndPrintLogs(fileImportPath,fileExportPath, ExportFormat.CSV);
    }
}

