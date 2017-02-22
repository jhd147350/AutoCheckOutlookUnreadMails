package studio.jhd;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import studio.jhd.tool.Tool;
import studio.jhd.ui.About;
import studio.jhd.ui.Home;
import studio.jhd.ui.Setting;
import studio.jhd.ui.StatusBar;
import studio.jhd.ui.Home.MyListener;

public class MyJFrame extends JFrame /*implements ActionListener, ListSelectionListener*/ {
	
	RefreshUserListener refreshUserListener;

	

	
	//private int width = 550;
	//private int height = 400;
	
	//menu
	/*JMenuBar menuBar;
	JMenu setting;
	JMenu about;*/
	
	//tab
	JTabbedPane tabP;
	StatusBar statusBar;
	Home home;

	MyJFrame() {
		super("auto receive mail");
		//setSize(width, height);
		// setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-width)/2,
		// (Toolkit.getDefaultToolkit().getScreenSize().height-height)/2);

		// 设置弹出居中
		setLocationRelativeTo(null);
		// setLayout(new GridLayout(3, 1));
		
		init();
		//add(top, BorderLayout.NORTH);
		//add(middle, BorderLayout.CENTER);
		//add(bottom, BorderLayout.SOUTH);
		//add(menuBar,BorderLayout.NORTH);
		
		getContentPane().add(statusBar, BorderLayout.SOUTH);
		getContentPane().add(tabP, BorderLayout.CENTER);
		
		// add(new Button("test2"),BorderLayout.SOUTH);
		// add(new Button("test3"),BorderLayout.CENTER);
		pack();

		setVisible(true);
	}

	private void init() {
		statusBar = new StatusBar();
		
		tabP=new JTabbedPane();
		home = new Home();
		//home.addlistener
		tabP.add("Home", home);
		tabP.add("Setting", new Setting());
		tabP.addTab("About", new About());
		//add(new StatusBar(), BorderLayout.S);
	}
	
	public void bindData(){
		home.bindData();
	}

	/*
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
	}*/
	public void setList(List<User> list){
		Vector<String> v=new Vector<String>();
		for(User u:list){
			v.add(u.getEmail());
		}
		
	//	emails.setListData(v);
	}
	public void setHomeMyListener (MyListener myListener){
		home.setMyListener(myListener);
		
	}
	public void setRefreshUserListener(RefreshUserListener refreshUserListener) {
		this.refreshUserListener = refreshUserListener;
	}

	/*@Override
	public void valueChanged(ListSelectionEvent e) {}*/

}
