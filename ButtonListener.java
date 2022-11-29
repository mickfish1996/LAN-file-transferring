import java.io.*;
import java.net.*;
import java.awt.event.*;

public class ButtonListener implements ActionListener
{
   Sending send = new Sending();
   Reciving receive = new Reciving();
   gui gi = new gui();

   public void actionPerformed(ActionEvent e) 
   {
      String command = e.getActionCommand();
      
      if (command.equals("sending"))
      {
         try
         {
            gi.sendFrame(gi.getIP());
            gi.getSending().startServer();
            gi.resetGui();

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
      } else if(command.equals("no")) {
         gi.close();
         System.exit(0);
      } else if (command.equals("yes")){
         gi.resetGui();

      }
   }
}
