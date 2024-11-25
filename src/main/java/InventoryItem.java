import java.util.ArrayList;

public class InventoryItem {
    private ArrayList<String> prescriptionInventory;
    private String medicationName;
    private int quantity;
    private int lowThreshold;
    private boolean outOfStock;
    private int returnPeriod;
    private String supplierInfo;
    private String notes;
    private double price;

    public InventoryItem(){
        prescriptionInventory = new ArrayList<String>();
        medicationName = "Medication Name";
        quantity = 0;
        lowThreshold = 0;
        outOfStock = false;
        returnPeriod = -1;
        supplierInfo = "Supplier Info";
    }
    public InventoryItem(String name){
        prescriptionInventory = new ArrayList<String>();
        medicationName = name;
        quantity = 0;
        lowThreshold = 0;
        outOfStock = false;
        returnPeriod = -1;
        supplierInfo = "";
    }

    public void setQuantity(int amount){
        this.quantity = amount;
    }

    public int getQuantity(){
        return this.quantity;
    } 

    public String getMedicationName(){
        return medicationName;
    }
    
    public void setLowThreshold(int amount){
        this.lowThreshold = amount;
    }

    public void setPrice(Double inputPrice){
        price = inputPrice;
    }

    public double getPrice(){
        return price;
    }

    public void subtractInventory(User user, int amount){
        if ((user.getRole()).equals("manager")){
            quantity -= amount;
        }
        else{
            System.out.println("User is not allowed to alter inventory.");
        }
    }

    public void addInventory(User user, int amount){
        if ((user.getRole()).equals("manager")){
            quantity += amount;
        }
        else{
            System.out.println("User is not allowed to alter inventory.");
        }
    }

    public void setSupplierInfo(String info){
        supplierInfo = info;
    }

    public String getSupplierInfo(){
        return supplierInfo;
    }
}
