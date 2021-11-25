package config;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Data
public class PeerInfoLoader {
    private InputStream peerInfoFile;
    private List<PeerInfo> peerInfos;

    public PeerInfoLoader(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        peerInfoFile = classLoader.getResourceAsStream(fileName);

        peerInfos = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(peerInfoFile));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] split = line.split(" ");
                Integer peerId = Integer.parseInt(split[0]);
                String hostName = split[1];
                Integer port = Integer.parseInt(split[2]);
                Boolean hasFile = Integer.parseInt(split[3]) != 0;
                PeerInfo peerInfo = new PeerInfo(peerId, hostName, port, hasFile);
                peerInfos.add(peerInfo);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
