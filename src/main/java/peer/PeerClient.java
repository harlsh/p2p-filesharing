package peer;

import lombok.SneakyThrows;
import messages.HandshakeMessage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class PeerClient implements Runnable{
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
        System.out.println("Server's response:");
        System.out.println(resp);
        return resp;
    }

    public String receiveMessage(){
        return "i got a message";
    }
    @SneakyThrows
    private void stopConnection() {
        in.close();
        out.close();
        clientSocket.close();
    }

    @Override
    public void run() {
        this.startConnection("127.0.0.1", 8888);
        Object msg1 = this.sendMessage(new HandshakeMessage(1001));
        //System.out.println("stop connection");
        this.stopConnection();
    }
}
