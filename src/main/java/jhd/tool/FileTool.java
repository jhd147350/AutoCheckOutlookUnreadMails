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
import jhd.config.Strings;

public class FileTool {

	// 获取用户
	public static List<Account> readUsers() {

		// HashMap<String, String> m=new HashMap<String,String>();
		List<Account> accounts = new ArrayList<Account>();
		File file = new File(Strings.PASSWORD_PATH);
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
			// Object[] array = keySet.toArray();
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

		File file = new File(Strings.PASSWORD_PATH);
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
		// List<User> users = new ArrayList<User>();
		File file = new File(Strings.SETTING_PATH);
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
			Config.INTERVAL = Integer.parseInt((String) p.getOrDefault(Strings.INTERVAL_STR, Config.INTERVAL + ""));
			Config.MP3_PATH = (String) p.getOrDefault(Strings.MP3_PATH_STR, Config.MP3_PATH);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveConfigToFile() {

		File file = new File(Strings.SETTING_PATH);
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
			p.put(Strings.INTERVAL_STR, Config.INTERVAL + "");
			p.put(Strings.MP3_PATH_STR, Config.MP3_PATH);

			p.store(output, "below are you setting config");
			output.close();
			System.out.println("Your config has been saved!");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
