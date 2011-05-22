import java.util.*;
import java.io.*;

public class SaveFile implements Serializable {
  Village village;
  World world;
  SaveFile(Initiation root) {    
    //make the village
    village=new Village(root);
    //make the world
    world=new World(root);
  }
}