/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jargo.pkg4.pkg0;

import Formatacao.FormatarO;
import Menu.MenuPropriedade;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author User
 */
public class Sql {
    private String url = "jdbc:mysql://localhost/memoria";
    private String usuario = "root";
    private String senha = "";
    static private Connection Conexao = null ;
    static private String respTemp = "aaa";
        public boolean conectar(){
            boolean resultado = false;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Conexao = DriverManager.getConnection(url,usuario,senha);
                resultado = true;
            } catch (ClassNotFoundException ex){
                System.out.println("Não foi possivel encontrar a classe");
            } catch(SQLException e){
                System.out.println("Ocorreu um erro de SQL "+e.getMessage());
            }
            return resultado;
        }
        
        
        public boolean existe(String pergunta){
            
            boolean localizado = false;
            
            try{
                String query = "select * from tbl_inf order by cod desc";
                PreparedStatement stmt = Conexao.prepareStatement(query); 

                ResultSet rs = stmt.executeQuery();


                while (rs.next()){
                    if(pergunta.contains(" "+rs.getString("pergunta")+" ")){
                        localizado = true;
                        
                }
            }
            } catch(SQLException e){
                System.out.println("Ocorreu um erro de SQL "+e.getMessage());
            }
            return localizado;
        }
        public boolean existe2(String pergunta){
            Connection con = null;
            boolean localizado = false;
            
            try{
                String query = "select * from tbl_inf";
                PreparedStatement stmt = Conexao.prepareStatement(query); 
                ResultSet rs = stmt.executeQuery();
                int l = 0, temp = 0, result = 0, result2 = 0;

                while (rs.next()){
                    if(pergunta.equals(" "+rs.getString("pergunta")+" ")){
                        localizado = true;                      
                    }
                }
            } catch(SQLException e){
                System.out.println("Ocorreu um erro de SQL "+e.getMessage());
            }
            return localizado;
        }
        
        
        public String exibir(String pergunta, String lista){
            String resposta = "não encontrado";
            String query = null;
            String rep = "";
            ContaPalavra cp = new ContaPalavra();
            Formatar ft = new Formatar();
            String acentos = ft.acentos(lista);
            String duplicatas = ft.duplicata(acentos);
//            System.out.println("acentos "+acentos);
//            System.out.println("duplicatas "+duplicatas);
//            System.out.println("PERGUNTA "+pergunta+" CONTEM "+cp.contador(pergunta,pergunta)+" PALAVRAS");
            
            int len = 0;
            try{
                
                query = "select * from tbl_inf where peso <= ? and (pergunta like ? or pergunta like ? or pergunta like ? or pergunta like ?) order by peso desc";
                
                PreparedStatement stmt = Conexao.prepareStatement(query);
                
                stmt.setString(5, "%" + respTemp);
                stmt.setString(4, "%" + lista);
                stmt.setString(2, "%" + acentos);
                stmt.setString(3, "%" + duplicatas);
                stmt.setInt(1, cp.contador(" "+respTemp+pergunta," "+respTemp+pergunta));
                
                System.out.println("Peso: "+cp.contador(pergunta,pergunta));
                
                ResultSet rs = stmt.executeQuery();
                int l = 1, temp = 0, result = 0;
                String acentos2 = ft.acentos(" "+pergunta+" ");
                String duplicatas2 = ft.duplicata(" "+acentos2+" ");
                String[] perguntas = new String[]{pergunta, acentos2, duplicatas2};
                int i;
                int rt;
                ArrayList<String> frases = new ArrayList<>();
                frases = new PalavraQ().Frases("");
                while (rs.next()){
                    i=0;
                    
//                    if(pergunta.contains(" "+rs.getString("pergunta")+" ")){
                    for (String pergunta1 : perguntas) {
                        i++;
                        result = cp.contador(pergunta1, " "+rs.getString("pergunta")+" ");
                        rt = cp.contador(" "+respTemp+" "," "+respTemp+" ");
                        
                        if(rs.getString("pergunta").contains(respTemp)){
                            result += rt;
                            result = cp.contador(pergunta1, rs.getString("pergunta").replaceAll(respTemp,"")+" ");
                        }
//                        
//                        if (rs.getString("resposta").contains("!absoluto") && i >= 3) {
//                            if (cp.contador(pergunta1, pergunta1) != result) {
//                                break;
//                            }
//                        }
                        
                        
                        if(result > temp){
                            temp = result;
                            if(rs.getString("resposta").contains("!absoluto")){
                                if(result == cp.contador(pergunta1,pergunta1)){
                                    resposta = rs.getString("resposta").replaceAll("!absoluto ", "");
                                    respTemp = rs.getString("pergunta")+ " &";
                                }
                            }else{
                                resposta = rs.getString("resposta").replaceAll("!absoluto ", "");
                                respTemp = rs.getString("pergunta")+ " &";
                            }
                                
                            
                            break;
                        }
                        if (temp == cp.contador(pergunta1, pergunta1)) {
                            
                            break;
                        }
                        l++;
                    }
                }
                        
                rs.close(); 
                stmt.close();
                
            } catch(SQLException e){
                System.out.println("Ocorreu um erro de SQL "+e.getMessage());
            }
            
            return new Comand().result(resposta);    
        }
        
        public String exibir2(String pergunta){
            Connection con = null;
            String resposta = null;
            DataHora dt = new DataHora();
            
            try{
                String query = "select * from tbl_inf where pergunta = ? COLLATE utf8_bin";
                PreparedStatement stmt = Conexao.prepareStatement(query); 
                stmt.setString(1, pergunta);
                ResultSet rs = stmt.executeQuery();


                while (rs.next()){    
                    resposta = rs.getString("resposta");
                }
            } catch(SQLException e){
                System.out.println("Ocorreu um erro de SQL "+e.getMessage());
            }
            return resposta;    
        }
