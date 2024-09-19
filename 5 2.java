import java.util.ArrayList;
import java.util.List;

// Observer interface for users
abstract class User {
    protected String username;

    public User(String username) {
        this.username = username;
    }

    public abstract void receiveMessage(String message);
}

// Concrete User class
class ChatUser extends User {
    public ChatUser(String username) {
        super(username);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println(username + " received message: " + message);
    }
}

// ChatRoom class acting as the subject (Observable)
class ChatRoom {
    private String roomId;
    private List<User> users;  // List of users (observers) in the room
    private List<String> messages;  // Stores chat history

    public ChatRoom(String roomId) {
        this.roomId = roomId;
        this.users = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    // Add user to the chat room
    public void addUser(User user) {
        users.add(user);
        System.out.println(user.username + " has joined room " + roomId);
        notifyAllUsers(user.username + " has joined the room.");
    }

    // Remove user from the chat room
    public void removeUser(User user) {
        users.remove(user);
        System.out.println(user.username + " has left room " + roomId);
        notifyAllUsers(user.username + " has left the room.");
    }

    // Post a message to the chat room
    public void postMessage(String username, String message) {
        String fullMessage = "[" + username + "]: " + message;
        messages.add(fullMessage);  // Add message to chat history
        notifyAllUsers(fullMessage);
    }

    // Notify all users in the room
    private void notifyAllUsers(String message) {
        for (User user : users) {
            user.receiveMessage(message);
        }
    }

    // Get active users in the room
    public List<String> getActiveUsers() {
        List<String> activeUsers = new ArrayList<>();
        for (User user : users) {
            activeUsers.add(user.username);
        }
        return activeUsers;
    }
}
