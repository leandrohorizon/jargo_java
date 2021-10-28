/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jargo.pkg4.pkg0;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Lab4-Micro20
 */
public class ArqExis {
    public String ex(String txt) throws IOException{
        String result = null;
        File arquivo;
        arquivo = new File(txt);
        if(txt.contains("!usuario")){
            txt = txt.replaceAll("!usuario ", "");
            txt = System.getProperty("user.home") + txt;
        }
        System.out.println(txt);
        if(arquivo.exists()){
            System.out.println("Encontrado");
            java.awt.Desktop.getDesktop().open( new File( txt ) );
            result = "Executando Ação";
        }else{
            String ref = txt.replaceAll(" \\(x86\\)", "");
//            System.out.println(ref);
            arquivo = new File(ref);
            if(arquivo.exists()){
                System.out.println("Encontrado");
                java.awt.Desktop.getDesktop().open( new File( ref ) );
                result = "Executando Ação";
            }else{
                result = "Arquivo não existe";
            }
        }
        return result;
    }
    public String resultados (String txt, String caminho) throws IOException{
        ArqExis ae = new ArqExis();
        
        Formatar ft = new Formatar();
        ContaPalavra cp = new ContaPalavra();
        System.out.println(caminho);
        if(caminho.contains("!usuario")){
            caminho = caminho.replaceAll("!usuario ", "");
            caminho = System.getProperty("user.home") + caminho;
        }
        System.out.println(caminho);
        File file = new File(caminho);
        if(!file.exists()){
            return "Pasta não encontrada.";
        }
        
	File afile[] = file.listFiles();
        
        String resultado = "Não encontrado";
        String resultado2 = "Não encontrado";
        StringBuffer f = new StringBuffer("");
        int m = 0;
	int i = 0;
        System.out.println("ENTRADA PRINCIPAL:"+txt);
        String entrada1 = txt;
        String entrada2 = ft.acentos(entrada1);
        String entrada3 = ft.duplicata(entrada2);
        String[] entradas = new String[]{entrada1, entrada2, entrada3};
            boolean mp = true;
	for (int j = afile.length; i < j; i++) {
            File arquivos = afile[i];
            String result = arquivos.getName().replaceAll("[-_.)(]", " ");
//            System.out.println(arquivos.getName());    
            
            String ft1 = ft.Formatar(result);
            String ft2 = ft.acentos(ft1);
            String ft3 = ft.duplicata(ft2);
            String[] arQv = new String[]{ft1, ft2, ft3};
forp:       for (String ent : entradas)
            for (String arQv1 : arQv) {
                
                int c = cp.cNotzero(" "+arQv1+" "," "+ent+" ");
                if(c >= m && c > 0){
                    System.out.println(c);
                    m = c;
                    resultado = arquivos.getName();
//                    System.out.print(resultado+" ");
//                    System.out.println(arQv1.split(" ").length-1+" "+c);
                    if(c == arQv1.split(" ").length-1 && c == ent.split(" ").length-1){
                        System.out.println("testee "+ arquivos.getName());
                        resultado2 = arquivos.getName();
                    }else{
//                        f += arquivos.getName()+"\n";
                        f.append(arquivos.getName()+"\n");
                    }
                    
                    break forp;
//                
//                }else if(arQv1.contains(ent)){
//                    resultado = arquivos.getName();
//                    f += arquivos.getName()+"\n";
//                    break forp;
                }
            }
        }
        
            System.out.println("1url "+caminho+resultado);
            
        
        if(!resultado2.equals("Não encontrado")){
            f.append(resultado2);
            ae.ex(caminho+resultado2);
        }else if(!resultado.equals("Não encontrado")){
            ae.ex(caminho+resultado);
        }else{
            f = new StringBuffer("Não encontrado");
        }
            return f.toString();
    }
}