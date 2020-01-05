/**
 * Copy(C) 2020 Luvina SoftWare Company
 * BasePanel.java Jan 4, 2020 2020 Minh
 * 
 */
package com.leminh.login.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.leminh.login.ui.ICommon;

/**
 * @author Minh
 *
 */
public abstract class BasePanel extends JPanel implements ICommon, ActionListener {
	public BasePanel() {
		innit();
		event();
		comp();
	}

	protected void notify(String string) {
		JOptionPane.showConfirmDialog(this, string);
	}

	@Override
	public final void actionPerformed(ActionEvent e) {
		Component comp = (Component) e.getSource();
		clickName(comp.getName());

	}

	protected void clickName(String name) {

	}

	/**
	 * Phương thức tạo 1 lable
	 */
	protected JLabel creatLable(Font f, String string, int i, int j, Color c) {
		JLabel lable = new JLabel();
		lable.setLocation(i, j);
		lable.setText(string);
		FontMetrics metric = getFontMetrics(f);
		int w = metric.stringWidth(lable.getText());
		int h = metric.getHeight();
		lable.setSize(w, h);
		lable.setFont(f);
		lable.setForeground(c);
		return lable;
	}

	protected JTextField creatTextField(Font f, int w, int h, int i, int j, Color c) {
		JTextField tf = new JTextField();
		tf.setLocation(i, j);
		tf.setFont(f);
		tf.setForeground(c);
		tf.setSize(w, h);
		return tf;
	}

	protected JButton creatButton(Font f, String string, int i, int j, Color c, String name) {
		JButton bt = new JButton();
		Insets padding = bt.getInsets();
		bt.setLocation(i, j);
		bt.setText(string);
		bt.setFont(f);
		FontMetrics metric = getFontMetrics(f);
		int w = metric.stringWidth(bt.getText()) + padding.left + padding.right;
		int h = metric.getHeight() + padding.bottom + padding.top;
		bt.setForeground(c);
		bt.setSize(w, h);
		bt.addActionListener(BasePanel.this);
		return bt;
	}

	protected JTextArea creatJTextArea(Font f, int w, int h, int i, int j, Color c) {
		JTextArea tfA = new JTextArea();
		tfA.setLocation(i, j);
		tfA.setFont(f);
		tfA.setForeground(c);
		tfA.setSize(w, h);
		return tfA;
	}

}
