package it.register.tech.exercise.one;

import java.util.Objects;

public class LogDetail {
    private final String timestamp; //TODO: Long?
    private final String bytes;
    private final String status;
    private final String remoteAddress;


    public LogDetail(String timestamp, String bytes, String status, String remoteAddress) {
        this.timestamp = timestamp;
        this.bytes = bytes;
        this.status = status;
        this.remoteAddress = remoteAddress;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getBytes() {
        return bytes;
    }

    public String getStatus() {
        return status;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    @Override
    public String toString() {
        return "LogDetail{" +
                "timestamp=" + timestamp +
                ", bytes=" + bytes +
                ", status='" + status + '\'' +
                ", remoteAddress='" + remoteAddress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogDetail logDetail = (LogDetail) o;
        return Objects.equals(timestamp, logDetail.timestamp) &&
                Objects.equals(bytes, logDetail.bytes) &&
                Objects.equals(status, logDetail.status) &&
                Objects.equals(remoteAddress, logDetail.remoteAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, bytes, status, remoteAddress);
    }
}
