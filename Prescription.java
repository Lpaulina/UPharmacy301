import java.time.LocalDate;
public class Prescription{
    private String patientName;
    private int prescriptionID;
    private String medicationName;
    private int dosageQuantity;
    private String prescriningDoctor;
    private String disposalNotes;
    private LocalDate dateReceived;
    private LocalDate expirationDate;
    private boolean expired;
    private LocalDate issuedDate;

    public Prescription(){
        patientName = "None";
        disposalNotes = "";
        dateReceived = LocalDate.now();;
        expirationDate = null;
        expired = false;
        issuedDate = null;
    }

    public Prescription(String newName, double newPrice ){
        patientName = newName;
        disposalNotes = "";
        dateReceived =  LocalDate.now();;
        expirationDate = null;
        expired = false;
        issuedDate = null;
    }

    public int getDosageQuantity(){
        return this.dosageQuantity;
    }

    public void setDosageQuantity(int newDosage){
        this.dosageQuantity = newDosage;
    }

    public String getPatientName(){
        return this.patientName;
    }

    public void setPatientName(String newName){
        this.patientName= newName;
    }
    
    public void setPrescriptionID(int id){
        this.prescriptionID = id;
    }

    public int getPrescriptionID(){
        return this.prescriptionID;
    }
    
    public String getDisposalNotes(){
        return this.disposalNotes;
    }

    public void setDisposalNotes(String newDisposalNotes){
        this.disposalNotes = newDisposalNotes;
    }

    public void setExpired(boolean expire){
        this.expired = expire;
    }

    public boolean getExpired(){
        return this.expired;
    }
}