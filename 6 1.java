public class Satellite {
    private static Satellite instance = null;
    private String orientation;
    private String solarPanels;
    private int dataCollected;

    // Private constructor to prevent instantiation from other classes
    private Satellite() {
        this.orientation = "North";
        this.solarPanels = "Inactive";
        this.dataCollected = 0;
    }

    // Static method to get the single instance of the class
    public static Satellite getInstance() {
        if (instance == null) {
            instance = new Satellite();
        }
        return instance;
    }

    // Method to rotate the satellite
    public void rotate(String direction) {
        this.orientation = direction;
        System.out.println("Satellite rotated to " + this.orientation);
    }

    // Method to activate solar panels
    public void activatePanels() {
        this.solarPanels = "Active";
        System.out.println("Solar panels activated");
    }

    // Method to deactivate solar panels
    public void deactivatePanels() {
        this.solarPanels = "Inactive";
        System.out.println("Solar panels deactivated");
    }

    // Method to collect data
    public void collectData() {
        if (this.solarPanels.equals("Active")) {
            this.dataCollected += 10;
            System.out.println("Data collected: " + this.dataCollected + " units");
        } else {
            System.out.println("Cannot collect data. Solar panels are inactive.");
        }
    }
}
