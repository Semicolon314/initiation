import java.util.*;
public class Initiation {
  Display d;
  MT rand;
  Vector people;
  
  public static void main(String[] args) {
    new Initiation();
  }
  Initiation() {
    d=new Display();
    rand=new MT();
    
    //add some people
    people=new Vector();
    addPerson(new Person(this,true));
    addPerson(new Person(this,false));
    
    d.out("Welcome to Initiation.");
    d.out("//insert storyline here");
    for(int i=0;i<people.size();i++) {
      Person p=(Person)people.get(i);
      p.details();
      if(i!=people.size()-1)
        d.out("-----------------------");
    }
  }
  private void addPerson(Person p) {
    people.add(p);
  }
}