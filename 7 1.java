public class Rocket {
    private static Rocket instance = null;

    private String stage;
    private int fuel;
    private int altitude;
    private int speed;
    private boolean isChecksPassed;

    // Private constructor to prevent instantiation
    private Rocket() {
        this.stage = "Pre-Launch";
        this.fuel = 100;
        this.altitude = 0;
        this.speed = 0;
        this.isChecksPassed = false;
    }

    // Static method to get the singleton instance
    public static Rocket getInstance() {
        if (instance == null) {
            instance = new Rocket();
        }
        return instance;
    }

    // Method to start the checks
    public void startChecks() {
        this.isChecksPassed = true;
        System.out.println("All systems are 'Go' for launch.");
    }

    // Method to update the rocket's status
    public boolean update(int fuelUsage, int altitudeGain, int speedIncrease) {
        this.fuel -= fuelUsage;
        this.altitude += altitudeGain;
        this.speed += speedIncrease;
        if (this.fuel <= 0) {
            this.fuel = 0;
            System.out.println("Mission Failed due to insufficient fuel.");
            return false;
        }
        return true;
    }

    // Getters for the rocket's properties (if needed)
    public String getStage() {
        return stage;
    }

    public int getFuel() {
        return fuel;
    }

    public int getAltitude() {
        return altitude;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isChecksPassed() {
        return isChecksPassed;
    }
}
