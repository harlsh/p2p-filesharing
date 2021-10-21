package peer;

import lombok.SneakyThrows;
import messages.HandshakeMessage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class PeerClient {
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    @SneakyThrows
    public void startConnection(String ip, int port) {
        clientSocket = new Socket(ip, port);
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
    }

    @SneakyThrows
    public Object sendMessage(HandshakeMessage msg) {
        out.writeObject(msg);
        HandshakeMessage resp = (HandshakeMessage) in.readObject();
        System.out.println(resp);
        return resp;
    }

    @SneakyThrows
    public void stopConnection() {
        in.close();
        out.close();
        clientSocket.close();
    }
}
