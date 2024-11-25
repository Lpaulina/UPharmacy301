import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.poi.ss.usermodel.Cell; 
import org.apache.poi.xssf.usermodel.XSSFRow; 
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 

public class Inventory{
    private Map<String, InventoryItem> inventoryItems;
    private int totalInventory;

    public Inventory(){
        inventoryItems = new HashMap<String, InventoryItem>();
        totalInventory  = 0;
    }

    public void addInventoryItem(String name){
        inventoryItems.put(name, new InventoryItem(name));
    }

    public InventoryItem getInventoryItem(String name){
        return inventoryItems.get(name);
    }

    public void getSpreadSheet() {
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
    
        // Create the directory if it doesn't exist
        File directory = new File("C:\\savedexcel");
        if (!directory.exists()) {
            directory.mkdirs(); // Creates the directory
        }

        // Saving the file
        try (FileOutputStream out = new FileOutputStream(new File("C:\\savedexcel\\GFGsheet.xlsx"))) {
            workbook.write(out);
            System.out.println("Excel file written successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}