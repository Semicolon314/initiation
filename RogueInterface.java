import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class RogueInterface extends JFrame implements ActionListener {
  public TextImage img;
  public static int WIDTH=50;
  public static int HEIGHT=50;
  
  //Elements
  public JLabel[] labels;
  public RogueInterface() {   
    //JFrame
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Initiation - Explore");
    this.setLayout(new FlowLayout());
    
    //TextImage
    img=new TextImage(WIDTH,HEIGHT);
    
    //Elements
    JPanel panel1=new JPanel();
    panel1.setLayout(new GridLayout(HEIGHT,WIDTH,5,0));
    labels=new JLabel[WIDTH*HEIGHT];
    for(int i=0;i<WIDTH*HEIGHT;i++) {
      labels[i]=new JLabel("#");
      panel1.add(labels[i]);
    }
    this.add(panel1);
    
    update();
    
    //Pack and Set Visible
    this.pack();
    this.setVisible(false);
  }
  
  public void update() {
    for(int i=0;i<WIDTH*HEIGHT;i++) {
      labels[i].setText(""+img.getPoint(i%WIDTH,(i-i%WIDTH)/WIDTH));
    }
  }
  
  public void actionPerformed(ActionEvent e) {
    
  }
}