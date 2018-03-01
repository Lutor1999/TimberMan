package Juego;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Rama {
    
    public Image der, izq, rama, vacia;
    public int x, y;
    
    /**
     * Constructor de la clase Rama:
     * inicializa todo lo que tiene una rama, como lo
     * es: posicion en X y Y, las dos posibles imagenes
     * de la rama, un vector de enteros y un random. Y
     * llama al metodo generar_ramas.
     */
    public Rama(){
        
        try {
            rama = ImageIO.read(new File("src/Imagenes/Juego/RamaVacia.png"));
            vacia = ImageIO.read(new File("src/Imagenes/Juego/RamaVacia.png"));
            der = ImageIO.read(new File("src/Imagenes/Juego/Rama.png"));
            izq = ImageIO.read(new File("src/Imagenes/Juego/RamaIzquierda.png"));
        } catch (IOException ex) {
            System.out.println("No se Encontro la Imagen...");
        }
        
    }
    
}
