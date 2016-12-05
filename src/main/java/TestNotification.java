import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.notification.EventType;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.misc.AsyncCallbackImplementation;
import microsoft.exchange.webservices.data.misc.IAsyncResult;
import microsoft.exchange.webservices.data.notification.GetEventsResults;
import microsoft.exchange.webservices.data.notification.ItemEvent;
import microsoft.exchange.webservices.data.notification.PullSubscription;
import microsoft.exchange.webservices.data.property.complex.FolderId;
import microsoft.exchange.webservices.data.property.complex.MessageBody;

public class TestNotification {

	public static void main(String[] args) throws Exception  {
		ExchangeService es = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
		ExchangeCredentials ec = new WebCredentials("jia.haodong@21vianet.com", "147350Jhd");
		es.setCredentials(ec);
		try {
			es.setUrl(new URI("https://mail.21vianet.com/EWS/Exchange.asmx"));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Subscribe to pull notifications in the Inbox folder, and get notified
		// when a new mail is received, when an item or folder is created, or
		// when an item or folder is deleted.
		
		System.out.println("start:");

		List<FolderId> folder = new ArrayList<FolderId>();
		folder.add(new FolderId().getFolderIdFromWellKnownFolderName(WellKnownFolderName.Inbox));

		PullSubscription subscription;
			subscription = es.subscribeToPullNotifications(folder, 5
			/*
			 * timeOut: the subscription will end if the server is not polled within
			 * 5 minutes.
			 */, null /* watermark: null to start a new subscription. */, EventType.NewMail);
		
		
		IAsyncResult subscription1 = es.beginSubscribeToPullNotifications(new AsyncCallbackImplementation(), null, folder, 5, null, EventType.NewMail);

		//PullSubscription ps= es.endSubscribeToPullNotifications(subscription1);
		int i=0;
		while (true) {
			
			System.currentTimeMillis();

			System.out.println("loop:"+System.currentTimeMillis()+"  "+i++);
			
			try {
				Thread.sleep(10 * 1000l);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Wait a couple minutes, then poll the server for new events.
			GetEventsResults events = subscription.getEvents();
			//subscription.beginGetEvents(callback, state)

			// Loop through all item-related events.
			for (ItemEvent itemEvent : events.getItemEvents()) {
				if (itemEvent.getEventType() == EventType.NewMail) {
					EmailMessage message = EmailMessage.bind(es, itemEvent.getItemId());
					System.out.println(message.getSender());
					System.out.println("Sub -->" + message.getSubject());
					// 以为设置为读取内容，否则会提示：You must load or assign this property before you
					// can read its value Body
					// >http://www.cnblogs.com/love007/archive/2013/06/26/3156852.html
					System.out.println("body-->" + MessageBody.getStringFromMessageBody(message.getBody()));
				}
			}
			

		}

	}

}
