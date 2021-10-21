package config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PeerInfo {
    Integer peerId;
    String hostName;
    Integer port;
    Boolean hasFile;

}
