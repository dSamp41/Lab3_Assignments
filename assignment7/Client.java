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

        try(Socket socket = new Socket(local, PORT);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ){
            
            System.out.println("Welcome player, these are your actions\n1: fight\t2: drink potion \t3: quit");

            while(true){
                //handle server response
                String serverResponse = in.readLine();
                
                if(serverResponse.contentEquals("See you again!")){
                    //socket.close();
                    break;
                }
                if(serverResponse.contains("Monster: hp=-") || serverResponse.contains("Monster: hp=0")){
                    System.out.println("YOU WON\tWanna play again?[y/n] ");
                    out.println(userInput.readLine());
                    continue;
                }
                if(serverResponse.contains("Player: hp=-") || serverResponse.contains("Player: hp=0")){
                    System.out.println("YOU LOST\tSee you next time");
                    break;
                }
                else{
                    System.out.println(serverResponse);
                }

                //handle client input
                System.out.print("Insert a command: ");
                String userInputStr = userInput.readLine();
                if(userInputStr.contentEquals("3") || userInputStr.contentEquals("n")){
                    //socket.close();
                    //System.out.println("Quitting...");
                    break;
                }
                out.println(userInputStr);  //send to server
               
            }
        }
        catch(IOException e){
            System.err.println(e.getMessage() + ": Server non trovato");
        }
        finally{
            System.out.println("Quitting...");
        }
    }
}
