package com.sfwe301;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private String filePath;
    private String[] headers;
    private int lowThreshold;
    private boolean autoOrder;
    private ArrayList<Integer> inventoryIDs;
    private static Integer ID_INDEX = 0;
    private static Integer  NAME_INDEX = 1;
    private static Integer  PRICE_INDEX = 2;
    private static Integer QUANTITY_INDEX = 3;
    private static Integer STOCK_INDEX = 4;
    private static Integer SUPPLIER_INDEX = 5;
    private static Integer LOGS_INDEX = 6;
    private static Integer DISPOSAL_INDEX = 7;

    public Inventory(String name){
        lowThreshold = 5;
        inventoryIDs = new ArrayList<Integer>();
        autoOrder = false;

        filePath = "inventory.csv";
        headers = new String[] {"ID", "Medication Name","Price", "Quantity", "Out of Stock", "Supplier Info", "Emergency Logs", "Disposal Notes"};

        try {
            File file = new File(filePath);
            boolean fileExists = file.exists();
            
            try (FileWriter writer = new FileWriter(filePath, true)){
                if (!fileExists){
                    writer.write(String.join(",", headers) + "\n");
                }
    
                }

        }
        catch (IOException e){
            System.out.println("Error " + e.getMessage());
        }
            
    }

    public ArrayList<String[]> getAllInventory(){
        ArrayList<String[]> inventory = new ArrayList<String[]>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] values = line.split(",");
                inventory.add(values);
            }
        } catch (IOException e){
            System.out.println("Error " + e.getMessage());
        }

        return inventory;
    }

    public void addInventoryItem(InventoryItem item){
        Integer itemID = item.getID();
        if (inventoryIDs.contains(itemID)){
            System.out.println("Inventory item already exists in inventory.");
            return;
        }

        inventoryIDs.add(itemID);
        ArrayList<String> itemData = new ArrayList<>();
        
        itemData.add(String.valueOf(itemID));
        itemData.add((String) item.getName());
        itemData.add(String.valueOf(item.getPrice()));
        itemData.add(String.valueOf(item.getQuantity()));
        itemData.add(String.valueOf(item.getOutOfStock()));
        itemData.add((String) item.getSupplierInfo());
        itemData.add((item.getEmergencyLogs()).toString());
        itemData.add((item.getDisposalNotes()).toString());

        try (FileWriter writer = new FileWriter(filePath, true)){
            writer.write(String.join(",", itemData) + "\n");
                
        } catch (IOException e){
            System.out.println("Error " + e.getMessage());
        }
    }

    public InventoryItem getInventoryItem(int id){
        if (!inventoryIDs.contains(id)){
            System.out.println("Item does not exist in inventory.");
            return null;
        }

        InventoryItem targetItem = new InventoryItem();
        try (BufferedReader reader  = new BufferedReader(new FileReader(filePath))){

            String line;

            while ((line = reader.readLine()) != null){
                String[] values = line.split(",");
                if( (values[ID_INDEX]).equals(String.valueOf(id))){

                    // Contruct the item
                    targetItem.setID(id);
                    targetItem.setName(values[NAME_INDEX]);
                    targetItem.setPrice(Double.parseDouble(values[PRICE_INDEX]));
                    targetItem.setQuantity(Integer.parseInt(values[QUANTITY_INDEX]));
                    targetItem.setOutofStock(Boolean.parseBoolean(values[STOCK_INDEX]));
                    targetItem.setSupplierInfo(values[SUPPLIER_INDEX]);
                    targetItem.setEmergencyLogs(values[LOGS_INDEX]);
                    targetItem.setDisposalNotes(values[DISPOSAL_INDEX]);

                    return targetItem;
                }
            }
        }   
        catch (IOException e){
            System.out.println("Error " + e.getMessage());
        }
        return null;
    }

    public void subtractFromInventory(String userRole, InventoryItem item, Integer amount){
        Integer id = item.getID();
        if (!inventoryIDs.contains(id)){
            System.out.println("Item does not exist in inventory.");
            return;
        }
        else if (!(userRole).equals("manager")){
            System.out.println("User is not allowed to alter inventory");
            return;
        }
        else if (item.getQuantity() < amount){
            System.out.println("Requested amount is more than what is available in inventory");
            return;
        }

        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader reader  = new BufferedReader(new FileReader(filePath))){

            String line;

            while ((line = reader.readLine()) != null){
                String[] values = line.split(",");
                if( (values[ID_INDEX]).equals(String.valueOf(id))){


                    values[QUANTITY_INDEX] = String.valueOf(item.getQuantity() - amount);

                    // Check if out of stock
                    if (item.getQuantity() == amount){
                        System.out.println("Out of stock for " + item.getName() + ". Please place an order.");
                        values[STOCK_INDEX] = "true";
                    }
                    else if ((item.getQuantity() - amount) <= lowThreshold){
                        // Alert if the item has reached low predefined inventory
                        System.out.println("Inventory for "+ item.getName() + ". Please place an order.");
                        System.out.println("Quantity left: " + values[QUANTITY_INDEX]);
                    }

                }

                lines.add(String.join(",", values));
            }
        }   
        catch (IOException e){
            System.out.println("Error " + e.getMessage());
        }

        try(FileWriter writer = new FileWriter(filePath)){
            for (String line : lines){
                writer.write(line + "\n");
            }
        } 
        catch (IOException e){
            System.out.println("Error " + e.getMessage());
        }

    }

    public void addToInventory(String userRole, InventoryItem item, Integer amount){
        Integer id = item.getID();
        if (!inventoryIDs.contains(id)){
            System.out.println("Item does not exist in inventory.");
            return;
        }
        else if (!(userRole).equals("manager")){
            System.out.println("User is not allowed to alter inventory");
            return;
        }

        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader reader  = new BufferedReader(new FileReader(filePath))){

            String line;

            while ((line = reader.readLine()) != null){
                String[] values = line.split(",");
                if( (values[ID_INDEX]).equals(String.valueOf(id))){

                    values[QUANTITY_INDEX] = String.valueOf(item.getQuantity() + amount);

                    // If out of stock, update it
                    if (!item.getOutOfStock()){
                        values[STOCK_INDEX] = "false";
                    }

                }

                lines.add(String.join(",", values));
            }
        }   
        catch (IOException e){
            System.out.println("Error " + e.getMessage());
        }

        try(FileWriter writer = new FileWriter(filePath)){
            for (String line : lines){
                writer.write(line + "\n");
            }
        } 
        catch (IOException e){
            System.out.println("Error " + e.getMessage());
        }
    }

    public ArrayList<Integer> getInventoryIds(){
        return inventoryIDs;
    }

    // public LocalDate checkExpiration(HashMap<Object, Object> prescription){
    //     LocalDate expDate = (LocalDate)prescription.get("expDate");
    // }

    
}
