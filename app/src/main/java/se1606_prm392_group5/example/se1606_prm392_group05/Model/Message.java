package se1606_prm392_group5.example.se1606_prm392_group05.Model;

public class Message {
    private String sender;
    private String message;
    private String timestamp;

    public Message(String sender, String message) {
        this.sender = sender;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}