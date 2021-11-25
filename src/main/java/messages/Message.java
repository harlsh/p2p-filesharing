package messages;

import lombok.Data;
import lombok.NonNull;

import java.util.BitSet;

@Data

public class Message {
    @NonNull
    Integer messageLength;
    @NonNull
    MessageType messageType;
    @NonNull
    byte[] messagePayload;

    Message(Integer messageLength, BitSet bitField) {
        this.messageLength = messageLength;
        this.messageType = MessageType.BITFIELD;
        bitField.set(0, bitField.size());
        System.out.println("Message length=" + messageLength);
        this.messagePayload = bitField.toByteArray();
    }
}
