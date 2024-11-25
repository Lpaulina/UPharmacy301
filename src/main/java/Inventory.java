import java.io.File;
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
            directory.mkdirs(); // Creates the directory
        }

        // Saving the file
        try (FileOutputStream out = new FileOutputStream(new File("C:\\Inventory301\\UPharmacyInventory.xlsx"))) {
            workbook.write(out);
            System.out.println("Excel file written successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addInventoryItem(String name){
        inventoryItems.put(name, new InventoryItem(name));
    }

    public InventoryItem getInventoryItem(String name){
        return inventoryItems.get(name);
    }

    public void updateInventory() {
        XSSFWorkbook workbook = new XSSFWorkbook(); 
        XSSFSheet spreadsheet = workbook.createSheet("Student Data");
    
        Map<String, Object[]> studentData = new TreeMap<>();
        studentData.put("1", new Object[] {"Roll No", "NAME", "Year"});
        studentData.put("2", new Object[] {"128", "Aditya", "2nd year"});
        studentData.put("3", new Object[] {"129", "Narayana", "2nd year"});
        studentData.put("4", new Object[] {"130", "Mohan", "2nd year"});
        studentData.put("5", new Object[] {"131", "Radha", "2nd year"});
        studentData.put("6", new Object[] {"132", "Gopal", "2nd year"});
    
        Set<String> keyid = studentData.keySet();
        int rowid = 0;
    
        for (String key : keyid) {
            XSSFRow row = spreadsheet.createRow(rowid++);
            Object[] objectArr = studentData.get(key);
            int cellid = 0;
            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String) obj);
            }
        }

    }
}