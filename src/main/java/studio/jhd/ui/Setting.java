package studio.jhd.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.Box;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Component;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Setting extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	public Setting() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		// setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel top=new JPanel();
		
		
		Box mp3DirBox = Box.createHorizontalBox();
		Box timeIntervalBox = Box.createHorizontalBox();
		Box baseBox = Box.createVerticalBox();

		JLabel mp3Dir = new JLabel("mp3 dir: ");
		JLabel timeInterval = new JLabel("time interval: ");
		
		Component horizontalGlue = Box.createHorizontalGlue();
		mp3DirBox.add(horizontalGlue);
		mp3DirBox.add(mp3Dir);
		timeIntervalBox.add(timeInterval);
		
		JTextField mp3DirTF=new JTextField();
		mp3DirTF.setColumns(50);
		mp3DirTF.setMaximumSize(mp3DirTF.getPreferredSize());
		JTextField timeInterTF=new JTextField();
		timeInterTF.setColumns(50);
		timeInterTF.setMaximumSize(timeInterTF.getPreferredSize());
		
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
		
		Box saveBox=Box.createHorizontalBox();
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		Component verticalStrut_1 = Box.createVerticalStrut(15);
		add(verticalStrut_1);
		saveBox.add(btnNewButton);
		add(saveBox);


		// --------------------------------
		/*
		JPanel setting1 = new JPanel();
		add(setting1);

		JLabel lblNewLabel_2 = new JLabel("New label");

		textField_1 = new JTextField();
		textField_1.setMaximumSize(textField_1.getPreferredSize());
		lblNewLabel_2.setLabelFor(textField_1);
		textField_1.setColumns(20);
		setting1.setLayout(new BoxLayout(setting1, BoxLayout.X_AXIS));
		setting1.add(lblNewLabel_2);
		setting1.add(textField_1);

		Component glue = Box.createGlue();
		setting1.add(glue);

		JLabel lblNewLabel = new JLabel("New label");
		add(lblNewLabel);

		Box horizontalBox = Box.createHorizontalBox();
		add(horizontalBox);
		horizontalBox.add(new JLabel("tyrs"));
		horizontalBox.add(new JPanel(new FlowLayout()).add(new JTextField("1111")));

		textField = new JTextField();
		add(textField);
		textField.setColumns(10);*/
	}
}
