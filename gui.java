import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.*;

public class gui{

   private static JFrame frame = new JFrame("File Transfering");
   private static Sending sending;
   private static Reciving receiving;

   /*******************************************************************
    * 
    *******************************************************************/
   public static void createWindow(Sending send, Reciving receive)
   {          
      sending = send;
      receiving = receive;
      frame.add(addButtons());
  
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      frame.setLocationRelativeTo(null);
      frame.pack();
      frame.setVisible(true);

   }

   /*******************************************************************
    * 
    *******************************************************************/
   private static JPanel addButtons()
   {
      JPanel panel = new JPanel();

      JButton sending = new JButton("Sending");
      JButton receiving = new JButton("Receiving");

      sending.setAlignmentY(Component.CENTER_ALIGNMENT);
      sending.setAlignmentX(Component.CENTER_ALIGNMENT);
      receiving.setAlignmentY(Component.CENTER_ALIGNMENT);

      sending.setActionCommand("sending");
      receiving.setActionCommand("receiving");

      sending.addActionListener(new ButtonListener());
      receiving.addActionListener(new ButtonListener());

      panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
      panel.add(sending);
      panel.add(receiving);
      panel.setPreferredSize(new Dimension(400, 200));
      panel.setAlignmentX(Component.CENTER_ALIGNMENT);
      panel.setOpaque(true);
      panel.setBackground(Color.WHITE);
   

      return panel;
   }

   /*******************************************************************
    * 
    *******************************************************************/
   public static void sendFrame(String message)
   {
      frame.setVisible(false);
      frame = new JFrame("File Transfering");
      frame.add(displayMessage(message));
      
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      frame.setLocationRelativeTo(null);

      frame.pack();
      frame.setVisible(true);


   }  

   /*******************************************************************
    * 
    *******************************************************************/
   private static JPanel displayMessage(String message)
   {
      JPanel panel = new JPanel();

      JLabel label = new JLabel(message);

      label.setAlignmentY(Component.CENTER_ALIGNMENT);
      label.setAlignmentX(Component.CENTER_ALIGNMENT);
      panel.add(label);

      panel.setPreferredSize(new Dimension(400, 200));
      panel.setAlignmentY(Component.CENTER_ALIGNMENT);
      panel.setOpaque(true);
      panel.setBackground(Color.WHITE);
      panel.isForegroundSet();

      return panel;
   }

   /*******************************************************************
    * 
    *******************************************************************/
   public static String getIP()
   {
      String ip = "";

      try
      {
         ip = InetAddress.getLocalHost().getHostAddress();
      } catch(UnknownHostException ex) {
         ex.printStackTrace();
      }

      return ip;
   }

   /*******************************************************************
    * 
    *******************************************************************/
   public static void resetGui()
   {
      frame.setVisible(false);
      frame = new JFrame("File Transfering");

      createWindow(sending, receiving);
   }

   /*******************************************************************
    * 
    *******************************************************************/
   public static void connecting()
   {
      frame.setVisible(false);
      frame = new JFrame("File Transfering");
      frame.add(displayMessage("Connecting to server..."));
      
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      frame.setLocationRelativeTo(null);

      frame.pack();
      frame.setVisible(true);

   }
   /*******************************************************************
    * 
    *******************************************************************/
   public static JPanel addAgain(JPanel panel)
   {

      JLabel label = new JLabel("Would you like to try again?");
      JButton yes = new JButton("YES");
      JButton no = new JButton("NO");

      label.setAlignmentY(Component.CENTER_ALIGNMENT);
      yes.setAlignmentY(Component.CENTER_ALIGNMENT);
      yes.setAlignmentY(Component.CENTER_ALIGNMENT);

      yes.setActionCommand("yes");
      no.setActionCommand("no");

      yes.addActionListener(new ButtonListener());
      no.addActionListener(new ButtonListener());

      panel.add(label);
      panel.add(yes);
      panel.add(no);
      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS ));

      return panel;
   }


   /*******************************************************************
    * 
    *******************************************************************/
    public static void failConnect()
    {
      frame.setVisible(false);
      frame = new JFrame("File Transfering");

      JPanel panel = displayMessage("Failed to connect to server!");

      panel = addAgain(panel);


      frame.add(panel);
      
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      frame.setLocationRelativeTo(null);

      frame.pack();
      frame.setVisible(true);
    }

   /*******************************************************************
    * 
    *******************************************************************/
   public static void close()
   {
      frame.dispose();
   }

   /*******************************************************************
    * 
    *******************************************************************/
    public static void connected()
    {
      frame.setVisible(false);
      frame = new JFrame("File Transfering");

      JPanel panel = displayMessage("Transer Successful!");

      panel = addAgain(panel);


      frame.add(panel);
      
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      frame.setLocationRelativeTo(null);

      frame.pack();
      frame.setVisible(true);
    }

    public static Sending getSending()
    {
      return sending;
    }
}


