package Menu;

import Juego.Frame;
import Sonido.Audio;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Pantalla extends JFrame{
    
    public Panel obj;
    public Audio sonido;
    public Timer sound;
    public int aux;
    
    /**
     * Constructor por defecto: 
     * instancia el objeto (obj) de tipo Panel, utiliza
     * los metodos de (obj), coloca el nombre del Juego, 
     * le da tamaño al JFrame, agrega (obj) al JFrame, etc.
     */
    public Pantalla(){
        
        obj = new Panel();
        sonido = new Audio();
        sound = new Timer(1000,aullido);
        aux = 5;
        
    }
    
    public void iniciar(){
        
        obj.agregar(true);
        obj.eventos();
        sound.start();
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setTitle("Timber Man");
        super.setBounds(0, 0, 840, 620);
        super.setLocationRelativeTo(null);
        super.setContentPane(obj);
        super.setLayout(new GridLayout(11,1));
        super.setResizable(false);
        super.setVisible(true);
        
    }
    
    /**
     * Metodo visibilidad: recibe un argumento de tipo 
     * boolean para desaparecer o aparecer el Menu del juego.
     */
    public void visibilidad(boolean visibilidad){
        
        super.setVisible(visibilidad);
        
    }  
    
    ActionListener aullido = new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            
            aux++;
            
            if(aux%10 == 0){
                
                try {
                    sonido.sonido(sonido.fondo);
                } catch (IOException ex) {
                    System.out.println("No se Encontro el Audio.");
                }
                
            }
            
        }
        
    };
    
}
