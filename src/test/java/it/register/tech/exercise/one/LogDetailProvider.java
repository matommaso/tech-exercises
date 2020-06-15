package it.register.tech.exercise.one;

public class LogDetailProvider {
    public static LogDetail getLogDetail(long timestamp, long bytes, int status, String remoteAddress) {

        LogDetail logDetail = new LogDetail();
        logDetail.setTimestamp(timestamp);
        logDetail.setBytes(bytes);
        logDetail.setStatus(status);
        logDetail.setRemoteAddress(remoteAddress);

        return logDetail;
    }
}
