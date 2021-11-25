package peer;

import config.CommonConfigLoader;
import config.PeerInfoLoader;
import lombok.SneakyThrows;
import messages.BitFieldMessage;
import messages.Message;

public class PeerProcess {
    @SneakyThrows
    public static void main(String[] args) {
//        Peer peer = new Peer();
//        peer.runServer(8888);
        CommonConfigLoader commonConfigLoader = new CommonConfigLoader("Common.cfg");
        System.out.println(commonConfigLoader.getCommonConfig());
        PeerInfoLoader peerInfoLoader = new PeerInfoLoader("PeerInfo.cfg");
        System.out.println(peerInfoLoader.getPeerInfos());

        Message message = new BitFieldMessage(100);
        System.out.println(message);
    }
}
