package messages;


import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;

@Data
public class HandshakeMessage implements Serializable {
    String handshakeHeader;
    Byte[] zeroBits;
    Integer peerId;

    public HandshakeMessage(Integer peerId) {
        this.handshakeHeader = "P2PFILESHARINGPROJ";
        zeroBits = new Byte[10];
        Arrays.fill(zeroBits, (byte) 0);
        this.peerId = peerId;
    }
}
