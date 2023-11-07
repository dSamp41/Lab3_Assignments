import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Ass: game in locale

public class Server{
    private static final int PORT = 7777;
    private static final int numThreads = 10;

    public static void main(String[] args){
        try(ServerSocket server = new ServerSocket(PORT)){
            System.out.println("Welcome to Dungeon Adventures!");
            ExecutorService pool = Executors.newFixedThreadPool(numThreads);

            while(true){
                pool.execute(new Player(server.accept()));
            }
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    //Simulazione game
    private static class Player implements Runnable{
        Socket socket;

        public Player(Socket s){
            this.socket = s;
        }
        public void run(){
            System.out.println("New player: " + socket);

            try(Scanner in = new Scanner(socket.getInputStream());
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)){
                while(in.hasNextLine()){
                    out.println(translateCommand(in.nextLine()));
                }
            }
            catch(IOException e){
                System.err.println(e.getMessage());
            }
        }

        private String translateCommand(String s){
            switch (s) {
                case "1":
                    return "Fight";
                case "2":
                    return "Drink";
                case "3":
                    return "Quit";    
                default:
                    return "Invalid";
            }
        }
    }
}