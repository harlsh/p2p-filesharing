package peer;

import config.CommonConfig;
import config.CommonConfigLoader;
import config.PeerInfo;
import config.PeerInfoLoader;

import java.util.List;

public class PeerUtil {

    public static List<PeerInfo> peerInfos = new PeerInfoLoader("PeerInfo.cfg").getPeerInfos();
    public static CommonConfig commonConfig = new CommonConfigLoader("Common.cfg").getCommonConfig();

    private static void accept(PeerInfo peerInfo) {
        new Thread(new PeerClient(peerInfo.getPeerId(), peerInfo.getHostName(), peerInfo.getPort())).start();
    }

}
