package Juego;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Rama {
    
    public Image der, izq;
    public int x, y;
    public int []num;
    public Random rand;
    
    /**
     * Constructor de la clase Rama:
     * inicializa todo lo que tiene una rama, como lo
     * es: posicion en X y Y, las dos posibles imagenes
     * de la rama, un vector de enteros y un random. Y
     * llama al metodo generar_ramas.
     */
    public Rama(){
        
        num = new int[20];
        num[0] = 0;
        rand = new Random();
        try {
            der = ImageIO.read(new File("src/Imagenes/Juego/Rama.png"));
            izq = ImageIO.read(new File("src/Imagenes/Juego/RamaIzquierda.png"));
        } catch (IOException ex) {
            System.out.println("No se Encontro la Imagen...");
        }
        generar_ramas();
        
    }
    
    /**
     * Metodo generar_ramas:
     * inicializa las posiciones de un vector
     * de enteros los cuales serviran para saber que 
     * tipo de rama hay en cada pedazo de tronco. Los 
     * posibles valores son:
     * 0 = vacio.
     * 1 = rama en la parte izquierda.
     * 2 = rama en la parte derecha.
     */
    public void generar_ramas(){
        
        int aux = 0;
        int i = 1, band = 0;
        
        do{
            
            aux = rand.nextInt(3);
            
            if(i >= 2){
                
                if(num[i] == num[i-1] && num[i-1] == num[i-2]){
                    
                    band = 1;
                    
                }else{
                    
                    band = 0;
                    
                }
                
            }
            
            if(band == 0){
                
                if(num[i-1] == 0){
                
                    num[i] = aux;
                    i++;
                
                }else{
                
                    if(aux == 0){
                
                        num[i] = aux;
                        i++;
                
                    }else{
                
                        if(num[i-1] == aux){
                    
                            num[i] = aux;
                            i++;
                    
                        }
                
                    }
                
                }
                
            }else{
                
                if(aux == num[i-1]){
                    
                    
                    
                }else{
                    
                    if(num[i-1] == 0){
                
                        num[i] = aux;
                        i++;
                
                    }else{
                
                        if(aux == 0){
                
                            num[i] = aux;
                            i++;
                
                        }else{
                
                            if(num[i-1] == aux){
                    
                                num[i] = aux;
                                i++;
                    
                            }
                
                        }
                
                    }
                    
                }
                
            } 
            
        }while(i < 20);
        
    }
    
}
