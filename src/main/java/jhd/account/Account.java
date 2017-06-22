package jhd.account;

/**
 * 
 * @author jia.haodong
 *
 */
public class Account {

	private String email;
	private String password;
	private String url;

	public Account(String email, String password) {
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
