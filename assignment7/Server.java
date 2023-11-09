import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
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

    //Simulazione game
    private static class Game implements Runnable{
        Socket clientSocket;
        GameInstance game = new GameInstance();

        public Game(Socket s){
            this.clientSocket = s;
        }

        public void run(){
            System.out.println("New player: " + clientSocket);
            
            try(Scanner in = new Scanner(clientSocket.getInputStream());
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)){
                
                out.println("Game:\t\t" + game.toString());

                while(in.hasNextLine()){
                    //command processing 
                    processCommand(in, out);
                }
            }
            catch(IOException e){
                System.err.println(e.getMessage());
            }
            finally{
                System.out.println("A player left: " + clientSocket);
            }
        }

        private void processCommand(Scanner in, PrintWriter out) {
            switch (in.nextLine()) {
                case "1":
                    game.fight();
                    out.println("Fighting:\t" + game.toString());
                    break;
                case "2":
                    game.usePotion();
                    out.println("Drinking:\t"  + game.toString());
                    break;
                case "3":
                    //out.println("Quitting...");
                    try {
                        clientSocket.close();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "y":
                    game.newGame();
                    out.println("New game:\t"  + game.toString());
                    break;

                case "n":
                    out.println("See you again!");
                    try {
                        clientSocket.close();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                default:
                    out.println("Invalid command");
                    break;
            }
        }
    }
}