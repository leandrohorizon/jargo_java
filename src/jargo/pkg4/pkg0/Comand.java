/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jargo.pkg4.pkg0;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;

/**
 *
 * @author User
 */
public class Comand {
    public String result (String resp){
        String resposta = resp;
//        resp.startsWith
            
        if(resp.contains("!adm ")){
            resp = resp.replaceAll("!adm ", "");
        }
        
        if(resp.contains("!dt")){
            String resultado = resp.replaceAll("!dt ", "");
            DataHora dt = new DataHora();
            for(int i = 0; i < 5; i++)
                resultado = dt.DT(resultado);            
            resp = resultado;
            
        }else if(resp.contains("!exec")){
            String resultado = resp.replaceAll("!exec ", "");
            String caminho2 = resultado.replaceAll(" \\(x86\\)", "");
            try{
                Runtime.getRuntime().exec(resultado);
                resp = "Executando ação";
                }catch(Exception e){
                        System.out.println(e);
                        try {
                            Runtime.getRuntime().exec(caminho2);
                            resp = "Executando ação";
                        } catch (IOException ex) {
                            resp = "Comando invalido !!";
                        }
                        
                }
            
        }else if(resp.contains("!site")){
            String resultado = resp.replaceAll("!site ", "");
            try{
                java.awt.Desktop.getDesktop().browse( new java.net.URI( resultado ) );
                resp = "Abrindo site";
            }catch(IOException e){
                System.out.println(e);
            }catch(URISyntaxException ex){
                System.out.println(ex);
            }
            
        }else if(resp.contains("!consulta")){
            JframeConsulta fcon = new JframeConsulta();
            fcon.setVisible(true);
            resp = "Abrindo janela de consulta";
            
        }else if(resp.contains("!arquivo")){
            String resultado = resp.replaceAll("!arquivo ", "");
            try{
                ArqExis ae = new ArqExis();            
                resp = ae.ex(resultado);
            }catch(IOException e){
                System.out.println(e);
            }
        }else if(resp.contains("!printscreen")){
            int y = Toolkit.getDefaultToolkit().getScreenSize().width;
            int x = Toolkit.getDefaultToolkit().getScreenSize().height;
            try{
                Robot Robot = new Robot();
                BufferedImage p = Robot.createScreenCapture( new Rectangle (y,x));
                DataHora dh = new DataHora();
                Formatar ft = new Formatar();
                ImageIO.write(p, "jpg", new File(System.getProperty("user.home")+"\\Desktop\\"+ft.Formatar(dh.DT("!dia!mesn!ano !horas !segundos"))+".jpg"));
                
            }catch(Exception e){
                e.printStackTrace();
            }
            
            
            resp = "Capturando tela !!";
        }
        
        
        if(resposta.contains("!ln"))
            resp ="\n"+resp.replaceAll(" !ln ", "\n");
        
        return resp;
        
        
    }
}
