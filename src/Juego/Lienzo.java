package Juego;

import Sonido.Audio;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Lienzo extends JPanel{
    
    public Image fondo, puntaje, barra, gameover;
    public int posx_punt, posy_punt, aux_pts, vidas, time, time_mov, i, mover;
    public int []num;    
    public Random rand;
    public Personaje jug;
    public Tronco pedazo;
    public Rama obj[];
    public Energia []vida;
    public Timer tiempo, mov, des, des1, bajar;
    public Audio sonido;
    public boolean live;
    public String muerte;
    
    /**
     * Constructor de la Clase Lienzo:
     * Inicializa todos los atributos de la clase, agrega los eventos,
     * llama a los metodos de esta misma clase (inicializar_vidas y 
     * generar_ramas) inicia los Timers correspondientes.
     */
    public Lienzo(){
        
        super.setFocusable(true);
        super.addMouseListener(salto);
        jug = new Personaje();
        pedazo = new Tronco();
        obj = new Rama[5];
        sonido = new Audio();
        live = true;
        for (int i = 0; i < obj.length; i++) {
            obj[i] = new Rama();
        }
        rand = new Random();
        vida = new Energia[41];
        for (int i = 0; i < vida.length; i++) {
            vida[i] = new Energia();
        }
        num = new int[500];
        num[0] = 0;
        mover = 0;
        tiempo = new Timer(90,barra_vida);
        mov = new Timer(50,mov_personaje);
        des = new Timer(50,desplazar);
        des1 = new Timer(50,desplazarizquierda);
        bajar = new Timer(50,bajada);
        posx_punt = 400;
        posy_punt = 232;
        i = 0;
        aux_pts = Integer.parseInt(jug.puntos);
        time = 0;
        time_mov = 0;
        vidas = 21;
        try {
            fondo = ImageIO.read(new File("src/Imagenes/Juego/Fondo.jpg"));
            puntaje = ImageIO.read(new File("src/Imagenes/Juego/Puntaje.png"));
            barra = ImageIO.read(new File("src/Imagenes/Juego/Barra.png"));
            gameover = ImageIO.read(new File("src/Imagenes/Juego/GameOver.png"));
        } catch (IOException ex) {
            System.out.println("No se Encontro la Imagen...");
        }
        this.setFont(this.getFont().deriveFont( 25.0f ));
        inicializar_vidas();
        generar_ramas();
        tiempo.start();
        mov.start();
        mov.stop();
        des.start();
        des.stop();
        repaint();
        
    }
    
    ActionListener bajada = new ActionListener(){
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            for (int j = 0; j < obj.length; j++) {
                
                obj[j].y += 105;
                
            }
            
            repaint();
            bajar.stop();
            
        }

    };    
    /**
     * Escuchadora de Accion (desplazar):
     * Genera el efecto de desplazamiento del pedazo de tronco cortado,
     * cada vez que el usuario da click en el arbol por la parte izquierda.
     */
    ActionListener desplazar = new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            
            mover++;
            
            if(mover <= 5){
                
                pedazo.x += 60;
                repaint();
                
            }
            
            if(mover > 5){
                
                pedazo.x = 1000;
                pedazo.cortado = pedazo.vacio;
                pedazo.x = 348;
                repaint();
                mover = 0;
                des.stop();
                
            }    
            
        }
        
    };
    
    /**
     * Escuchadora de Accion (desplazarizquierda):
     * Genera el efecto de desplazamiento del pedazo de tronco cortado,
     * cada vez que el usuario da click en el arbol por la parte derecha.
     */
    ActionListener desplazarizquierda = new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            
            mover++;
            
            if(mover <= 5){
                
                pedazo.x -= 70;
                repaint();
                
            }
            
            if(mover > 5){
                
                pedazo.x = -1000;
                pedazo.cortado = pedazo.vacio;
                pedazo.x = 348;
                repaint();
                mover = 0;
                des1.stop();
                
            }    
            
        }
        
    };
    
    /**
     * Metodo colocar_ramas:
     * agarra los valores del vector num[] y segun dicho valor
     * se asigna una rama, posibles valores de num[]:
     * 0 = asigna una rama vacia;
     * 1 = asigna una rama derecha;
     * 2 = asigna una rama izquierda;.
     */
    public void colocar_ramas(){
         
        int aux1 = 0;
        
        do{
            
            switch(num[aux1]){
                
                case 0:
                    
                    obj[aux1].rama = obj[aux1].vacia;
                    obj[aux1].x = 220;
                    obj[aux1].y -= 100*aux1;
                    break;
                
                case 1:
                    
                    obj[aux1].rama = obj[aux1].der;
                    obj[aux1].x = 368;
                    obj[aux1].y -= 100*aux1;
                    break;
                    
                case 2:
                    
                    obj[aux1].rama = obj[aux1].izq;
                    obj[aux1].x = 198;
                    obj[aux1].y -= 100*aux1;
                    break;
                    
                default:
                    
            }
            
            aux1++;
            
        }while(aux1 < obj.length);
        
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
                live = false;
                muerte = "Energia.";
                repaint();
                
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
    
    /**
     * Escuchadora de Accion (mov_personaje):
     * Hace el efecto de que el leñador se esta
     * moviendo para cortar el arbol.
     */
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
            
            colocar_ramas();
            
            if(jug.posicion){
                
                if(e.getX() < 410){
                    
                    try {
                        sonido.sonido(sonido.hachazo);
                    } catch (IOException ex) {
                        System.out.println("No se Encontro el Audio...");
                    }
                    
                    jug.posicion = true;
                    aux_pts++;
                    mov.restart();
                    
                    switch(num[i]){
                        
                        case 0:
                            
                            pedazo.cortado = pedazo.vacioder;
                            des.restart();
                            break;
                            
                        case 1:
                            
                            pedazo.cortado = pedazo.der;
                            des.restart();
                            break;
                            
                        case 2:
                            
                            pedazo.cortado = pedazo.izq;
                            des.restart();
                            break;
                            
                        default:
                            
                    }
                    
                    bajar.start();
                    i++;
                    
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
                    
                    try {
                        sonido.sonido(sonido.hachazo);
                    } catch (IOException ex) {
                        System.out.println("No se Encontro el Audio...");
                    }
                    bajar.start();
                    jug.posicion = false;
                    aux_pts++;
                    mov.restart();
                    
                    switch(num[i]){
                        
                        case 0:
                            
                            pedazo.cortado = pedazo.vacioder;
                            des1.restart();
                            break;
                            
                        case 1:
                            
                            pedazo.cortado = pedazo.der;
                            des1.restart();
                            break;
                            
                        case 2:
                            
                            pedazo.cortado = pedazo.izq;
                            des1.restart();
                            break;
                            
                        default:
                            
                    }
                    
                    i++;
                    
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
        
        if(live){
        
            g2.drawImage(this.puntaje, 380, 207, this);
            g2.drawString(this.jug.puntos, this.posx_punt, this.posy_punt);
            g2.drawImage(this.jug.pint, this.jug.x, this.jug.y, this);
            g2.drawImage(this.barra, 323, 120, this);
            
            for (int i = 0; i < obj.length; i++) {
                
                g2.drawImage(this.obj[i].rama, this.obj[i].x , this.obj[i].y, this);
                
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
        
            g2.drawString(jug.nombre, 485, 50);
            
        }
        
        else{
            
            g2.drawImage(this.gameover, 250, 150, this);
            g2.drawString(muerte, 420, 292);
            g2.drawString(jug.nombre, 425, 327);
            g2.drawString(jug.puntos, 465, 360);
            
        }
            
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
            
        }while(i < 500);
        
    }
    
}
