package Database;

import java.util.LinkedList;
import java.util.List;

public class Fila<object> {

	  private List<object> objetos = new LinkedList<object>();

	  public void push(object t) {
	    this.objetos.add(t);
	  }

	  public object pop() {
	    return this.objetos.remove(0);
	  }
	  public List<object> getFila() {
		  return objetos;
	  }

	  public boolean vazia() {
	    return this.objetos.size() == 0;
	  }
}