import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// SmartHub class
public class SmartHub {
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

// Example usage
public class Main {
    public static void main(String[] args) {
        SmartHub hub = new SmartHub();
        SmartDevice livingRoomLight = DeviceFactory.createDevice("light", "LR001");
        Thermostat homeThermostat = (Thermostat) DeviceFactory.createDevice("thermostat", "HT001");
        SmartDevice frontDoorLock = DeviceFactory.createDevice("door", "DL001");

        hub.addDevice(livingRoomLight);
        hub.addDevice(homeThermostat);
        hub.addDevice(frontDoorLock);

        hub.addTrigger("temperature > 70", "setTemp(60)");
        hub.checkTriggers();

        hub.getStatus();
    }
}
