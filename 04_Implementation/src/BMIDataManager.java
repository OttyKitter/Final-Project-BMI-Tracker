import java.io.*;
import java.util.*;

/**
 * Manages BMI records storage and retrieval.
 */
public class BMIDataManager {
    private static final String DATA_FILE = "bmi_data.dat";
    private List<BMIRecord> records;
    
    public BMIDataManager() {
        this.records = new ArrayList<>();
        loadData();
    }
    
    /**
     * Add a new BMI record
     */
    public void addRecord(BMIRecord record) {
        records.add(record);
        saveData();
    }
    
    /**
     * Get all records
     */
    public List<BMIRecord> getAllRecords() {
        return new ArrayList<>(records);
    }
    
    /**
     * Get the latest record
     */
    public BMIRecord getLatestRecord() {
        if (records.isEmpty()) {
            return null;
        }
        return records.get(records.size() - 1);
    }
    
    /**
     * Delete a record by index
     */
    public boolean deleteRecord(int index) {
        if (index >= 0 && index < records.size()) {
            records.remove(index);
            saveData();
            return true;
        }
        return false;
    }
    
    /**
     * Clear all records
     */
    public void clearAllRecords() {
        records.clear();
        saveData();
    }
    
    /**
     * Get total number of records
     */
    public int getTotalRecords() {
        return records.size();
    }
    
    /**
     * Save data to file
     */
    @SuppressWarnings("unchecked")
    private void loadData() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                records = (List<BMIRecord>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading data: " + e.getMessage());
                records = new ArrayList<>();
            }
        } else {
            records = new ArrayList<>();
        }
    }
    
    /**
     * Save data to file
     */
    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(records);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }
}
