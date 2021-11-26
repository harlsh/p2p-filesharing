package peer;

import lombok.SneakyThrows;
import messages.BitFieldMessage;
import messages.HandshakeMessage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServiceRequest implements Runnable {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private int peerId;


    @SneakyThrows
    public ServiceRequest(Socket clientSocket, int peerId) {
        this.socket = clientSocket;

        this.in = new ObjectInputStream(clientSocket.getInputStream());
        this.out = new ObjectOutputStream(clientSocket.getOutputStream());
        this.peerId = peerId;
    }


    @SneakyThrows
    public void run() {
        //Do your logic here. You have the `socket` available to read/write data.

        Object readObject = in.readObject();
        if (readObject instanceof HandshakeMessage) {
            //perform handshake
            HandshakeMessage message = (HandshakeMessage) readObject;
            System.out.println("Received handshake message: " + message + " from client " + message.getPeerId());
            HandshakeMessage msg1 = new HandshakeMessage(peerId);
            this.out.writeObject(msg1);
            System.out.println("Sent handshake message: " + msg1 + " back to client " + message.getPeerId());
        }
        if (PeerUtil.peerInfos.get(0).getHasFile()) {
            int numberOfPieces = PeerUtil.commonConfig.getFileSize() / PeerUtil.commonConfig.getPieceSize();
            this.out.writeObject(new BitFieldMessage(numberOfPieces));
        }

        while (true) {
            readObject = in.readObject();
            if (readObject instanceof HandshakeMessage) {
                //perform handshake
                HandshakeMessage message = (HandshakeMessage) readObject;
                System.out.println("Received handshake message: " + message + " from client " + message.getPeerId());
                HandshakeMessage msg1 = new HandshakeMessage(peerId);
                this.out.writeObject(msg1);
                System.out.println("Sent handshake message: " + msg1 + " back to client " + message.getPeerId());
            } else if (readObject instanceof BitFieldMessage) {
                BitFieldMessage message = (BitFieldMessage) readObject;
                System.out.println("Received a bitfield message from client: " + message);
            }
        }

    }
}

