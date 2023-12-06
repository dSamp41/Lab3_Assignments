import java.io.IOException;
import java.net.DatagramSocket;

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
            
        } 
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
        
    }
}
