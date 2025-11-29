import java.util.List;

/**
 * Handles BMI calculations and reporting.
 */
public class BMICalculator {
    
    /**
     * Calculate BMI from weight and height
     */
    public static double calculateBMI(double weight, double height) {
        double heightInMeters = height / 100.0;
        return Math.round((weight / (heightInMeters * heightInMeters)) * 10.0) / 10.0;
    }
    
    /**
     * Categorize BMI value
     */
    public static String categorizeBMI(double bmi) {
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
    
    /**
     * Get health recommendation based on BMI
     */
    public static String getHealthRecommendation(String category) {
        switch (category) {
            case "Underweight":
                return "Try to gain weight through balanced nutrition and exercise.";
            case "Normal":
                return "Maintain your current weight through healthy lifestyle.";
            case "Overweight":
                return "Consider reducing weight through diet and exercise.";
            case "Obese":
                return "Consult a healthcare professional for a weight management plan.";
            default:
                return "";
        }
    }
    
    /**
     * Calculate average BMI from records
     */
    public static double calculateAverageBMI(List<BMIRecord> records) {
        if (records.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (BMIRecord record : records) {
            sum += record.getBMI();
        }
        return Math.round((sum / records.size()) * 10.0) / 10.0;
    }
    
    /**
     * Get BMI trend (improving, stable, or worsening)
     */
    public static String getBMITrend(List<BMIRecord> records) {
        if (records.size() < 2) {
            return "Insufficient data for trend analysis";
        }
        
        double firstBMI = records.get(0).getBMI();
        double lastBMI = records.get(records.size() - 1).getBMI();
        double difference = lastBMI - firstBMI;
        
        if (Math.abs(difference) < 0.5) {
            return "Stable";
        } else if (difference < 0) {
            return "Improving (BMI decreasing)";
        } else {
            return "Worsening (BMI increasing)";
        }
    }
}
