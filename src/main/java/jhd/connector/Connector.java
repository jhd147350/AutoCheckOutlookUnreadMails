package jhd.connector;

import jhd.account.Account;

/**
 * 
 * @author jia.haodong 此类目的在于 以后添加对其他协议邮箱的支持
 */
public abstract class Connector {

	

	// 每个 都一一对应一个邮箱地址
	protected String email;

	public String getEmail() {

		return email == null ? "null" : email;

	}

	abstract public int tryConnect(Account a);

	// 必须至少先调用一次tryConnect
	/**
	 * 
	 * @return -1 表示错误
	 */
	abstract public int getUnreadNums();
}
