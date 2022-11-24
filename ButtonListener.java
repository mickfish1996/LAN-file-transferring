import java.io.*;
import java.net.*;
import java.awt.event.*;

public class ButtonListener implements ActionListener
{
   Sending send = new Sending();
   Reciving receive = new Reciving();

   public void actionPerformed(ActionEvent e) 
   {
      String command = e.getActionCommand();

      try
      {
         System.out.println("IP: " + InetAddress.getLocalHost().getHostAddress());
      } catch(UnknownHostException ex) {
         ex.printStackTrace();
      }
      
      if (command.equals("sending"))
      {
         try
         {
            send.startServer();
         } catch(IOException ex) {
            System.out.println("ERROR!!!");
         }            
      } else if(command.equals("receiving")){
         try
         {
            receive.start();
         } catch(IOException ex) {
            System.out.println("ERROR!!!");
         }
      }
   }
}
