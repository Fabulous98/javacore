/**
 * Copy(C) 2020 Luvina SoftWare Company
 * MainPanel.java Jan 4, 2020 2020 Minh
 * 
 */
package com.leminh.login.panel;

import java.awt.CardLayout;


/**
 * @author Minh
 *
 */
public class MainPanel extends BasePanel {
	private LoginPanel login;
	private DangNhapPanel dangnhap;

	@Override
	public void innit() {
		setLayout(new CardLayout());
	}

	@Override
	public void event() {

	}

	@Override
	public void comp() {
		login = new LoginPanel();
		add(login);
		
		  java.awt.EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                new DangNhapPanel().setVisible(true);
	            }
	        });

	}

//	public void showPanel() {
//		login.setVisible(true);
//	}

}