//        public String exibir3(String pergunta){
//            Connection con = null;
//            String resposta = "não encontrado";
//            DataHora dt = new DataHora();
//            
//            try{
//                Class.forName("com.mysql.jdbc.Driver");
//                con=DriverManager.getConnection(url,usuario,senha);
//
//                String query = "select * from tbl_inf order by cod desc";
//                PreparedStatement stmt = con.prepareStatement(query); 
//
//                ResultSet rs = stmt.executeQuery();
//
//                int l = 0, temp = 0, result = 0;
//                while (rs.next()){
//                    ContaPalavra cp = new ContaPalavra();
//                    result = cp.contador(pergunta, " "+rs.getString("pergunta")+" ");
//                    if(result > temp){
//                        temp = result;
//                        resposta = rs.getString("resposta");
//                    }
////                    if(pergunta.contains(" "+rs.getString("pergunta")+" ")){
////                        resposta = rs.getString("resposta");   
////                    }
//                }
//            }catch (ClassNotFoundException ex){
//                System.out.println("Não foi possivel encontrar a classe");
//            } catch(SQLException e){
//                System.out.println("Ocorreu um erro de SQL "+e.getMessage());
//            }
//            return resposta;    
//        }
        
        
//         public String pergunta(String pergunta){
//            Connection con = null;
//            String resposta = null;
//            DataHora dt = new DataHora();
//            
//            try{
//                Class.forName("com.mysql.jdbc.Driver");
//                con=DriverManager.getConnection(url,usuario,senha);
//
//                String query = "select * from tbl_inf order by cod desc";
//                PreparedStatement stmt = con.prepareStatement(query); 
//
//                ResultSet rs = stmt.executeQuery();
//
//
//                while (rs.next()){
//                    if(pergunta.contains(rs.getString("pergunta"))){
//                        resposta = rs.getString("pergunta");
//                    }
//                }
//            }catch (ClassNotFoundException ex){
//                System.out.println("Não foi possivel encontrar a classe");
//            } catch(SQLException e){
//                System.out.println("Ocorreu um erro de SQL "+e.getMessage());
//            }
//            return resposta;    
//        }
        
        public String inserir(String pergunta,String resposta){
            ContaPalavra cp = new ContaPalavra();
            
            try{
                String query = "insert into tbl_inf (pergunta, resposta, peso) values(?, ?, ?)";
                PreparedStatement stmt = Conexao.prepareStatement(query); 
                
//                stmt.setInt(1, sql.cod());
                stmt.setString(1, pergunta);
                stmt.setString(2, resposta);
                stmt.setInt(3, cp.contador(" "+pergunta+" ", " "+pergunta+" "));

                stmt.executeUpdate();
                
                stmt.close();

            } catch(SQLException e){
                System.out.println("Ocorreu um erro de SQL "+e.getMessage());
            }
            return "Inserida com sucesso";
        }
        
        public String alterar(String pergunta, String resposta){
            
            try{
                String query = "update tbl_inf set resposta = ? where pergunta= ? COLLATE utf8_bin";
                PreparedStatement stmt = Conexao.prepareStatement(query);
                System.out.println("Sql "+pergunta);
                stmt.setString(1, resposta);
                stmt.setString(2, pergunta);

                stmt.executeUpdate();

                stmt.executeUpdate();

                stmt.close();
            } catch(SQLException e){
                System.out.println("Ocorreu um erro de SQL "+e.getMessage());
            }    
            return "Alterada com sucesso";
        }
        
        public String alterar2(String pergunta, String PerguntaFinal){
            ContaPalavra cp = new ContaPalavra();
            try{
                String query = "update tbl_inf set pergunta = ?, peso = ? where pergunta= ? COLLATE utf8_bin";
                PreparedStatement stmt = Conexao.prepareStatement(query);

                stmt.setString(1, PerguntaFinal);
                stmt.setInt(2, cp.contador(" "+PerguntaFinal+" ", " "+PerguntaFinal+" "));
                stmt.setString(3, pergunta);

                stmt.executeUpdate();

                stmt.executeUpdate();

                stmt.close();
            } catch(SQLException e){
                System.out.println("Ocorreu um erro de SQL "+e.getMessage());
            }    
            return "Alterada com sucesso";
        }
