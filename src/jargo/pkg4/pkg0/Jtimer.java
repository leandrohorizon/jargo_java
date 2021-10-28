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
public class Jtimer {
    public void tempo (double segundos){
        try {
           Thread.sleep((long) (segundos * 1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) { 
        Jtimer tm = new Jtimer();
        Pesquisar p = new Pesquisar();
        String teste = p.resposta(" mensagem de boas vindas ");
        boolean o = true;
        
        for(int i = 0; i<teste.length(); i++){
            tm.tempo(0.30);
            System.out.append(teste.substring(i, i+1));
        }
    }
}
