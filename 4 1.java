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

// Example usage
public class Main {
    public static void main(String[] args) {
        Light livingRoomLight = new Light("LR001");
        livingRoomLight.performAction("turnOn");
        System.out.println(livingRoomLight);

        Thermostat homeThermostat = new Thermostat("HT001", 70);
        homeThermostat.performAction("setTemp(72)");
        System.out.println(homeThermostat);

        DoorLock frontDoorLock = new DoorLock("DL001");
        frontDoorLock.performAction("lock");
        System.out.println(frontDoorLock);
    }
}
