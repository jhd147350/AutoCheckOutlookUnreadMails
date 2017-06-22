package jhd.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import jhd.account.Account;
import jhd.config.Config;
import jhd.config.Constant;

public class FileTool {

	//获取用户
	public static List<Account> readUsers() {

		// HashMap<String, String> m=new HashMap<String,String>();
		List<Account> accounts = new ArrayList<Account>();
		File file = new File(Constant.PASSWORD_PATH);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Properties p = new Properties();
		try {

			p.load(new FileInputStream(file));
			// FIRSTRUN = Boolean.parseBoolean(p.getProperty(FIRSTRUN_STRING));
			Set<Object> keySet = p.keySet();
			//Object[] array = keySet.toArray();
			for (Object o : keySet) {
				accounts.add(new Account(o.toString(), p.get(o).toString()));
				// m.put(o.toString(), p.get(o).toString());
				/*
				 * System.out.println(o); System.out.println(p.get(o));
				 */
			}
			// p.get(array)
			// p.getProperty(keySet.)
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println(users.toString());
		return accounts;
	}

	// if password is null then remove the email from list, otherwise add a new
	// email to the list 写入新用户
	public static void writeUsers(String email, String password) {

		File file = new File(Constant.PASSWORD_PATH);
		// file.

		Properties p = new Properties();
		try {
			// load the previous data
			FileInputStream input = new FileInputStream(file);
			p.load(input);
			input.close();

			// overwrite the previous data
			FileOutputStream output = new FileOutputStream(file);
			// p.put(FIRSTRUN_STRING, "false");
			// p.setProperty("phone22", "123456");
			// p.put(USERNAME_STRING, USERNAME);
			// p.put(PASSWORD_STRING, PASSWORD);
			if (password == null) {
				p.remove(email);
			} else {
				p.put(email, password);
			}

			p.store(output, "账号信息如下");
			output.close();
			System.out.println("Your info has been saved!");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initConfigFromFile() {

		// HashMap<String, String> m=new HashMap<String,String>();
		//List<User> users = new ArrayList<User>();
		File file = new File(Constant.SETTING_PATH);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Properties p = new Properties();
		try {

			p.load(new FileInputStream(file));
			// String property = p.getProperty(Config.INTERVAL_STR,
			// Config.INTERVAL + "");
			System.out.println("初始化配置");
			Config.INTERVAL = Integer.parseInt((String) p.getOrDefault(Constant.INTERVAL_STR, Config.INTERVAL+""));
			Config.MP3_PATH = (String) p.getOrDefault(Constant.MP3_PATH_STR, Config.MP3_PATH);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void saveConfigToFile() {


		File file = new File(Constant.SETTING_PATH);
		// file.

		Properties p = new Properties();
		try {
			// load the previous data
			FileInputStream input = new FileInputStream(file);
			p.load(input);
			input.close();

			// overwrite the previous data
			FileOutputStream output = new FileOutputStream(file);
			// p.put(FIRSTRUN_STRING, "false");
			// p.setProperty("phone22", "123456");
			// p.setProperty(key, value)
			p.put(Constant.INTERVAL_STR, Config.INTERVAL + "");
			p.put(Constant.MP3_PATH_STR, Config.MP3_PATH);

			p.store(output, "below are you setting config");
			output.close();
			System.out.println("Your config has been saved!");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	//获取用户对应的url
		public static Map<String,String> readUsersUrl() {

			// HashMap<String, String> m=new HashMap<String,String>();
			Map<String,String> urls = new HashMap<String,String>();
			File file = new File(Constant.URL_MAP_PATH);
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			Properties p = new Properties();
			try {

				p.load(new FileInputStream(file));
				// FIRSTRUN = Boolean.parseBoolean(p.getProperty(FIRSTRUN_STRING));
				Set<Object> keySet = p.keySet();
				//Object[] array = keySet.toArray();
				for (Object o : keySet) {
					urls.put(o.toString(), p.get(o).toString());
					//urls.add(new User(o.toString(), p.get(o).toString()));
					// m.put(o.toString(), p.get(o).toString());
					/*
					 * System.out.println(o); System.out.println(p.get(o));
					 */
				}
				// p.get(array)
				// p.getProperty(keySet.)
			} catch (IOException e) {
				e.printStackTrace();
			}
			// System.out.println(users.toString());
			return urls;
		}

		// if password is null then remove the email from list, otherwise add a new
		// email to the list 复制的写入用户的代码
		public static void writeUsersUrl(String email, String url) {

			File file = new File(Constant.URL_MAP_PATH);
			// file.

			Properties p = new Properties();
			try {
				// load the previous data
				FileInputStream input = new FileInputStream(file);
				p.load(input);
				input.close();

				// overwrite the previous data
				FileOutputStream output = new FileOutputStream(file);
				// p.put(FIRSTRUN_STRING, "false");
				// p.setProperty("phone22", "123456");
				// p.put(USERNAME_STRING, USERNAME);
				// p.put(PASSWORD_STRING, PASSWORD);
				if (url == null) {
					p.remove(email);
				} else {
					p.put(email, url);
				}

				p.store(output, "url信息可手动修改：你的邮箱对应的url可能是下面这种格式：https\\://mail.xxxx.com/ews/exchange.asmx");
				output.close();
				System.out.println("Your url info has been saved!");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

}
