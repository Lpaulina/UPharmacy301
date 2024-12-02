package com.sfwe301;

import static org.junit.Assert.*;

import org.junit.BeforeClass;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.junit.Test;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class InventoryControlTest {
    Inventory testInventory = new Inventory("UPharmacy");
    public static JSONParser jsonParser = new JSONParser();
    public static JSONArray jsonItems;

    @BeforeClass
    public static void setUpTestData(){
        try (FileReader reader = new FileReader("inventoryItems.json")){

            JSONObject items = (JSONObject) jsonParser.parse(reader);
            jsonItems = (JSONArray) items.get("items");

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
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
        System.out.println("INV: "+ advilInventory.getQuantity());
        System.out.println("MED: " + advilMed.getQuantity());
        System.out.println(advilMed.sameAs(advilInventory));
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
    public void fillPrescription(){
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

}
