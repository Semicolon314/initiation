public class TextImage {
  private char[][] image;
  TextImage(int width,int height) {
    image=new char[width][height];
  }
  public void setPoint(int x,int y,char c) {
    image[x][y]=c;
  }
  public char getPoint(int x,int y) {
    return image[x][y];
  }
  public int getWidth() {
    return image.length;
  }
  public int getHeight() {
    return image[0].length;
  }
  public void drawString(int x,int y,String s) {
    if(y>=0&&y<getHeight()) {
      char[] ss=s.toCharArray();
      for(int i=0;i<ss.length;i++) {
        if(x+i<getWidth()&&x+i>=0) {
          setPoint(x+i,y,ss[i]);
        }
      }
    }
  }
  public void drawImage(int x,int y,TextImage t) {
    for(int i=0;i<t.getWidth();i++) {
      for(int j=0;j<t.getHeight();j++) {
        if(x+i>=0&&x+i<getWidth()&&y+j>=0&&y+j<getHeight()) {
          setPoint(x+i,y+j,t.getPoint(i,j));
        }
      }
    }
  }
}