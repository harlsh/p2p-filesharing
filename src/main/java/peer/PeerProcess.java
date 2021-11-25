package peer;

import config.CommonConfig;
import config.CommonConfigLoader;
import config.PeerInfoLoader;
import lombok.SneakyThrows;
import messages.HandshakeMessage;

public class PeerProcess{
    @SneakyThrows
    public static void main(String[] args){

        CommonConfigLoader commonConfigLoader = new CommonConfigLoader("Common.cfg");
        System.out.println(commonConfigLoader.getCommonConfig());
        PeerInfoLoader peerInfoLoader = new PeerInfoLoader("PeerInfo.cfg");
        System.out.println(peerInfoLoader.getPeerInfos());

        Integer peerId1=peerInfoLoader.getPeerInfos().get(0).getPeerId();
        String peerHost1 = peerInfoLoader.getPeerInfos().get(0).getHostName();
        Integer peerPort1=peerInfoLoader.getPeerInfos().get(0).getPort();

        //System.out.println("Listening");
        PeerServer peer = new PeerServer(peerId1,8888);
        Thread t1 = new Thread(peer);
        t1.start();

        //System.out.println("Starting client thread..");
        Integer peerId2=peerInfoLoader.getPeerInfos().get(1).getPeerId();
        PeerClient peerclient=new PeerClient(peerId2);
        Thread ClientThread = new Thread(peerclient);
        ClientThread.start();


    }
}
