package com.sfwe301;

public class InventoryItem {
    private int id;
    private String medicationName;
    private Double price;
    private int quantity;
    private boolean outOfStock;
    private String supplierInfo;
    private String emergencyLogs;
    private String disposalNotes;

    InventoryItem(){
        id = 1;
        medicationName = "None";
        price = 0.00;
        quantity = 0;
        outOfStock = false;
        supplierInfo = "None";
        emergencyLogs = "None";
        disposalNotes = "None";
    }

    InventoryItem(String name, Double newPrice, Integer newQuantity, String newSupplier){
        id = 1;
        medicationName = name;
        price = newPrice;
        quantity = newQuantity;
        outOfStock = false;
        supplierInfo = newSupplier;
        emergencyLogs = "None";
        disposalNotes = "None";
    }

    public void setID(int newID){
        id = newID;
    }
    public int getID(){
        return id;
    }
    public void setName(String newName){
        medicationName = newName;
    }
    public String getName(){
        return medicationName;
    }
    public void setPrice(Double newPrice){
        price = newPrice;
    }
    public Double getPrice(){
        return price;
    }
    public void setQuantity(Integer newQuantity){
        quantity = newQuantity;
    }
    public Integer getQuantity(){
        return quantity;
    }
    public void setOutofStock(boolean out){
        outOfStock = out;
    }
    public boolean getOutOfStock(){
        return outOfStock;
    }
    public void setSupplierInfo(String info){
        supplierInfo = info;
    }
    public String getSupplierInfo(){
        return supplierInfo;
    }
    public void setEmergencyLogs(String log){
        emergencyLogs = log;
    }
    public String getEmergencyLogs(){
        return emergencyLogs;
    }
    public void setDisposalNotes(String notes){
        disposalNotes = notes;
    }
    public String getDisposalNotes(){
        return disposalNotes;
    }
    public boolean sameAs(InventoryItem item){
        if(id != item.getID()){
            System.out.println("ID");
            return false;
        }
        if(!(medicationName).equals(item.getName())){
            System.out.println("NAME");
            return false;
        }
        if (!(Double.compare(price , item.getPrice()) == 0)){
            System.out.println("PRICE");
            return false;
        }
        if (quantity != item.getQuantity()){
            System.out.println("QUANTITY");
            return false;
        }
        if (outOfStock != item.getOutOfStock()){
            System.out.println("ITEM");
            return false;
        }
        if (!(supplierInfo).equals(item.getSupplierInfo())){
            System.out.println("SUPPLIER");
            return false;
        }
        if (!(emergencyLogs).equals(item.getEmergencyLogs())){
            System.out.println("LOGS");
            return false;
        }
        if (!(disposalNotes).equals(item.getDisposalNotes())){
            return false;
        }

        return true;
    }
}
