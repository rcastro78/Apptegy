package info.kodelogic.POJO;

public class SportDetails {
  private int id,counter;
  private String name;
  public SportDetails(int id, String name, int counter)
  {
	  this.id=id;
	  this.name=name;
	  this.counter=counter;
  }
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getCounter() {
	return counter;
}
public void setCounter(int counter) {
	this.counter = counter;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
  
  

}
