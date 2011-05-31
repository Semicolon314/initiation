import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class RogueInterface extends JFrame implements ActionListener, KeyListener {
  public TextImage img;
  public static int WIDTH=50;
  public static int HEIGHT=20;
  public int player_chunk_x;
  public int player_chunk_y;
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
    panel1.setLayout(new GridLayout(HEIGHT,1,0,0));
    labels=new JLabel[HEIGHT];
    String fillerrow="";
    for(int i=0;i<WIDTH;i++) {
      fillerrow+="#";
    }
    for(int i=0;i<HEIGHT;i++) {
      labels[i]=new JLabel(fillerrow);
      labels[i].setFont(new Font(Font.MONOSPACED,Font.PLAIN,12));
      panel1.add(labels[i]);
    }
    this.add(panel1);
    this.addKeyListener(this);
    
    //Pack and Set Visible
    this.pack();
    this.setVisible(false);
    
    //Update after pack so that the frame is the right size
    update();
    
    //Player x and y
    player_x=WIDTH/2;
    player_y=HEIGHT/2;
  }
  
  public void update() {
    for(int i=0;i<HEIGHT;i++) {
      String t="";
      for(int j=0;j<WIDTH;j++) {
        t+=img.getPoint(j,i);
      }
      labels[i].setText(t);
    }
  }
  
  public void actionPerformed(ActionEvent e) {
    
  }
  
  public void keyTyped(KeyEvent e) {
    
  }
  public void keyPressed(KeyEvent e) {
    if(this.isVisible()) { //Roguelike is active?
      if(player_chunk_x==0) {
        player_chunk_x=root.s.world.village_x;
        player_chunk_y=root.s.world.village_y;
        root.s.world.updateBuffer(player_chunk_x,player_chunk_y);
      }
      //handle key
      int key=e.getKeyCode();
      if(key==37) { //LEFT
        player_x--;
        if(player_x<0) {
          player_x=99;
          player_chunk_x--;
          root.s.world.updateBuffer(player_chunk_x,player_chunk_y);
        }
      }
      if(key==38) { //UP
        player_y--;
        if(player_y<0) {
          player_y=99;
          player_chunk_y--;
          root.s.world.updateBuffer(player_chunk_x,player_chunk_y);
        }
      }
      if(key==39) { //RIGHT
        player_x++;
        if(player_x>99) {
          player_x=0;
          player_chunk_x++;
          root.s.world.updateBuffer(player_chunk_x,player_chunk_y);
        }
      }
      if(key==40) { //DOWN
        player_y++;
        if(player_y>99) {
          player_y=0;
          player_chunk_y++;
          root.s.world.updateBuffer(player_chunk_x,player_chunk_y);
        }
      }
      
      //display the map and player
      int disp_map_x=(WIDTH/2)-player_x;
      int disp_map_y=(HEIGHT/2)-player_y;
      int disp_player_x=(WIDTH/2);
      int disp_player_y=(HEIGHT/2);
      boolean[][] extra=new boolean[3][3];
      if(disp_map_x>0) {
        extra[0][0]=true;
        extra[1][0]=true;
        extra[2][0]=true;
      }
      if(disp_map_y>0) {
        extra[0][0]=true;
        extra[0][1]=true;
        extra[0][2]=true;
      }
      if(disp_map_x<WIDTH-100) {
        extra[0][2]=true;
        extra[1][2]=true;
        extra[2][2]=true;
      }
      if(disp_map_y<HEIGHT-100) {
        extra[2][0]=true;
        extra[2][1]=true;
        extra[2][2]=true;
      }
      img.clear();
      img.drawImage(disp_map_x,disp_map_y,root.s.world.getBufferImage(player_chunk_x,player_chunk_y));
      for(int i=-1;i<2;i++) {
        for(int j=-1;j<2;j++) {
          if(extra[i+1][j+1]) {
            img.drawImage(disp_map_x+j*100,disp_map_y+i*100,root.s.world.getBufferImage(player_chunk_x+j,player_chunk_y+i));
          }
        }
      }
      img.drawString(disp_player_x,disp_player_y,"@");
      update();
    }
  }
  public void keyReleased(KeyEvent e) {
    
  }
}