// Abstract Command Interface
public interface Command {
    void execute();
}

// Command to start pre-launch checks
public class StartChecksCommand implements Command {
    private Rocket rocket;

    public StartChecksCommand(Rocket rocket) {
        this.rocket = rocket;
    }

    @Override
    public void execute() {
        rocket.startChecks();
    }
}

// Command to launch the rocket
public class LaunchCommand implements Command {
    private Rocket rocket;
    private StageContext stageContext;

    public LaunchCommand(Rocket rocket, StageContext stageContext) {
        this.rocket = rocket;
        this.stageContext = stageContext;
    }

    @Override
    public void execute() {
        if (rocket.isChecksPassed()) {
            stageContext.setStage(new Stage1());
        } else {
            System.out.println("Cannot launch. Pre-launch checks are incomplete.");
        }
    }
}

// Command to fast forward the simulation
public class FastForwardCommand implements Command {
    private Rocket rocket;
    private StageContext stageContext;
    private int seconds;

    public FastForwardCommand(Rocket rocket, StageContext stageContext, int seconds) {
        this.rocket = rocket;
        this.stageContext = stageContext;
        this.seconds = seconds;
    }

    @Override
    public void execute() {
        for (int i = 0; i < seconds; i++) {
            stageContext.nextStage();
        }
    }
}
