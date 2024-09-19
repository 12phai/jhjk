// Command interface
public interface Command {
    void execute();
}

// BookRoomCommand class
public class BookRoomCommand implements Command {
    private ConferenceRoom room;
    private String startTime;
    private int duration;

    public BookRoomCommand(ConferenceRoom room, String startTime, int duration) {
        this.room = room;
        this.startTime = startTime;
        this.duration = duration;
    }

    @Override
    public void execute() {
        if (room.getOccupants() == 0) {
            System.out.println("Room " + room.getRoomId() + " booked from " + startTime + " for " + duration + " minutes.");
        } else {
            System.out.println("Room " + room.getRoomId() + " is already booked or occupied. Cannot book.");
        }
    }
}

// CancelBookingCommand class
public class CancelBookingCommand implements Command {
    private ConferenceRoom room;

    public CancelBookingCommand(ConferenceRoom room) {
        this.room = room;
    }

    @Override
    public void execute() {
        if (room.getOccupants() == 0) {
            System.out.println("Booking for Room " + room.getRoomId() + " cancelled successfully.");
        } else {
            System.out.println("Room " + room.getRoomId() + " is occupied or not booked. Cannot cancel.");
        }
    }
}
