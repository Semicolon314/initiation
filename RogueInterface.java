import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class RogueInterface extends JFrame implements ActionListener, KeyListener {
  public TextImage img;
  public static int WIDTH=50;
  public static int HEIGHT=50;
  public int player_x;
  public int player_y;
  public int next_key;
  public Initiation root;
  
  //Elements
  public JLabel[] labels;
  public RogueInterface(Initiation root) {
    this.root=root;
    
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
    this.addKeyListener(this);
    
    update();
    
    //Pack and Set Visible
    this.pack();
    this.setVisible(false);
    
    //Player x and y
    player_x=WIDTH/2;
    player_y=HEIGHT/2;
  }
  
  public void update() {
    for(int i=0;i<WIDTH*HEIGHT;i++) {
      labels[i].setText(""+img.getPoint(i%WIDTH,(i-i%WIDTH)/WIDTH));
    }
  }
  
  public void actionPerformed(ActionEvent e) {
    
  }
  
  public void keyTyped(KeyEvent e) {
    
  }
  public void keyPressed(KeyEvent e) {
    if(this.isVisible()) { //Roguelike is active?
      //handle key
      int key=e.getKeyCode();
      if(key==37) { //LEFT
        player_x--;
        if(player_x<0) player_x=0;
      }
      if(key==38) { //UP
        player_y--;
        if(player_y<0) player_y=0;
      }
      if(key==39) { //RIGHT
        player_x++;
        if(player_x>99) player_x=99;
      }
      if(key==40) { //DOWN
        player_y++;
        if(player_y>99) player_y=99;
      }
      
      //display the map and player
      int disp_map_x=player_x-(WIDTH/2);
      int disp_map_y=player_y-(HEIGHT/2);
      int disp_player_x=(WIDTH/2);
      int disp_player_y=(HEIGHT/2);
      /*if(disp_map_x<(WIDTH/2)-100) {
        disp_player_x-=((WIDTH/2)-100)-disp_map_x;
        disp_map_x=(WIDTH/2)-100;
      }
      if(disp_map_y<0) {
        disp_map_y-=disp_map_y;
        disp_map_y=0;
      }*/
      img.clear();
      img.drawImage(disp_map_x,disp_map_y,root.s.world.village);
      img.drawString(disp_player_x,disp_player_y,"@");
      update();
    }
  }  
  public void keyReleased(KeyEvent e) {
    
  }
}