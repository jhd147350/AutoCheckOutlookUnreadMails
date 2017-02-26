package jhd.tool;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import jhd.Config;
import jhd.ui.StatusBar;

public class Mp3Player {
	//
	private String filename;
	private Player player;
	private boolean playing = false;

	public Mp3Player() {
	}

	public void play() {
		if (playing) {
			System.out.println("playing");
			return;
		}
		playing = true;
		try {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(Config.MP3_PATH));
			player = new Player(in);
			System.out.println("play");
			//StatusBar.currentStatus.setText("playing");
			player.play();
			in.close();
			player.close();
			StatusBar.currentStatus.setText("playing over");
			// player.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			StatusBar.currentStatus.setText("File not Found!");
		} catch (JavaLayerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		playing = false;

	}

	public void stop() {
		if (player != null) {
			player.close();
			StatusBar.currentStatus.setText("playing stop");
			System.out.println("close");
		}
	}

}
