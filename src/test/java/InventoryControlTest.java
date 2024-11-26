import java.util.ArrayList;
import java.util.Scanner;
// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.Test;

public class InventoryControlTest {

    public  void testFunctionality(){

        User testManager = new User();
        testManager.setID(1);
        testManager.setName("Erin");
        testManager.setRole("manager");

        User testPharmacist = new User();
        testManager.setID(2);
        testManager.setName("Pau");
        testManager.setRole("pharmacist");

        Inventory testInventory = new Inventory();
        System.out.println("Beginning tests for Inventory Control");

        // Create an Inventory for a medication
        String advilTestMedication = "Advil";
        InventoryItem advilMedicationInventory = new InventoryItem(advilTestMedication);

        // Add quantity, low threshold, price
        Integer amount = 10;
        advilMedicationInventory.setQuantity(amount);

        Integer threshold = 5;
        advilMedicationInventory.setLowThreshold(threshold);

        Double medicationPrice = 20.00;
        advilMedicationInventory.setPrice(medicationPrice);

        // Add & delete to inventory count for the medication

        Integer subtractMedication = 5;
        advilMedicationInventory.subtractInventory(testManager, subtractMedication);

        Integer addMedication = 10;
        advilMedicationInventory.addInventory(testManager, addMedication);

        // Add supplier info for medication
        String supplierInfo = "Adress123, PhoneNumber1234";
        advilMedicationInventory.setSupplierInfo(supplierInfo);

        testInventory.addInventoryItem(advilMedicationInventory);

        InventoryItem Item = testInventory.getInventoryItem(advilTestMedication);

        System.out.println(Item.getQuantity());

        testInventory.subtractFromInventory("Advil", 5);
    }   

}
