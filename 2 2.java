import java.util.ArrayList;
import java.util.List;

// ConferenceRoom class
public class ConferenceRoom {
    private int roomId;
    private int capacity;
    private int occupants;
    private boolean acOn;
    private boolean lightsOn;
    private List<Observer> observers;

    public ConferenceRoom(int roomId) {
        this.roomId = roomId;
        this.capacity = 0;
        this.occupants = 0;
        this.acOn = false;
        this.lightsOn = false;
        this.observers = new ArrayList<>();
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void addOccupants(int count) {
        if (count < 2) {
            System.out.println("Room " + roomId + " occupancy insufficient to mark as occupied.");
        } else {
            this.occupants = count;
            notifyObservers();
            System.out.println("Room " + roomId + " is now occupied by " + occupants + " persons. AC and lights turned on.");
        }
    }

    public void removeOccupants() {
        this.occupants = 0;
        notifyObservers();
        System.out.println("Room " + roomId + " is now unoccupied. AC and lights turned off.");
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Getter methods for testing purposes
    public int getRoomId() {
        return roomId;
    }

    public boolean isAcOn() {
        return acOn;
    }

    public boolean isLightsOn() {
        return lightsOn;
    }

    // Setter methods for internal use
    public void setAcOn(boolean acOn) {
        this.acOn = acOn;
    }

    public void setLightsOn(boolean lightsOn) {
        this.lightsOn = lightsOn;
    }
}

// Observer interface
interface Observer {
    void update(ConferenceRoom room);
}

// ControlSystem class implementing Observer
public class ControlSystem implements Observer {
    @Override
    public void update(ConferenceRoom room) {
        if (room.getOccupants() > 0) {
            room.setAcOn(true);
            room.setLightsOn(true);
        } else {
            room.setAcOn(false);
            room.setLightsOn(false);
        }
    }
}
