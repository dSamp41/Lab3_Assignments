import java.io.IOException;
import java.net.DatagramSocket;

public class PingClient {
    public static void main(String[] args) {
        String NAME = "";
        int PORT = 0;

        //args sanity checker
        if(args.length != 2){
            System.err.println("Invalid number of args");
            return;
        }
        else{
            int argCnt = 1;
            try {
                NAME = args[0];
                
                argCnt++;
                PORT = Integer.parseInt(args[1]);
            } 
            catch (Exception e) {
                System.err.println("ERR - arg " + argCnt);
            }
        }

        //...
        try(DatagramSocket socket = new DatagramSocket(0)) {
            
        } 
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
        
    }
    
}
