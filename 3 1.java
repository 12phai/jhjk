// Command interface
public interface Command {
    void execute(Rover rover);
}

// Concrete Command classes
public class MoveForwardCommand implements Command {
    @Override
    public void execute(Rover rover) {
        rover.moveForward();
    }
}

public class TurnLeftCommand implements Command {
    @Override
    public void execute(Rover rover) {
        rover.turnLeft();
    }
}

public class TurnRightCommand implements Command {
    @Override
    public void execute(Rover rover) {
        rover.turnRight();
    }
}

// Receiver class
public class Rover {
    public void moveForward() {
        System.out.println("Rover is moving forward.");
    }

    public void turnLeft() {
        System.out.println("Rover is turning left.");
    }

    public void turnRight() {
        System.out.println("Rover is turning right.");
    }
}
