import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server{
    private static final int PORT = 7777;
    public static void main(String[] args) {
        System.out.println("Listening for connections on port " + PORT);
        
        ServerSocketChannel serverChannel;
        Selector selector;

        try{
            serverChannel = ServerSocketChannel.open();
            ServerSocket serverSocket = serverChannel.socket();
            InetSocketAddress address = new InetSocketAddress(PORT);
            serverSocket.bind(address);

            serverChannel.configureBlocking(false);
            selector = Selector.open();
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        }
        catch(IOException e){
            System.err.println(e.getMessage());
            return;
        }

        while(true){
            try{
                selector.select();
            }
            catch(IOException e){
                System.err.println(e.getMessage());
                return;
            }

            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();

            while(iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();

                try{
                    if(key.isAcceptable()){
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        System.out.println("Accepted connection from " + client);
                        client.configureBlocking(false);

                        //...
                    }
                }
                catch(IOException e){
                    key.cancel();
                    try{key.channel().close();}
                    catch(IOException e1){System.err.println(e1.getMessage());}
                    System.err.println(e.getMessage());
                    return;
                }
            }
        }
    }
}