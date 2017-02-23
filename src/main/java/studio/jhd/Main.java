package studio.jhd;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import studio.jhd.ui.Home.MyListener;
import studio.jhd.ui.StatusBar;

public class Main {

	static MyJFrame f;

	public static void main(String[] args) {

		// TFFrame frame=new TFFrame();
		f = new MyJFrame();
		// 数据初始化要和界面初始话分开，否则当数据出异常时会影响到界面的加载

		f.setHomeMyListener(new MyListener() {

			@Override
			public void clickStop() {
				User.player.stop();
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
			public void receiveUnReadMail(int num) {
				User.player.play();
			}

			@Override
			public void bindException() {
				myException();

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

	public static void myException() {
		// TODO
		StatusBar.currentStatus.setText("邮箱密码有误或网络问题");
		// e.printStackTrace();
		System.out.println("绑定数据或网络出问题");
		User.player.play();
	}

}
