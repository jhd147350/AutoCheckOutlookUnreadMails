package jhd.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

/**
 * 添加邮箱账户，需要用户指定url
 * @author jia.haodong
 *
 */
public class AddAccount extends JPanel implements ActionListener {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public AddAccount() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("email");
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("password");
		panel.add(lblPassword);
		
		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JRadioButton rdbtnShow = new JRadioButton("show");
		panel.add(rdbtnShow);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		
		JButton btnTest = new JButton("测试");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1.add(btnTest);
		
		JButton btnAdd = new JButton("添加");
		panel_1.add(btnAdd);
		
		JPanel panel_3 = new JPanel();
		panel_3.setToolTipText("");
		add(panel_3, BorderLayout.CENTER);
		
		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_8.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		
		JRadioButton radioButton_4 = new JRadioButton("New radio button");
		panel_8.add(radioButton_4);
		
		JLabel label = new JLabel("");
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_7.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		
		JRadioButton radioButton_3 = new JRadioButton("New radio button");
		panel_7.add(radioButton_3);
		
		JLabel label_1 = new JLabel("");
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_6.getLayout();
		flowLayout_2.setVgap(0);
		flowLayout_2.setHgap(0);
		
		JRadioButton radioButton_2 = new JRadioButton("New radio button");
		panel_6.add(radioButton_2);
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_5.getLayout();
		flowLayout_3.setVgap(0);
		flowLayout_3.setHgap(0);
		
		JRadioButton radioButton_1 = new JRadioButton("New radio button");
		panel_5.add(radioButton_1);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_4.getLayout();
		flowLayout_4.setVgap(0);
		flowLayout_4.setHgap(0);
		
		JRadioButton radioButton = new JRadioButton("New radio button");
		panel_4.add(radioButton);
		
		JLabel label_2 = new JLabel("");
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_2.getLayout();
		flowLayout_5.setVgap(0);
		flowLayout_5.setHgap(0);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("手动输入");
		panel_2.add(rdbtnNewRadioButton);
		
		textField_2 = new JTextField();
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label_3 = new JLabel("");
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		panel_3.add(panel_8);
		panel_3.add(label);
		panel_3.add(panel_7);
		panel_3.add(label_1);
		panel_3.add(panel_6);
		panel_3.add(panel_5);
		panel_3.add(panel_4);
		panel_3.add(label_2);
		panel_3.add(panel_2);
		panel_3.add(label_3);
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
