package peer;

import config.CommonConfig;
import config.CommonConfigLoader;
import config.PeerInfoLoader;
import lombok.SneakyThrows;
import messages.HandshakeMessage;

public class PeerProcess{
    @SneakyThrows
    public static void main(String[] args){
        //System.out.println("Listening");
        PeerServer peer = new PeerServer(8888);
        Thread t1 = new Thread(peer);
        t1.start();

        //System.out.println("Starting client thread..");
        PeerClient peerclient=new PeerClient();
        Thread ClientThread = new Thread(peerclient);
        ClientThread.start();



        CommonConfigLoader commonConfigLoader = new CommonConfigLoader("Common.cfg");
        System.out.println(commonConfigLoader.getCommonConfig());
        PeerInfoLoader peerInfoLoader = new PeerInfoLoader("PeerInfo.cfg");
        System.out.println(peerInfoLoader.getPeerInfos());

        System.out.println(commonConfigLoader.getCommonConfig().getFileSize());


    }
}
