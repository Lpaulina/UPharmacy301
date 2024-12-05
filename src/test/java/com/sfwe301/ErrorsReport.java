package com.sfwe301;

public class ErrorsReport {

    private String patientId;
    private String feedbackDate;
    private String prescriptionError;
    private String causeOfError;
    private String correctiveActionTaken;

    public ErrorsReport(String patientId, String feedbackDate, String prescriptionError, String causeOfError, String correctiveActionTaken) {
        this.patientId = patientId;
        this.feedbackDate = feedbackDate;
        this.prescriptionError = prescriptionError;
        this.causeOfError = causeOfError;
        this.correctiveActionTaken = correctiveActionTaken;
    }

    public ErrorsReport() {
        this.patientId = "";
        this.feedbackDate = "";
        this.prescriptionError = "";
        this.causeOfError = "";
        this.correctiveActionTaken = "";
    }

    public String getPatientId() {
        return patientId;
    }

    public String getFeedbackDate() {
        return feedbackDate;
    }

    public String getPrescriptionError() {
        return prescriptionError;
    }

    public String getCauseOfError() {
        return causeOfError;
    }

    public String getCorrectiveActionTaken() {
        return correctiveActionTaken;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setFeedbackDate(String feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public void setPrescriptionError(String prescriptionError) {
        this.prescriptionError = prescriptionError;
    }

    public void setCauseOfError(String causeOfError) {
        this.causeOfError = causeOfError;
    }

    public void setCorrectiveActionTaken(String correctiveActionTaken) {
        this.correctiveActionTaken = correctiveActionTaken;
    }
}
