package jhd;

import java.net.URI;
import java.net.URISyntaxException;

import jhd.tool.Mp3Player;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.service.folder.Folder;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.service.schema.EmailMessageSchema;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.search.FindItemsResults;
import microsoft.exchange.webservices.data.search.ItemView;
import microsoft.exchange.webservices.data.search.filter.SearchFilter;

public class User {
	static public Mp3Player player;
	static {
		System.out.println("static init mp3player");
		player = new Mp3Player(Config.MP3_PATH);
	}
	private String email;
	private String password;
	private int unReadNum = 0;

	private ExchangeService es;
	private ExchangeCredentials ec;
	private Folder inbox;
	ItemView view;
	SearchFilter sf;
	FindItemsResults<Item> findResults;

	private boolean connectFailed = false;

	public User(String email, String password){
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// 帐号密码错误 或网络连接错误抛出一场
	public boolean connect2Email(/* String email,String password */) {

		es = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
		ec = new WebCredentials(email, password);
		es.setCredentials(ec);
		
		// Setting the URL of the Service

		try {
			es.setUrl(new URI(Config.OUTLOOK_URL));
		} catch (URISyntaxException e) {
			e.printStackTrace();
			connectFailed = true;
			System.out.println("--------------------OUTLOOK URL ERR");
			return false;
		}
		// es.autodiscoverUrl(email);

		try {
			inbox = Folder.bind(es, WellKnownFolderName.Inbox);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("--------------------OUTLOOK BIND ERR");
			connectFailed = true;
			return false;
		}

		view = new ItemView(10);

		//
		sf = new SearchFilter.IsEqualTo(EmailMessageSchema.IsRead, false);
		
		return true;

	}

	public int getUnReadNum() {
		if(connectFailed){
			return -1;
		}

		try {
			findResults = es.findItems(inbox.getId(), sf, view);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

		// get all unread numbers.
		unReadNum = findResults.getTotalCount();

		return unReadNum;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + "]";
	}

	/*
	 * class MyException extends Exception { public MyException(String email,
	 * String password) {
	 * 
	 * }
	 * 
	 * @Override public void printStackTrace() { super.printStackTrace();
	 * System.out.println(email + ":" + password); } }
	 */
	public void closeConection(){
		if(es!=null){
			es.close();
		}
	}
}
