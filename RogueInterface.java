import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class RogueInterface extends JFrame implements ActionListener {
  public RogueInterface() {   
    //JFrame
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Initiation");
    this.setLayout(new FlowLayout());
    
    
    //Pack and Set Visible
    //this.pack();
    this.setVisible(false);
  }
  
  public void actionPerformed(ActionEvent e) {
    
  }
}