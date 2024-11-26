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
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow; 
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 

public class Inventory{
    private Map<String, Integer> inventoryItems;
    private int totalInventory;
    private int rowID;
    private int returnPeriod;
    private File directory;
    private File inventoryFile;
    private XSSFWorkbook workbook;
    private XSSFSheet spreadsheet;
    private static int NAME_INDEX = 0;
    private static int QUANTITY_INDEX = 1;
    private static int THRESHOLD_INDEX = 2;
    private static int OUT_OF_STOCK_INDEX = 3;
    private static int SUPPLIER_INFO_INDEX = 4;

    public Inventory(){
        rowID = 0;
        inventoryItems = new HashMap<String, Integer>();
        totalInventory  = 0;
        returnPeriod = 0;

        // Create Base Inventory with column titles
        workbook = new XSSFWorkbook(); 
        spreadsheet = workbook.createSheet("UPharmacyInventory");

        String[] inventoryColumns = {"Medication Name", "Quantity", "Low Threshold", "Out of Stock", "Return Period", "Supplier Info"};

        int cellID = 0;

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

    public void addInventoryItem(InventoryItem newItem){
        inventoryItems.put(newItem.getMedicationName(), rowID);

        XSSFRow newRow = spreadsheet.createRow(rowID++);

        Cell newCell = newRow.createCell(NAME_INDEX);
        newCell.setCellValue(newItem.getMedicationName());

        newCell = newRow.createCell(QUANTITY_INDEX);
        newCell.setCellValue(newItem.getQuantity());

        newCell = newRow.createCell(THRESHOLD_INDEX);
        newCell.setCellValue(newItem.getLowThreshold());

        newCell = newRow.createCell(OUT_OF_STOCK_INDEX);
        newCell.setCellValue(newItem.getOutofStock());

        newCell = newRow.createCell(SUPPLIER_INFO_INDEX);
        newCell.setCellValue(newItem.getSupplierInfo());

        updateExcelFile();

    }

    public InventoryItem getInventoryItem(String name){
        DataFormatter formatter = new DataFormatter();
        InventoryItem item;

        Integer rowNum = inventoryItems.get(name);

        XSSFRow currentRow = spreadsheet.getRow(rowNum);
        String medName = formatter.formatCellValue(currentRow.getCell(NAME_INDEX));
        
        if((medName).equals(name)){
            item = new InventoryItem(name);

            item.setQuantity((int)(currentRow.getCell(QUANTITY_INDEX)).getNumericCellValue());

            item.setLowThreshold((int)(currentRow.getCell(THRESHOLD_INDEX)).getNumericCellValue());

            item.setOutofStock(currentRow.getCell(OUT_OF_STOCK_INDEX).getBooleanCellValue());

            item.setSupplierInfo(formatter.formatCellValue(currentRow.getCell(SUPPLIER_INFO_INDEX)));

            return item;
        }
        
        return null;
    }

    public void subtractFromInventory(String name, int amount){
        Integer rowNum = inventoryItems.get(name);
        XSSFRow currentRow = spreadsheet.getRow(rowNum);

        Cell cellNum = currentRow.getCell(QUANTITY_INDEX);

        Integer newTotal = (int)(cellNum).getNumericCellValue() - amount;

        cellNum.setCellValue(newTotal);

        updateExcelFile();

    }

    public void addToInventory(String name, int amount){
        Integer rowNum = inventoryItems.get(name);
        XSSFRow currentRow = spreadsheet.getRow(rowNum);

        Cell cellNum = currentRow.getCell(QUANTITY_INDEX);
        System.out.println(cellNum);

        Integer newTotal = (int)(cellNum).getNumericCellValue() + amount;

        cellNum.setCellValue(newTotal);

        updateExcelFile();

    }

    public void updateExcelFile(){
        try { 
            FileOutputStream out = new FileOutputStream(inventoryFile);
            workbook.write(out);
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setReturnPeriod(int period){
        returnPeriod = period;
    }

    public int getReturnPeriod(){
        return returnPeriod;
    }

    public boolean checkLowStock(String name ){
        InventoryItem item = getInventoryItem(name);
        if (item.getQuantity() <= item.getLowThreshold()){
            System.out.println("WARNING: Inventory Item " + item.getMedicationName() + " is low in stock.")
        }
        if (item.getQuantity() == 0){
            item.setOutofStock(true);
        }
    }

}