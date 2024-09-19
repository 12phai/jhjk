public class StageContext {
    private Stage currentStage;

    public StageContext() {
        this.currentStage = new PreLaunch();  // Initialize with PreLaunch stage
    }

    // Method to set the current stage
    public void setStage(Stage stage) {
        this.currentStage = stage;
    }

    // Method to progress to the next stage
    public void nextStage() {
        if (currentStage != null) {
            // Fetch the singleton instance of the Rocket and execute the current stage logic
            this.currentStage = currentStage.executeStage(Rocket.getInstance());
        }
    }
}
