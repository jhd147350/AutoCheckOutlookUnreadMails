package studio.jhd.ui;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;

import studio.jhd.Config;

import javax.swing.JSlider;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import javax.swing.DropMode;

public class StatusBar extends JPanel{
	

	public static JLabel currentStatus;
	static JProgressBar progressBar;
	static JLabel countDown;
	 Component horizontalStrut;
	 Component horizontalStrut_1;
	 Component horizontalStrut_2;
	 Component glue_2;
	public StatusBar() {
		init();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut);
		//add(new JLabel("stutas"));
		add(currentStatus);
		
		glue_2 = Box.createGlue();
		add(glue_2);
		
		progressBar = new JProgressBar();
		//progressBar.setPreferredSize(new Dimension(progressBar.getWidth(), 100));
		progressBar.setMaximumSize(new Dimension(100, 12));
		progressBar.setValue(0);
		add(progressBar);
		
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		add(horizontalStrut_1);
		
		countDown = new JLabel(Config.INTERVAL+"s");
		add(countDown);
		
		horizontalStrut_2 = Box.createHorizontalStrut(20);
		add(horizontalStrut_2);
		//setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{currentStatus, glue}));
	}
	private void init() {
		currentStatus=new JLabel("");
		//progressBar.set
	}
}
