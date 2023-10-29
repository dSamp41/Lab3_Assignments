import java.net.InetAddress;
import java.net.UnknownHostException;

public class Task implements Runnable{
    String line;

    public Task(String s){
        this.line = s;
    } 

    public void run(){
        translateLine(line);
    }

    private static void translateLine(String s){
        String[] strArr = s.split(" ");
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