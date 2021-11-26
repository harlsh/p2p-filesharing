package peer;


import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@NoArgsConstructor
public class PeerServer implements Runnable {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private ExecutorService executorService = Executors.newFixedThreadPool(8);

    private Integer peerId;
    private Integer portNumber;

    PeerServer(Integer peerId, Integer portNumber) {
        this.peerId = peerId;
        this.portNumber = portNumber;
    }


    @SneakyThrows
    @Override
    public void run() {
        serverSocket = new ServerSocket(portNumber);

        while (true) {
            clientSocket = serverSocket.accept();
            executorService.submit(new ServiceRequest(clientSocket, peerId)); //happens in the background
        }
    }


}