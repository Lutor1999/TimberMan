package Menu;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Animacion {
    
    public Image ppl, der, der1, izq, izq1, pint;
    
    public Animacion(){
        
        try {
            pint = ImageIO.read(new File("src/Imagenes/personaje1.png"));
            ppl = ImageIO.read(new File("src/Imagenes/personaje1.png"));
            der = ImageIO.read(new File("src/Imagenes/personaje2.png"));
            der1 = ImageIO.read(new File("src/Imagenes/personaje3.png"));
            izq = ImageIO.read(new File("src/Imagenes/personaje2inverso.png"));
            izq1 = ImageIO.read(new File("src/Imagenes/personaje3inverso.png"));
        } catch (IOException ex) {
            System.out.println("No se Encontro la Imagen...");
        }
        
    }
    
}
