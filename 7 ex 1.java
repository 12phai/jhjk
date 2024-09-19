public class Main {
    public static void main(String[] args) {
        // Initialize rocket and stage context
        Rocket rocket = Rocket.getInstance();
        StageContext stageContext = new StageContext();

        // Create and execute the pre-launch command
        Command preLaunchCommand = new StartChecksCommand(rocket);
        preLaunchCommand.execute();
    }
}
