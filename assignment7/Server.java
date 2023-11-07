import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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


    private static class Player implements Runnable{
        Socket socket;

        public Player(Socket s){
            this.socket = s;
        }
        public void run(){}
    }
}