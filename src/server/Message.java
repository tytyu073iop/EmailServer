package server;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 943223721334958643L;
    
    public String sender;
    public String receiver;
    public EmailFile file;
    public String message;

    // Constructor
    public Message(String sender, String receiver, EmailFile file, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.file = file;
        this.message = message;
    }
}