/**
 * Handles input validation for BMI Tracker.
 */
public class InputValidator {
    
    private static final double MIN_WEIGHT = 20.0;  // kg
    private static final double MAX_WEIGHT = 600.0; // kg
    private static final double MIN_HEIGHT = 50.0;  // cm
    private static final double MAX_HEIGHT = 300.0; // cm
    
    public static boolean isValidWeight(double weight) {
        return weight >= MIN_WEIGHT && weight <= MAX_WEIGHT;
    }
    
    public static boolean isValidHeight(double height) {
        return height >= MIN_HEIGHT && height <= MAX_HEIGHT;
    }
    
    public static String validateInput(double weight, double height) {
        if (!isValidWeight(weight)) {
            return "Invalid weight! Please enter weight between " + MIN_WEIGHT + " and " + MAX_WEIGHT + " kg.";
        }
        
        if (!isValidHeight(height)) {
            return "Invalid height! Please enter height between " + MIN_HEIGHT + " and " + MAX_HEIGHT + " cm.";
        }
        
        return null; // No errors
    }
    
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
