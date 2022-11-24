import javax.swing.*;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class gui{

   private static JFrame frame = new JFrame("File Transfering");
   private static boolean selection;

   public static void createWindow()
   {          
      frame.add(addButtons());
  
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      frame.setLocationRelativeTo(null);
      frame.pack();
      frame.setVisible(true);

   }

   private static JPanel addButtons()
   {
      JPanel panel = new JPanel();

      JButton sending = new JButton("Sending");
      JButton receiving = new JButton("Receiving");

      sending.setAlignmentY(Component.CENTER_ALIGNMENT);
      receiving.setAlignmentY(Component.CENTER_ALIGNMENT);

      sending.setActionCommand("sending");
      receiving.setActionCommand("receiving");

      sending.addActionListener(new ButtonListener());
      receiving.addActionListener(new ButtonListener());

      panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
      panel.add(sending);
      panel.add(receiving);
      panel.setBorder(BorderFactory.createEmptyBorder(25, 50, 25, 50));
      panel.setOpaque(true);
      panel.setBackground(Color.WHITE);
   

      return panel;
   }


   
}


