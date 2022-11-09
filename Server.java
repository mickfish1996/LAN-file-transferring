import java.net.*;
import java.io.*;

class Server{
   private ServerSocket serverSocket;
   private Socket clientSocket;
   private PrintWriter out;
   private BufferedReader in;

   public void startConnection(String ip, int port) throws IOException
   {
      serverSocket = new ServerSocket(port);
      clientSocket = serverSocket.accept();
      out = new PrintWriter(clientSocket.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
   }
}