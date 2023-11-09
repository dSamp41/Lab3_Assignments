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
                
                /*out.print("Welcome player, these are your actions\n1: fight\t2: drink potion \t3: quit\n");*/
                out.println("Game:\t\t" + game.toString());

                while(in.hasNextLine()){
                    if(game.getState() == "WIN"){
                        out.println("YOU WON\tWanna play again? [y/n]");
                    }

                    if(game.getState() == "LOST"){
                        out.println("YOU LOST");
                        break;
                    }

                    //command processing 
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
                            out.println("Quitting...");
                            clientSocket.close();
                            break;

                        case "y":
                            game.newGame();
                            out.println("New game");
                            break;

                        case "n":
                            out.println("See you again!");
                            clientSocket.close();
                            break;

                        default:
                            out.println("Invalid");
                            break;
                    }
                }
            }
            catch(IOException e){
                System.err.println(e.getMessage());
            }
        }
    }
}