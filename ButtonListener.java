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
      
      if (command.equals("sending"))
      {
         try
         {
            gui.sendFrame(gui.getIP());
            gui.getSending().startServer();
            gui.checkRepeat("File Transfered!");

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
         gui.close();
         System.exit(0);
      } else if (command.equals("yes")){
         gui.resetGui();

      }
   }
}
