package studio.jhd;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import studio.jhd.ui.About;
import studio.jhd.ui.Home;
import studio.jhd.ui.Setting;
import studio.jhd.ui.StatusBar;
import studio.jhd.ui.Home.MyListener;

public class MyJFrame
		extends JFrame /* implements ActionListener, ListSelectionListener */ {

	//RefreshUserListener refreshUserListener;

	// private int width = 550;
	// private int height = 400;

	// tab
	JTabbedPane tabP;
	StatusBar statusBar;
	Home home;

	MyJFrame() {
		super("auto receive mail");
		// setSize(width, height);
		// setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-width)/2,
		// (Toolkit.getDefaultToolkit().getScreenSize().height-height)/2);

		// 设置弹出居中
		setLocationRelativeTo(null);
		// setLayout(new GridLayout(3, 1));

		init();
		// add(top, BorderLayout.NORTH);
		// add(middle, BorderLayout.CENTER);
		// add(bottom, BorderLayout.SOUTH);
		// add(menuBar,BorderLayout.NORTH);

		getContentPane().add(statusBar, BorderLayout.SOUTH);
		getContentPane().add(tabP, BorderLayout.CENTER);

		// add(new Button("test2"),BorderLayout.SOUTH);
		// add(new Button("test3"),BorderLayout.CENTER);
		pack();

		setVisible(true);
	}

	private void init() {
		statusBar = new StatusBar();

		tabP = new JTabbedPane();
		home = new Home();
		// home.addlistener
		tabP.add("Home", home);
		tabP.add("Setting", new Setting());
		tabP.addTab("About", new About());
		// add(new StatusBar(), BorderLayout.S);
	}

	public void bindData() {
		home.bindData();
	}

	
	/*public void setList(List<User> list) {
		Vector<String> v = new Vector<String>();
		for (User u : list) {
			v.add(u.getEmail());
		}
	}*/

	public void setHomeMyListener(MyListener myListener) {
		home.setMyListener(myListener);

	}

	/*public void setRefreshUserListener(RefreshUserListener refreshUserListener) {
		this.refreshUserListener = refreshUserListener;
	}*/

}
