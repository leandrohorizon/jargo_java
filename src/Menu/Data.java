/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author User
 */
public class Data implements java.io.Serializable{
    
    private Color cor1 = new Color(-14737374);
    private Color cor2 = new Color(-14210778);
    private Color corFonte = new Color(-262145);
    
    private boolean respostaDireta = false;
    private boolean sobreTela = true;
    
    private final String caminho = "C:\\Users\\User\\Desktop\\N0d_v01P\\config.txt";
    public Data(){
        Path path = Paths.get(caminho);
        try{
            if(!Files.exists(path)){
                Salvar();
            }else{
                FileInputStream fis = new FileInputStream(caminho);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Data conf = (Data)ois.readObject();

                this.cor1 = conf.cor1;
                this.cor2 = conf.cor2;
                this.corFonte = conf.corFonte;
                this.respostaDireta = conf.respostaDireta;
                this.sobreTela = conf.sobreTela;
                ois.close();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public void Salvar(){
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(caminho);
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(this);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public Color getCor1() {
        return cor1;
    }

    public void setCor1(Color cor1) {
        this.cor1 = cor1;
    }

    public Color getCor2() {
        return cor2;
    }

    public void setCor2(Color cor2) {
        this.cor2 = cor2;
    }

    public Color getCorFonte() {
        return corFonte;
    }

    public void setCorFonte(Color corFonte) {
        this.corFonte = corFonte;
    }

    public boolean isRespostaDireta() {
        return respostaDireta;
    }

    public void setRespostaDireta(boolean repostaDireta) {
        this.respostaDireta = repostaDireta;
    }

    public boolean isSobreTela() {
        return sobreTela;
    }

    public void setSobreTela(boolean sobreTela) {
        this.sobreTela = sobreTela;
    }
}