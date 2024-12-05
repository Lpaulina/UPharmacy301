package com.sfwe301;

public class InsuranceReport {
    private String patientId;
    private String claimDate;
    private String insuranceClaimId;
    private String claimDescription;
    private double claimAmount;
    private String claimStatus;

    public InsuranceReport(String patientId, String claimDate, String insuranceClaimId, String claimDescription, double claimAmount, String claimStatus) {
        this.patientId = patientId;
        this.claimDate = claimDate;
        this.insuranceClaimId = insuranceClaimId;
        this.claimDescription = claimDescription;
        this.claimAmount = claimAmount;
        this.claimStatus = claimStatus;
    }

    public InsuranceReport() {
        this.patientId = "";
        this.claimDate = "";
        this.insuranceClaimId = "";
        this.claimDescription = "";
        this.claimAmount = 0.0;
        this.claimStatus = "";
    }

    public String getPatientId() {
        return patientId;
    }

    public String getClaimDate() {
        return claimDate;
    }

    public String getInsuranceClaimId() {
        return insuranceClaimId;
    }

    public String getClaimDescription() {
        return claimDescription;
    }

    public double getClaimAmount() {
        return claimAmount;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setClaimDate(String claimDate) {
        this.claimDate = claimDate;
    }

    public void setInsuranceClaimId(String insuranceClaimId) {
        this.insuranceClaimId = insuranceClaimId;
    }

    public void setClaimDescription(String claimDescription) {
        this.claimDescription = claimDescription;
    }

    public void setClaimAmount(double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }
}
