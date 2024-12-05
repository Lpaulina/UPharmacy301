package com.sfwe301;

public class InventoryReport {
    private String medicationId;
    private String medicationName;
    private String expirationDate;
    private int daysUntilExpiration;

    public InventoryReport(String medicationId, String medicationName, String expirationDate, int daysUntilExpiration) {
        this.medicationId = medicationId;
        this.medicationName = medicationName;
        this.expirationDate = expirationDate;
        this.daysUntilExpiration = daysUntilExpiration;
    }

    public InventoryReport() {
        this.medicationId = "";
        this.medicationName = "";
        this.expirationDate = "";
        this.daysUntilExpiration = 0;
    }

    public String getMedicationId() {
        return medicationId;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public int getDaysUntilExpiration() {
        return daysUntilExpiration;
    }

    public void setMedicationId(String medicationId) {
        this.medicationId = medicationId;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setDaysUntilExpiration(int daysUntilExpiration) {
        this.daysUntilExpiration = daysUntilExpiration;
    }
}
