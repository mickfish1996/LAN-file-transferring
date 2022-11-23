import java.net.*;
import java.io.*;
import javax.swing.*;
import java.util.*;

public class Reciving {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private static int port = 6066;

    // public void startServer() throws IOException
    // {
    //    int port = Integer.parseInt(JOptionPane.showInputDialog("Input Your Port: "));
    //    String ip = JOptionPane.showInputDialog("Input Your IP Server: ");
    //    serverSock = new ServerSocket(6066);
    //    sock = serverSock.accept();
    //    DataOutputStream out = new DataOutputStream(sock.getOutputStream());
    //    out.writeUTF("I am fine, thank you");
 
    //    DataInputStream in = new DataInputStream(sock.getInputStream());
    //    System.out.println(in.readUTF());
    //    sock.close();
 
    // }
 
    // public void stop() throws IOException
    // {
    //    sock.close();
    // }
 
    public static void start() throws IOException
    {
       List<String> ipList = getNetworkDeviceIPs(port);
 
       System.out.println("\nTrying To Connect To Devices...");
       connectToDevices(ipList, port);
    }
 
    public static List<String> getNetworkDeviceIPs(int portNumber)
    {
          Socket socket = new Socket();
          List<String> ipList = new ArrayList<>(); // List<> Array to hold IP Addresses
 
          try {
             Process process = Runtime.getRuntime().exec("arp -a"); 
             process.waitFor();
             BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
 
             String ip = null;
             while ((ip = reader.readLine()) != null) {
                ip = ip.trim();     // Trim the data
                if (!ip.equals("")) { 
                      if (!ip.equals("")) {
                         // Remove all the unwanted spaces between data provided by 
                         // the ARP Table when it is generated.
                         while (ip.contains("  ")) { ip = ip.trim().replace("  ", " "); }
                         // Split each data line into a String Array for processing
                         String[] dataArray = ip.split(" ");
                         // For console output display only...
                         if (dataArray[0].toLowerCase().startsWith("interface:")) {
                            System.out.println("Locating Devices Connected To: " + dataArray[1]);
                         }
                         // If the data line contains the word "dynamic"
                         // then add the IP address on that line to the 
                         // List<> Array...
                         if (dataArray[2].equalsIgnoreCase("dynamic")) {
                            ipList.add(dataArray[0]);
                            // For console output display only...
                            System.out.println("Device Located On IP: " + dataArray[0]);
                         }
                      }
                }
             }
             // Close the Reader
             reader.close();
          } 
          catch (IOException | InterruptedException e) { 
             System.out.println("\nPROCESS/READER ERROR - " + e.getMessage()); 
          }
          return ipList;
    }
 
    public static void connectToDevices(List<String> localIPAddresses, int port) {
       // try to connect to the device(s)....
       // You'll need to play with this.
       File file = getFolder();
       for (int i = 0; i < localIPAddresses.size(); i++) {
          if (i > 0) { System.out.println(""); }
          try {
             System.out.println("Connecting to: " + localIPAddresses.get(i) + " on port: " + port + " - Please Wait...");
             reciveFile(localIPAddresses.get(i), file);
 
             //System.out.println("Just connected to: " + thisSystem.getRemoteSocketAddress());
 
             // Reciving rec = new Reciving();
             // rec.start("192.168.1.25", 6060);
             break;
          }
          catch(IOException e) { System.out.println(e.getLocalizedMessage()); }
       }
    }

   public static void reciveFile(String ip, File file) throws IOException
   {
      Socket socket = new Socket(ip, port);
      BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
      FileOutputStream fos = new FileOutputStream(file);
      int n;
      byte[] buffer = new byte[70022386];
      JOptionPane.showMessageDialog(null, "Connected");

      while ((n = bis.read(buffer)) >-1)
      {
         fos.write(buffer, 0, n);
         if(n<1024)
         {
            fos.close();
            bis.close();
            break;
         }
         fos.flush();
      }
      socket.close();
   }

   public static File getFolder()
   {
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setCurrentDirectory(new File("user.home"));
      fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

      int result = fileChooser.showOpenDialog(null);
      File file = new File("");
      if(result == JFileChooser.APPROVE_OPTION)
      {
         file = fileChooser.getSelectedFile();
      }

      return file;
   }
}
