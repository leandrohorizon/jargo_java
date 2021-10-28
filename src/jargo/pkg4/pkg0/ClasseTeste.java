/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jargo.pkg4.pkg0;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lab4-Micro20
 */
public class ClasseTeste{  
  
//    public static void main(String[] args) { 
//            
//    try{
//        int keyInput[] = {  
//            KeyEvent.VK_ENTER  
//        };  
//        Robot robot = new Robot();  
//        for (int cnt2 = 0;cnt2 < keyInput.length; cnt2++){  
//          robot.keyPress(keyInput[cnt2]);
//          robot.delay(1);
//        }
//  }catch(AWTException e){
//     System.out.println(e);
//    }
    
//  }
//    public static void main(String[] args) { 
//        String teste = " amanha ";
//        Formatar ft = new Formatar();
//        DataHora dt = new DataHora();
//        teste = teste.replaceAll(" amanha "," "+dt.DT("!amanha")+"/"+dt.DT("!mesn")+" ");
//        System.out.println(ft.Formatar(teste));
//    }
//    public static void main(String[] args) { 
//        String pergunta   = "idade";
//        String referencia = "leandro";
//        
//        Sql sql = new Sql();
////        System.out.println("resposta "+sql.exibir(" "+pergunta+" "," "+referencia));
//    }
//    public static void main(String[] args) { 
//        Sql sql = new Sql();
//        sql.conectar();
//    }
//    public static void main(String[] args) { 

//        System.out.println(teste.length());
//        
////        String caminho = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";
////        String caminho2 = caminho.replaceAll(" \\(x86\\)", "");
////        System.out.println(caminho2);
////        try{
////            java.awt.Desktop.getDesktop().open( new File( "C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\Google Chrome.lnk" ) );
////        }catch(IOException e){
////            System.out.println(e);
////        }
//
//
////        try{
////            Process pro = Runtime.getRuntime().exec(caminho);
////            int waitFor = pro.waitFor();
////        }catch(IOException r){
////            System.out.println(r);
////        } catch (InterruptedException ex) {
////            Logger.getLogger(ClasseTeste.class.getName()).log(Level.SEVERE, null, ex);
////        }
//    }
//    public static void main(String[] args) throws IOException {
////        try {
//        ArqExis ae = new ArqExis();
//        String txt = "asdfhkafsdhjkl";
//        System.out.println(ae.ex(txt));
////            java.awt.Desktop.getDesktop().browse( new java.net.URI( "http://www.google.com" ) );
////        } catch (URISyntaxException ex) {
////            Logger.getLogger(ClasseTeste.class.getName()).log(Level.SEVERE, null, ex);
////            
//        }
//    public static void main(String[] args) throws URISyntaxException, IOException{
//    DataHora dt = new DataHora();
//        String result = "hoje é !dia do mes de !mes de !ano";
//        for(int i = 0; i < 5; i++)
//        result = dt.DT(result);
//        
//        System.out.println(result);
//        
//        String pesquisa = "pesquisa a ser feita";
//        pesquisa = pesquisa.replace(" ", "+");
//        java.awt.Desktop.getDesktop().browse( new java.net.URI( "https://www.google.com.br/search?q="+pesquisa ) );
//    }
//    public static void main(String[] args){
//        String t = " Qual idade do leandro ";
//        String r = " leandro idade ";
//        String rr = " teste 3 ";
//        int a = 0;
//        Sql sql = new Sql();
//        ContaPalavra cp = new ContaPalavra();
//        System.out.println(cp.contador(t, r));
//        // se for maior que o anterior ele se torna a resposta original
//        int ba = 0;
//        if(cp.contador(t,r)>ba||ba == 0){
//            ba = cp.contador(t,r);
//            // resposta
//        }
////        if(t.contains("teste") && t.contains("3"))
////            System.out.println("é 12");
//        
//        String Array = "";
//        int g = 0;
//        ArrayList<String> lista = new ArrayList<String>();
//        for(int i = 0;i<r.length();i++){
//            if(!r.substring(i,i+1).equals(" ")){
//                Array += r.substring(i,i+1);
//            }else if(i>0){
//                lista.add(String.valueOf(Array));
//                Array = "";
//            }
//        }
//        boolean finish = false;
//        int i = 0;
//        for(int j=0; j<lista.size();j++){
//            if(t.contains(" "+lista.get(j)+" ")){
//                finish = true;
//                i++;
//            }else{
//                finish = false;
//            }
//        }
//        
//        if(a<0)
//        a = i;
//        if(i<a)
//            System.out.println("funciono "+i);
//        
//        if(finish)
//                System.out.println("funciono "+i);
//            else
//                System.out.println("proximo resultado");
//        }
//        public static void main(String[] args){
//            Sql sql = new Sql();
////            Formatar ft = new Formatar();
//            System.out.println("Codigo sem uso #"+sql.cod());
////            String pergunta = " musica do homem de ferro ";
////            String comp = "musica homem de ferro";
////            ContaPalavra cp = new ContaPalavra();
//////            System.out.println(cp.contador(pergunta, comp));
////            System.out.println(sql.exibir(ft.Formatar(pergunta)));
//        }
//    }
//}
//            public static void main(String[] args){
//                Comand cd = new Comand();
//                Sql sql = new Sql();
//                System.out.println(sql.exibir(" leandro "));
//            }
//            public static void main(String[] args){
//                String Array = "";
//                String sql = " "+"leandro couto messias"+" ";
//                ArrayList<String> lista = new ArrayList<String>();
//                for(int i = 0;i<sql.length();i++){
//                    if(!sql.substring(i,i+1).equals(" ")){
//                        Array += sql.substring(i,i+1);
//                    }else if(i>0){
//                        lista.add(String.valueOf(Array));
//                        Array = "";
//                    }
//                }
//                for(int i = 0; i<lista.size();i++){
//                    System.out.println(lista.get(i));
//            }
//            String entrada = " "+"Quem é leandro"+" ";
//            String saida;
//            
//            PalavraQ pq = new PalavraQ();
//            
//            Sql sql = new Sql();
//            ContaPalavra cp = new ContaPalavra();
//            
//            for(int i=0;i<cp.contador(entrada,entrada);i++){
//                
//                System.out.println(1+i+" procurando por "+pq.palavra(entrada, i));
//                
//                if (!sql.exibir(entrada,pq.palavra(entrada, i)).equals("não encontrado")){
//                    saida = sql.exibir(entrada,pq.palavra(entrada, i));
//                    break;
//                }
//            }
//            };
//    public static void main(String[] args) throws IOException{
////        File file = new File("C:\\Users\\User\\Music\\");
////        Formatar ft = new Formatar();
////        PalavraQ pq = new PalavraQ();
////        String txt = "Bruno Mars Talking To The Moon (Video Offcial)";
////        String comp = ft.Formatar(txt);
////        System.out.println(comp);
////	File afile[] = file.listFiles();
////        String resultado = "";
////	int i = 0;
////        System.out.println(pq.palavra("  "+comp+"  ", 1));
////	for (int j = afile.length; i < j; i++) {
////		File arquivos = afile[i];
////
////                String result = arquivos.getName().replaceAll("[-()_]", " ");
////                
//////                System.out.println(ft.Formatar(result).replaceAll("mp3", ""));
////                if((ft.Formatar(result).replaceAll("mp3", "")).contains(comp)){
////                    resultado = arquivos.getName()+"\n";
////                    
////                }
////	}
////        System.out.println(resultado);
////
//        ArqExis ae = new ArqExis();
//        Formatar ft = new Formatar();
////        System.out.println(ae.resultados("talking", "C:\\Users\\User\\Music\\"));
////        String txt = "Asking_Alexandria_(Ben_Bruce_Acoustic)_-_Someone_Somewhere";
////        txt = txt.replaceAll("[-_]", " ");
////        System.out.println(ft.Formatar(txt));
////        System.out.println("F "+ae.resultados("asking alexandria", "C:\\Users\\User\\Music\\"));
////        ae.ex("C:\\Users\\User\\Music\\AlbumArt_{B969CA59-9247-47F6-ACAB-F8420DB7B194}_Large.jpg");
//
//        File file = new File("C:\\Users\\User\\Music\\");
//        File afile[] = file.listFiles();
//        
//        ArrayList<String> lista = new ArrayList<String>();
//        for (int i = 0; i<10;i++){
//            lista.add(String.valueOf(i));
//        }
//        
//        System.out.println(lista.contains("1"));
//        System.out.println(afile[1].getName());
//        Collections.sort(lista);
//        
//        System.out.println("Busca Binaria: "+Collections.binarySearch(lista,"5"));
//        Collections.replaceAll(lista, " ", " ");
////        for (int i = 0; i<10;i++)
////            System.out.println(lista.get(i));
////    }
//    }
//    public static void main(String[] args) throws IOException{
//        ArqExis ae = new ArqExis();
////        ae.ex("");
////    String us = System.getProperty("user.name");// nome do usuario
////    us = System.getProperty("user.dir");// onde o arquivo esta
////    us = System.getProperty("user.home");// pasta do usuario
////    System.out.println(us);
//    boolean passar_tudao_fodase = true;
//    int i = 0;
////    Runtime.getRuntime().exec("cmd.exe /c move "+"C:\\Label\\*.*"+" "+"C:\\Users\\User\\Desktop\\Label");
////    System.out.println(Runtime.getRuntime().exec("ipconfig"));
//        Comand cmd = new Comand();
//        System.out.println(cmd.result("!exec notepad"));
//        
//    
//    }
//    public static void main(String[] args){
//        try {
            //        Formatar ft = new Formatar();
//        String entrada = " posso te fazer uma pergunta ? ";
//        entrada = ft.Formatar(entrada);
////        if(entrada != " "){
////        if(entrada.substring(0,1).equals(" "))
////            entrada = entrada.substring(1,entrada.length());
////        if(entrada.substring(entrada.length()-1,entrada.length()).equals(" "))
////            entrada = entrada.substring(0,entrada.length()-1);
////        }
//        System.out.println(entrada);
    
//        while(true){
//            System.out.print("Você: ");
//            Scanner ler = new Scanner(System.in);    
//            String entrada = ler.nextLine();
//            Jargo j4 = new Jargo();
//            System.out.println("Jargo: "+j4.saida(entrada));
//        }
//        Comand cm = new Comand();
//        System.out.println(cm.result("!printscreen"));
//        DataHora dh = new DataHora();
//        System.out.println(dh.DT("!dia\\!mes\\!ano !horas!segundos"));
//            Sql sql = new Sql();
//            System.out.println(sql.conectar());
//          Formatar ft = new Formatar();
//          System.out.println(ft.duplicata(" leandrooooooooooooo "));
//        File arquivo;
//        arquivo = new File("Computador\\Leandro\\Phone");
//        if(arquivo.exists())
//            System.out.println("existe");
//        else
//            System.out.println("nao existe");
//
//        ContaPalavra cp = new ContaPalavra();
//
//        Formatar ft = new Formatar();
//        
////        System.out.println(cp.contador(ft.Formatar(" "+"cpm"+" "), ft.Formatar(" "+"CPM"+" ")));
//        
//        String txt = "sei la";
//        String comp = ft.Formatar(txt);
//        String  ca = "C:\\Users\\User\\Desktop\\teste\\";
//                ca = "C:\\Users\\User\\Music\\";
//        File file = new File(ca);
//        if(!file.exists()){
//            return;
//        }
//        
//	File afile[] = file.listFiles();
//        
//        String resultado = "Não encontrado";
//
//        String f = "";
//        int m = 0;
//	int i = 0;
//        
//        String entrada1 = ft.Formatar(" "+txt+" ");
////        System.out.println(entrada1);
//        String entrada2 = ft.acentos(entrada1);
////        System.out.println(entrada2);
//        String entrada3 = ft.duplicata(entrada2);
////        System.out.println(entrada3);
//        String[] entradas = new String[]{entrada1, entrada2, entrada3};
//        
//	for (int j = afile.length; i < j; i++) {
//            File arquivos = afile[i];
//            String result = arquivos.getName().replaceAll("[-_.]", " ");
//
//        
//            String ft1 = ft.Formatar(" "+result+" ");
//            String ft2 = ft.acentos(ft1);
//            String ft3 = ft.duplicata(ft2);
//            String[] arQv = new String[]{ft1, ft2, ft3};
//forp:       for (String ent : entradas)
//            for (String arQv1 : arQv) {
//                System.out.println("entrada"+ent+"\ncomparar"+arQv1);
//                int c = cp.contador(arQv1,ent);
////                if(c>0)
////                    System.out.println(c);
//                if(c>m){
//                    m = c;
//                    resultado = arquivos.getName();
//                    f += arquivos.getName()+"\n";
////                    if(m == cp.contador(txt, txt))
////                        break;
//                    break forp;
//                
//                }else if(arQv1.contains(comp)){
//                    resultado = arquivos.getName();
//                    f += arquivos.getName()+"\n";
//                    break;
//                }
//                
//            }
////            if(comp.contains(ft1)){
////                f += arquivos.getName()+"\n";
////            }
//	}
//        System.out.println(resultado);
//        
//        ContaPalavra cp = new ContaPalavra();
//        System.out.println("teste 1: "+cp.cNotzero(" teste ashajsasj "," teste "));
//        System.out.println("teste 2: "+cp.cNotzero(" teste "," teste jdsajsdaj "));
//        
//        System.out.println("teste 3: "+cp.contador(" teste ashajsasj "," teste "));
//        System.out.println("teste 4: "+cp.contador(" teste "," teste jdsajsdaj "));

//        Runtime.getRuntime().exec("tskill notepad.exe");
//        } catch (IOException ex) {
//            ex.printStackTrace();
////        }
//
//        String entrada = " tudo bem & fdashfdjsafh sim ";
//        String sql = " tudo bem? & sim ";
//        ArrayList<String> frases = new ArrayList<String>();
//        ArrayList<String> comparar = new ArrayList<String>();
////        String com = "";
////        for(int i = 0; i < entrada.length(); i++){
////            String letra = entrada.substring(i,i+1);
////            if(letra.equals("&")){
////                frases.add(com);
////                com = "";
////                System.out.println();
////            }else{
////                com += letra;
//////                System.out.print(entrada.substring(i,i+1));
////            }
////        }
////        frases.add(com);
////        for(int i = 0; i < frases.size(); i++)
////            System.out.println(1+i+frases.get(i));
//        frases = new PalavraQ().Frases(entrada);
//        comparar = new PalavraQ().Frases(sql);
//        ContaPalavra cp = new ContaPalavra();
//        int c = 0;
////        System.out.println(1+i+frases.get(i));
//        for(int x = 0; x < frases.size(); x++){
//            for(int y = 0; y < comparar.size(); y++){
//                c = cp.contador(frases.get(x), comparar.get(y));
//                System.out.println("x: "+x+" y: "+y+" c: "+c);
//                if(c == 0 && x == frases.size()){
//                    break;
//                }
//            }
//        }
//        
//        if(c != 0)
//            System.out.println("encontrado");
//        else
//            System.out.println("Não encontrado");
//}
        public static void main(String[] args){
//            Formatar ft = new Formatar();
//            ContaPalavra cp = new ContaPalavra();
//            
//            System.out.println(cp.cNotzero(" daughter the end ", " the day i left the womb escape the fate mp3 "));
//            System.out.println(cp.cNotzero(" the day i left the womb escape the fate mp3 ", " daughter the end "));
//            String entrada = "leandro couto messias";
//            int e = entrada.split(" ").length;
//            new Sql().conectar();
//            System.out.println(new Pesquisar().resposta(" data enem "));
//            PalavraQ pq = new PalavraQ();
            
//            for(int i = entrada.split(" ").length-1; i >= 0; i--){
//                System.out.println(entrada.split(" ")[i]);
//                System.out.println(pq.palavra(entrada, i));
//            }
//            System.out.println(pq.palavra(entrada, 1));
//            System.out.println(new Sql().exibir(" mensagem de boas vindas ", "mensagem"));
        new Sql().conectar();
        Formatar ft = new Formatar();
        ContaPalavra cp = new ContaPalavra();
        
        
        String result = "Daughter - If You Leave - 2013 (320 kbps)";
        String txt    = "cypher";
        
        String ft1 = ft.Formatar(result);
        String ft2 = ft.acentos(ft1);
        String ft3 = ft.duplicata(ft2);
        
        String[] arQv = new String[]{ft1, ft2, ft3};
        String entrada1 = " "+txt+" ";
        String entrada2 = ft.acentos(entrada1);
        String entrada3 = ft.duplicata(entrada2);
        String[] entradas = new String[]{entrada1, entrada2, entrada3};
        
        for (String ent : entradas)
            for (String arQv1 : arQv) {
                int c = cp.cNotzero(" "+arQv1+" "," "+ent+" ");
                System.out.println(c);
            }
    }
}