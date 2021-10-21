package peer;

import lombok.SneakyThrows;
import messages.HandshakeMessage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServiceRequest implements Runnable {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ServiceRequest(Socket connection, ObjectInputStream in, ObjectOutputStream out) {
        this.socket = connection;
        this.in = in;
        this.out = out;
    }

    @SneakyThrows
    public void run() {
        //Do your logic here. You have the `socket` available to read/write data.
        System.out.println("Sending a handshake to client");
        out.writeObject(new HandshakeMessage(1000));
    }
}

