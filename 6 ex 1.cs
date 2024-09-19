public class Main {
    public static void main(String[] args) {
        // Get the singleton instance of Satellite
        Satellite satellite = Satellite.getInstance();

        // Create command instances
        Command rotateCommand = new RotateCommand(satellite, "East");
        Command activatePanelsCommand = new ActivatePanelsCommand(satellite);
        Command collectDataCommand = new CollectDataCommand(satellite);

        // Create CommandInvoker instance
        CommandInvoker invoker = new CommandInvoker();

        // Add commands to the invoker
        invoker.addCommand(rotateCommand);
        invoker.addCommand(activatePanelsCommand);
        invoker.addCommand(collectDataCommand);

        // Execute all commands
        invoker.executeCommands();
    }
}
