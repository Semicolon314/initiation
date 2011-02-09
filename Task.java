import java.io.*;

public class Task implements Serializable {
  String task;
  
  Task(String taskname) {
    taskname=taskname.toLowerCase();
    if(isValidTask(taskname)) {
      task=taskname;
    } else {
      task="nothing";
    }
  }
  public String getDisplayName() {
    if(task.equals("nothing")) {
      return "Nothing";
    }
    if(task.equals("farm")) {
      return "Farming";
    }
    if(task.equals("build")) {
      return "Building";
    }
    if(task.equals("rest")) {
      return "Resting";
    }
    if(task.equals("mine")) {
      return "Mining";
    }
    if(task.equals("trainint")) {
      return "Training Intelligence";
    }
    if(task.equals("trainstr")) {
      return "Training Strength";
    }
    return "Nothing";
  }
  public static boolean isValidTask(String taskname) {
    taskname=taskname.toLowerCase();
    if(taskname.equals("nothing")||
       taskname.equals("farm")||
       taskname.equals("build")||
       taskname.equals("rest")||
       taskname.equals("mine")||
       taskname.equals("trainint")||
       taskname.equals("trainstr")) {
      return true;
    }
    return false;
  }
}