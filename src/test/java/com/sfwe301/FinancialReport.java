package com.sfwe301;

public class FinancialReport {
    private String transactionId;
    private String time;
    private String medName;
    private double dosePrice;
    private double doseCost;
    private int quantitySold;

    public FinancialReport(String transactionId, String time, String medName, double dosePrice, double doseCost, int quantitySold){
        this.transactionId = transactionId;
        this.time = time;
        this.medName = medName;
        this.dosePrice = dosePrice;
        this.doseCost = doseCost;
        this.quantitySold = quantitySold;
    }

    public FinancialReport(){
        this.transactionId = "";
        this.time = "";
        this.medName = "";
        this.dosePrice = 0.0;
        this.doseCost = 0.0;
        this.quantitySold = 0;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getTime() {
        return time;
    }

    public String getMedName() {
        return medName;
    }

    public double getDosePrice() {
        return dosePrice;
    }

    public double getDoseCost() {
        return doseCost;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public void setDosePrice(double dosePrice) {
        this.dosePrice = dosePrice;
    }

    public void setDoseCost(double doseCost) {
        this.doseCost = doseCost;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

}
