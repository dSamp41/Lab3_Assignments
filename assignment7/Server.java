import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//game in locale
public class Server{
    private static final int PORT = 7777;

    public static void main(String[] args){
        try(ServerSocket server = new ServerSocket(PORT)){
            System.out.println("Welcome to Dungeon Adventures!");
            ExecutorService pool = Executors.newCachedThreadPool();

            while(true){
                pool.execute(new Game(server.accept()));
            }
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
}