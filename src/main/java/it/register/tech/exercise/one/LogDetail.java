package it.register.tech.exercise.one;

import com.opencsv.bean.CsvBindByPosition;

import java.util.Objects;

public class LogDetail {
    @CsvBindByPosition(position = 0, required = true)
    private long timestamp;

    @CsvBindByPosition(position = 1, required = true)
    private long bytes;

    @CsvBindByPosition(position = 2, required = true)
    private int status;

    @CsvBindByPosition(position = 3, required = true)
    private String remoteAddress;

    public void LogDetail() {
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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getBytes() {
        return bytes;
    }

    public void setBytes(long bytes) {
        this.bytes = bytes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }
}
