package peer;

import config.CommonConfig;
import config.CommonConfigLoader;
import config.PeerInfo;
import config.PeerInfoLoader;
import lombok.SneakyThrows;
import messages.BitFieldMessage;
import messages.HandshakeMessage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class PeerClient implements Runnable {
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private Integer peerId;
    private String hostName;
    private int port;
    private PeerInfo peerInfo;
    private CommonConfig commonConfig;

    public PeerClient(Integer peerId, String hostName, int port) {
        this.peerId = peerId;
        this.hostName = hostName;
        this.port = port;
        this.commonConfig = new CommonConfigLoader("Common.cfg").getCommonConfig();
        peerInfo = new PeerInfoLoader("PeerInfo.cfg").getPeerInfos()
                .stream()
                .filter(peerInfo1 -> peerInfo1.getPeerId().equals(peerId))
                .findFirst()
                .get();
    }


    @SneakyThrows
    public void startConnection() {
        clientSocket = new Socket(this.hostName, this.port);
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
    }


    @SneakyThrows
    private void stopConnection() {
        in.close();
        out.close();
        clientSocket.close();
    }

    @SneakyThrows
    @Override
    public void run() {
        this.startConnection(); // setup input and output streams


        System.out.println("Sending handshake to server");
        this.out.writeObject(new HandshakeMessage(peerId));
        Object response = this.in.readObject();
        if (response instanceof HandshakeMessage) {
            HandshakeMessage msg1 = (HandshakeMessage) response;
            if (msg1.getHandshakeHeader().equals("P2PFILESHARINGPROJ") && msg1.getPeerId() < peerId)
                System.out.println("Successful handshake");
        } else this.stopConnection();

        System.out.println("Do I have the file " + peerInfo.getHasFile());
        if (peerInfo.getHasFile()) //send a bitfield message.
        {
            int numberOfPieces = commonConfig.getFileSize() / commonConfig.getPieceSize();
            this.out.writeObject(new BitFieldMessage(numberOfPieces));
        }
        response = in.readObject();
        if (response instanceof BitFieldMessage) {
            BitFieldMessage msg1 = (BitFieldMessage) response;
            System.out.println("Got bitfield message from server " + msg1);
        }

    }
}