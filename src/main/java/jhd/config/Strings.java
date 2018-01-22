package jhd.config;

/**
 * 一些字符串
 * 
 * @author jiahaodong
 *
 */
public interface Strings {
	public static final String APP_NAME = "Auto check unread mails";

	public static final String VERSION = "V1.2";

	public static final String ABOUT_PROJECT = "github源代码";

	public static final String GITHUB = "https://github.com/jhd147350/UnreadMails4Outlook";

	public static final String SAVE_OK = "保存成功且生效";
	public static final String SAVE_FAIL = "保存失败";
	public static final String SAVE_FAIL_MP3_NOT_FOUND = "保存失败,mp3路径出错";

	// 密码文件路径
	public static final String PASSWORD_PATH = "password.txt";
	// 设置文件路径
	public static final String SETTING_PATH = "setting.txt";

	// 21V 用到的企业邮箱url
	public static final String OUTLOOK_URL = "https://mail.21vianet.com/EWS/Exchange.asmx";

	// 刷新间隔 保存的字段名称
	public static final String INTERVAL_STR = "INTERVAL";

	// 用于提示音的MP3文件目录 保存的字段名称
	public static final String MP3_PATH_STR = "MP3_PATH";

}
