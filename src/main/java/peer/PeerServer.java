package peer;


import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import messages.HandshakeMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


@NoArgsConstructor
public class PeerServer implements Runnable{
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private Integer portNumber;

    PeerServer(Integer portNumber) {
        this.portNumber = portNumber;
    }


    @SneakyThrows
    @Override
    public void run() {
        serverSocket = new ServerSocket(portNumber);

        while (true) {
            clientSocket = serverSocket.accept();// blocking -> wait
            this.in = new ObjectInputStream(clientSocket.getInputStream());
            this.out = new ObjectOutputStream(clientSocket.getOutputStream());
            System.out.println("Someone connected to this server by port"+portNumber);

            HandshakeMessage message = (HandshakeMessage) in.readObject();
            System.out.println("Receive handshake message: " + message + " from client ");

            Object msg1 = new HandshakeMessage(1002);
            this.out.writeObject(msg1);
            System.out.println("Send handshake message: " + msg1 + " back to client ");

//            Object msg1 = client1.sendMessage(new HandshakeMessage(100_server));
//            out.writeObject("thank you");

        }
    }


//    public void runServer(int port) throws IOException {
//        serverSocket = new ServerSocket(port);
//        executorService = Executors.newFixedThreadPool(8);
//
//        while (true) {
//            clientSocket = serverSocket.accept();// blocking -> wait
//            this.in = new ObjectInputStream(clientSocket.getInputStream());
//            this.out = new ObjectOutputStream(clientSocket.getOutputStream());
//            //try to connect to some server
////            PeerClient client1 = new PeerClient();
////            client1.startConnection("127.0.0.1", 8888);
////            Object msg1 = client1.sendMessage(new HandshakeMessage(100));
////            System.out.println(msg1);
//
//            System.out.println("Someone connected to me.");
//            executorService.submit(new ServiceRequest(clientSocket, in, out));
//        }
//
//    }
}
