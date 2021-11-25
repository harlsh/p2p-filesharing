package messages;

import java.util.BitSet;


public class BitFieldMessage extends Message {

    public BitFieldMessage(Integer numberOfPieces) {
        super(((numberOfPieces >> 3) + 1) << 3, new BitSet(numberOfPieces));
    }

}
