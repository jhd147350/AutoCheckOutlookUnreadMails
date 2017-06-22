package jhd.connector;

import java.net.URI;
import java.net.URISyntaxException;

import jhd.account.Account;
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

/**
 * 
 * @author jia.haodong outlook专用,连接exchange服务器，获取未读邮件数量
 */
public class OutlookConnector extends Connector {

	// 网络错误
	public static final int ERR_NET = 1;
	// 账号错误
	public static final int ERR_ACCOUNT = 2;
	public static final int ERR_BIND = 3;
	public static final int ERR_OUTLOOK_URL = 4;
	private ExchangeService es;
	private ExchangeCredentials ec;
	private Folder inbox;
	private ItemView view;
	private SearchFilter sf;
	private FindItemsResults<Item> findResults;

	private String url;

	public String getUrl() {
		return this.url;
	}

	@Override
	public int tryConnect(Account a) {
		email = a.getEmail();
		es = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
		ec = new WebCredentials(a.getEmail(), a.getPassword());
		es.setCredentials(ec);

		if (a.getUrl() == null || a.getUrl().equals("")) {
			try {
				// TODO 自动发现url速度异常慢，不建议每次都要进行一次，可以将第一次发现的url保存，以便程序直接使用
				es.autodiscoverUrl(a.getEmail());
				System.out.println("自动发现的url" + es.getUrl());
				this.url = es.getUrl().toString();
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("--------------------OUTLOOK URL ERR");
				return ERR_OUTLOOK_URL;
			}
		} else {
			try {
				es.setUrl(new URI(a.getUrl()));
			} catch (URISyntaxException e) {
				e.printStackTrace();
				return ERR_OUTLOOK_URL;
			}
		}

		try {
			inbox = Folder.bind(es, WellKnownFolderName.Inbox);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("--------------------OUTLOOK BIND ERR");

			return ERR_BIND;
		}

		view = new ItemView(10);

		// 初始化查询条件
		sf = new SearchFilter.IsEqualTo(EmailMessageSchema.IsRead, false);

		return 0;
	}

	@Override
	public int getUnreadNums() {

		try {
			findResults = es.findItems(inbox.getId(), sf, view);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

		// get all unread numbers.
		return findResults.getTotalCount();
	}

	public void closeConection() {
		if (es != null) {
			es.close();
		}
	}

}
