import java.util.HashMap;
import java.util.Map;

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


    
}