//        public String alterarCod(int CodMod){
//            Connection con = null;
//            ContaPalavra cp = new ContaPalavra();
//            Sql sql = new Sql();
//            try{
//                Class.forName("com.mysql.jdbc.Driver");
//                con=DriverManager.getConnection(url,usuario,senha);
//
//                String query = "update tbl_inf set cod = ? where cod = ?";
//                PreparedStatement stmt = con.prepareStatement(query);
//
//                stmt.setInt(1, sql.cod());
//                stmt.setInt(2, CodMod);
//
//                stmt.executeUpdate();
//
//                stmt.executeUpdate();
//
//                stmt.close();
//                con.close();
//            }catch (ClassNotFoundException ex){
//                System.out.println("Não foi possivel encontrar a classe");
//            } catch(SQLException e){
//                System.out.println("Ocorreu um erro de SQL "+e.getMessage());
//            }    
//            return "Alterada com sucesso";
//        }
        public String deletar(String pergunta){
            try{
                String query = "DELETE FROM tbl_inf WHERE pergunta = ? COLLATE utf8_bin";
                PreparedStatement stmt = Conexao.prepareStatement(query);
                stmt.setString(1, pergunta);
                stmt.executeUpdate();

                stmt.close();
            } catch(SQLException e){
                System.out.println("Ocorreu um erro de SQL "+e.getMessage());
            }
            return "Apagada com sucesso";
        }
        public String SR(){
            DataHora dt = new DataHora();
            String txt = "";
            
            try{
                String query = "select * from tbl_inf where resposta = 'Sem resposta'";
                PreparedStatement stmt = Conexao.prepareStatement(query); 

                ResultSet rs = stmt.executeQuery();

                int i = 0;
                while (rs.next()){
                        i++;
                        txt += i+"º pergunta: "+rs.getString("pergunta")+"\n";
//                        System.out.println(rs.getString("pergunta"));
//                    System.out.println(rs.getString("pergunta"));
                }
            } catch(SQLException e){
                System.out.println("Ocorreu um erro de SQL "+e.getMessage());
            }
             
            return txt;
        }
