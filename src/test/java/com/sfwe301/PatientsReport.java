package com.sfwe301;

public class PatientsReport {
    private String patientId;
    private String patientName;
    private int prescriptionsFilled;
    private String changesMade;
    private int age;
    private String gender;
    private String insuranceCoverage;
    private String loyaltyProgramStatus;

    public PatientsReport(String patientId, String patientName, int prescriptionsFilled, String changesMade, int age, String gender, String insuranceCoverage, String loyaltyProgramStatus) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.prescriptionsFilled = prescriptionsFilled;
        this.changesMade = changesMade;
        this.age = age;
        this.gender = gender;
        this.insuranceCoverage = insuranceCoverage;
        this.loyaltyProgramStatus = loyaltyProgramStatus;
    }

    public PatientsReport() {
        this.patientId = "";
        this.patientName = "";
        this.prescriptionsFilled = 0;
        this.changesMade = "";
        this.age = 0;
        this.gender = "";
        this.insuranceCoverage = "";
        this.loyaltyProgramStatus = "";
    }

    public String getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public int getPrescriptionsFilled() {
        return prescriptionsFilled;
    }

    public String getChangesMade() {
        return changesMade;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getInsuranceCoverage() {
        return insuranceCoverage;
    }

    public String getLoyaltyProgramStatus() {
        return loyaltyProgramStatus;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setPrescriptionsFilled(int prescriptionsFilled) {
        this.prescriptionsFilled = prescriptionsFilled;
    }

    public void setChangesMade(String changesMade) {
        this.changesMade = changesMade;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setInsuranceCoverage(String insuranceCoverage) {
        this.insuranceCoverage = insuranceCoverage;
    }

    public void setLoyaltyProgramStatus(String loyaltyProgramStatus) {
        this.loyaltyProgramStatus = loyaltyProgramStatus;
    }
}
