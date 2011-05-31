import java.io.*;

public class World implements Serializable {
  transient MT mt;
  public PerlinNoise height;
  public PerlinNoise temp;
  public PerlinNoise wet;
  public TextImage village;
  public TextImage[][] buffer;
  public int buffer_x;
  public int buffer_y;
  transient Initiation root;
  int village_x;
  int village_y;
  public static void main(String[] args) {
    //new World();
  }
  World(Initiation root) {
    this.root=root;
    
    mt=new MT();
    height=new PerlinNoise(10,.4,mt.random());
    temp=new PerlinNoise(10,.4,mt.random());
    wet=new PerlinNoise(10,.4,mt.random());
    //find suitable spot for city (sample points across city area, between 50% and 75% land
    for(int i=0;;i++) {
      //area is (i*0.01,0) to (i*0.01+0.01,0.01)
      int land_tiles=0;
      for(int j=0;j<10;j++) {
        for(int k=0;k<10;k++) {
          if(height.perlin_noise((i+((j+0.0)/10.0))*0.01,((k+0.0)/10.0)*0.01)>=0) {
            land_tiles++;
          }
        }
      }
      if(land_tiles>=60&&land_tiles<=80) {
        village_x=i;
        village_y=0;
        break;
      }
    }
    village=getChunkImage(village_x,village_y);
  }
  public void load(Initiation root) {
    this.root=root;
    mt=new MT();
  }
  public TextImage getChunkImage(int x,int y) {
    TextImage chunk=new TextImage(100,100);
    for(int i=0;i<100;i++) {
      for(int j=0;j<100;j++) {
        if(height.perlin_noise((x+(i/100.0))*0.01,(y+(j/100.0))*0.01)>=0) {
          chunk.setPoint(i,j,'.');
        } else {
          chunk.setPoint(i,j,'~');
        }
      }
    }
    return chunk;
  }
  public void updateBuffer(int x,int y) {
    TextImage[][] newbuffer=new TextImage[3][3];
    if(!(buffer_x==x&&buffer_y==y)) {
      for(int i=-1;i<2;i++) {
        for(int j=-1;j<2;j++) {
          if(getBufferImage(x+i,y+j)!=null) {
            newbuffer[i+1][j+1]=getBufferImage(x+i,y+j);
          } else {
            newbuffer[i+1][j+1]=getChunkImage(x+i,y+j);
          }
        }
      }
      buffer_x=x;
      buffer_y=y;
      buffer=newbuffer;
    }
  }
  public TextImage getBufferImage(int x,int y) {
    /*if(Math.abs(x-buffer_x)>1||Math.abs(y-buffer_y)>1) {
      if(buffer_x<x&&Math.abs(x-buffer_x)>1) {
        buffer_x=x-1;
      } else if(buffer_x>x&&Math.abs(x-buffer_x)>1) {
        buffer_x=x+1;
      }
      if(buffer_y<y&&Math.abs(y-buffer_y)>1) {
        buffer_y=y-1;
      } else if(buffer_y>y&&Math.abs(y-buffer_y)>1) {
        buffer_y=y+1;
      }
      updateBuffer(buffer_x,buffer_y);
      return getBufferImage(x,y);
    } else {*/
    if(Math.abs(x-buffer_x)>1||Math.abs(y-buffer_y)>1) {
      return null;
    } else {
      return buffer[x-buffer_x+1][y-buffer_y+1];
    }
    //}
  }
  /*
   * Broken code, does not work (or probably doesn't)
  boolean isLake(int x,int y,double scale,double max) {
    double cur=height.perlin_noise(x*scale,y*scale);
    if(max==0.0)
      max=cur;
    if(cur>0.0) {
      boolean r=true;
      if(height.perlin_noise(x*scale-scale,y*scale)<cur)
        r=r&&isLake(x-1,y,scale,max);
      if(height.perlin_noise(x*scale+scale,y*scale)<cur)
        r=r&&isLake(x+1,y,scale,max);
      if(height.perlin_noise(x*scale,y*scale-scale)<cur)
        r=r&&isLake(x,y-1,scale,max);
      if(height.perlin_noise(x*scale,y*scale+scale)<cur)
        r=r&&isLake(x,y+1,scale,max);
      return r;
    } else {
      return false;
    }
  }
  */
}