package messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.util.BitSet;

@Data
@AllArgsConstructor
public class Message implements Serializable {
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
        this.messagePayload = bitField.toByteArray();
    }

    Message(MessageType messageType) {
        this.messageType = messageType;
    }

}
