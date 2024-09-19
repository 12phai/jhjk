public class Main {
    public static void main(String[] args) {
        // Get the singleton instance of Satellite
        Satellite satellite = Satellite.getInstance();

        // Create CommandInvoker instance
        CommandInvoker invoker = new CommandInvoker();

        // Add commands to the invoker
        invoker.addCommand(new RotateCommand(satellite, "South"));
        invoker.addCommand(new ActivatePanelsCommand(satellite));
        invoker.addCommand(new CollectDataCommand(satellite));

        // Execute all commands
        invoker.executeCommands();

        // Example of new command execution
        invoker.addCommand(new DeactivatePanelsCommand(satellite));
        invoker.executeCommands();
    }
}
