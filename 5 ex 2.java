public class Main {
    public static void main(String[] args) {
        // Initialize the singleton chat room manager
        ChatRoomManager manager = ChatRoomManager.getInstance();

        // Create or join chat rooms
        manager.createRoom("Room123");
        ChatRoom room = manager.getRoom("Room123");

        // Add users to the chat room
        ChatUser alice = new ChatUser("Alice");
        ChatUser bob = new ChatUser("Bob");

        room.addUser(alice);
        room.addUser(bob);

        // Users sending messages
        room.postMessage("Alice", "Hello, everyone!");
        room.postMessage("Bob", "Hi, Alice!");
    }
}
