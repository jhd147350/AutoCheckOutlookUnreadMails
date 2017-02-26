package jhd.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.List;
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

import jhd.Config;
import jhd.User;
import jhd.tool.Tool;

import javax.swing.JRadioButton;

public class Home extends JPanel implements ActionListener{

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
	private List<User> users;

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
		if (thread != null)
			thread.stop();
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						System.out.println(users.size());
						float i = 0;
						int limitSec = Config.INTERVAL;
						for (User temp : users) {
							i++;
							int unReadNum = temp.getUnReadNum();
							if (unReadNum > 0) {
								myListener.receiveUnReadMail(unReadNum);
							} else if (unReadNum == -1) {
								myListener.bindException();
							}
							int j = (int) (i / users.size() * 100);
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
				if(e.getSource()==showPassword){
					if( showPassword.isSelected()){
						tpassword.setEchoChar('\0');

					}else{
						tpassword.setEchoChar('•');
					};
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
	//	tpassword.setEchoChar('\0');
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
			if(tpassword.getText().equals("")){
				break;
			}
			String email = temail.getText();
			String password = tpassword.getText();
			User user = new User(email, password);
			boolean connect2Email = user.connect2Email();
			if (!connect2Email) {

				user.closeConection();
				JOptionPane.showMessageDialog(null, "pls check your email and password!", "check again",
						JOptionPane.WARNING_MESSAGE);
				break;
			}
			user.closeConection();
			Tool.writeProperties(email, password, Config.PASSWORD_PATH);
			tpassword.setText("");
			myListener.clickAdd();

			// 每次添加一个用户都要重置数据
			// bindData();

			break;
		case "delete":
			Tool.writeProperties(emails.getSelectedValue().toString(), null, Config.PASSWORD_PATH);
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

	private void setList(List<User> list) {
		Vector<String> v = new Vector<String>();
		for (User u : list) {
			v.add(u.getEmail());
		}
		emails.setListData(v);
	}

	private void setUsers() throws Exception {
		users = Tool.readProperties(Config.PASSWORD_PATH);
		setList(users);
		for (User temp : users) {
			temp.connect2Email();
		}
	}

	public void bindData() {
		// 初始化数据
		try {
			setUsers();
		} catch (Exception e) {
			e.printStackTrace();
			myListener.bindException();
		} finally {
			restartListen();
		}

	}

	public interface MyListener {
		public void clickAdd();

		public void clickStop();

		public void clickDelete();

		public void receiveUnReadMail(int num);

		public void bindException();
	}
}
