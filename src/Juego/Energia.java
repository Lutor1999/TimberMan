package Juego;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class Energia {
    
    public Image img;
    public int x, y;
    public boolean estado;
    
    /**
     * Constructor de la clase Energia:
     * inicializa: la imagen que va a representar cada energia,
     * las posiciones X y Y, inicializa el estado de la energia.
     */
    public Energia(){
        
        try {
            img = ImageIO.read(new File("src/Imagenes/Juego/vida.png"));
        } catch (IOException ex) {
            System.out.println("No se Encontro la Imagen...");
        }
        x = 327;
        y = 125;
        estado = false;
        
    }
    
}
