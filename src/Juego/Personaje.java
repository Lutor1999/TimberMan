package Juego;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Personaje {
    
    public String puntos, nombre;
    public boolean vida, posicion;
    public Image der1, der2, der3, izq1, izq2, izq3, pint;
    public int x, y;
    
    /**
     * Constructor de la clase Personaje:
     * inicializa: la cantidad de puntos del personaje, el nombre,
     * si posee vida, si se encuentra ubicado a la derecha o a la 
     * izquierda del arbol, las posiciones X y Y. e inicializa todas
     * las imagenes a utilizar del jugador.
     */
    public Personaje(){
        
        puntos = "0";
        nombre = " ";
        vida = true;
        posicion = true;
        try {
            pint = ImageIO.read(new File("src/Imagenes/personaje2.png"));
            der1 = ImageIO.read(new File("src/Imagenes/personaje2.png"));
            der2 = ImageIO.read(new File("src/Imagenes/personaje3.png"));
            der3 = ImageIO.read(new File("src/Imagenes/personaje4.png"));
            izq1 = ImageIO.read(new File("src/Imagenes/personaje2inverso.png"));
            izq2 = ImageIO.read(new File("src/Imagenes/personaje3inverso.png"));
            izq3 = ImageIO.read(new File("src/Imagenes/personaje4inverso.png"));
        } catch (IOException ex) {
            System.out.println("No se Encontro la Imagen...");
        }
        x = 190;
        y = 420;
        
    }
    
}
