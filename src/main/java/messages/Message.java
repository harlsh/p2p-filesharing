package messages;

import lombok.Data;

@Data
public class Message {
    Integer messageLength;
    MessageType messageType;
    Byte[] messagePayload;
}
