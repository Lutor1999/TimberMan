package Menu;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Boton {
    
    int band;
    public JLabel jugar, ayuda, creditos, salir;
    
    /**
     * Constructor de la clase Boton:
     * Inicializa los JLabel's como imagenes, las cuales
     * van a ser los botones e inicializa una bandera que 
     * se utilizara para hacer cambiar la imagen de un 
     * JLabel en especifico.
     */
    public Boton(){
        
        band = 1;
        jugar = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/Jugar" + band +".jpg")));
        ayuda = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/Ayuda" + band +".jpg")));
        creditos = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/Creditos" + band +".jpg")));
        salir = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/Salir" + band +".jpg")));
        
    }
    
}
