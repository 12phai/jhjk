// DeviceFactory class
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
