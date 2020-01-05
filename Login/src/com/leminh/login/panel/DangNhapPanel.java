/**
 * Copy(C) 2020 Luvina SoftWare Company
 * DangNhapPanel.java Jan 5, 2020 2020 Minh
 * 
 */
package com.leminh.login.panel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import com.leminh.login.ui.GUI;

/**
 * @author Minh
 *
 */
public class DangNhapPanel extends BasePanel implements ActionListener {

	private JLabel login;

	@Override
	public void innit() {
		setBackground(Color.WHITE);
		setLayout(new CardLayout());
	}

	@Override
	public void event() {

	}

	@Override
	public void comp() {
		Font f1 = new Font("Times New Roman", Font.BOLD, 40);
		login = creatLable(f1, "THÀNH CÔNG", 0, 0, Color.RED);
		FontMetrics metric = getFontMetrics(f1);
		int w = metric.stringWidth(login.getText());
		int h = metric.getHeight();
		login.setSize(w, h);
		int x = GUI.W_FRAME / 2 - w / 2;
		int y = 10;
		login.setLocation(x, y);
		add(login);

	}

}
