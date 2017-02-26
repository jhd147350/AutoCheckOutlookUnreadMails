package jhd.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

import jhd.Strings;
import javax.swing.Box;
import java.awt.Component;

public class About extends JPanel {

	JLabel version;

	public About() {
		init();
	}

	private void init() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		add(verticalStrut_1);

		Box verticalBox = Box.createVerticalBox();
		version = new JLabel(Strings.VERSION);
		version.setHorizontalAlignment(SwingConstants.CENTER);
		// add(version);

		// .add(version);

		// URLLabel iLabel=new URLLabel(Strings.ABOUT_PROJECT, "935410469");
		JLabel iLabel = new JLabel(Strings.ABOUT_PROJECT);
		iLabel.setMaximumSize(iLabel.getPreferredSize());

		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);

		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue);
		horizontalBox.add(version);

		// add(iLabel);
		verticalBox.add(horizontalBox);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue_1);

		Component verticalStrut = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut);

		JPanel panel = new JPanel();
		panel.add(iLabel);
		verticalBox.add(panel);
		verticalBox.add(panel);
		add(verticalBox);

		Component glue = Box.createGlue();
		add(glue);

	}

}
