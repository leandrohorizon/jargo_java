package jargo.pkg4.pkg0;

import Formatacao.FormatarO;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class Formatar {
    
    ArrayList<String> lista2 = new ArrayList<String>();
    
    static ArrayList<FormatarO> form = new ArrayList<>();
    static ArrayList<String> Palavras = new ArrayList<>();
    String[] Letras = new String[]
        { "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","X","W","Y","Z",
        "Á","À","Ã","Â","Ä",
        "É","È","Ê","Ë",
        "Í","Ì","Î","Ï",
        "Ó","Ò","Ô","Õ","Ö",
        "Ú","Ù","Û","Ü",
        "Ç"};
    public String Matematica(String txt){
        String saida = txt;
        saida = saida
                .replaceAll(" dividido por ","/")
                .replaceAll(" soma com ","+")
                .replaceAll(" mais ","+")
                .replaceAll(" subtrai ", "-")
                .replaceAll(" menos ", "-")
                .replaceAll(" multiplica por ", "*")
                .replaceAll(" multiplicado por ", "*")
                .replaceAll(" vezes ", "*")
                .replaceAll(" ", "")
                .replaceAll("=", "")
                ;
        return saida;
    }
    public String Formatar(String txt){
        
        DataHora ft = new DataHora();
        
        String teste = txt;
        teste = teste
                .toLowerCase()
                .replaceAll(" enviar","")
                .replaceAll(" !ap "," apagar pergunta ")
                .replaceAll(" !pr "," perguntas sem respostas ")
                .replaceAll(" !re "," resposta errada ")
                .replaceAll(" !pg "," pesquisar ")
                .replaceAll(" !ep "," editar pergunta ")
                .replaceAll(" !if "," inserir forcado ")
                
                .replaceAll("[-+=*;.!_~^ºª°/?!@#$%¨*`´:§£¢¬|¹²³]", " ")
                .replaceAll("\\<"," ")
                .replaceAll("\\>"," ")
                .replaceAll("\\["," ")
                .replaceAll("\\]"," ")       
                .replaceAll("['\"]", "")
                .replaceAll("[<>()\\{\\}]", "")
                .replaceAll("['\\\\.,()|/]", "")
//                
//                .replaceAll(" ep "," episodio ")
//                .replaceAll(" q "," que ")
//                .replaceAll(" twd "," the walking dead ")
//                .replaceAll(" vc "," voce ")
//                .replaceAll(" tdb "," tudo bem ")
//                .replaceAll(" td "," tudo ")
//                .replaceAll(" pc "," computador ")
//                .replaceAll(" fb "," facebook ")
//                .replaceAll(" sqn "," so que nao ")
//                .replaceAll(" add "," adicionar ")
//                .replaceAll(" cmg "," comigo ")
//                .replaceAll(" ctz "," certeza ")
//                .replaceAll(" msg "," mensagem ")
//                .replaceAll(" n "," nao ")
//                .replaceAll(" pfv "," por favor ")
//                .replaceAll(" pq "," porque ")
//                .replaceAll(" vdd "," verdade ")
//                .replaceAll(" kra "," cara ")
//                .replaceAll(" blz "," beleza ")
//                .replaceAll(" kd "," cade ")
//                .replaceAll(" qdo "," quando ")
//                .replaceAll(" sla "," sei la ")
//                .replaceAll(" oq "," o que ")
//                .replaceAll(" msg "," mensagem ")
//                .replaceAll(" mto "," muito ")
//                .replaceAll(" obg "," obrigado ")
//                .replaceAll(" msc "," musica ")
//                .replaceAll(" face "," facebook ")
//                .replaceAll(" cs "," counter strike ")
                //data
                .replaceAll(" amanha "," "+ft.DT("!amanha")+"/"+ft.DT("!mesn")+" ")
                .replaceAll(" ontem "," "+ft.DT("!ontem")+"/"+ft.DT("!mesn")+" ")
                .replaceAll(" hoje "," "+ft.DT("!dia")+"/"+ft.DT("!mesn")+" ");
        // atualização
        Sql con = new Sql();
        
        teste = teste.toUpperCase();
        
        String vogal = null;
        String vogal2 = null;
        String asString = null;
        
        
        
        for (String a : Letras) {
            while(teste.contains(a+a+a) || (teste.contains("  "))){
                teste = teste.replace(a+a+a, a+a);
                teste = teste.replace("  ", " ");
            }
        }
        
//        for (int i = 65; i < 91; i++){
//            char a = (char) i;
//            lista2.add(String.valueOf(a));
//            asString = Character.toString(a);
//            vogal = asString+asString+asString;
//            vogal2 = asString+asString;
//                for(int j = 0;j<50; j++ ){
//                    teste = teste.replaceAll("  "," ");
//                    teste = teste.replaceAll(vogal,vogal2);
//                }
//        }
            teste = teste.toLowerCase();
            for(String p : txt.split(" ")){
            
                if((!Palavras.contains(p)) && (!p.equals(""))){// && (p.matches(".*\\w\\.*") && (!p.matches(".*\\d\\.*")))){
                    form.add(con.traduzir(p));
                    Palavras.add(p);
                }
            }
            for(String p : teste.split(" ")){
                int index = Palavras.indexOf(p);
//                System.out.println(index);
                if(index >= 0)
                    teste = teste.replaceAll(form.get(index).getPalavra(), form.get(index).getTraducao());
            }
            
//            for(FormatarO saida : form){
//                if(!saida.getPalavra().matches(".*\\W+|\\d.*"))
//                    teste = teste.replaceAll(" "+saida.getPalavra()+" ", " "+saida.getTraducao()+" ");
////                System.out.println(saida.getPalavra());
//            }
        
        return teste;
    }
    public String Formatar2(String txt){
        return duplicata(acentos(Formatar(txt)));
    }
    public String duplicata(String entrada){
        entrada = entrada.toUpperCase();
        for (String a : Letras) {
            
            while(entrada.contains(a+a) || (entrada.contains("  "))){
                entrada = entrada.replace(a+a, a);    
                entrada = entrada.replace("  ", " ");
            }
        }
        entrada = entrada.toLowerCase();
        return entrada;
    }
    public String acentos(String entrada){
        String teste = entrada;
        
        teste = teste.toLowerCase()
                .replaceAll("[aáàãâä]","a")
                .replaceAll("[eéèëê]","e")
                .replaceAll("[iíìïî]","i")
                .replaceAll("[oóòöôõ]","o")
                .replaceAll("[uúùüû]","u")
                .replaceAll("[ç]","c");
        
        return teste;
    }
}
