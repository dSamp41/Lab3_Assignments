import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

//single-threaded version
public class Weblog {
    public static void main(String[] args){
        if(args.length < 1){
            System.err.println("Inserire nome del file");
            return;
        }

        File log = new File(args[0]);
        
        try(BufferedReader reader = new BufferedReader(new FileReader(log))){
            String line = reader.readLine();

            while(line != null){
                translateLine(line);
                line = reader.readLine();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    private static void translateLine(String s){
        String[] strArr = s.split(" ");
        /*  string -> address; 
            address -> name
        */
        String addrStr = strArr[0];

        try{
            InetAddress address = InetAddress.getByName(addrStr);
            strArr[0] = address.getHostName();

            System.out.println(String.join(" ", strArr));
        }
        catch(UnknownHostException e){
            System.out.println("Indirizzo non valido");
        }
    }
} 