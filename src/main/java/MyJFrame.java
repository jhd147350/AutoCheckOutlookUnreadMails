import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MyJFrame extends JFrame implements ActionListener, ListSelectionListener {
	
	RefreshUserListener refreshUserListener;

		// top
	JPanel top;
	JButton add;
	JLabel lemail;
	JLabel lpassword;
	JTextField temail;
	JTextField tpassword;

	// middle
	JPanel middle;
	JScrollPane scroll;
	JList emails;
	JPanel middle_bottom;
	JButton begin;
	JButton stop;
	JButton delete;

	// bottom
	JPanel bottom;
	JLabel lastRefresh;

	//private int width = 550;
	//private int height = 400;

	MyJFrame() {
		super("auto receive mail");
		//setSize(width, height);
		// setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-width)/2,
		// (Toolkit.getDefaultToolkit().getScreenSize().height-height)/2);

		// ÆÁÄ»¾ÓÖÐ
		setLocationRelativeTo(null);
		// setLayout(new GridLayout(3, 1));
		init();
		add(top, BorderLayout.NORTH);
		add(middle, BorderLayout.CENTER);
		add(bottom, BorderLayout.SOUTH);

		// add(new Button("test2"),BorderLayout.SOUTH);
		// add(new Button("test3"),BorderLayout.CENTER);
		pack();

		setVisible(true);
	}

	public void init() {
		top = new JPanel(new FlowLayout());
		add = new JButton("add");
		add.addActionListener(this);
		lemail = new JLabel("email:");
		lpassword = new JLabel("password:");
		temail = new JTextField("@21vianet.com", 15);
		// temail.set
		// temail.sesiz
		tpassword = new JTextField(15);
		top.add(lemail);
		top.add(temail);
		top.add(lpassword);
		top.add(tpassword);
		top.add(add);

		middle = new JPanel(new BorderLayout());
		//String data[] = { "a", "b", "c" };
		Vector<String> temps = new Vector<String>();
		temps.add("jhd@21viasadsdsadasddsnet.com");
		temps.add("jhdadas@21vianet.com");
		temps.add("jhasdasd@21sdfsdfsdvianet.com");
		temps.add("jhasdadad@21vianet.com");
		temps.add("jhd@21vianet.com");
		temps.add("jhdadas@21vianet.com");
		temps.add("jhasdasd@21vianet.com");
		temps.add("jhasdadad@21vianet.com");
		emails = new JList();
		emails.addListSelectionListener(this);
		//emails.set
		emails.setVisibleRowCount(6);
		// emails.add
		scroll = new JScrollPane(emails);
		begin = new JButton("begin");
		begin.setEnabled(false);
		stop = new JButton("stop");
		begin.addActionListener(this);
		stop.addActionListener(this);
		middle_bottom = new JPanel(new FlowLayout());
		//middle_bottom.add(begin);
		middle_bottom.add(stop);
		delete = new JButton("delete");
		delete.addActionListener(this);
		middle.add(middle_bottom, BorderLayout.SOUTH);
		middle.add(scroll, BorderLayout.CENTER);
		middle.add(delete, BorderLayout.EAST);

		bottom = new JPanel(new FlowLayout());
		lastRefresh = new JLabel("last refresh:");
		bottom.add(lastRefresh);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// System.out.println(e.getSource().toString());
		// System.out.println(e.getActionCommand().toString());
		// System.out.println(e.getID());
		switch (e.getActionCommand()) {
		case "add":
			Tool.writeProperties(temail.getText(), tpassword.getText());
			tpassword.setText("");
			refreshUserListener.refresh();
			//setList(list);

			break;
		case "delete":
			System.out.println("2");
			Tool.writeProperties(emails.getSelectedValue().toString(), null);
			refreshUserListener.refresh();
			break;
		case "begin":
			System.out.println("3");
		//	refreshUserListener.begin();
		//	begin.setEnabled(false);
			//stop.setEnabled(true);
			break;
		case "stop":
			//refreshUserListener.stop();
			//begin.setEnabled(true);
			//stop.setEnabled(false);
			//System.out.println("4");
			User.player.stop();
			break;

		default:
			break;
		}
	}
	public void setList(List<User> list){
		Vector<String> v=new Vector<String>();
		for(User u:list){
			v.add(u.getEmail());
		}
		
		emails.setListData(v);
	}
	
	public void setRefreshUserListener(RefreshUserListener refreshUserListener) {
		this.refreshUserListener = refreshUserListener;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {}

}
