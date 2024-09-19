public class Main {
    public static void main(String[] args) {
        // Create a Grid instance with width 10 and height 10
        Grid grid = new Grid(10, 10);
        
        // Add obstacles at (2, 2) and (3, 5)
        grid.addObstacle(2, 2);
        grid.addObstacle(3, 5);

        // Create a Rover instance at position (0, 0) facing 'N'
        Rover rover = new Rover(0, 0, "N", grid);

        // Create command instances
        Command[] commands = {
            new MoveForwardCommand(),
            new MoveForwardCommand(),
            new TurnRightCommand(),
            new MoveForwardCommand(),
            new TurnLeftCommand(),
            new MoveForwardCommand()
        };

        // Execute commands
        for (Command command : commands) {
            command.execute(rover);
        }

        // Print the final status of the Rover
        rover.statusReport();
    }
}
