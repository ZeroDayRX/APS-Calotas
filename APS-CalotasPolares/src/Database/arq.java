package Database;

import Janelas.TelaPrincipal;
import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class arq{
	public static void importArqAnimais(String Caminho) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(Caminho));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
