package Juego;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tronco {
    
    public int x, y;
    public boolean rama;
    public Image cortado, vacio, der, izq, vacioder, vacioizq;
    
    /**
     * Constructor de la clase Tronco:
     * inicializa: la imagen a utilizar como un tronco,
     * las posiciones X y Y donde se va a situar el tronco y
     * un asigna false al atributo rama que quiere decir que no 
     * posee una rama.
     */
    public Tronco(){
        
        x = 348;
        y = 462;
        rama = false;
        try {
            
            cortado = ImageIO.read(new File("src/Imagenes/Juego/Tronco.png"));
            vacio = ImageIO.read(new File("src/Imagenes/Juego/Tronco.png"));
            vacioder = ImageIO.read(new File("src/Imagenes/Juego/CortadoDerecha.png"));
            vacioizq = ImageIO.read(new File("src/Imagenes/Juego/CortadoIzquierda.png"));
            der = ImageIO.read(new File("src/Imagenes/Juego/CortadoRamaDerecha.png"));
            izq = ImageIO.read(new File("src/Imagenes/Juego/CortadoRamaIzquierda.png"));
            
        } catch (IOException ex) {
            System.out.println("No se Encontro la Imagen...");
        }
        
    }
    
}
