import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private static final int PORT = 7777;
    public static void main(String[] args){
        InetAddress local;

        try{
            local = InetAddress.getLocalHost();
        }
        catch(UnknownHostException e){
            return;
        }

        Scanner scanner, in = null;
        try(Socket socket = new Socket(local, PORT)){
            System.out.println("Welcome player, these are your actions\n1: fight\t2: drink potion \t3: quit");


        }
        catch(IOException e){}
    }
    
}
