package jhd.config;

/**
 * some config Outllook url etc.
 * 
 * @author jiahaodong
 *
 */
//TODO 该类存一些配置变量
public class Config {

	//TODO 以后改用 service.autodiscoverUrl
	// 21V 用到的企业邮箱url
	public static String OUTLOOK_URL = "https://mail.21vianet.com/EWS/Exchange.asmx";
	
	// 默认刷新间隔90  
	public static int INTERVAL = 90;

	//默认MP3路径为空，应由程序启动后从配置文件中读取
	public static String MP3_PATH = "";

	

}
