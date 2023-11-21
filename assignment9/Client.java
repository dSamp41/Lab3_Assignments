import java.io.IOException;
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
            CharBuffer view = buffer.asCharBuffer();
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
    
}
