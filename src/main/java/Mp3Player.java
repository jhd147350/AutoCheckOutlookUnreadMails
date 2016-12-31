import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
//import javax.sound.sampled.

public class Mp3Player{
	//
	private String filename;
	private Player player;
	public Mp3Player(String filename) {
		this.filename=filename;
	}
	public void play(){
		try {
			BufferedInputStream in=new BufferedInputStream(new FileInputStream(filename));
			player=new Player(in);
			System.out.println("play");
			player.play();
			//player.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
		
	}
	public void stop(){
		if (player!=null) {
			player.close();
			System.out.println("close");
		}
	}
	
}
