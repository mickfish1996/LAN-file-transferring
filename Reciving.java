import java.net.*;
import java.io.*;
import javax.swing.*;
import java.util.*;

public class Reciving {
    private static int port = 6066;
    private static boolean stopProcess = false;
    private static gui gi = new gui();

   /************************************************************************
    * 
    ************************************************************************/
    public void start() throws IOException
    {
       List<String> ipList = getNetworkDeviceIPs(port);

       gi.connecting();
 
       System.out.println("\nTrying To Connect To Devices...");
       connectToDevices(ipList, port);
    }
   /************************************************************************
    * 
    ************************************************************************/
    public List<String> getNetworkDeviceIPs(int portNumber)
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
   /************************************************************************
    * 
    ************************************************************************/
    public void connectToDevices(List<String> localIPAddresses, int port) 
    {
       // try to connect to the device(s)....
       // You'll need to play with this.
       File file = getFolder();
       for (int i = 0; i < localIPAddresses.size(); i++) {
         if (i > 0) { System.out.println(""); }
         try {
            System.out.println("Connecting to: " + localIPAddresses.get(i) + " on port: " + port + " - Please Wait...");
            reciveFile(localIPAddresses.get(i), file);

            if (stopProcess)
            {
               gi.connected();
               break;
            }
              
         }
         catch(IOException e) { 
            System.out.println(e.getLocalizedMessage());
            if (stopProcess)
            {
               gi.connected();
               break;
            }
               
         }
      }

      if (!stopProcess)
      gi.failConnect();

   }

   /************************************************************************
    * 
    ************************************************************************/
   public void reciveFile(String ip, File file) throws IOException
   {
      Socket socket = new Socket(ip, port);
      BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
      file.setWritable(true);
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
      stopProcess = true;
      socket.close();

   }

   /************************************************************************
    * 
    ************************************************************************/
   public File getFolder()
   {
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setCurrentDirectory(new File("user.home"));
   

      int result = fileChooser.showOpenDialog(null);
      File file = new File("");
      if(result == JFileChooser.APPROVE_OPTION)
      {
         file = fileChooser.getSelectedFile();
      }

      return file;
   }
}
