import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a single BMI record with weight, height, BMI, and date information.
 */
public class BMIRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private double weight;      // in kg
    private double height;      // in cm
    private double bmi;
    private String category;    // Underweight, Normal, Overweight, Obese
    private Date recordDate;
    
    public BMIRecord(double weight, double height) {
        this.weight = weight;
        this.height = height;
        this.recordDate = new Date();
        calculateBMI();
    }
    
    public BMIRecord(double weight, double height, Date date) {
        this.weight = weight;
        this.height = height;
        this.recordDate = date;
        calculateBMI();
    }
    
    private void calculateBMI() {
        // BMI = weight (kg) / (height (m))^2
        double heightInMeters = this.height / 100.0;
        this.bmi = Math.round((this.weight / (heightInMeters * heightInMeters)) * 10.0) / 10.0;
        this.category = categorizeBMI();
    }
    
    private String categorizeBMI() {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25.0) {
            return "Normal";
        } else if (bmi < 30.0) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
    
    // Getters
    public double getWeight() {
        return weight;
    }
    
    public double getHeight() {
        return height;
    }
    
    public double getBMI() {
        return bmi;
    }
    
    public String getCategory() {
        return category;
    }
    
    public Date getRecordDate() {
        return recordDate;
    }
    
    public String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(recordDate);
    }
    
    @Override
    public String toString() {
        return String.format("Date: %s | Weight: %.1f kg | Height: %.1f cm | BMI: %.1f | Category: %s",
                getFormattedDate(), weight, height, bmi, category);
    }
}
