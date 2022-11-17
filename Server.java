import java.net.*;
import java.io.*;
import javax.swing.*;

public class Server{
   // private ServerSocket serverSocket;
   // private Socket clientSocket;
   // private PrintWriter out;
   // private BufferedReader in;

   // public void start(int port) throws IOException
   // {
   //    serverSocket = new ServerSocket(port);
   //    clientSocket = serverSocket.accept();
   //    out = new PrintWriter(clientSocket.getOutputStream(), true);
   //    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

   //    String greeting = in.readLine();
      
   //    if ("hello server".equals(greeting))
   //    {
   //       out.println("hello client");
   //    }
   //    else {
   //       out.println("unrecognised greeting");
   //    }
   // }

   // public void stop() throws IOException
   // {
   //    in.close();
   //    out.close();
   //    clientSocket.close();
   //    serverSocket.close();
   // }

   public void startServer() throws IOException
   {
      int port = Integer.parseInt(JOptionPane.showInputDialog("Input Your Port: "));
      String ip = JOptionPane.showInputDialog("Input Your IP Server: ");
      ServerSocket serverSock = new ServerSocket(6066);
      Socket sock = serverSock.accept();
      DataOutputStream out = new DataOutputStream(sock.getOutputStream());
      out.writeUTF("I am fine, thank you");

      DataInputStream in = new DataInputStream(sock.getInputStream());
      System.out.println(in.readUTF());
      sock.close();

   }

   
}
