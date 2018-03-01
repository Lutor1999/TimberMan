package Juego;

import java.awt.GridLayout;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Frame extends JFrame{
    
    public Lienzo obj;
    
    /**
     * Constructor de Frame:
     * Inicializa un objeto de tipo Lienzo que sera el encargado 
     * de contener todos los componentes.
     * @param nombre sirve para asignar el nombre del jugador a un objeto 
     * de la clase Personaje.
     */
    public Frame(String nombre){
        
        obj = new Lienzo();
        obj.jug.nombre = nombre;
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setTitle("Timber Man");
        super.setBounds(0, 0, 840, 620);
        super.setLocationRelativeTo(null);
        super.setContentPane(obj);
        super.setLayout(new GridLayout(11,1));
        super.setResizable(false);
        super.setVisible(true);
        
    }
    
}
