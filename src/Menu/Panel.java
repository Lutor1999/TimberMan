package Menu;

import Juego.Frame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class Panel extends JPanel{
    
    public Image fondo, ayuda, creditos, regresar, boton1, boton2;
    public int evento, x, y, time, band, tipo;
    public Boton obj;
    public Animacion personaje;
    public JButton []vacio;
    public Timer tiempo;
   
    /**
     * Constructor por defecto: instacia los atributos de la clase.
     */
    public Panel(){
        
        try {
            fondo = ImageIO.read(new File("src/Imagenes/Fondo.jpg"));
            ayuda = ImageIO.read(new File("src/Imagenes/Ayuda.png"));
            creditos = ImageIO.read(new File("src/Imagenes/Creditos.jpg"));
            regresar = ImageIO.read(new File("src/Imagenes/boton1.png"));
            boton1 = ImageIO.read(new File("src/Imagenes/boton1.png"));
            boton2 = ImageIO.read(new File("src/Imagenes/boton2.png"));
        } catch (IOException e) {
            System.out.println("No se Encontro la Imagen...");
        }
        evento = 0;
        obj = new Boton();
        personaje = new Animacion();
        vacio = new JButton [4];
        for (int i = 0; i < vacio.length; i++) {
            
            vacio[i] = new JButton();
            super.add(vacio[i]);
            vacio[i].setVisible(false);
            
        }
        tiempo = new Timer(250,movimiento);
        x = 190;
        y = 460;
        band = 1;
        time = 0;
        tipo = 1;
        super.setFocusable(true);
        tiempo.start();
        super.addMouseListener(boton);
        
    }
    
    /**
     * Metodo agregar: 
     * posiciona los JLabel'S en la pantalla y los 
     * añade al JFrame. Ademas inicia el Timer del 
     * movimento del personaje.
     */
    public void agregar(boolean si){
        
        if(si){
            
            obj.jugar.setBounds( 0, 0, obj.jugar.getWidth(), obj.jugar.getHeight());
            super.add(obj.jugar);
            obj.ayuda.setBounds(0, 0, obj.ayuda.getWidth(), obj.ayuda.getHeight());
            super.add(obj.ayuda);
            obj.creditos.setBounds(0, 0, obj.creditos.getWidth(), obj.creditos.getHeight());
            super.add(obj.creditos);
            obj.salir.setBounds(0, 0, obj.salir.getWidth(), obj.salir.getHeight());
            super.add(obj.salir);
            repaint();
            
        }
        
    }
    
    /**
     * Escuchadora de ActionListener "movimiento":
     * Cada N tiempo cambia la imagen del personaje
     * para hacer la animacion de que esta cortando.
     */
    ActionListener movimiento = new ActionListener(){
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            time++;
            
            if(tipo == 4){
                
                tipo = 1;
                band *= -1;
                
            }
            
            if(band == 1){
                
                x = 190;
                
                if(tipo <= 3){
                
                    if(time%3 == 0){
                
                        switch(tipo){
                        
                            case 1:
                                y = 460;
                                personaje.pint = personaje.ppl;
                                break;
                            case 2:
                                y = 420;
                                personaje.pint = personaje.der;
                                break;
                            case 3:
                                y = 460;
                                personaje.pint = personaje.der1;
                                break;
                            default:
                            
                        }
                        repaint();
                        tipo++;
                
                    }
                
                }
                
            }else{
                
                x = 505;
                
                if(tipo <= 3){
                
                    if(time%3 == 0){
                
                        switch(tipo){
                        
                            case 1:
                                y = 460;
                                personaje.pint = personaje.ppl;
                                break;
                            case 2:
                                y = 420;
                                personaje.pint = personaje.izq;
                                break;
                            case 3:
                                y = 460;
                                personaje.pint = personaje.izq1;
                                break;
                            default:
                            
                        }
                        repaint();
                        tipo++;
                
                    }
                
                }
                
            }
            
        }

    };
     
    /**
     * Metodo paintComponent: 
     * Establece la imagen (fondo) como fondo de pantalla y 
     * coloca el personaje.
     */
    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g);         
        Graphics2D g2 = (Graphics2D) g;
        
        switch(evento){
            
            case 0:
                g2.drawImage(this.fondo,0,0,this);
                g2.drawImage(personaje.pint, x, y, this);
                break;
            case 1:
                break;
            case 2:
                g2.drawImage(ayuda, 0, 0, this);
                g2.drawImage(regresar, 700, 450, this);
                break;
            case 3:
                g2.drawImage(creditos, 0, 0, this);
                g2.drawImage(regresar, 700, 450, this);
                break;
            default:
            
        }
            
    }
    
    MouseListener boton = new MouseListener(){

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getX() >= 700 && e.getX() <= 700 + boton1.getWidth(null) && e.getY() >= 450 && e.getY() <= 450 + boton1.getHeight(null)){
                
                evento = 0;
                obj.ayuda.setVisible(true);
                obj.creditos.setVisible(true);
                obj.jugar.setVisible(true);
                obj.salir.setVisible(true);
                repaint();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
           
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }

    };
    
    /**
     * Metodo eventos: asigna un entero que dependiendo de su valor, ocurrio algun evento.
     * 0 = no ocurrio nada.
     * 1 = pulsaron boton Jugar.
     * 2 = pulsaron boton Ayuda.
     * 3 = pulsaron boton Creditos.
     * 4 = pulsaron boton Salir.
     */
    public void eventos(){
        
        obj.jugar.addMouseListener(new MouseAdapter(){
            
            public void mouseEntered(MouseEvent e){
                
                obj.band = 2;
                obj.jugar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Jugar" + obj.band +".jpg")));
                
            }
            
            public void mouseExited(MouseEvent e){
                
                obj.band = 1;
                obj.jugar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Jugar" + obj.band +".jpg")));
                
            }
            
            public void mouseClicked(MouseEvent e){
                
                setVisible(false);
                Frame obj = new Frame();
                
            }
            
        });
        
        obj.ayuda.addMouseListener(new MouseAdapter(){
            
            public void mouseEntered(MouseEvent e){
                
                obj.band = 2;
                obj.ayuda.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Ayuda" + obj.band +".jpg")));
                
            }
            
            public void mouseExited(MouseEvent e){
                
                obj.band = 1;
                obj.ayuda.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Ayuda" + obj.band +".jpg")));
                
            }
            
            public void mouseClicked(MouseEvent e){
                
                obj.ayuda.setVisible(false);
                obj.creditos.setVisible(false);
                obj.jugar.setVisible(false);
                obj.salir.setVisible(false);
                evento = 2;
                repaint();
                
            }
            
        });
        
        obj.creditos.addMouseListener(new MouseAdapter(){
            
            public void mouseEntered(MouseEvent e){
                
                obj.band = 2;
                obj.creditos.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Creditos" + obj.band +".jpg")));
                
            }
            
            public void mouseExited(MouseEvent e){
                
                obj.band = 1;
                obj.creditos.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Creditos" + obj.band +".jpg")));
                
            }
            
            public void mouseClicked(MouseEvent e){
                
                obj.ayuda.setVisible(false);
                obj.creditos.setVisible(false);
                obj.jugar.setVisible(false);
                obj.salir.setVisible(false);
                evento = 3;
                repaint();
                
            }
            
        });
        
        obj.salir.addMouseListener(new MouseAdapter(){
            
            public void mouseEntered(MouseEvent e){
                
                obj.band = 2;
                obj.salir.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Salir" + obj.band +".jpg")));
                
            }
            
            public void mouseExited(MouseEvent e){
                
                obj.band = 1;
                obj.salir.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Salir" + obj.band +".jpg")));
                
            }
            
            public void mouseClicked(MouseEvent e){
                
                System.exit(0);
                
            }
            
        });
        
    }
    
}
