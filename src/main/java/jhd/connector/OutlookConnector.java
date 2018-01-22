package jhd.connector;

import java.net.URI;
import java.net.URISyntaxException;

import jhd.account.Account;
import jhd.config.Strings;
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

	public static final int ERR_IN_OUTLOOKCONNECTOR = 1;
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

		try {
			es.setUrl(new URI(Strings.OUTLOOK_URL));
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
			return ERR_IN_OUTLOOKCONNECTOR;
		}

		try {
			inbox = Folder.bind(es, WellKnownFolderName.Inbox);
		} catch (Exception e) {
			e.printStackTrace();
			return ERR_IN_OUTLOOKCONNECTOR;
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
