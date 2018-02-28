 package Sonido;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class Audio {
    
    public String fondo;
    
    public Audio(){
        
        fondo = "src/Audios/fondo.wav";
        
    }
    
    public void sonidoContinuo(String tipo){
        
        try {
            AudioData data = new AudioStream(new FileInputStream(tipo)).getData();
            ContinuousAudioDataStream BGM = new ContinuousAudioDataStream(data);
            AudioPlayer.player.start(BGM);
        } catch (IOException ex) {
            System.out.println("No se encontro el Audio...");
        }
        
    }
    
    public void sonido(String tipo) throws FileNotFoundException, IOException{
        
        InputStream in = new FileInputStream(tipo);
        AudioStream audio = new AudioStream(in);
        AudioPlayer.player.start(audio);
        
    }
    
}
