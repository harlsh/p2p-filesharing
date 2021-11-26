package peer;

import config.CommonConfigLoader;
import config.PeerInfo;
import config.PeerInfoLoader;
import lombok.SneakyThrows;
import messages.BitFieldMessage;
import messages.Message;

import java.util.List;
import java.util.stream.Collectors;

public class PeerProcess {
    @SneakyThrows
    public static void main(String[] args) {


        Integer inputPeerId = 1001;
        PeerInfoLoader peerInfoLoader = new PeerInfoLoader("PeerInfo.cfg");
        CommonConfigLoader commonConfigLoader = new CommonConfigLoader("Common.cfg");
        System.out.println(commonConfigLoader.getCommonConfig());

        int numberOfPieces = commonConfigLoader.getCommonConfig().getFileSize() / commonConfigLoader.getCommonConfig().getPieceSize();
        System.out.println(numberOfPieces);
        List<PeerInfo> peerInfos = peerInfoLoader.getPeerInfos();
        System.out.println(peerInfos);

        System.out.println(peerInfos.stream().filter(peerInfo -> peerInfo.getPeerId() == inputPeerId).collect(Collectors.toList()));
        final PeerInfo peerInfo1 = peerInfos.stream().filter(peerInfo -> peerInfo.getPeerId().equals(inputPeerId)).findFirst().get();
        if (peerInfo1.getHasFile()) {
            // Send bitfield message to server
            Message message = new BitFieldMessage(numberOfPieces);
        }

        Integer peerId1 = peerInfoLoader.getPeerInfos().get(0).getPeerId();
        String peerHost1 = peerInfoLoader.getPeerInfos().get(0).getHostName();
        Integer peerPort1 = peerInfoLoader.getPeerInfos().get(0).getPort();


        PeerServer peer = new PeerServer(peerId1, 8888);
        Thread t1 = new Thread(peer);
        t1.start();

        Integer peerId2 = peerInfoLoader.getPeerInfos().get(1).getPeerId();
        PeerClient peerclient = new PeerClient(peerId2, "127.0.0.1", 8888);
        Thread ClientThread = new Thread(peerclient);
        ClientThread.start();

    }
}