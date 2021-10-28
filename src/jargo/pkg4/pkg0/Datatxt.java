/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jargo.pkg4.pkg0;

import jargo.pkg4.pkg0.Comand;
import jargo.pkg4.pkg0.DataHora;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Lab4-Micro22
 */
public class Datatxt {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Calendar c1 = Calendar.getInstance();
        
        Datatxt dt = new Datatxt();
        dt.inserir("!despertador 20:20 Bom dia");
        DataHora dh = new DataHora();
        Comand cm = new Comand();
        ArrayList<String> lista = new ArrayList<String>();
        ArrayList<String> despertador = new ArrayList<String>();
        lista = dt.listar();
        String result = "";
        String nome = "";
        for(int i = 0;i<lista.size(); i++){
            if(lista.get(i).contains("!despertador")){
                despertador.add(String.valueOf(lista.get(i).replaceAll("!despertador ","")));
            }
            
            if(lista.get(i).contains("!nome"))
                nome = lista.get(i).replaceAll("!nome ","");
        }
        System.out.println("Seu nome é "+nome);
        
        boolean parar = true;
//        while(parar){
//        for(int i = 0; i<despertador.size();i++)
            if(despertador.get(1).contains(cm.result("!dt !horas")))
                System.out.println(despertador.get(1).replaceAll(cm.result("!dt !horas")+" ", ""));
//                parar = false;
//            }
//        }
        while (true){
            if(result.contains(cm.result("!dt !horas"))){
                System.out.println("pronto");
                break;
            }
        }
    }
    public ArrayList<String> listar (){
        ArrayList<String> lista = new ArrayList<String>();
        System.out.println("listar");
        
        try{
            File file = new File(System.getProperty("user.home")+"\\Desktop\\data.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String data = null;
            
             while((data = reader.readLine()) != null){
                lista.add(String.valueOf(data));
            }
            fileReader.close();
            reader.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return lista;
    }
    public void inserir (String entrada){
        System.out.println("inserir");
        Datatxt dt = new Datatxt();
        ArrayList<String> lista = new ArrayList<String>();
        lista = dt.listar();
        try{
            File file = new File(System.getProperty("user.home")+"\\Desktop\\data.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            boolean encontrado = true;
            for(int i = 0; i < lista.size(); i++){
                writer.write(lista.get(i)+"\n");
                if(lista.get(i).contains(entrada))
                    encontrado = false;
            }
            if(encontrado)
                writer.write(entrada+"\n");
            writer.flush();
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void apagar (String entrada){
        Datatxt dt = new Datatxt();
        ArrayList<String> lista = new ArrayList<String>();
        lista = dt.listar();
        try{
            File file = new File(System.getProperty("user.home")+"\\Desktop\\data.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(int i = 0; i < lista.size(); i++){
                if(!entrada.contains(lista.get(i)))
                    writer.write(lista.get(i));
            }
            writer.flush();
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}