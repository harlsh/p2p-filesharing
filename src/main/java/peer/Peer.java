package peer;


import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@NoArgsConstructor
public class Peer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private ExecutorService executorService;

    public void runServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        executorService = Executors.newFixedThreadPool(8);

        while (true) {
            clientSocket = serverSocket.accept();
            this.in = new ObjectInputStream(clientSocket.getInputStream());
            this.out = new ObjectOutputStream(clientSocket.getOutputStream());
            System.out.println("Someone connected to me.");
            executorService.submit(new ServiceRequest(clientSocket, in, out));
        }

    }


}
