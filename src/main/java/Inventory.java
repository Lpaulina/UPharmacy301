import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.ObjIntConsumer;

import org.apache.poi.ss.usermodel.Cell; 
import org.apache.poi.xssf.usermodel.XSSFRow; 
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 

public class Inventory{
    private Map<String, InventoryItem> inventoryItems;
    private int totalInventory;
    private int rowID;
    private int cellID;
    private File directory;
    private File inventoryFile;
    private XSSFWorkbook workbook;
    private XSSFSheet spreadsheet;

    public Inventory(){
        rowID = 0;
        cellID = 0;
        inventoryItems = new HashMap<String, InventoryItem>();
        totalInventory  = 0;

        // Create Base Inventory with column titles
        workbook = new XSSFWorkbook(); 
        spreadsheet = workbook.createSheet("UPharmacyInventory");

        String[] inventoryColumns = {"Medication Name", "Quantity", "Low Threshold", "Out of Stock", "Return Period", "Supplier Info"};


        XSSFRow row = spreadsheet.createRow(rowID++);
        for (String col : inventoryColumns) {
            Cell cell = row.createCell(cellID++);
            cell.setCellValue(col);
        }

        // Create a directory for it if it doesn't exist and insert the excel file in it
        directory = new File("C:\\Inventory301");
        if (!directory.exists()) {
            directory.mkdirs(); 
        }

        // Saving the file
        inventoryFile = new File("C:\\Inventory301\\UPharmacyInventory.xlsx");
        try (FileOutputStream out = new FileOutputStream(inventoryFile)) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addInventoryItem(String name){
        InventoryItem newItem = new InventoryItem(name);
        inventoryItems.put(name, newItem);

        // try {
        //     FileInputStream file = new FileInputStream(inventoryFile);
        // }
        // catch (IOException e) {
        //     e.printStackTrace();
        // }
        cellID = 0;
        XSSFRow newRow = spreadsheet.createRow(rowID++);

        Cell newCell = newRow.createCell(cellID++);
        newCell.setCellValue(newItem.getMedicationName());

        newCell = newRow.createCell(cellID++);
        newCell.setCellValue(newItem.getMedicationName());

        newCell = newRow.createCell(cellID++);
        newCell.setCellValue(newItem.getQuantity());

        newCell = newRow.createCell(cellID++);
        newCell.setCellValue(newItem.getLowThreshold());

        newCell = newRow.createCell(cellID++);
        newCell.setCellValue(newItem.getOutofStock());

        newCell = newRow.createCell(cellID++);
        newCell.setCellValue(newItem.getReturnPeriod());

        newCell = newRow.createCell(cellID++);
        newCell.setCellValue(newItem.getSupplierInfo());


        try { 
            FileOutputStream out = new FileOutputStream(inventoryFile);
            workbook.write(out);
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public InventoryItem getInventoryItem(String name){
        return inventoryItems.get(name);
    }

}