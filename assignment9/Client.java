import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    private static final int PORT = 7777;
    private static final int buffSize = 10;

    public static void main(String[] args) {
        try{
            SocketAddress address = new InetSocketAddress(PORT);
            SocketChannel client = SocketChannel.open(address);

            ByteBuffer buffer = ByteBuffer.allocate(buffSize);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while(true){
                System.out.print("Insert something: ");
                String input = reader.readLine();

                if(input.equals("exit")){
                    break;
                }

                //send input to server
                buffer = ByteBuffer.wrap(input.getBytes());


                // Receive and display the response from the server
                ByteBuffer responseBuffer = ByteBuffer.allocate(1024);
                int bytesRead = client.read(responseBuffer);
                responseBuffer.flip();

                byte[] responseData = new byte[bytesRead];
                responseBuffer.get(responseData);
                
                String response = new String(responseData);
                System.out.println("Received from server: " + response);
            }



        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
    
}
