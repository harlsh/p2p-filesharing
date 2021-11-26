package config;

import lombok.Data;

@Data
public class CommonConfig {
    private Integer numberOfPreferredNeighbors;
    private Integer unchockingInterval;
    private Integer optimisticUnchockingInterval;
    private String fileName;
    private Integer fileSize;
    private Integer pieceSize;
}
