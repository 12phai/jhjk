import java.util.List;

// HubProxy class
public class HubProxy {
    private SmartHub hub;
    private List<String> authorizedUsers;

    public HubProxy(SmartHub hub, List<String> authorizedUsers) {
        this.hub = hub;
        this.authorizedUsers = authorizedUsers;
    }

    public void sendCommand(String user, String deviceId, String command) {
        if (authorizedUsers.contains(user)) {
            hub.sendCommand(deviceId, command);
        } else {
            System.out.println("Unauthorized user " + user + " attempted to control device " + deviceId + ".");
        }
    }

    public void getStatus(String user) {
        if (authorizedUsers.contains(user)) {
            hub.getStatus();
        } else {
            System.out.println("Unauthorized user " + user + " attempted to view status.");
        }
    }
}

// Example usage
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        SmartHub hub = new SmartHub();
        SmartDevice livingRoomLight = DeviceFactory.createDevice("light", "LR001");
        Thermostat homeThermostat = (Thermostat) DeviceFactory.createDevice("thermostat", "HT001");
        SmartDevice frontDoorLock = DeviceFactory.createDevice("door", "DL001");

        hub.addDevice(livingRoomLight);
        hub.addDevice(homeThermostat);
        hub.addDevice(frontDoorLock);

        List<String> authorizedUsers = Arrays.asList("Alice", "Bob");
        HubProxy proxy = new HubProxy(hub, authorizedUsers);

        // Authorized user
        proxy.sendCommand("Alice", "LR001", "turnOn");
        proxy.getStatus("Alice");

        // Unauthorized user
        proxy.sendCommand("Charlie", "LR001", "turnOn");
        proxy.getStatus("Charlie");
    }
}
