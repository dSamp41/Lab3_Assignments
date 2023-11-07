import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

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

        Scanner scanner, in = null;
        try(Socket socket = new Socket(local, PORT)){
            System.out.println("Welcome player, these are your actions\n1: fight\t2: drink potion \t3: quit");

            scanner = new Scanner(System.in);
            in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            while(true){
                String line = scanner.nextLine();
                out.println(line);

                if(line.contentEquals("1")){
                    System.out.println("Fighting...");
                }

                if(line.contentEquals("2")){
                    System.out.println("Drinking...");
                }
                
                if(line.contentEquals("3")){
                    System.out.println("Quitting...");
                    socket.close();
                    break;
                }

                System.out.println(in.nextLine());

            }


        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
    
}
