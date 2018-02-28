package Juego;

import java.awt.GridLayout;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Frame extends JFrame{
    
    public Lienzo obj;
    
    public Frame(){
        
        obj = new Lienzo();
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
