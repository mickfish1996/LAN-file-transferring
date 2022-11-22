import java.util.*;
import java.net.*;
import java.io.*;

public class FileTransfering
{ 
    /*******************************************************
     * getInput
     * 
     *******************************************************/
    private static String getInput()
    {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static void main(String[] args)
    {
        Sending send = new Sending();
        Reciving recive = new Reciving();
        System.out.println("Sending or Reciving file? ");


        
        String input = getInput();

        if(input.equals("Sending"))
        {
            try{
                System.out.println("IP: " + InetAddress.getLocalHost().getHostAddress());

            } catch(UnknownHostException ex) {
                ex.printStackTrace();
            }
            try{
                send.start();
            }
            catch(IOException ex) {
                System.out.println("Error");
            }
        }

        else if(input.equals("Reciving"))
        {
            try{
                System.out.println("IP: " + InetAddress.getLocalHost().getHostAddress());

            } catch(UnknownHostException ex) {
                ex.printStackTrace();
            }

            try{
                recive.start();
            }
            catch(IOException ex) {
                System.out.println("Error");
            }
        }
    }
}