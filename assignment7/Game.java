//Simulazione game

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Game implements Runnable{
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