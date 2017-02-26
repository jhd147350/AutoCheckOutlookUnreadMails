package jhd.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import jhd.Config;
import jhd.User;

public class Tool {

	public static List<User> readProperties(String path) {

		// HashMap<String, String> m=new HashMap<String,String>();
		List<User> users = new ArrayList<User>();
		File file = new File(path);
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
				users.add(new User(o.toString(), p.get(o).toString()));
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
		return users;
	}

	// if password is null then remove the email from list, otherwise add a new
	// email to the list
	public static void writeProperties(String email, String password, String path) {

		File file = new File(path);
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

			p.store(output, "update");
			output.close();
			System.out.println("Your info has been saved!");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initConfigFromFile() {

		String path = "setting.txt";
		// HashMap<String, String> m=new HashMap<String,String>();
		//List<User> users = new ArrayList<User>();
		File file = new File(path);
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
			Config.INTERVAL = Integer.parseInt((String) p.getOrDefault(Config.INTERVAL_STR, Config.INTERVAL+""));
			Config.MP3_PATH = (String) p.getOrDefault(Config.MP3_PATH_STR, Config.MP3_PATH);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void saveConfigToFile() {

		String path = "setting.txt";

		File file = new File(path);
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
			p.put(Config.INTERVAL_STR, Config.INTERVAL + "");
			p.put(Config.MP3_PATH_STR, Config.MP3_PATH);

			p.store(output, "below are you setting config");
			output.close();
			System.out.println("Your config has been saved!");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
