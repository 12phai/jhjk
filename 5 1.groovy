public class ChatRoomManager {

    // Singleton instance
    private static ChatRoomManager instance = null;
    
    // Store chat rooms by room ID
    private Map<String, ChatRoom> chatRooms;

    // Private constructor to prevent instantiation
    private ChatRoomManager() {
        chatRooms = new HashMap<>();
    }

    // Method to get the singleton instance
    public static synchronized ChatRoomManager getInstance() {
        if (instance == null) {
            instance = new ChatRoomManager();
        }
        return instance;
    }

    // Method to create a new chat room
    public void createRoom(String roomId) {
        if (!chatRooms.containsKey(roomId)) {
            chatRooms.put(roomId, new ChatRoom(roomId));
            System.out.println("Chat room " + roomId + " created.");
        }
    }

    // Method to retrieve a chat room by ID
    public ChatRoom getRoom(String roomId) {
        return chatRooms.get(roomId);
    }

    // Nested ChatRoom class for demonstration
    class ChatRoom {
        private String roomId;

        public ChatRoom(String roomId) {
            this.roomId = roomId;
        }

        public String getRoomId() {
            return roomId;
        }
    }
}
