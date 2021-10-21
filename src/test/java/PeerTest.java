import messages.HandshakeMessage;
import org.junit.Test;
import peer.PeerClient;

import static org.junit.Assert.assertEquals;

public class PeerTest {
    @Test
    public void givenClient1_whenServerResponds_thenCorrect() {
        PeerClient client1 = new PeerClient();
        client1.startConnection("127.0.0.1", 8888);
        Object msg1 = client1.sendMessage(new HandshakeMessage(100));
        System.out.println(msg1);
        assertEquals("hello", "hello");
    }

}
