package it.register.tech.exercise.one.model;

import java.util.Objects;


public class LogSummary {
    public static final String COMMA_SEPARATOR = ",";
    private String remoteAddress;
    private int requestNumber;
    private double percentageRequest;
    private long totalBytes;
    private double percentageTotalBytes;

    public LogSummary(String remoteAddress, int requestNumber, double percentageRequest, long totalBytes, double percentageTotalBytes) {
        this.remoteAddress = remoteAddress;
        this.requestNumber = requestNumber;
        this.percentageRequest = percentageRequest;
        this.totalBytes = totalBytes;
        this.percentageTotalBytes = percentageTotalBytes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogSummary that = (LogSummary) o;
        return requestNumber == that.requestNumber &&
                percentageRequest == that.percentageRequest &&
                totalBytes == that.totalBytes &&
                percentageTotalBytes == that.percentageTotalBytes &&
                remoteAddress.equals(that.remoteAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(remoteAddress, requestNumber, percentageRequest, totalBytes, percentageTotalBytes);
    }

    public String toCSV() {
        return remoteAddress
                + COMMA_SEPARATOR + requestNumber
                + COMMA_SEPARATOR + percentageRequest
                + COMMA_SEPARATOR + totalBytes
                + COMMA_SEPARATOR + percentageTotalBytes;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public int getRequestNumber() {
        return requestNumber;
    }

    public double getPercentageRequest() {
        return percentageRequest;
    }

    public long getTotalBytes() {
        return totalBytes;
    }

    public double getPercentageTotalBytes() {
        return percentageTotalBytes;
    }

    public void setPercentageTotalBytes(double percentageTotalBytes) {
        this.percentageTotalBytes = percentageTotalBytes;
    }
}
