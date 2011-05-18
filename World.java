import java.io.*;

public class World implements Serializable {
  MT mt;
  public PerlinNoise height;
  public PerlinNoise temp;
  public PerlinNoise wet;
  int village_x;
  int village_y;
  public static void main(String[] args) {
    new World();
  }
  World() {
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
    for(int i=0;i<100;i++) {
      for(int j=0;j<100;j++) {
        if(height.perlin_noise((village_x+(i/100.0))*0.01,(j/100.0)*0.01)>=0) {
          System.out.print("#");
        } else {
          System.out.print("~");
        }
      }
      System.out.println();
    }
  }
}