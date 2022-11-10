import java.util.*;
import java.io.*;
import java.net.InetAddress;

public class FileTransfering
{
    public static void main(String[] args)
    {

        System.out.println("Its working");

        Server server = new Server();
        Client client = new Client();

        try{
            server.start(2222);
            client.startConnection(InetAddress.getLocalHost().getHostAddress(),2222);
        }
        catch(IOException ex)
        {
            System.out.println("exeption Thrown!");
        }
    }
}