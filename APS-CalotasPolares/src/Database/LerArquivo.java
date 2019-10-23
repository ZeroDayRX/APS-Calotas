package Database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LerArquivo {

    public static void gravar(String path,String texto){
        File arq = new File(path);
        try {
            FileWriter fw = new FileWriter(arq,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(texto+"\n");
            bw.close();
            fw.close();
        } catch (IOException e) {

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