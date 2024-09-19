public class OfficeManager {
    private static OfficeManager instance = null;
    private Map<Integer, ConferenceRoom> rooms;

    // Private constructor to prevent instantiation
    private OfficeManager() {
        rooms = new HashMap<>();
    }

    // Static method to get the singleton instance
    public static synchronized OfficeManager getInstance() {
        if (instance == null) {
            instance = new OfficeManager();
        }
        return instance;
    }

    // Configure office with the specified number of rooms
    public void configureOffice(int numRooms) {
        for (int i = 1; i <= numRooms; i++) {
            rooms.put(i, new ConferenceRoom(i));
        }
        System.out.println("Office configured with " + numRooms + " meeting rooms.");
    }

    // Set the maximum capacity of a specific room
    public void setRoomCapacity(int roomId, int capacity) {
        ConferenceRoom room = rooms.get(roomId);
        if (room != null && capacity > 0) {
            room.setCapacity(capacity);
            System.out.println("Room " + roomId + " maximum capacity set to " + capacity + ".");
        } else {
            System.out.println("Invalid room or capacity.");
        }
    }
}

// ConferenceRoom class
class ConferenceRoom {
    private int id;
    private int capacity;

    public ConferenceRoom(int id) {
        this.id = id;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
