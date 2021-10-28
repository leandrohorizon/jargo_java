package Menu;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Botoes {
    URL url     = this.getClass().getResource("/res/fechar.png");  
    URL url2    = this.getClass().getResource("/res/mini.png");  
    URL url3    = this.getClass().getResource("/res/menu.png");
//    URL t = this.getClass().getClassLoader().getResource("");
    
    public Image[] trocarCor(Color cor){
        Image[] saida = new Image[3];
        try {
            BufferedImage[] imga = new BufferedImage[]{
                ImageIO.read( new File(url.toURI())),
                ImageIO.read( new File(url2.toURI())),
                ImageIO.read( new File(url3.toURI()))
            };
//            File destino = new File(url.toURI());
            
            int i = 0;
            for(BufferedImage img : imga){
                for(int y = 0; y < img.getHeight(); y++){
                    for(int x = 0; x < img.getWidth();x++){
                        Color color1 = new Color(img.getRGB(x,y));
                        if(!color1.equals((Color.WHITE)))
                            img.setRGB(x,y,cor.getRGB());
                    }
                }
                saida[i] = img.getScaledInstance(18, 18, Image.SCALE_SMOOTH);
//              ImageIO.write(img, "png", new File(System.getProperty("user.home")+"\\Desktop\\result"+(i++)+".jpg"));
                
//                File destino2 = new File(destino.getParent()+"/result"+(i++)+".png");
//                System.out.println(destino2.getAbsolutePath());
//                ImageIO.write(img, "png", destino2);
                i++;
            }
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
        }
        return saida;
    }
    public static void main(String[] args){
        File file = new File("/res/fechar.png");
        System.out.println(file.getAbsoluteFile());
    }
}