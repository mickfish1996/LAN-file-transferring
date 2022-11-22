import java.net.*;
import java.io.*;

import javax.swing.*;


import java.util.*;


public class Sending{
   private Socket sock;
   private ServerSocket serverSock;
   private static int port = 6066;

   public void startServer() throws IOException
   {
      //int fileName = Integer.parseInt(JOptionPane.showInputDialog("Enter file name: "));
      JFileChooser filechoose = new JFileChooser();
      filechoose.setCurrentDirectory(new File(System.getProperty("user.home")));
      int result = filechoose.showOpenDialog(null);

      File selected = new File("");

      if(result == JFileChooser.APPROVE_OPTION)
      {
         selected = filechoose.getSelectedFile();
      }

      serverSock = new ServerSocket(port);
      
      JOptionPane.showMessageDialog(null, "Server started");
      JOptionPane.showMessageDialog(null, "Waiting client");

      sock = serverSock.accept();

      

      BufferedOutputStream out = new BufferedOutputStream(sock.getOutputStream());
      FileInputStream fis = new FileInputStream(selected);
      BufferedInputStream bis = new BufferedInputStream(fis);
      int n = -1;

      byte[] buffer;

      buffer = new byte[70022386];

      while((n = bis.read(buffer)) > -1)
      {
         out.write(buffer, 0, n);
         out.flush();
      }
   }

   public void start() throws IOException
   {
      startServer();

   }
   
}