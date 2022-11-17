import java.util.*;
import java.io.*;
import java.net.InetAddress;

public class FileTransfering
{
    public static void main(String[] args)
    {

        Server server = new Server();
        Client client = new Client();

        try
        {
            server.startServer();
        }
        catch(IOException ex)
        {
            System.out.println("Exeption Thrown!");
        }

        try
        {
            client.start();
        }
        catch(IOException ex)
        {
            System.out.println("Exception Thrown!");
        }
    }
}