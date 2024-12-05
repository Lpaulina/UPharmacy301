package com.sfwe301;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class ReportGenerator {
    public static void main(String[] args) throws IOException {
        int reportChoice;

        Scanner scnr = new Scanner(System.in);
        System.out.println("Please select a report to generate:");
        System.out.println("1. Inventory Report");
        System.out.println("2. Financial Report");
        System.out.println("3. Feedback Report");
        System.out.println("4. Errors Report");
        System.out.println("5. Insurance Report");
        System.out.println("6. System Performance Report");
        System.out.println("7. Patient Report");

        reportChoice = scnr.nextInt();

        if (reportChoice == 1) {
            generateInventoryReport();
        } 
        else if (reportChoice == 2) {
            generateFinancialReport();
        } 
        else if (reportChoice == 3) {
            generateFeedbackReport();
        } 
        else if (reportChoice == 4) {
            generateErrorsReport();
        } 
        else if (reportChoice == 5) {
            generateInsuranceReport();
        } 
        else if (reportChoice == 6) {
            generateSystemPerformanceReport();
        } 
        else if (reportChoice == 7) {
            generatePatientReport();
        }
        else {
            System.out.println("Invalid choice. Please select a valid report number.");
        }
    }

    public static void generateInventoryReport() throws IOException {
        int lineCount = 40; 
        InventoryReport[] reports = new InventoryReport[lineCount];

        FileInputStream fileByteStream = new FileInputStream("inventoryReportInfo.csv");
        Scanner inFS = new Scanner(fileByteStream);

        inFS.nextLine();

        int index = 0;

        while (inFS.hasNext()) {
            String id = inFS.next();
            String name = inFS.next();
            String date = inFS.next();
            int days = inFS.nextInt();

            InventoryReport report = new InventoryReport();
            report.setMedicationId(id);
            report.setMedicationName(name);
            report.setExpirationDate(date);
            report.setDaysUntilExpiration(days);

            reports[index] = report;
            index++;
        }

        inFS.close();
        fileByteStream.close();

        int advilCount = 0;
        int ibuCount = 0;
        int penCount = 0;
        int lisCount = 0;

        System.out.println("\nINVENTORY REPORT");
        System.out.println("-------------");
        System.out.println("BATCHES APPROACHING EXPIRATION DATE");
        System.out.println();

        for (InventoryReport report : reports) {
            if (report.getMedicationName().equals("Advil")) {
                advilCount += 1;
            }
            else if (report.getMedicationName().equals("Ibuprofen")) {
                ibuCount += 1;
            }
            else if (report.getMedicationName().equals("Penicillin")) {
                penCount += 1;
            }
            else if (report.getMedicationName().equals("Lisinopril")) {
                lisCount += 1;
            }

            if (report.getDaysUntilExpiration() < 21) {
                System.out.println(report.getMedicationName() + ", " + report.getMedicationId() + ", " + report.getDaysUntilExpiration() + " days until expiration");
            }
        }

        int totalStock = advilCount + ibuCount + penCount + lisCount;

        System.out.println("-------------");
        System.out.println("INVENTORY BREAKDOWN") ;
        System.out.println();
        System.out.println("Advil in stock: " + advilCount);
        System.out.println("Ibuprofen in stock: " + ibuCount);
        System.out.println("Penicillin in stock: " + penCount);
        System.out.println("Lisinopril in stock: " + lisCount);
        System.out.println("Total medication in stock: " + totalStock);
        System.out.println("-------------");
        System.out.println("ALL INVENTORY LISTED");
        System.out.println();


        for (InventoryReport report : reports) {
            System.out.println("Medication ID: " + report.getMedicationId());
            System.out.println("Medication Name: " + report.getMedicationName());
            System.out.println("Expiration Date: " + report.getExpirationDate());
            System.out.println("Days until Expiration: " + report.getDaysUntilExpiration());
            System.out.println("-------------");
        }
    }

    public static void generateFinancialReport() throws IOException {
        int lineCount = 15; 
        FinancialReport[] reports = new FinancialReport[lineCount];

        FileInputStream fileByteStream = new FileInputStream("financials.csv");
        Scanner inFS = new Scanner(fileByteStream);

        inFS.nextLine();

        int index = 0;

        while (inFS.hasNext()) {
            String id = inFS.next();
            String time = inFS.next();
            String name = inFS.next();
            double price = inFS.nextDouble();
            double cost = inFS.nextDouble();
            int quantity = inFS.nextInt();

            FinancialReport report = new FinancialReport();
            report.setTransactionId(id);
            report.setTime(time);
            report.setMedName(name);
            report.setDosePrice(price);
            report.setDoseCost(cost);
            report.setQuantitySold(quantity);

            reports[index] = report;
            index++;
        }

        inFS.close();
        fileByteStream.close();

        int numTransactions = 0;
        double totalRevenue = 0.0;
        double totalAdvRevenue = 0.0;
        double totalIbuRevenue = 0.0;
        double totalPenRevenue = 0.0;
        double totalLisRevenue = 0.0;
        int totalSales = 0;

        System.out.println("\nFINANCIAL REPORT");
        System.out.println("-------------");
        System.out.println("REVENUE BY PRODUCT");
        System.out.println();

        for (FinancialReport report : reports) {

            if (report.getMedName().equals("Advil")) {
                double tempAdvRevenue = 0;
                tempAdvRevenue = report.getDosePrice() - report.getDoseCost();
                tempAdvRevenue = tempAdvRevenue * report.getQuantitySold();
                totalAdvRevenue += tempAdvRevenue;
            }
            else if (report.getMedName().equals("Ibuprofen")) {
                double tempIbuRevenue = 0;
                tempIbuRevenue = report.getDosePrice() - report.getDoseCost();
                tempIbuRevenue = tempIbuRevenue * report.getQuantitySold();
                totalIbuRevenue += tempIbuRevenue;
            }
            else if (report.getMedName().equals("Penicillin")) {
                double tempPenRevenue = 0;
                tempPenRevenue = report.getDosePrice() - report.getDoseCost();
                tempPenRevenue = tempPenRevenue * report.getQuantitySold();
                totalPenRevenue += tempPenRevenue;
            }
            else if (report.getMedName().equals("Lisinopril")) {
                double tempLisRevenue = 0;
                tempLisRevenue = report.getDosePrice() - report.getDoseCost();
                tempLisRevenue = tempLisRevenue * report.getQuantitySold();
                totalLisRevenue += tempLisRevenue;
            }
            double tempRevenue = 0;
            numTransactions += 1;
            tempRevenue = report.getDosePrice() - report.getDoseCost();
            tempRevenue = tempRevenue * report.getQuantitySold();
            totalRevenue += tempRevenue;
            totalSales += report.getQuantitySold();
        }

        System.out.println("Advil Daily Revenue: " + totalAdvRevenue);
        System.out.println("Ibuprofen Daily Revenue: " + totalIbuRevenue);
        System.out.println("Penicillin Daily Revenue: " + totalPenRevenue);
        System.out.println("Lisinopril Daily Revenue: " + totalLisRevenue);
        System.out.println("-------------");
        System.out.println("TOTAL SALES, REVENUE, AND TRANSACTIONS");
        System.out.println();
        System.out.println("Today's sales: " + totalSales);
        System.out.println("Today's revenue: " + totalRevenue);
        System.out.println("Number of transactions: " + numTransactions);
        System.out.println("-------------");
        System.out.println("ALL TRANSACTIONS LISTED");
        System.out.println();

        for (FinancialReport report : reports) {
            System.out.println("Transaction ID: " + report.getTransactionId());
            System.out.println("Time: " + report.getTime());
            System.out.println("Medicine Name: " + report.getMedName());
            System.out.println("Quantity: " + report.getQuantitySold());
            System.out.println("-------------");
        }
    }

    public static void generateFeedbackReport() throws IOException {
        int lineCount = 4; 
        FeedbackReport[] reports = new FeedbackReport[lineCount];

        FileInputStream fileByteStream = new FileInputStream("feedback.csv");
        Scanner inFS = new Scanner(fileByteStream);

        inFS.nextLine();

        int index = 0;

        while (inFS.hasNext()) {
            String line = inFS.nextLine();

            String[] fields = line.split(",\\s*");
            String id = fields[0];
            String date = fields[1];
            int rating = Integer.parseInt(fields[2]);
            String review = fields[3];

            FeedbackReport report = new FeedbackReport();
            report.setPatientId(id);
            report.setFeedbackDate(date);
            report.setFeedbackRating(rating);
            report.setWrittenReview(review);

            reports[index] = report;
            index++;
        }

        inFS.close();
        fileByteStream.close();
        int runningCount = 0;
        double averageRating = 0;
        double totalReviews = 0.0;

        for (FeedbackReport report : reports) {
            runningCount = runningCount + report.getFeedbackRating();
            totalReviews += 1.0;
        }

        averageRating = runningCount / totalReviews;

        System.out.println("\nFEEDBACK REPORT");
        System.out.println("-------------");
        System.out.println("Daily Average Satifaction Rating (1-5): " + averageRating);
        System.out.println("-------------");
        System.out.println("ALL FEEDBACK LISTED");
        System.out.println();

        for (FeedbackReport report : reports) {
            System.out.println("Patient ID: " + report.getPatientId());
            System.out.println("Date: " + report.getFeedbackDate());
            System.out.println("Rating (1/5): " + report.getFeedbackRating());
            System.out.println("Review: " + report.getWrittenReview());
            System.out.println("-------------");
        }
    }

    public static void generateErrorsReport() throws IOException {
        int lineCount = 4; 
        ErrorsReport[] reports = new ErrorsReport[lineCount];

        FileInputStream fileByteStream = new FileInputStream("errors.csv");
        Scanner inFS = new Scanner(fileByteStream);

        inFS.nextLine();

        int index = 0;

        while (inFS.hasNext()) {
            String line = inFS.nextLine();

            String[] fields = line.split(",\\s*");
            String id = fields[0];
            String date = fields[1];
            String error = fields[2];
            String cause = fields[3];
            String actionTaken = fields[4];

            ErrorsReport report = new ErrorsReport();
            report.setPatientId(id);
            report.setFeedbackDate(date);
            report.setPrescriptionError(error);
            report.setCauseOfError(cause);
            report.setCorrectiveActionTaken(actionTaken);

            reports[index] = report;
            index++;
        }

        inFS.close();
        fileByteStream.close();

        System.out.println("\nERRORS REPORTED");
        System.out.println();

        for (ErrorsReport report : reports) {
            System.out.println("Patient ID: " + report.getPatientId());
            System.out.println("Date Reported: " + report.getFeedbackDate());
            System.out.println("Error: " + report.getPrescriptionError());
            System.out.println("Cause of Error: " + report.getCauseOfError());
            System.out.println("Corrective Action: " + report.getCorrectiveActionTaken());
            System.out.println("-------------");
        }
    }

    public static void generateInsuranceReport() throws IOException {
        int lineCount = 4; 
        InsuranceReport[] reports = new InsuranceReport[lineCount];

        FileInputStream fileByteStream = new FileInputStream("insurance.csv");
        Scanner inFS = new Scanner(fileByteStream);

        inFS.nextLine();

        int index = 0;

        while (inFS.hasNext()) {
            String line = inFS.nextLine();

            String[] fields = line.split(",\\s*");
            String patientId = fields[0];
            String date = fields[1];
            String claimId = fields[2];
            String description = fields[3];
            double amount = Double.parseDouble(fields[4]);
            String status = fields[5];

            InsuranceReport report = new InsuranceReport();
            report.setPatientId(patientId);
            report.setClaimDate(date);
            report.setInsuranceClaimId(claimId);
            report.setClaimDescription(description);
            report.setClaimAmount(amount);
            report.setClaimStatus(status);

            reports[index] = report;
            index++;
        }

        inFS.close();
        fileByteStream.close();

        System.out.println("\nINSURANCE CLAIM REPORTS");
        System.out.println();
        for (InsuranceReport report : reports) {
            System.out.println("Patient ID: " + report.getPatientId());
            System.out.println("Claim Date: " + report.getClaimDate());
            System.out.println("Claim ID: " + report.getInsuranceClaimId());
            System.out.println("Description: " + report.getClaimDescription());
            System.out.println("Amount: " + report.getClaimAmount());
            System.out.println("Status: " + report.getClaimStatus());
            System.out.println("-------------");
        }
    }

    public static void generateSystemPerformanceReport() throws IOException {
        int lineCount = 3; 
        SystemPerformanceReport[] reports = new SystemPerformanceReport[lineCount];

        FileInputStream fileByteStream = new FileInputStream("systemPerformance.csv");
        Scanner inFS = new Scanner(fileByteStream);

        inFS.nextLine();

        int index = 0;

        while (inFS.hasNext()) {
            String line = inFS.nextLine();

            String[] fields = line.split(",\\s*");
            String name = fields[0];
            String value = fields[1];
            String status = fields[2];
            

            SystemPerformanceReport report = new SystemPerformanceReport();
            report.setMetricName(name);
            report.setMetricValue(value);
            report.setStatus(status);

            reports[index] = report;
            index++;
        }

        inFS.close();
        fileByteStream.close();

        System.out.println("\nDAILY SYSTEM PERFORMANCE REPORT");
        System.out.println();
        for (SystemPerformanceReport report : reports) {
            System.out.println("Metric: " + report.getMetricName());
            System.out.println("Value: " + report.getMetricValue());
            System.out.println("Status: " + report.getStatus());
            System.out.println("-------------");
        }
    }

    public static void generatePatientReport() throws IOException {
        int lineCount = 10; 
        PatientsReport[] reports = new PatientsReport[lineCount];
    
        FileInputStream fileByteStream = new FileInputStream("patients.csv");
        Scanner inFS = new Scanner(fileByteStream);
    
        inFS.nextLine(); 
    
        int index = 0;
    
        while (inFS.hasNext()) {
            String line = inFS.nextLine();
    
            String[] fields = line.split(",\\s*");
            String patientId = fields[0];
            String patientName = fields[1];
            int prescriptionsFilled = Integer.parseInt(fields[2]);
            String changesMade = fields[3];
            int age = Integer.parseInt(fields[4]);
            String gender = fields[5];
            String insuranceCoverage = fields[6];
            String loyaltyProgramStatus = fields[7];
    
            PatientsReport report = new PatientsReport();
            report.setPatientId(patientId);
            report.setPatientName(patientName);
            report.setPrescriptionsFilled(prescriptionsFilled);
            report.setChangesMade(changesMade);
            report.setAge(age);
            report.setGender(gender);
            report.setInsuranceCoverage(insuranceCoverage);
            report.setLoyaltyProgramStatus(loyaltyProgramStatus);
    
            reports[index] = report;
            index++;
        }
    
        inFS.close();
        fileByteStream.close();
    
        System.out.println("\nPatient Report:");
        for (PatientsReport report : reports) {
            System.out.println("Patient ID: " + report.getPatientId());
            System.out.println("Patient Name: " + report.getPatientName());
            System.out.println("Prescriptions Filled: " + report.getPrescriptionsFilled());
            System.out.println("Changes Made to Record Today? : " + report.getChangesMade());
            System.out.println("Age: " + report.getAge());
            System.out.println("Gender: " + report.getGender());
            System.out.println("Insurance Coverage? : " + report.getInsuranceCoverage());
            System.out.println("Returning Customer? : " + report.getLoyaltyProgramStatus());
            System.out.println("-------------");
        }
    }
    
}
