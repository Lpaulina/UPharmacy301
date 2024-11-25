// import java.util.Scanner;

// public class InventoryControlInput {
//      public static void main(String[] args){
//         Scanner scnr = new Scanner(System.in);

//         User testManager = new User();
//         testManager.setID(1);
//         testManager.setName("Erin");
//         testManager.setRole("manager");

//         User testPharmacist = new User();
//         testManager.setID(2);
//         testManager.setName("Pau");
//         testManager.setRole("pharmacist");

//         Inventory testInventory = new Inventory();
//         System.out.println("Beginning tests for Inventory Control");
//         System.out.println("Please enter a medication name");

//         // Create an Inventory for a medication
//         String medication = scnr.nextLine();
//         testInventory.addInventoryItem(medication);
//         InventoryItem medicationInventory = testInventory.getInventoryItem(medication);

//         // Add quantity, low threshold, price
//         System.out.println("Enter current quantity of the medication in stock");
//         Integer amount = scnr.nextInt();
//         medicationInventory.setQuantity(amount);

//         System.out.println("Enter the quantity at which you would like to be notified after inventory for an item gets below it.");
//         Integer threshold = scnr.nextInt();
//         medicationInventory.setLowThreshold(threshold);

//         System.out.println("Enter price for the medication.");
//         Double medicationPrice = scnr.nextDouble();
//         medicationInventory.setPrice(medicationPrice);

//         // Add & delete to inventory count for the medication
//         System.out.println("A prescription has been filled! Enter how much of the medication was used to fulfill the prescription.");
//         Integer subtractMedication = scnr.nextInt();

//         medicationInventory.subtractInventory(testManager, subtractMedication);
//         System.out.println("Updated Inventory Count: " + medicationInventory.getQuantity());

//         System.out.println("A stock for the medication has arrived! Enter how much medication needs to be added to the system.");
//         Integer addMedication = scnr.nextInt();

//         medicationInventory.addInventory(testManager, addMedication);
//         System.out.println("Updated Inventory Count: " + medicationInventory.getQuantity());

//         // Add supplier info for medication
//         System.out.println("Add supplier info for the medication.");
//         String supplierInfo = scnr.nextLine();
//         medicationInventory.setSupplierInfo(supplierInfo);


//         scnr.close();
//     }   

// }
