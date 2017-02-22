package studio.jhd;

/**
 * some config Outllook url etc.
 * 
 * @author jiahaodong
 *
 */
public interface Config {

	public static String OUTLOOK_URL = "https://mail.21vianet.com/EWS/Exchange.asmx";
	// 默认刷新间隔30s
	public static int INTERVAL = 10;

	// 用于提示音的MP3文件目录
	public static String MP3_PATH = "contra.mp3";
	
	public static String PASSWORD_PATH="password.txt";
	
}
