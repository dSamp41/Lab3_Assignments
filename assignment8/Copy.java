import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class Copy{
    public static void main(String[] args) {
        if(args.length < 1){
            System.err.println("Inserire filepath");
            return;
        }

        File f = new File(args[0]);
        String[] parts = f.getName().split("\\.");
        String outName = "out." + parts[parts.length - 1];
        
        try(ReadableByteChannel source = Channels.newChannel(new FileInputStream(f));
            WritableByteChannel dest = Channels.newChannel(new FileOutputStream(outName))
        ){
            directBufferCopy(source, dest);
            indirectBufferCopy(source, dest);
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    private static void directBufferCopy(ReadableByteChannel src, WritableByteChannel dest) throws IOException{
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        while(src.read(buffer) != -1){
            buffer.flip();
            dest.write(buffer);
            buffer.compact();
        }
        buffer.flip();
        while(buffer.hasRemaining()){dest.write(buffer);}
    }

    private static void indirectBufferCopy(ReadableByteChannel src, WritableByteChannel dest) throws IOException{
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while(src.read(buffer) != -1){
            buffer.flip();
            dest.write(buffer);
            buffer.compact();
        }
        buffer.flip();
        while(buffer.hasRemaining()){dest.write(buffer);}
    }

    //TODO
    private static void transferCopy(ReadableByteChannel src, WritableByteChannel dest) throws IOException{
        
    }

    //TODO
    private static void bufferedStreamCopy(ReadableByteChannel src, WritableByteChannel dest) throws IOException{
        
    }
}