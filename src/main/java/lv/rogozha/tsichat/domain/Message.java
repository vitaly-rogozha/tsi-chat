package lv.rogozha.tsichat.domain;

public class Message {
    
    private User author;
    private String messageText;
    private Long timestamp;
    
    public Message(User author, String messageText, Long timestamp) {
        this.author = author;
        this.messageText = messageText;
        this.timestamp = timestamp;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTextMessage() {
        return messageText;
    }

    public void setTextMessage(String messageText) {
        this.messageText = messageText;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
    
}
