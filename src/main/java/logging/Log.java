package logging;

import config.CommonConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Log {
    BufferedWriter logWriter = null;
    File logFile = null;

    public Log() {
    }

    public Log(File logFile) {
        try {
            this.logFile = logFile;
            logWriter = new BufferedWriter(new FileWriter(logFile.getAbsolutePath(), true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void readCommonFile(int id1, CommonConfig cfg) {
        String timeStamp = new SimpleDateFormat("y-M-d 'at' h:m:s a z").format(Calendar.getInstance().getTime());
        StringBuffer log_entry = new StringBuffer();
        log_entry.append(timeStamp + ": Peer [" + id1 + "] read Common.cfg file. \n Variables set: " + cfg);
        try {
            logWriter.write(log_entry.toString());
            logWriter.newLine();
            logWriter.flush();
        } catch (Exception e) {

        }
    }

    public synchronized void bitFieldReceived(int id1, int id2) {
        String timeStamp = new SimpleDateFormat("y-M-d 'at' h:m:s a z").format(Calendar.getInstance().getTime());
        StringBuffer log_entry = new StringBuffer();
        log_entry.append(timeStamp + ": Peer [" + id1 + "] received bitfield from Peer [" + id2 + "]");
        try {
            logWriter.write(log_entry.toString());
            logWriter.newLine();
            logWriter.flush();
        } catch (Exception e) {

        }
    }

    public synchronized void bitFieldSent(int id1, int id2) {
        String timeStamp = new SimpleDateFormat("y-M-d 'at' h:m:s a z").format(Calendar.getInstance().getTime());
        StringBuffer log_entry = new StringBuffer();
        log_entry.append(timeStamp + ": Peer [" + id1 + "] sent bitfield to Peer [" + id2 + "]");
        try {
            logWriter.write(log_entry.toString());
            logWriter.newLine();
            logWriter.flush();
        } catch (Exception e) {

        }
    }

    public synchronized void tcpConnectionTo(int id1, int id2) {

        String timeStamp = new SimpleDateFormat("y-M-d 'at' h:m:s a z").format(Calendar.getInstance().getTime());

        StringBuffer log_entry = new StringBuffer();

        log_entry.append(timeStamp + ": Peer [" + id1 + "] makes a connection to Peer [" + id2 + "].");
        try {
            logWriter.write(log_entry.toString());
            logWriter.newLine();
            logWriter.flush();
        } catch (Exception e) {

        }
    }

    public synchronized void tcpConnectionFrom(int id1, int id2) {

        String timeStamp = new SimpleDateFormat("y-M-d 'at' h:m:s a z").format(Calendar.getInstance().getTime());

        StringBuffer log_entry = new StringBuffer();

        log_entry.append(timeStamp + ": Peer [" + id1 + "] is connected from Peer [" + id2 + "].");
        try {
            logWriter.write(log_entry.toString());
            logWriter.newLine();
            logWriter.flush();
        } catch (Exception e) {
        }
    }


    public synchronized void ChangeNeighbors(int id, int[] id_list) {

        String timeStamp = new SimpleDateFormat("y-M-d 'at' h:m:s a z").format(Calendar.getInstance().getTime());

        StringBuffer log_entry = new StringBuffer();

        log_entry.append(timeStamp + ": Peer [" + id + "] has the preferred neighbors [");

        String result_string = "";
        StringBuilder sb = new StringBuilder();
        String s;
        for (int i : id_list) {
            s = Integer.toString(i);
            sb.append(s).append(",");
        }
        result_string = sb.deleteCharAt(sb.length() - 1).toString();

        log_entry.append(result_string);

        log_entry.append("].");

        try {
            logWriter.write(log_entry.toString());
            logWriter.newLine();
            logWriter.flush();
        } catch (Exception e) {
        }

    }

    public synchronized void changeOptimisticallyUnchockedNeighbor(int id1, int id2) {

        String timeStamp = new SimpleDateFormat("y-M-d 'at' h:m:s a z").format(Calendar.getInstance().getTime());

        StringBuffer log_entry = new StringBuffer();

        log_entry.append(timeStamp + ": Peer [" + id1 + "] has the optimistically unchoked neighbor [" + id2 + "].");


        try {
            logWriter.write(log_entry.toString());

            logWriter.newLine();
            logWriter.flush();
        } catch (Exception e) {
        }

    }

    public synchronized void unChoke(int id1, int id2) {

        String timeStamp = new SimpleDateFormat("y-M-d 'at' h:m:s a z").format(Calendar.getInstance().getTime());

        StringBuffer log_entry = new StringBuffer();

        log_entry.append(timeStamp + ": Peer [" + id1 + "] is unchoked by [" + id2 + "].");
        try {
            logWriter.write(log_entry.toString());
            logWriter.newLine();
            logWriter.flush();
        } catch (Exception e) {
        }

    }

    public synchronized void choke(int id1, int id2) {

        String timeStamp = new SimpleDateFormat("y-M-d 'at' h:m:s a z").format(Calendar.getInstance().getTime());

        StringBuffer log_entry = new StringBuffer();

        log_entry.append(timeStamp + ": Peer [" + id1 + "] is choked by [" + id2 + "].");
        try {
            logWriter.write(log_entry.toString());
            logWriter.newLine();
            logWriter.flush();
        } catch (Exception e) {
        }

    }

    public synchronized void sendHaveMessage(int id1, int id2, int index) {
        String timeStamp = new SimpleDateFormat("y-M-d 'at' h:m:s a z").format(Calendar.getInstance().getTime());
        StringBuffer log_entry = new StringBuffer();
        log_entry.append(timeStamp + ": Peer [" + id1 + "] sent 'have' message to [" + id2 + "] for the piece: " + index + ".");

        try {
            logWriter.write(log_entry.toString());
            logWriter.newLine();
            logWriter.flush();
        } catch (Exception e) {
        }
    }

    public synchronized void sendRequestMessage(int id1, int id2, int index) {
        String timeStamp = new SimpleDateFormat("y-M-d 'at' h:m:s a z").format(Calendar.getInstance().getTime());
        StringBuffer log_entry = new StringBuffer();
        log_entry.append(timeStamp + ": Peer [" + id1 + "] sent 'request' message to [" + id2 + "] for the piece: " + index + ".");

        try {
            logWriter.write(log_entry.toString());
            logWriter.newLine();
            logWriter.flush();
        } catch (Exception e) {
        }
    }

    public synchronized void sendPieceMessage(int id1, int id2, int index) {
        String timeStamp = new SimpleDateFormat("y-M-d 'at' h:m:s a z").format(Calendar.getInstance().getTime());
        StringBuffer log_entry = new StringBuffer();
        log_entry.append(timeStamp + ": Peer [" + id1 + "] sent the 'piece' " + index + " to Peer [" + id2 + "]. ");

        try {
            logWriter.write(log_entry.toString());
            logWriter.newLine();
            logWriter.flush();
        } catch (Exception e) {
        }
    }

    public synchronized void sendInterestedMessage(int id1, int id2) {

        String timeStamp = new SimpleDateFormat("y-M-d 'at' h:m:s a z").format(Calendar.getInstance().getTime());

        StringBuffer log_entry = new StringBuffer();

        log_entry.append(timeStamp + ": Peer [" + id1 + "] sent 'interested' message to [" + id2 + "].");
        try {
            logWriter.write(log_entry.toString());
            logWriter.newLine();
            logWriter.flush();
        } catch (Exception e) {
        }

    }

    public synchronized void sendNotInterestedMessage(int id1, int id2) {

        String timeStamp = new SimpleDateFormat("y-M-d 'at' h:m:s a z").format(Calendar.getInstance().getTime());

        StringBuffer log_entry = new StringBuffer();

        log_entry.append(timeStamp + ": Peer [" + id1 + "] sent 'not interested' message to [" + id2 + "].");
        try {
            logWriter.write(log_entry.toString());
            logWriter.newLine();
            logWriter.flush();
        } catch (Exception e) {
        }

    }

    public synchronized void receiveHaveMessage(int id1, int id2, int index) {
        String timeStamp = new SimpleDateFormat("y-M-d 'at' h:m:s a z").format(Calendar.getInstance().getTime());
        StringBuffer log_entry = new StringBuffer();
        log_entry.append(timeStamp + ": Peer [" + id1 + "] received 'have' message from [" + id2 + "] for the piece: " + index + ".");

        try {
            logWriter.write(log_entry.toString());
            logWriter.newLine();
            logWriter.flush();
        } catch (Exception e) {
        }
    }

    public synchronized void receiveInterestedMessage(int id1, int id2) {

        String timeStamp = new SimpleDateFormat("y-M-d 'at' h:m:s a z").format(Calendar.getInstance().getTime());

        StringBuffer log_entry = new StringBuffer();

        log_entry.append(timeStamp + ": Peer [" + id1 + "] received the 'interested' message from [" + id2 + "].");
        try {
            logWriter.write(log_entry.toString());
            logWriter.newLine();
            logWriter.flush();
        } catch (Exception e) {
        }

    }

    public synchronized void receiveNotInterestedMessage(int id1, int id2) {

        String timeStamp = new SimpleDateFormat("y-M-d 'at' h:m:s a z").format(Calendar.getInstance().getTime());

        StringBuffer log_entry = new StringBuffer();

        log_entry.append(timeStamp + ": Peer [" + id1 + "] received the 'not interested' message from [" + id2 + "].");

        try {
            logWriter.write(log_entry.toString());
            logWriter.newLine();
            logWriter.flush();
        } catch (Exception e) {
        }

    }

    public synchronized void log_receiving_request_message(int id1, int id2, int index) {

        String timeStamp = new SimpleDateFormat("y-M-d 'at' h:m:s a z").format(Calendar.getInstance().getTime());

        StringBuffer log_entry = new StringBuffer();

        log_entry.append(timeStamp + ": Peer [" + id1 + "] received the 'request' message from [" + id2 + "] for the piece " + index + " .");

        try {
            logWriter.write(log_entry.toString());
            logWriter.newLine();
            logWriter.flush();
        } catch (Exception e) {
        }

    }


    public synchronized void downloadPiece(int id1, int id2, int index, int number_of_pieces) {

        String timeStamp = new SimpleDateFormat("y-M-d 'at' h:m:s a z").format(Calendar.getInstance().getTime());

        StringBuffer log_entry = new StringBuffer();

        log_entry.append(timeStamp + ": Peer [" + id1 + "] has downloaded the piece " + index + " from [" + id2 + "]. " + "Now the number of pieces it has is : " + number_of_pieces + ".");

        try {
            logWriter.write(log_entry.toString());
            logWriter.newLine();
            logWriter.flush();
        } catch (Exception e) {
        }

    }


    public synchronized void completeDownload(int id) {

        String timeStamp = new SimpleDateFormat("y-M-d 'at' h:m:s a z").format(Calendar.getInstance().getTime());

        StringBuffer log_entry = new StringBuffer();

        log_entry.append(timeStamp + ": Peer [" + id + "] has downloaded the complete file.");

        try {
            logWriter.write(log_entry.toString());
            logWriter.newLine();
            logWriter.flush();
        } catch (Exception e) {
        }
    }

    public synchronized void processComplete() {

        String timeStamp = new SimpleDateFormat("y-M-d 'at' h:m:s a z").format(Calendar.getInstance().getTime());

        StringBuffer log_entry = new StringBuffer();

        log_entry.append(timeStamp + ": All peers have finished downloading. So stopping the service");

        try {
            logWriter.write(log_entry.toString());
            logWriter.newLine();
            logWriter.flush();
        } catch (Exception e) {
        }
    }

}
