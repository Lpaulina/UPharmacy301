package com.sfwe301;

public class FeedbackReport {
    private String patientId;
    private String feedbackDate;
    private int feedbackRating;
    private String writtenReview;

    public FeedbackReport(String patientId, String feedbackDate, int feedbackRating, String writtenReview) {
        this.patientId = patientId;
        this.feedbackDate = feedbackDate;
        this.feedbackRating = feedbackRating;
        this.writtenReview = writtenReview;
    }

    public FeedbackReport() {
        this.patientId = "";
        this.feedbackDate = "";
        this.feedbackRating = 0;
        this.writtenReview = "";
    }

    public String getPatientId() {
        return patientId;
    }

    public String getFeedbackDate() {
        return feedbackDate;
    }

    public int getFeedbackRating() {
        return feedbackRating;
    }

    public String getWrittenReview() {
        return writtenReview;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setFeedbackDate(String feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public void setFeedbackRating(int feedbackRating) {
        this.feedbackRating = feedbackRating;
    }

    public void setWrittenReview(String writtenReview) {
        this.writtenReview = writtenReview;
    }
}
