import java.io.*;

public class World implements Serializable {
  transient MT mt;
  public PerlinNoise height;
  public PerlinNoise temp;
  public PerlinNoise wet;
  public TextImage village;
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
    village=new TextImage(100,100);
    for(int i=0;i<100;i++) {
      for(int j=0;j<100;j++) {
        if(height.perlin_noise((village_x+(i/100.0))*0.01,(j/100.0)*0.01)>=0) {
          if(mt.random()%2==0) {
            village.setPoint(i,j,'.');
          } else {
            village.setPoint(i,j,',');
          }
        } else {
          village.setPoint(i,j,'~');
        }
      }
    }
  }
  public void load(Initiation root) {
    this.root=root;
    mt=new MT();
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