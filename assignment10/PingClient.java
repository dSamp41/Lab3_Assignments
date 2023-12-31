import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collections;

public class PingClient {
    private static final int timeoutMillis = 2000;     //2 secondi
    public static void main(String[] args) {
        String HOSTNAME = "";
        int PORT = 0;

        //args sanity checker
        if(args.length != 2){
            System.err.println("Invalid number of args");
            return;
        }
        else{
            int argCnt = 1;
            try {
                HOSTNAME = args[0];
                
                argCnt++;
                PORT = Integer.parseInt(args[1]);
            } 
            catch (Exception e) {
                System.err.println("ERR - arg " + argCnt);
            }
        }

        //...
        try(DatagramSocket socket = new DatagramSocket(0)) {
            socket.setSoTimeout(timeoutMillis);

            InetAddress address = InetAddress.getByName(HOSTNAME);
            DatagramPacket request, response;
            
            ArrayList<Long> RTTs = new ArrayList<Long>();
            int numPackets = 10;

            for(int seqNo = 0; seqNo<numPackets; seqNo++){
                Long requestTimestamp = System.currentTimeMillis();
                String requestMsg = "PING " + String.valueOf(seqNo) + " " + String.valueOf(requestTimestamp);
                byte[] data = requestMsg.getBytes("US-ASCII");
                
                request = new DatagramPacket(data, data.length, address, PORT);
                socket.send(request);

                response = new DatagramPacket(new byte[data.length], data.length);
                try {
                    socket.receive(response);
                    //String responseMsg = new String(response.getData(), 0, response.getLength(), "US-ASCII");
                    long responseTimestamp = System.currentTimeMillis();
                    long delta = responseTimestamp - requestTimestamp;

                    System.out.printf("%s RTT: %d ms\n", requestMsg, delta);

                    RTTs.add(delta);
                } 
                catch (SocketTimeoutException e) {
                    System.out.printf("%s RTT: *\n", requestMsg);
                }
            }
            
            //output statistics
            System.out.println("\n---- PING Statistics ----");
            float pct = (float) (numPackets - RTTs.size()) / numPackets;
            System.out.printf("%d packets transmitted, %d packets received, %.2f%% packet loss\n", numPackets, RTTs.size(), pct);
            
            long min = !RTTs.isEmpty() ? Collections.min(RTTs) : 0;
            long max = !RTTs.isEmpty() ? Collections.max(RTTs) : 0;
            double avg = RTTs.stream()
                .mapToDouble(a -> a)
                .average()
                .orElse(0.0);
        
            System.out.printf("round-trip (ms) min/avg/max = %d/%.2f/%d\n", min, avg, max);
        } 
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
        
    }
    
}
