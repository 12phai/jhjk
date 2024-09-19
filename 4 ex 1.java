import java.util.Arrays;
import java.util.List;

// Main class demonstrating the setup and usage
public class Main {
    public static void main(String[] args) {
        // Create the SmartHub instance
        SmartHub hub = new SmartHub();

        // Create the HubProxy instance with authorized users
        List<String> authorizedUsers = Arrays.asList("Alice", "Bob");
        HubProxy proxy = new HubProxy(hub, authorizedUsers);

        // Create devices using the DeviceFactory
        SmartDevice light = DeviceFactory.createDevice("light", "1");
        SmartDevice thermostat = DeviceFactory.createDevice("thermostat", "2");
        SmartDevice doorLock = DeviceFactory.createDevice("door", "3");

        // Add devices to the SmartHub
        hub.addDevice(light);
        hub.addDevice(thermostat);
        hub.addDevice(doorLock);

        // Authorized user operations
        proxy.sendCommand("Alice", "1", "turnOn");
        proxy.getStatus("Alice");

        // Unauthorized user operations
        proxy.sendCommand("Charlie", "1", "turnOn");
        proxy.getStatus("Charlie");
    }
}

// DeviceFactory class to create devices
public class DeviceFactory {
    public static SmartDevice createDevice(String deviceType, String deviceId) {
        switch (deviceType.toLowerCase()) {
            case "light":
                return new Light(deviceId);
            case "thermostat":
                return new Thermostat(deviceId, 70); // default temperature set to 70°F
            case "door":
                return new DoorLock(deviceId);
            default:
                throw new IllegalArgumentException("Unknown device type: " + deviceType);
        }
    }
}

// Abstract SmartDevice class
abstract class SmartDevice {
    protected String deviceId;
    protected String deviceType;
    protected String status;

    public SmartDevice(String deviceId, String deviceType) {
        this.deviceId = deviceId;
        this.deviceType = deviceType;
        this.status = "off";
    }

    public abstract void performAction(String action);

    @Override
    public String toString() {
        return deviceType.substring(0, 1).toUpperCase() + deviceType.substring(1) + " " + deviceId + " is " + status;
    }
}

// Light class
class Light extends SmartDevice {
    public Light(String deviceId) {
        super(deviceId, "light");
    }

    @Override
    public void performAction(String action) {
        if ("turnOn".equals(action)) {
            this.status = "on";
        } else if ("turnOff".equals(action)) {
            this.status = "off";
        }
    }
}

// Thermostat class
class Thermostat extends SmartDevice {
    private int temperature;

    public Thermostat(String deviceId, int temperature) {
        super(deviceId, "thermostat");
        this.temperature = temperature;
    }

    @Override
    public void performAction(String action) {
        if (action.startsWith("setTemp")) {
            String[] parts = action.split("\\(");
            this.temperature = Integer.parseInt(parts[1].replace(")", "").trim());
        }
    }

    public int getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return "Thermostat " + deviceId + " is set to " + temperature + "°F";
    }
}

// DoorLock class
class DoorLock extends SmartDevice {
    public DoorLock(String deviceId) {
        super(deviceId, "doorlock");
    }

    @Override
    public void performAction(String action) {
        if ("lock".equals(action)) {
            this.status = "locked";
        } else if ("unlock".equals(action)) {
            this.status = "unlocked";
        }
    }
}

// HubProxy class for managing authorized access
class HubProxy {
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

// SmartHub class for managing devices and triggers
class SmartHub {
    private Map<String, SmartDevice> devices;
    private List<Trigger> triggers;

    public SmartHub() {
        devices = new HashMap<>();
        triggers = new ArrayList<>();
    }

    public void addDevice(SmartDevice device) {
        devices.put(device.deviceId, device);
    }

    public void removeDevice(String deviceId) {
        devices.remove(deviceId);
    }

    public void sendCommand(String deviceId, String command) {
        SmartDevice device = devices.get(deviceId);
        if (device != null) {
            device.performAction(command);
            System.out.println("Executed " + command + " on " + device);
        }
    }

    public void addTrigger(String condition, String action) {
        triggers.add(new Trigger(condition, action));
    }

    public void checkTriggers() {
        for (Trigger trigger : triggers) {
            String condition = trigger.condition;
            String action = trigger.action;

            if (evaluateCondition(condition)) {
                String actionCommand = action.substring(0, action.indexOf("("));
                String deviceId = action.substring(action.indexOf("(") + 1, action.indexOf(")"));
                sendCommand(deviceId, actionCommand);
            }
        }
    }

    private boolean evaluateCondition(String condition) {
        for (SmartDevice device : devices.values()) {
            if (device instanceof Thermostat) {
                Thermostat thermostat = (Thermostat) device;
                if (condition.startsWith("temperature")) {
                    String[] parts = condition.split(" ");
                    String operator = parts[1];
                    int threshold = Integer.parseInt(parts[2]);

                    if (operator.equals(">") && thermostat.getTemperature() > threshold) {
                        return true;
                    } else if (operator.equals("<") && thermostat.getTemperature() < threshold) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void getStatus() {
        for (SmartDevice device : devices.values()) {
            System.out.println(device);
        }
    }

    // Inner class to represent a trigger
    private class Trigger {
        String condition;
        String action;

        Trigger(String condition, String action) {
            this.condition = condition;
            this.action = action;
        }
    }
}
