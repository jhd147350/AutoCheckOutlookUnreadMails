package studio.jhd;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;

import javax.swing.JLabel;

public class URLLabel extends JLabel implements MouseListener {
	private String url;

	public URLLabel(String url, String text) {
		this.url = url;
		// this.setText(ustr);
		// "<html><font color=blue><u>" + text
		this.setText("<html><a href=\"\">" + text + "</a></html>");
		// this.setForeground(Color.blue);//设置链接颜色
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// 设置鼠标样式
		this.setToolTipText(url);// 设置提示文字
		this.addMouseListener(this);
	}

	// 点击时打开默认浏览器浏览指定的页面。
	public void mouseClicked(MouseEvent e) {
		try {
			Desktop.getDesktop().browse(new URI(url));
		} catch (Exception e1) {
			e.paramString();
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



}