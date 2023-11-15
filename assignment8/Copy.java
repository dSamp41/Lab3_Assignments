import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class Copy{
    public static void main(String[] args) {
        if(args.length < 1){
            System.err.println("Inserire filepath");
            return;
        }

        String inpName = args[0];
        String[] parts = inpName.split("\\.");
        String extension = parts[parts.length - 1];

        String outD = "outD." + extension;
        String outI = "outI." + extension;
        String outT = "outT." + extension;
        String outB = "outB." + extension;
        String outE = "outE." + extension;

        int bufSize = 1024;

        directBufferCopy(inpName, outD, bufSize);
        indirectBufferCopy(inpName, outI, bufSize);
        transferCopy(inpName, outT, bufSize);
        bufferedStreamCopy(inpName, outB, bufSize);
        explicitByteArray(inpName, outE, bufSize);
    }

    private static void directBufferCopy(String inpName, String outName, int bufSize){

        try(ReadableByteChannel src = Channels.newChannel(new FileInputStream(inpName));
            WritableByteChannel dest = Channels.newChannel(new FileOutputStream(outName));
        ){
            long start = System.currentTimeMillis();
            ByteBuffer buffer = ByteBuffer.allocateDirect(bufSize);

            while(src.read(buffer) != -1){
                buffer.flip();
                dest.write(buffer);
                buffer.compact();
            }
            buffer.flip();
            while(buffer.hasRemaining()){dest.write(buffer);}
            
            long end = System.currentTimeMillis();
            long delta = end - start;
            System.out.println("Direct Buffer: " + delta + " ms");
        }
        catch(IOException e){
            System.err.println("Error during direct buffer copy");
        }
    }

    private static void indirectBufferCopy(String inpName, String outName, int bufSize){
        try(ReadableByteChannel src = Channels.newChannel(new FileInputStream(inpName));
            WritableByteChannel dest = Channels.newChannel(new FileOutputStream(outName));
        ){
            long start = System.currentTimeMillis();
            ByteBuffer buffer = ByteBuffer.allocate(bufSize);

            while(src.read(buffer) != -1){
                buffer.flip();
                dest.write(buffer);
                buffer.compact();
            }
            buffer.flip();
            while(buffer.hasRemaining()){dest.write(buffer);}
            
            long end = System.currentTimeMillis();
            long delta = end - start;

            System.out.println("Indirect Buffer: " + delta + " ms");
        }
        catch(IOException e){
            System.err.println("Error during indirect buffer copy");
        }
    }

    private static void transferCopy(String inpName, String outName, int bufSize){
        try(FileInputStream fromFile = new FileInputStream(inpName);
            FileOutputStream toFile = new FileOutputStream(outName);
        ){
            long start = System.currentTimeMillis();

            FileChannel fromChannel = fromFile.getChannel();
            FileChannel toChannel = toFile.getChannel();
            
            long position = 0;
            long count = fromChannel.size();
            toChannel.transferFrom(fromChannel, position, count);

            long end = System.currentTimeMillis();
            long delta = end - start;
            System.out.println("Transfer: " + delta + " ms");
        } 
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void bufferedStreamCopy(String inpName, String outName, int bufSize){
        try(BufferedInputStream inp = new BufferedInputStream(new FileInputStream(inpName));
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outName));
        ){
            long start = System.currentTimeMillis();

            byte[] buffer = new byte[bufSize];
            int bytesRead;

            while((bytesRead = inp.read(buffer)) != -1){
                out.write(buffer, 0, bytesRead);
            }

            long end = System.currentTimeMillis();
            long delta = end - start;
            System.out.println("Buffered Stream: " + delta + " ms");
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    private static void explicitByteArray(String inpName, String outName, int bufSize){
        try(FileInputStream inp = new FileInputStream(inpName);
            FileOutputStream out = new FileOutputStream(outName)
        ){
            long start = System.currentTimeMillis();

            ByteArrayOutputStream baOut = new ByteArrayOutputStream();
            byte[] buffer = new byte[bufSize];
            int bytesRead;

            while((bytesRead = inp.read(buffer)) != -1){
                baOut.write(buffer, 0, bytesRead);
            }

            out.write(baOut.toByteArray());
            baOut.close();

            long end = System.currentTimeMillis();
            long delta = end - start;
            System.out.println("Byte Array: " + delta + " ms");
        
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
}