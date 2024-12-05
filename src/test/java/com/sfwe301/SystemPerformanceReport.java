package com.sfwe301;

public class SystemPerformanceReport {
    private String metricName;
    private String metricValue;
    private String status;

    public SystemPerformanceReport(String metricName, String metricValue, String status) {
        this.metricName = metricName;
        this.metricValue = metricValue;
        this.status = status;
    }

    public SystemPerformanceReport() {
        this.metricName = "";
        this.metricValue = "";
        this.status = "";
    }

    public String getMetricName() {
        return metricName;
    }

    public String getMetricValue() {
        return metricValue;
    }

    public String getStatus() {
        return status;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public void setMetricValue(String metricValue) {
        this.metricValue = metricValue;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
