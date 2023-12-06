import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Random;

public class PingServer {
    public static void main(String[] args) {
        int PORT = 0;
        long SEED = 0;

        //args sanity checker
        if(args.length != 2){
            System.err.println("Invalid number of args");
            return;
        }
        else{
            int argCnt = 1;
            try {
                PORT = Integer.parseInt(args[0]);

                argCnt++;
                SEED = Long.parseLong(args[1]);
            } 
            catch (Exception e) {
                System.err.println("ERR - arg " + argCnt);
            }
        }


        //...
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            Random rnd = new Random(SEED);
            int decisor;    //(decisor <= 500), if rnd%7 == 0 -> ignore packet; else delay
            String action;

            DatagramPacket request, response;
            System.out.println("PingServer is running...");

            while(true){
                decisor = rnd.nextInt(500);
                

                request = new DatagramPacket(new byte[1024], 1024);
                socket.receive(request);
                byte[] data = request.getData();
                String msg = data.toString();

                //delay
                if(decisor % 7 != 0){
                    Thread.sleep(decisor);
                    response = new DatagramPacket(data, data.length, request.getAddress(), request.getPort());
                    socket.send(response);

                    action = "delayed " + String.valueOf(decisor) + " ms";

                }
                //ignore
                else{
                    action = "not sent";
                }

                System.out.println(request.getAddress() + ":" + request.getPort() + ">" + msg + "ACTION: " + action);           
            }
            
        } 
        catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
}
