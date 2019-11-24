package Database;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import Janelas.TelaAnimal;

public class LerArquivo {
	public static Pilha p = new Pilha();
	public static Fila<Object> f = new Fila<Object>();
	
    public static void gravar(String meio){
        try {    		
    		JFileChooser fileChooser = new JFileChooser();
    		fileChooser.setDialogTitle("Selecione um local para salvar");
    		fileChooser.setAcceptAllFileFilterUsed(false);
    		FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos Texto","*.txt");
    		fileChooser.addChoosableFileFilter(filter);
    		int userSelection = fileChooser.showSaveDialog(new JFrame());
    		 
    		if (userSelection == JFileChooser.APPROVE_OPTION) {
    		    File fileToSave = fileChooser.getSelectedFile();
    		    FileWriter arq;
    		    
    			try {
    				arq = new FileWriter(fileToSave.getAbsolutePath()+"-"+meio+"-fila.txt",true);
    				BufferedWriter bw = new BufferedWriter(arq);
    				while(!f.vazia()) {
    					bw.write(f.pop().toString());
    					bw.newLine();
    				}
    				bw.close();
    			} catch (IOException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
    			
    			try {
    				arq = new FileWriter(fileToSave.getAbsolutePath()+"-pilha.txt",true);
    				BufferedWriter bw = new BufferedWriter(arq);
    				while(!p.vazia()) {
    					bw.write(p.pop().toString());
    					bw.newLine();
    				}
    				bw.close();
    			}catch(IOException e2) {
    				e2.printStackTrace();
    			}
    		    
    		}
        	
        	/*
            FileWriter fw = new FileWriter(arq,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(texto+"\n");
            bw.close();
            fw.close();
            */
        } catch (Exception e) {

        }
    }

    public static ArrayList<String> carregarLinhas(String path){
        File arq = new File(path);
        try {
            FileReader fr = new FileReader(arq);
            BufferedReader br = new BufferedReader(fr);
            String linha=null;
            ArrayList<String> lista = new ArrayList<String>();
            while ((linha = br.readLine()) != null) {
                lista.add(linha);
            }
            br.close();
            fr.close();
            return lista;
        }catch (Exception ex){

            return null;
        }
    }

    //Recebe como parametro o simbolo separador e uma linha
    //e retorna um vetor de String preenchido com todos os dados separados pelo separado
    public static String[] lerDados(String separador, String linha){
        return linha.split(separador);
    }
    
    
}