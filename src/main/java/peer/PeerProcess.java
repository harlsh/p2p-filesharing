package peer;

import config.CommonConfigLoader;
import config.PeerInfoLoader;
import lombok.SneakyThrows;

public class PeerProcess {
    @SneakyThrows
    public static void main(String[] args) {
//        Peer peer = new Peer();
//        peer.runServer(8888);
        CommonConfigLoader commonConfigLoader = new CommonConfigLoader("Common.cfg");
        System.out.println(commonConfigLoader.getCommonConfig());
        PeerInfoLoader peerInfoLoader = new PeerInfoLoader("PeerInfo.cfg");
        System.out.println(peerInfoLoader.getPeerInfos());
    }
}
