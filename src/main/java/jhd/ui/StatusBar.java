package jhd.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import jhd.Config;

import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;

public class StatusBar extends JPanel {

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
		// add(new JLabel("stutas"));
		add(currentStatus);

		glue_2 = Box.createGlue();
		add(glue_2);

		progressBar = new JProgressBar();
		// progressBar.setPreferredSize(new Dimension(progressBar.getWidth(),
		// 100));
		progressBar.setMaximumSize(new Dimension(100, 12));
		progressBar.setValue(0);
		add(progressBar);

		horizontalStrut_1 = Box.createHorizontalStrut(20);
		add(horizontalStrut_1);

		countDown = new JLabel(Config.INTERVAL + "s");
		add(countDown);

		horizontalStrut_2 = Box.createHorizontalStrut(20);
		add(horizontalStrut_2);
		// setFocusTraversalPolicy(new FocusTraversalOnArray(new
		// Component[]{currentStatus, glue}));
	}

	private void init() {
		currentStatus = new JLabel("");
		// progressBar.set
	}
}
