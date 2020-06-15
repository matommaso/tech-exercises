package it.register.tech.exercise.one;

import java.util.Objects;

public class LogSummary {


    private final String remoteAddress;
    private final int requestNumber;
    private final int percentageRequest;
    private final long totalBytes;
    private final int percentageTotalBytes;

    public LogSummary(String remoteAddress, int requestNumber, int percentageRequest, long totalBytes, int percentageTotalBytes) {
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
                + "," + requestNumber
                + "," + percentageRequest
                + "," + totalBytes
                + "," + percentageTotalBytes;
    }
}
