import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private static final int PORT = 7777;
    //private static final String[] commands = {"1", "2", "3"};
    public static void main(String[] args){
        InetAddress local;

        try{
            local = InetAddress.getLocalHost();
        }
        catch(UnknownHostException e){
            return;
        }

        BufferedReader userInput, in = null;
        try(Socket socket = new Socket(local, PORT)){
            userInput = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            System.out.println("Welcome player, these are your actions\n1: fight\t2: drink potion \t3: quit");

            while(true){
                //System.out.println("####0");

                String serverResponse = in.readLine();
                if(serverResponse.contentEquals("See you again!")){
                    //socket.close();
                    break;
                }
                System.out.println("Server\t" + serverResponse);


                String userInputStr = userInput.readLine();
                out.println(userInputStr);  //send to server
               
            }
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
    
}
