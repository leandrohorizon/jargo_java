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
public class ContaPalavra {
     public int contador(String pergunta, String sql){
        String Array = "";
        int g = 0;
        
        ArrayList<String> lista = new ArrayList<String>();
        for(int i = 0;i<sql.length();i++){
            if(!sql.substring(i,i+1).equals(" ")){
                Array += sql.substring(i,i+1);
            }else if(i>0){
                lista.add(String.valueOf(Array));
                Array = "";
            }
        }
        for(int j=0; j<lista.size();j++){
            if(pergunta.contains(" "+lista.get(j)+" "))
                g++;
            else{
                g = 0;
                break;
            }
        }
        return g;
    }
     
    public int cNotzero(String pergunta, String sql){
        String Array = "";
        int g = 0;
        
        ArrayList<String> lista = new ArrayList<String>();
        for(int i = 0;i<sql.length();i++){
            if(!sql.substring(i,i+1).equals(" ")){
                Array += sql.substring(i,i+1);
            }else if(i>0){
                lista.add(String.valueOf(Array));
                Array = "";
            }
        }
        for(int j=0; j<lista.size();j++){
            if(pergunta.contains(" "+lista.get(j)+" "))
                g++;
        }
        return g;
    }
}