//        public int cod(){
//            Connection con = null;
//            int codigo = 1;
//            
//            try{
//                Class.forName("com.mysql.jdbc.Driver");
//                con=DriverManager.getConnection(url,usuario,senha);
//
//                String query = "select * from tbl_inf";
//                PreparedStatement stmt = con.prepareStatement(query); 
//
//                ResultSet rs = stmt.executeQuery();
//                
//
//                while (rs.next()){
//                    if(codigo == rs.getInt("cod")){
//                        codigo++;
//                    }else{
//                        break;
//                    }
//                }
//            }catch (ClassNotFoundException ex){
//                System.out.println("Não foi possivel encontrar a classe");
//            } catch(SQLException e){
//                System.out.println("Ocorreu um erro de SQL "+e.getMessage());
//            }
//            return codigo;
//            
//        }
        
        public ArrayList<MenuPropriedade> Menu(){
            ArrayList<MenuPropriedade> saida = new ArrayList<>();
            try{
                String query = 
                        "select * from tbl_inf where (resposta like '!exec%' or "
                        + "resposta like '!arquivo%' or resposta like '!site%') "
                        + "order by resposta desc";
                PreparedStatement stmt = Conexao.prepareStatement(query);

                ResultSet rs = stmt.executeQuery();
                
                while (rs.next()){
                    MenuPropriedade m = new MenuPropriedade();
                    m.setNome(rs.getString("pergunta"));
                    m.setComando(rs.getString("resposta"));
                    
                    saida.add(m);
                }
            } catch(SQLException e){
                System.out.println("Ocorreu um erro de SQL "+e.getMessage());
            }
            return saida;
        }
        public ArrayList<String> Pesquisar(JComboBox jc){
            ArrayList<String> Valor = new ArrayList<>();
            try{

                PreparedStatement stmt = Conexao.prepareStatement("select * from tbl_inf where resposta like '!pesquisar%' or resposta like '!pasta%'");

                ResultSet rs = stmt.executeQuery();
                String resultado = null;
                jc.addItem("Jargo");
                Valor.add("Jargo");
                while (rs.next()){
//                    Valor.add(rs.getString("cod"));
//                    System.out.println(rs.getString("pergunta"));
                    jc.addItem(rs.getString("pergunta").replace("pesquisar ","").replace("no ", ""));
                    Valor.add(rs.getString("resposta"));
                }
            } catch(SQLException e){
                System.out.println("Ocorreu um erro de SQL "+e.getMessage());
            }
            return Valor;
        }
        
    public FormatarO traduzir(String palavra){
//        System.out.println("passou "+palavra);
        FormatarO ft = new FormatarO();
        ft.setPalavra(palavra);
        ft.setTraducao(palavra);
        
        try{
            String query = "select palavra, traducao from tbl_dicionario where palavra = ?";
            PreparedStatement stmt = Conexao.prepareStatement(query); 
            stmt.setString(1, palavra);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
                ft = new FormatarO();
                ft.setPalavra(rs.getString("palavra"));
                ft.setTraducao(rs.getString("traducao"));
            }
            
        } catch(SQLException e){
            System.out.println("Ocorreu um erro de SQL "+e.getMessage());
        }
        return ft;
    }
    
    public String inserirFormatacao(FormatarO ft){
        String retorno = "Já foi registrado!!";
        if(ftExiste(ft)){
            try{
                String query = "insert into tbl_dicionario (palavra, traducao) values(?, ?)";
                try (PreparedStatement stmt = Conexao.prepareStatement(query)) {
                    stmt.setString(1, ft.getPalavra());
                    stmt.setString(2, ft.getTraducao());
                    stmt.executeUpdate();
                    retorno = "Registrado com sucesso";
                }
            } catch(SQLException e){
                System.out.println("Ocorreu um erro de SQL "+e.getMessage());
            }
        }
        return retorno;
    }
    
    private boolean ftExiste(FormatarO entrada){
        boolean localizado = true;
        
        try{
            String query = "select codDicionario from tbl_dicionario where palavra = ?";
            PreparedStatement stmt = Conexao.prepareStatement(query); 
            stmt.setString(1, entrada.getPalavra());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                localizado = false;
                System.out.println("Já registrado!!");
                break;
            }
        } catch(SQLException e){
            System.out.println("Ocorreu um erro de SQL "+e.getMessage());
        }
        return localizado;
    }
        
        public void Reformar(){
            int contador = 1;
            Sql sql = new Sql();
            try{

                String query = "select * from tbl_inf where pergunta like ? COLLATE utf8_bin";
//                query = "select * from tbl_inf where peso <= ? and (pergunta like ? or pergunta like ? or pergunta like ? or pergunta like ?) order by peso desc";
                String add;
                String[] jjj = "abrir a porra do google".split(" ");
                query = "select * from tbl_inf where (pergunta like '%"+jjj[0]+"'";
                for(int i = 1; i < jjj.length; i++){
                    query += " or pergunta like '%"+jjj[i]+"'";
                }
                query += ")";
                System.out.println(query);
//                query = "select * from tbl_inf where pergunta like ? COLLATE utf8_bin";
//                query = 
//                        "select nome, data, lembrete " +
//                        "from tbl_usuario inner join tbl_lembrete " +
//                        "on cod = idusuario where cod = ?";
                PreparedStatement stmt = Conexao.prepareStatement(query); 
//                respTemp = "mensagem de boas vindas &";
//                System.out.println("respTemp = "+respTemp);
                
//                Array array = Conexao.createArrayOf("String", jjj);
//                stmt.setString(1, "ola");

                ResultSet rs = stmt.executeQuery();
                String resultado = null;
                Formatar ft = new Formatar();
                
                while (rs.next()){
                    contador ++;
//                    if(rs.getInt("cod")>= sql.cod()){
//                        System.out.println(rs.getInt("cod"));
//                        sql.alterar2(rs.getString("pergunta"),rs.getString("pergunta").replaceAll("pizaria","pizzaria"));
                    
//                    }
                
                    System.out.println(rs.getString("pergunta"));
                }
                System.out.println(contador);
            } catch(SQLException e){
                System.out.println("Ocorreu um erro de SQL "+e.getMessage());
            }
        }
        public static void main(String args[]) {
            Sql sql = new Sql();
            sql.conectar();
            sql.Reformar();
        }
}