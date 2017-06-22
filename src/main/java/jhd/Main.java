package jhd;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import jhd.ui.StatusBar;
import jhd.tool.Mp3Player;
import jhd.ui.Home.MyListener;

public class Main {

	static MyJFrame f;
	static Mp3Player player=new Mp3Player();

	public static void main(String[] args) {

		// TFFrame frame=new TFFrame();
		f = new MyJFrame();
		// 数据初始化要和界面初始话分开，否则当数据出异常时会影响到界面的加载

		f.setHomeMyListener(new MyListener() {

			@Override
			public void clickStop() {
				player.stop();
				StatusBar.currentStatus.setText("stop");
			}

			@Override
			public void clickDelete() {
				f.bindData();
				StatusBar.currentStatus.setText("delete");
			}

			@Override
			public void clickAdd() {
				f.bindData();
				StatusBar.currentStatus.setText("add");
			}

			@Override
			public void receiveUnReadMail(String email, int num) {
				StatusBar.currentStatus.setText(email+":"+num);
				player.play();
			}

			@Override
			public void bindException(String email) {
				myBindException(email);
				player.playErr();
			}

			@Override
			public void netException(String email) {
				myNetException(email);
				player.playErr();
			}

			@Override
			public void connectException(String email) {
				StatusBar.currentStatus.setText("连接问题");
				player.playErr();	
			}

			@Override
			public void threadException() {
				StatusBar.currentStatus.setText("工作线程问题");
				player.playErr();
			}

			@Override
			public void urlException(String email) {
				StatusBar.currentStatus.setText(email+":url配置问题");
				player.playErr();		
			}
		});
		// f.addlistener
		f.addWindowListener(new MyWin());

		// 最后绑定数据 否则监听器 会在异常之后得不到初始化
		f.bindData();

	}

	static class MyWin extends WindowAdapter {

		@Override
		public void windowClosing(WindowEvent e) {
			// System.out.println("Window closing"+e.toString());
			System.out.println("exit");
			System.exit(0);
		}

		@Override
		public void windowActivated(WindowEvent e) {
		}

		@Override
		public void windowOpened(WindowEvent e) {
		}

	}

	public static void myBindException(String email) {
		StatusBar.currentStatus.setText(email+"邮件获取失败");
		
		System.err.println("绑定数据出问题");
	}
	public static void myNetException(String email) {
		StatusBar.currentStatus.setText(email+"遇到网络问题");
		
		System.err.println("网络出问题");
	}

}
