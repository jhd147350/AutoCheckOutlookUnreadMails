import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.net.URI;
import java.net.URISyntaxException;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.BasePropertySet;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.enumeration.service.ConflictResolutionMode;
import microsoft.exchange.webservices.data.core.service.folder.Folder;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.service.schema.AppointmentSchema;
import microsoft.exchange.webservices.data.core.service.schema.EmailMessageSchema;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.ItemId;
import microsoft.exchange.webservices.data.property.complex.MessageBody;
import microsoft.exchange.webservices.data.search.FindItemsResults;
import microsoft.exchange.webservices.data.search.ItemView;
import microsoft.exchange.webservices.data.search.filter.SearchFilter;

//https://github.com/OfficeDev/ews-java-api/wiki/Getting-Started-Guide
public class Test {

	public static void main(String[] args) throws Exception {
		//To access Exchange Web Services 
		ExchangeService es = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
		ExchangeCredentials ec = new WebCredentials("jia.haodong@21vianet.com", "147350Jhd");
		es.setCredentials(ec);
		
		//Setting the URL of the Service
		es.setUrl(new URI("https://mail.21vianet.com/EWS/Exchange.asmx"));
		
		//Binding to an Existing Folder绑定收件箱
		Folder inbox = Folder.bind(es, WellKnownFolderName.Inbox);
		System.out.println(inbox.getDisplayName());
		
		//最多查询到的数量
		ItemView view = new ItemView(10);
		
		//过滤未读邮件
		SearchFilter sf=new SearchFilter.IsEqualTo(EmailMessageSchema.IsRead,false);
		
		//查询返回结果
		FindItemsResults<Item> findResults = es.findItems(inbox.getId(),sf, view);
		for (Item item : findResults.getItems()) {
			EmailMessage message = EmailMessage.bind(es, item.getId());
			//输出未读邮件信息
			System.out.println(message.getSender());
			System.out.println("Sub -->" + message.getSubject());
			MessageBody mb=message.getBody();			
			System.out.println("body-->" + mb.toString());
			/*
			 *中间需要处理的逻辑可以放这里
			 *
			 */
			
			//将邮件置为已读
			message.setIsRead(true);
			//更新到服务器上
			message.update(ConflictResolutionMode.AlwaysOverwrite);
		}
	}
}