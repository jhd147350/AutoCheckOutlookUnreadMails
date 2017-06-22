package jhd.ui;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;

import jhd.config.Config;
import jhd.config.Strings;
import jhd.tool.FileTool;

import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.awt.event.ActionEvent;

public class Setting extends JPanel {

	private JButton save;
	private JTextField mp3DirTF;
	private JTextField timeInterTF;

	public Setting() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// 先初始化配置文件
		FileTool.initConfigFromFile();

		JPanel top = new JPanel();

		Box mp3DirBox = Box.createHorizontalBox();
		Box timeIntervalBox = Box.createHorizontalBox();
		Box baseBox = Box.createVerticalBox();

		JLabel mp3Dir = new JLabel("mp3路径: ");
		JLabel timeInterval = new JLabel("刷新间隔s: ");

		Component horizontalGlue = Box.createHorizontalGlue();
		mp3DirBox.add(horizontalGlue);
		mp3DirBox.add(mp3Dir);
		timeIntervalBox.add(timeInterval);

		mp3DirTF = new JTextField();
		mp3DirTF.setColumns(50);
		mp3DirTF.setMaximumSize(mp3DirTF.getPreferredSize());
		mp3DirTF.setText(Config.MP3_PATH);
		timeInterTF = new JTextField();
		timeInterTF.setColumns(50);
		timeInterTF.setMaximumSize(timeInterTF.getPreferredSize());
		timeInterTF.setText(Config.INTERVAL + "");

		mp3DirBox.add(mp3DirTF);
		timeIntervalBox.add(timeInterTF);
		baseBox.add(mp3DirBox);

		Component verticalStrut = Box.createVerticalStrut(15);
		baseBox.add(verticalStrut);
		baseBox.add(timeIntervalBox);
		top.add(baseBox);
		top.setMaximumSize(top.getPreferredSize());

		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		top.setLayout(layout);
		add(top);

		Box saveBox = Box.createHorizontalBox();
		save = new JButton("Save");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					int i=Integer.parseInt(timeInterTF.getText());
					}
				catch (Exception e) {
					StatusBar.currentStatus.setText(Strings.SAVE_FAIL);
					return ;
				}
				try {
					BufferedInputStream in = new BufferedInputStream(new FileInputStream(mp3DirTF.getText()));
					in.close();
				} catch (Exception e) {
					StatusBar.currentStatus.setText(Strings.SAVE_FAIL_MP3_NOT_FOUND);
					return ;
				}
				Config.INTERVAL = Integer.parseInt(timeInterTF.getText());
				Config.MP3_PATH = mp3DirTF.getText();
				FileTool.saveConfigToFile();
				FileTool.initConfigFromFile();
				StatusBar.currentStatus.setText(Strings.SAVE_OK);
			}
		});

		Component verticalStrut_1 = Box.createVerticalStrut(15);
		add(verticalStrut_1);
		saveBox.add(save);
		add(saveBox);

		// --------------------------------
		/*
		 * JPanel setting1 = new JPanel(); add(setting1);
		 * 
		 * JLabel lblNewLabel_2 = new JLabel("New label");
		 * 
		 * textField_1 = new JTextField();
		 * textField_1.setMaximumSize(textField_1.getPreferredSize());
		 * lblNewLabel_2.setLabelFor(textField_1); textField_1.setColumns(20);
		 * setting1.setLayout(new BoxLayout(setting1, BoxLayout.X_AXIS));
		 * setting1.add(lblNewLabel_2); setting1.add(textField_1);
		 * 
		 * Component glue = Box.createGlue(); setting1.add(glue);
		 * 
		 * JLabel lblNewLabel = new JLabel("New label"); add(lblNewLabel);
		 * 
		 * Box horizontalBox = Box.createHorizontalBox(); add(horizontalBox);
		 * horizontalBox.add(new JLabel("tyrs")); horizontalBox.add(new
		 * JPanel(new FlowLayout()).add(new JTextField("1111")));
		 * 
		 * textField = new JTextField(); add(textField);
		 * textField.setColumns(10);
		 */
	}
}
