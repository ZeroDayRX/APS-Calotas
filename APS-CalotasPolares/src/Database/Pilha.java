package Database;

import java.util.LinkedList;
import java.util.List;


public class Pilha {
	
	  private List<Object> objetos = new LinkedList<Object>();

	  public void push(Object objeto) {
	    this.objetos.add(objeto);
	  }

	  public Object pop() {
	    return this.objetos.remove(this.objetos.size() - 1);
	  }
	  
	  public List<Object> getPilha() {
		  return objetos;
	  }

	  public boolean vazia() {
		  return this.objetos.size() == 0;
	  }

	  
	  

}