package Juego;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Lienzo extends JPanel{
    
    public Image fondo, puntaje, barra;
    public int posx_punt, posy_punt, aux_pts, vidas, time, time_mov; 
    public Personaje jug;
    public Tronco pedazo;
    public Rama obj[];
    public Energia []vida;
    public Timer tiempo, mov;
    
    public Lienzo(){
        
        super.setFocusable(true);
        super.addMouseListener(salto);
        jug = new Personaje();
        pedazo = new Tronco();
        obj = new Rama[5];
        for (int i = 0; i < obj.length; i++) {
            obj[i] = new Rama();
        }
        vida = new Energia[41];
        for (int i = 0; i < vida.length; i++) {
            vida[i] = new Energia();
        }
        tiempo = new Timer(90,barra_vida);
        mov = new Timer(50,mov_personaje);
        posx_punt = 400;
        posy_punt = 232;
        aux_pts = Integer.parseInt(jug.puntos);
        time = 0;
        time_mov = 0;
        vidas = 21;
        try {
            fondo = ImageIO.read(new File("src/Imagenes/Juego/Fondo.jpg"));
            puntaje = ImageIO.read(new File("src/Imagenes/Juego/Puntaje.png"));
            barra = ImageIO.read(new File("src/Imagenes/Juego/Barra.png"));
        } catch (IOException ex) {
            System.out.println("No se Encontro la Imagen...");
        }
        this.setFont(this.getFont().deriveFont( 25.0f ));
        inicializar_vidas();
        tiempo.start();
        mov.start();
        mov.stop();
        repaint();
        
    }
    
    /**
     * Metodo imprimir_puntaje:
     * acomoda visualmente la posicion X en el Lienzo 
     * del string que contiene los puntos del jugador.
     */
    public void imprimir_puntaje(){ 
        
        if(aux_pts < 10){
            
            posx_punt = 400;
            
        }else if(aux_pts >= 10 && aux_pts < 100){
            
            posx_punt = 393;
            
        }else{
            
            posx_punt = 387;
            
        }
        
    }
    
    /**
     * Metodo inicializar_vidas:
     * coloca una cantidad n(21) de energías que va a poseer
     * el personaje inicialmente y las coloca en estado verdadero
     * cada una de ellas.
     */
    public void inicializar_vidas(){
        
        for (int i = 0; i < vidas; i++) {
            vida[i].estado = true;
        }
        
    }
    
    /**
     * Escuchadora de Accion (barra_vida):
     * se utiliza como cronometro para que cada X(500 milisegundos) 
     * tiempo el personaje pierda una Energia y si llega a cero(0) acabe el 
     * juego.
     */
    ActionListener barra_vida = new ActionListener(){
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            time++;
            
            if(vidas == 0){
                
                tiempo.stop();
                jug.vida = false;
                System.exit(0);
                
            }
            
            if(time%5 == 0){
                
                vidas--;
                for (int i = vidas; i < vida.length; i++) {
                    vida[i].estado = false;
                }
                repaint();
                
            }
            
        }

    };
    
    ActionListener mov_personaje = new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            
            time_mov++;
            
            if(time_mov <= 3 && jug.posicion){
                
                switch(time_mov){
                
                    case 1:
                        jug.y = 420;
                        jug.pint = jug.der1;
                        repaint();
                        break;
                    case 2:
                        jug.y = 460;
                        jug.pint = jug.der2;
                        repaint();
                        break;
                    case 3:
                        jug.y = 460;
                        jug.pint = jug.der3;
                        repaint();
                        break;
                    default:
                
                }
                
                if(time_mov == 3){
                    
                    time_mov = 0;
                    mov.stop();
                    jug.y = 420;
                    jug.pint = jug.der1;
                    repaint();
                    
                }
                
            }
            
            if(time_mov <= 3 && !jug.posicion){
                
                switch(time_mov){
                
                    case 1:
                        jug.y = 420;
                        jug.pint = jug.izq1;
                        repaint();
                        break;
                    case 2:
                        jug.y = 460;
                        jug.pint = jug.izq2;
                        repaint();
                        break;
                    case 3:
                        jug.y = 460;
                        jug.pint = jug.izq3;
                        repaint();
                        break;
                    default:
                
                }
                
                if(time_mov == 3){
                    
                    time_mov = 0;
                    mov.stop();
                    jug.y = 420;
                    jug.pint = jug.izq1;
                    repaint();
                    
                }
                
            }
            
        }
        
    };
    
    /**
     * Escuchadora de Mouse (salto):
     * Cada vez que el usuario da un click en determinado
     * punto del JFrame esta se acciona. Puede:
     * -Cambiar de Posicion al jugador.
     * -Sumar puntos / cortar una rama.
     * -Aumentar la barra de Energia del jugador.
     */
    MouseListener salto = new MouseListener(){
        
        @Override
        public void mouseClicked(MouseEvent e) {
            
            if(jug.posicion){
                
                if(e.getX() < 410){
                
                    jug.posicion = true;
                    aux_pts++;
                    mov.restart();
                    pedazo.x += 200;
                
                }else{
                    
                    jug.posicion = false;
                    jug.pint = jug.izq1;
                    jug.x = 505;
                    
                }
                
            }else{
                
                if(e.getX() < 410){
                
                    jug.posicion = true;
                    jug.pint = jug.der1;
                    jug.x = 190;
                
                }else{
                    
                    jug.posicion = false;
                    aux_pts++;
                    mov.restart();
                    pedazo.x -= 200;
                    
                } 
                
            }
            
            if(aux_pts%5 == 0){
                
                if(vidas <= 38){
                    
                    vidas += 3;
                    for (int i = 0; i < vidas; i++) {
                        vida[i].estado = true;
                    }
                    
                }
                
            }
            
            jug.puntos = Integer.toString(aux_pts);
            imprimir_puntaje();
            repaint();
            
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
     * Metodo paintComponent:
     * imprime en pantalla un jugador, su puntaje, el tronco,
     * el fondo del juego, la barra de energia del jugador y
     * las ramas.
     */
    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g);         
        Graphics2D g2 = (Graphics2D) g;
        
        g2.drawImage(this.fondo, 0, 0, this);
        g2.drawImage(this.pedazo.cortado, this.pedazo.x, this.pedazo.y, this);
        g2.drawImage(this.puntaje, 380, 207, this);
        g2.drawString(this.jug.puntos, this.posx_punt, this.posy_punt);
        g2.drawImage(this.jug.pint, this.jug.x, this.jug.y, this);
        g2.drawImage(this.barra, 323, 120, this);
        for (int i = 0; i < obj.length; i++) {
            g2.drawImage(this.obj[i].der, 467 , 10 + (this.pedazo.cortado.getHeight(null)*(i)), this);
        }
        for (int i = 0; i < vida.length; i++) {
            if(vida[i].estado){
                if(i > 0){
                    g2.drawImage(vida[i].img, vida[i].x + ((i-1)*vida[i].img.getWidth(null)), vida[i].y, this);
                }else{
                    g2.drawImage(vida[i].img, vida[i].x, vida[i].y, this);
                }
            }
        }
            
    }
    
}
