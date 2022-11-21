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
      int port = Integer.parseInt(JOptionPane.showInputDialog("Input Your Port: "));
      String ip = JOptionPane.showInputDialog("Input Your IP Server: ");
      serverSock = new ServerSocket(port);
      
      System.out.println("Server started");
      sock = serverSock.accept();

      DataOutputStream out = new DataOutputStream(sock.getOutputStream());
      out.writeUTF("I am fine, thank you");

      DataInputStream in = new DataInputStream(sock.getInputStream());
      System.out.println(in.readUTF());
      sock.close();

   }

   // public void stop() throws IOException
   // {
   //    sock.close();
   // }

   // public static void start()
   // {
   //    List<String> ipList = getNetworkDeviceIPs(6500);

   //    System.out.println("\nListing Device Names - Please Wait ...");

   //    for(int i = 0; i < ipList.size(); i++)
   //    {
   //       System.out.println(getDeviceName(ipList.get(i)));
   //    }

   //    // System.out.println("\nTrying To Connect To Devices...");
   //    // connectToDevices(ipList, port);
   // }

   // public static List<String> getNetworkDeviceIPs(int portNumber)
   // {
   //       Socket socket = new Socket();
   //       List<String> ipList = new ArrayList<>(); // List<> Array to hold IP Addresses

   //       try {
   //          Process process = Runtime.getRuntime().exec("arp -a"); 
   //          process.waitFor();
   //          BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

   //          String ip = null;
   //          while ((ip = reader.readLine()) != null) {
   //             ip = ip.trim();     // Trim the data
   //             if (!ip.equals("")) { 
   //                   if (!ip.equals("")) {
   //                      // Remove all the unwanted spaces between data provided by 
   //                      // the ARP Table when it is generated.
   //                      while (ip.contains("  ")) { ip = ip.trim().replace("  ", " "); }
   //                      // Split each data line into a String Array for processing
   //                      String[] dataArray = ip.split(" ");
   //                      // For console output display only...
   //                      if (dataArray[0].toLowerCase().startsWith("interface:")) {
   //                         System.out.println("Locating Devices Connected To: " + dataArray[1]);
   //                      }
   //                      // If the data line contains the word "dynamic"
   //                      // then add the IP address on that line to the 
   //                      // List<> Array...
   //                      if (dataArray[2].equalsIgnoreCase("dynamic")) {
   //                         ipList.add(dataArray[0]);
   //                         // For console output display only...
   //                         System.out.println("Device Located On IP: " + dataArray[0]);
   //                      }
   //                   }
   //             }
   //          }
   //          // Close the Reader
   //          reader.close();
   //       } 
   //       catch (IOException | InterruptedException e) { 
   //          System.out.println("\nPROCESS/READER ERROR - " + e.getMessage()); 
   //       }
   //       return ipList;
   // }

   // public static String getDeviceName(String localIP) {
   //    String result = "";
   //    try {
   //       InetAddress address = InetAddress.getByName(localIP);
   //       if (address.isReachable(500)) {
   //          // Device is turned on and can be pinged!;
   //          result = address.toString();
   //       }
   //       else if (!address.getHostAddress().equals(address.getHostName())) {
   //          // Device is identified in a DNS lookup!
   //          result = address.toString();
   //       }
   //       else {
   //          // if you keep getting something like "Unknown Device!/192.168.0.5 then the host
   //          // address and host name are the same, meaning the host name could not be resolved.
   //          // This means that either your router just isn't storing the information OR those 
   //          // devices just choose not to submit their host name to the router, and that is why
   //          // you will continually get this message. Apparently, there is no way around this 
   //          // because those device names literally aren't stored anywhere.
   //          result = "Unknown Device!/" + address.toString().substring(0,address.toString().indexOf("/"));
   //       }
   //    } 
   //    catch (UnknownHostException ex) { System.out.println(ex.getMessage()); } 
   //    catch (IOException ex) { System.out.println(ex.getMessage()); }

   //    return result;
   // }

   // public static void connectToDevices(List<String> localIPAddresses, int port) {
   //    // try to connect to the device(s)....
   //    // You'll need to play with this.
   //    for (int i = 0; i < localIPAddresses.size(); i++) {
   //       if (i > 0) { System.out.println(""); }
   //       try {
   //          System.out.println("Connecting to: " + localIPAddresses.get(i) + " on port: " + port + " - Please Wait...");
   //          Socket thisSystem = new Socket(localIPAddresses.get(i), port);

   //          System.out.println("Just connected to: " + thisSystem.getRemoteSocketAddress());
   //          OutputStream outToServer = thisSystem.getOutputStream();
   //          DataOutputStream out = new DataOutputStream(outToServer);

   //          out.writeUTF("Hello from: " + thisSystem.getLocalSocketAddress());
   //          InputStream inFromServer = thisSystem.getInputStream();
   //          DataInputStream in = new DataInputStream(inFromServer);

   //          System.out.println("Device says " + in.readUTF());
   //          thisSystem.close();
   //       }
   //       catch(IOException e) { System.out.println(e.getLocalizedMessage()); }
   //    }
   // }

   public void start() throws IOException
   {
      startServer();

   }
   
}
