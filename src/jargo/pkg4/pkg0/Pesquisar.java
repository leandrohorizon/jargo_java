/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jargo.pkg4.pkg0;

/**
 *
 * @author User
 */
public class Pesquisar {
    public String resposta (String entrada){
    String saida = "Problema na saida";

            
            Sql sql = new Sql();
            
            String g = "";
            for(int i = 1 ; i < entrada.length()-1 ; i++)
                g += entrada.substring(i,i+1);
            
            ContaPalavra cp = new ContaPalavra();
//            if(!sql.exibir(entrada, entrada).equals("não encontrado")){
//                saida = sql.exibir(entrada, entrada);
//            }else{
                for(int i = g.split(" ").length-1; i>=0;i--){
                    System.out.println(g.split(" ")[i]);
                    saida = sql.exibir(entrada,g.split(" ")[i]);
                    if (!saida.equals("não encontrado")){
    //                    System.out.println("encontrado");
                        break;
                    }else{
                        saida = "não encontrado";
                    }
            }
            
            return saida;
    }
}
    
 