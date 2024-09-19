public class Main {
    public static void main(String[] args) {
        // Initialize rocket and stage context
        Rocket rocket = Rocket.getInstance();
        StageContext stageContext = new StageContext();

        // Create and execute the pre-launch command
        Command preLaunchCommand = new StartChecksCommand(rocket);
        preLaunchCommand.execute();

        // Create and execute the launch command
        Command launchCommand = new LaunchCommand(rocket, stageContext);
        launchCommand.execute();

        // Simulate the launch for 10 seconds
        for (int i = 0; i < 10; i++) {
            stageContext.nextStage();
            try {
                Thread.sleep(1000);  // Pause for 1 second between each stage transition
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Fast forward the simulation by 20 seconds
        Command fastForwardCommand = new FastForwardCommand(rocket, stageContext, 20);
        fastForwardCommand.execute();
    }
}
