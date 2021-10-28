/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jargo.pkg4.pkg0;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class PalavraQ {
    public String palavra(String p, int e){
        String Array = "";
                
                ArrayList<String> lista = new ArrayList<String>();
                for(int i = 0;i<p.length();i++){
                    if(!p.substring(i,i+1).equals(" ")){
                        Array += p.substring(i,i+1);
                    }else if(i>0){
                        lista.add(String.valueOf(Array));
                        Array = "";
                    }
                }
                
        return lista.get(e);
    }
    
    public ArrayList<String> Frases(String entrada){
//        String entrada = " tudo bem? & sim ";
        ArrayList<String> frases = new ArrayList<>();
        String com = "";
        for(int i = 0; i < entrada.length(); i++){
            String letra = entrada.substring(i,i+1);
            if(letra.equals("&")){
                frases.add(com);
                com = "";
            }else{
                com += letra;
//                System.out.print(entrada.substring(i,i+1));
            }
        }
        frases.add(com);
        
        return frases;
    }
}
