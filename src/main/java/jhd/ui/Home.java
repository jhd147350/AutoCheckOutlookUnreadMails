package jhd.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import jhd.account.Account;
import jhd.config.Config;
import jhd.connector.Connector;
import jhd.connector.OutlookConnector;
import jhd.tool.FileTool;

import javax.swing.JRadioButton;

public class Home extends JPanel implements ActionListener {

	// top
	private JPanel top;
	private JButton add;
	private JLabel lemail;
	private JLabel lpassword;
	private JTextField temail;
	private JPasswordField tpassword;
	// middle
	private JPanel middle;
	private JScrollPane scroll;
	private JList emails;
	private JPanel middle_bottom;
	// private JButton begin;
	private JButton stop;
	private JButton delete;
	private JRadioButton showPassword;
	// JLabel lastRefresh;

	// 工作线程
	private Thread thread;

	private MyListener myListener;

	public void setMyListener(MyListener myListener) {
		this.myListener = myListener;

	}

	public Home() {
		init();
		initListener();
		// setList();
		setLayout(new BorderLayout());

		add(top, BorderLayout.NORTH);
		add(middle, BorderLayout.CENTER);

	}

	private void restartListen() {
		if (thread != null) {
			thread.stop();
			thread = null;
		}

		// 先重新初始化 用户信息
		List<Account> accounts = FileTool.readUsers();
		// 刷新list UI
		setList(accounts);
		Map<String, String> urlMap = FileTool.readUsersUrl();
		for (Account temp : accounts) {
			temp.setUrl(urlMap.get(temp.getEmail()));
			System.out.println(temp.getEmail() + ":" + temp.getUrl());
		}
		// 再初初始化Connector
		List<Connector> outlookConnectors = new ArrayList<>();
		for (Account temp : accounts) {
			Connector c = new OutlookConnector();
			int status = c.tryConnect(temp);
			switch (status) {
			case OutlookConnector.ERR_ACCOUNT:
				myListener.connectException(temp.getEmail());
				break;
			case OutlookConnector.ERR_BIND:
				myListener.bindException(temp.getEmail());
				break;
			case OutlookConnector.ERR_NET:
				myListener.netException(temp.getEmail());
				break;
			case OutlookConnector.ERR_OUTLOOK_URL:
				myListener.urlException(temp.getEmail());
				break;
			case 0:
				outlookConnectors.add(c);
				break;
			}
		}

		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						System.out.println(outlookConnectors.size());
						float i = 0;
						int limitSec = Config.INTERVAL;
						for (Connector temp : outlookConnectors) {
							i++;
							int unReadNum = temp.getUnreadNums();
							if (unReadNum > 0) {
								myListener.receiveUnReadMail(temp.getEmail(), unReadNum);
							} else if (unReadNum == -1) {
								myListener.netException(temp.getEmail());
							}
							int j = (int) (i / accounts.size() * 100);
							System.out.println(j);
							StatusBar.progressBar.setValue(j);
						}
						while (limitSec > 0) {
							System.out.println("remians " + --limitSec + " s");
							StatusBar.countDown.setText(limitSec + "s");

							TimeUnit.SECONDS.sleep(1);
						}
						StatusBar.progressBar.setValue(0);
					}
				} catch (Exception e) {
					e.printStackTrace();
					myListener.threadException();
				}
			}
		});
		thread.start();
	}

	private void initListener() {
		add.addActionListener(this);
		delete.addActionListener(this);
		stop.addActionListener(this);

		showPassword.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if (e.getSource() == showPassword) {
					if (showPassword.isSelected()) {
						tpassword.setEchoChar('\0');

					} else {
						tpassword.setEchoChar('•');
					}
					;
				}
			}
		});

		// this.addVetoableChangeListener(listener);
	}

	private void init() {

		top = new JPanel(new FlowLayout());
		// top.setPreferredSize(new Dimension(100, 100));
		top.addComponentListener(new ComponentListener() {

			@Override
			public void componentShown(ComponentEvent arg0) {
			}

			@Override
			public void componentResized(ComponentEvent arg0) {
				top.setPreferredSize(top.getPreferredSize());
				System.out.println(arg0.getComponent());
				JPanel component2 = (JPanel) arg0.getComponent();
				Dimension preferredSize = component2.getPreferredSize();
				if (getWidth() < preferredSize.getWidth()) {
					top.setPreferredSize(new Dimension(preferredSize.width, 72));
				} else {
					top.setPreferredSize(new Dimension(preferredSize.width, 36));
				}

			}

			@Override
			public void componentHidden(ComponentEvent arg0) {
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
			}

		});
		add = new JButton("add");
		lemail = new JLabel("email:");
		lpassword = new JLabel("password:");
		temail = new JTextField("@21vianet.com", 15);
		// temail.set
		// temail.sesiz
		tpassword = new JPasswordField(15);
		// tpassword.setEchoChar('\0');
		top.add(lemail);
		top.add(temail);
		top.add(lpassword);
		top.add(tpassword);

		showPassword = new JRadioButton("show");
		top.add(showPassword);
		top.add(add);

		middle = new JPanel(new BorderLayout());
		// String data[] = { "a", "b", "c" };
		/*
		 * Vector<String> temps = new Vector<String>();
		 * temps.add("jhd@21viasadsdsadasddsnet.com");
		 * temps.add("jhdadas@21vianet.com");
		 * temps.add("jhasdasd@21sdfsdfsdvianet.com");
		 * temps.add("jhasdadad@21vianet.com"); temps.add("jhd@21vianet.com");
		 * temps.add("jhdadas@21vianet.com");
		 * temps.add("jhasdasd@21vianet.com");
		 * temps.add("jhasdadad@21vianet.com");
		 */
		emails = new JList();
		// emails.set
		emails.setVisibleRowCount(6);
		// emails.add
		scroll = new JScrollPane(emails);
		// begin = new JButton("begin");
		// begin.setEnabled(false);
		stop = new JButton("stop mp3");
		middle_bottom = new JPanel(new FlowLayout());
		// middle_bottom.add(begin);
		middle_bottom.add(stop);
		delete = new JButton("delete");
		middle.add(middle_bottom, BorderLayout.SOUTH);
		middle.add(scroll, BorderLayout.CENTER);
		middle.add(delete, BorderLayout.EAST);
		// lastRefresh = new JLabel("last refresh:");
		// lastRefresh.setFont(new Font("����", Font.BOLD, 20));
		// bottom.add(lastRefresh);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "add":
			
			if (tpassword.getText().equals("")) {
				break;
			}
			String email = temail.getText();
			String password = tpassword.getText();
			Account account = new Account(email, password);
			OutlookConnector c = new OutlookConnector();
			Thread t=new Thread(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					super.run();
					int tryConnect = c.tryConnect(account);

					switch (tryConnect) {
					case OutlookConnector.ERR_ACCOUNT:
						myListener.connectException(email);
						break;
					case OutlookConnector.ERR_BIND:
						myListener.bindException(email);
						break;
					case OutlookConnector.ERR_NET:
						myListener.netException(email);
						break;
					case OutlookConnector.ERR_OUTLOOK_URL:
						myListener.urlException(email);
						break;
					case 0:
						// 将密码和得到的url分别保存
						FileTool.writeUsers(email, password);
						FileTool.writeUsersUrl(email, c.getUrl());
						break;
					}
					c.closeConection();
					StatusBar.currentStatus.setText("");
					if (tryConnect != 0) {

						JOptionPane.showMessageDialog(null, "pls check your email and password!", "check again",
								JOptionPane.WARNING_MESSAGE);
					}

					tpassword.setText("");

					// 每次添加一个用户都要重置数据
					// 这部分交由 监听器去处理
					// bindData();
					myListener.clickAdd();
				}
				
			};
			StatusBar.currentStatus.setText("正在尝试识别邮箱所对应的服务器url，需要一定的时间");
			t.start();
			

			break;
		case "delete":
			try {
				FileTool.writeUsers(emails.getSelectedValue().toString(), null);
			} catch (Exception e2) {
				break;
			}

			// 删除时也要重置列表数据
			// bindData();
			myListener.clickDelete();
			break;

		case "stop mp3":
			// User.player.stop();
			myListener.clickStop();
			break;
		}

	}

	private void setList(List<Account> list) {
		Vector<String> v = new Vector<String>();
		for (Account a : list) {
			v.add(a.getEmail());
		}
		emails.setListData(v);
	}

	/*
	 * private void setUsers() { users = FileTool.readUsers(); for (User temp :
	 * users) { if (!temp.connect2Email()) {
	 * myListener.bindException(temp.getEmail()); } } }
	 */

	public void bindData() {
		// 初始化数据，也可以重新刷新数据初始化

		// setUsers();

		restartListen();

	}

	public interface MyListener {
		public void clickAdd();

		public void clickStop();

		public void clickDelete();

		public void receiveUnReadMail(String email, int num);

		// add param 'email' to display which email box have a exception
		public void netException(String email);

		public void bindException(String email);

		public void connectException(String email);

		public void urlException(String email);

		public void threadException();
	}
}
