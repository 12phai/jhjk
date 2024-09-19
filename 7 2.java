// Abstract Stage class
public abstract class Stage {
    public abstract Stage executeStage(Rocket rocket);
}

// Pre-Launch Stage
public class PreLaunch extends Stage {
    @Override
    public Stage executeStage(Rocket rocket) {
        if (rocket.isChecksPassed()) {
            System.out.println("Launching...");
            return new Stage1();
        } else {
            System.out.println("Pre-Launch checks not complete.");
            return this;
        }
    }
}

// Stage 1: Rocket Launch
public class Stage1 extends Stage {
    @Override
    public Stage executeStage(Rocket rocket) {
        System.out.printf("Stage: 1, Fuel: %d%%, Altitude: %d km, Speed: %d km/h%n", 
                          rocket.getFuel(), rocket.getAltitude(), rocket.getSpeed());
        rocket.update(10, 10, 1000);
        if (rocket.getFuel() <= 50) {
            return new Stage2();
        }
        return this;
    }
}

// Stage 2: Rocket Transitioning to Orbit
public class Stage2 extends Stage {
    @Override
    public Stage executeStage(Rocket rocket) {
        System.out.printf("Stage: 2, Fuel: %d%%, Altitude: %d km, Speed: %d km/h%n",
                          rocket.getFuel(), rocket.getAltitude(), rocket.getSpeed());
        rocket.update(5, 20, 1500);
        if (rocket.getAltitude() >= 100 && rocket.getFuel() > 0) {
            return new Orbit();
        }
        return this;
    }
}

// Orbit Stage: Success
public class Orbit extends Stage {
    @Override
    public Stage executeStage(Rocket rocket) {
        System.out.printf("Orbit achieved! Altitude: %d km. Mission Successful.%n", 
                          rocket.getAltitude());
        return this;
    }
}
