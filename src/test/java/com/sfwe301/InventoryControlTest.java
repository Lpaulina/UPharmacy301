package com.sfwe301;

import static org.junit.Assert.*;

import org.junit.BeforeClass;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Unit test for simple App.
 */
public class InventoryControlTest {
    Inventory testInventory = new Inventory("UPharmacy");
    public static JSONParser jsonParser = new JSONParser();
    public static JSONArray jsonItems;
    public static Map<String, Object> nonExpData = Map.of(
            "expDate", "2025-03-24",
            "id", 23,
            "patient", "Paulina",
            "receivedDate", "",
            "filledDate", "",
            "item", "",
            "quantity", 0
    );

    public static Map<String, Object> expData = Map.of(
            "expDate", "2023-03-24",
            "id", 29,
            "patient", "Paulina",
            "receivedDate", "",
            "filledDate", "",
            "item", "",
            "quantity", 0
    );
    
    public static Map<String, Object> nonExpPrescription;

    public static Map<String, Object> expPrescription;



    @BeforeClass
    public static void setUpTestData(){
        try (FileReader reader = new FileReader("inventoryItems.json")){

            JSONObject items = (JSONObject) jsonParser.parse(reader);
            jsonItems = (JSONArray) items.get("items");

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        nonExpPrescription = new HashMap<>(nonExpData);

        expPrescription = new HashMap<>(expData);
    }

    @Test
    public void createInventory() {
        ArrayList<String[]> inv = testInventory.getAllInventory();
        
        File file = new File("inventory.csv");
        assertTrue(file.exists());
        
    }

    @Test
    public void insertInventoryItems(){
        // Inventory testInventory = new Inventory("UPharmacy");
        // setUpTestData();
        JSONObject newItem = (JSONObject) jsonItems.get(0);
        InventoryItem advilMed = new InventoryItem();

        advilMed.setID(((Long) newItem.get("id")).intValue()); // Cast to int
        advilMed.setName((String) newItem.get("name"));
        advilMed.setPrice((Double) newItem.get("price"));
        advilMed.setQuantity(((Long) newItem.get("quantity")).intValue());
        advilMed.setOutofStock((boolean) newItem.get("outOfStock"));
        advilMed.setSupplierInfo((String) newItem.get("supplierInfo"));
        advilMed.setEmergencyLogs((String) newItem.get("emergencyLogs"));

        testInventory.addInventoryItem(advilMed);
        InventoryItem advilInventory = testInventory.getInventoryItem(1);
 
        assertTrue(advilMed.sameAs(advilInventory));
        
    }
    @Test
    public void duplicateItems(){
        // Inventory testInventory = new Inventory("UPharmacy");
        // setUpTestData();
        JSONObject newItem = (JSONObject) jsonItems.get(1);
        InventoryItem advilMed = new InventoryItem();

        advilMed.setID(((Long) newItem.get("id")).intValue()); // Cast to int
        advilMed.setName((String) newItem.get("name"));
        advilMed.setPrice((Double) newItem.get("price"));
        advilMed.setQuantity(((Long) newItem.get("quantity")).intValue());
        advilMed.setOutofStock((boolean) newItem.get("outOfStock"));
        advilMed.setSupplierInfo((String) newItem.get("supplierInfo"));
        advilMed.setEmergencyLogs((String) newItem.get("emergencyLogs"));

        testInventory.addInventoryItem(advilMed);
        // Duplicate
        testInventory.addInventoryItem(advilMed);
        // At this point there should only be 1 id in inventory
        Integer size = (testInventory.getInventoryIds()).size();
        assertEquals((Integer) 1, size);
    }

    @Test
    public void addInventory(){
        // Inventory testInventory = new Inventory("UPharmacy");
        // setUpTestData();
        JSONObject newItem = (JSONObject) jsonItems.get(2);
        InventoryItem advilMed = new InventoryItem();

        advilMed.setID(((Long) newItem.get("id")).intValue()); // Cast to int
        advilMed.setName((String) newItem.get("name"));
        advilMed.setPrice((Double) newItem.get("price"));
        advilMed.setQuantity(((Long) newItem.get("quantity")).intValue());
        advilMed.setOutofStock((boolean) newItem.get("outOfStock"));
        advilMed.setSupplierInfo((String) newItem.get("supplierInfo"));
        advilMed.setEmergencyLogs((String) newItem.get("emergencyLogs"));

        testInventory.addInventoryItem(advilMed);
        testInventory.subtractFromInventory("manager", advilMed, 4);

        InventoryItem inventoryMed = testInventory.getInventoryItem(3);
        assertEquals(inventoryMed.getQuantity(), (Integer) 6);

        
    }
    @Test
    public void restockInventoryItem(){
        // Inventory testInventory = new Inventory("UPharmacy");
        // setUpTestData();
        JSONObject newItem = (JSONObject) jsonItems.get(3);
        InventoryItem advilMed = new InventoryItem();

        advilMed.setID(((Long) newItem.get("id")).intValue()); // Cast to int
        advilMed.setName((String) newItem.get("name"));
        advilMed.setPrice((Double) newItem.get("price"));
        advilMed.setQuantity(((Long) newItem.get("quantity")).intValue());
        advilMed.setOutofStock((boolean) newItem.get("outOfStock"));
        advilMed.setSupplierInfo((String) newItem.get("supplierInfo"));
        advilMed.setEmergencyLogs((String) newItem.get("emergencyLogs"));

        testInventory.addInventoryItem(advilMed);
        testInventory.addToInventory("manager",advilMed, 3);

        InventoryItem inventoryMed = testInventory.getInventoryItem(4);
        assertEquals(inventoryMed.getQuantity(), (Integer) 33);
        
    }

    @Test
    public void inventorySecurity(){
        // Using inventory item with id 1 as it should already be in inventory
        JSONObject newItem = (JSONObject) jsonItems.get(4);
        InventoryItem med = new InventoryItem();

        med.setID(((Long) newItem.get("id")).intValue()); // Cast to int
        med.setName((String) newItem.get("name"));
        med.setPrice((Double) newItem.get("price"));
        med.setQuantity(((Long) newItem.get("quantity")).intValue());
        med.setOutofStock((boolean) newItem.get("outOfStock"));
        med.setSupplierInfo((String) newItem.get("supplierInfo"));
        med.setEmergencyLogs((String) newItem.get("emergencyLogs"));
        
        testInventory.addInventoryItem(med);
        testInventory.addToInventory("pharmacist",med, 3);
        InventoryItem inventoryMed = testInventory.getInventoryItem(5);

        // If it works, the quantity should remain as the original 25
        assertEquals( (Integer) 25, inventoryMed.getQuantity());

        testInventory.addToInventory("manager",med, 3);
        inventoryMed = testInventory.getInventoryItem(5);

        // With manager permissions quantatiy should have changed
        assertEquals((Integer) 28, inventoryMed.getQuantity());

    }

    @Test
    public void fillPrescription(){
        JSONObject newItem = (JSONObject) jsonItems.get(5);
        InventoryItem med = new InventoryItem();

        med.setID(((Long) newItem.get("id")).intValue()); // Cast to int
        med.setName((String) newItem.get("name"));
        med.setPrice((Double) newItem.get("price"));
        med.setQuantity(((Long) newItem.get("quantity")).intValue());
        med.setOutofStock((boolean) newItem.get("outOfStock"));
        med.setSupplierInfo((String) newItem.get("supplierInfo"));
        med.setEmergencyLogs((String) newItem.get("emergencyLogs"));

        testInventory.addInventoryItem(med);

        nonExpPrescription.put("item",med);
        nonExpPrescription.put("quantity", 5);

        testInventory.fillPrescription(nonExpPrescription);

        InventoryItem inventoryMed = testInventory.getInventoryItem(6);

        // Prescription was not expired and therefore inventory should decrease
        assertEquals((Integer)27, inventoryMed.getQuantity());

    }

    @Test
    public void fillExpPrescription(){
        JSONObject newItem = (JSONObject) jsonItems.get(6);
        InventoryItem med = new InventoryItem();

        med.setID(((Long) newItem.get("id")).intValue()); // Cast to int
        med.setName((String) newItem.get("name"));
        med.setPrice((Double) newItem.get("price"));
        med.setQuantity(((Long) newItem.get("quantity")).intValue());
        med.setOutofStock((boolean) newItem.get("outOfStock"));
        med.setSupplierInfo((String) newItem.get("supplierInfo"));
        med.setEmergencyLogs((String) newItem.get("emergencyLogs"));

        testInventory.addInventoryItem(med);

        expPrescription.put("item",med);
        expPrescription.put("quantity", 5);

        testInventory.fillPrescription(nonExpPrescription);

        InventoryItem inventoryMed = testInventory.getInventoryItem(7);

        // Prescription was expired and therefore inventory shouldn't decrease
        assertEquals((Integer)32, inventoryMed.getQuantity());
    }

    @Test
    public void prescriptionNotPickedUp(){
        JSONObject newItem = (JSONObject) jsonItems.get(7);
        InventoryItem med = new InventoryItem();
        ArrayList<Map<String, Object>> prescriptions = new ArrayList<>();

        med.setID(((Long) newItem.get("id")).intValue()); // Cast to int
        med.setName((String) newItem.get("name"));
        med.setPrice((Double) newItem.get("price"));
        med.setQuantity(((Long) newItem.get("quantity")).intValue());
        med.setOutofStock((boolean) newItem.get("outOfStock"));
        med.setSupplierInfo((String) newItem.get("supplierInfo"));
        med.setEmergencyLogs((String) newItem.get("emergencyLogs"));

        testInventory.addInventoryItem(med);
        
        nonExpPrescription.put("item",med);
        nonExpPrescription.put("quantity", 5);
        nonExpPrescription.put("filledDate", LocalDate.now());

        prescriptions.add(nonExpPrescription);

        // Filling the prescription sets the filledDate, but lets change it to 8 days from now and then run the function

        Map<String, Object> notPickedUp = nonExpPrescription;
        
        notPickedUp.put("filledDate",(LocalDate.now()).plusDays(8));
        notPickedUp.put("quantity", 10);
        prescriptions.add(notPickedUp);

        testInventory.checkPrescriptionPickup(prescriptions);

        InventoryItem inventoryMed = testInventory.getInventoryItem(8);

        // Prescription was expired and therefore inventory shouldn't decrease
        assertEquals((Integer)60, inventoryMed.getQuantity());
    }

}
