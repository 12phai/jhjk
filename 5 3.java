// Abstract adapter for communication protocols
public abstract class CommunicationAdapter {
    public abstract void send(String message);
    public abstract void receive();
}

// Concrete adapter for WebSocket communication
public class WebSocketAdapter extends CommunicationAdapter {
    @Override
    public void send(String message) {
        System.out.println("Sending via WebSocket: " + message);
        // WebSocket-specific logic would go here
    }

    @Override
    public void receive() {
        System.out.println("Receiving via WebSocket");
        // WebSocket-specific logic would go here
    }
}
