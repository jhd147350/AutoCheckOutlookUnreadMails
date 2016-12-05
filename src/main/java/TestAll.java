import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.Future;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.notification.EventType;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.misc.AsyncCallback;
import microsoft.exchange.webservices.data.misc.AsyncCallbackImplementation;
import microsoft.exchange.webservices.data.misc.IAsyncResult;
import microsoft.exchange.webservices.data.notification.GetEventsResults;
import microsoft.exchange.webservices.data.notification.PullSubscription;

public class TestAll {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ExchangeService es = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
		ExchangeCredentials ec = new WebCredentials("jia.haodong@21vianet.com", "147350Jhd");
		es.setCredentials(ec);
		es.setUrl(new URI("https://mail.21vianet.com/EWS/Exchange.asmx"));
		
		AsyncCallback callback=new AsyncCallbackImplementation(){

			@Override
			public Object processMe(Future<?> task) {
				// TODO Auto-generated method stub
				return super.processMe(task);
			}

			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
			}
			
		};
		
		
		IAsyncResult asyncresult = es.beginSubscribeToPullNotificationsOnAllFolders(new AsyncCallbackImplementation(){
			
		}, null, 5, null, EventType.NewMail);

		PullSubscription subscription = es.endSubscribeToPullNotifications(asyncresult);

		GetEventsResults events = subscription.getEvents();

		System.out.println("events======" + events.getItemEvents());

	}

}
