/**
 * Copy(C) 2020 Luvina SoftWare Company
 * GUI.java Jan 4, 2020 2020 Minh
 * 
 */
package com.leminh.login.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.leminh.login.panel.MainPanel;

/**
 * Tạo khung giao diện Tạo class GUI kế thừa JFram và implement bộ hình vi chung
 * ICommons
 * 
 * @author Minh
 *
 */
public class GUI extends JFrame implements ICommon {

	private static final String TITLE = "Login";
	public static final int W_FRAME = 400;
	public static final int H_FRAME = 300;
	private MainPanel mainPanel;

	public GUI() {
		innit();
		event();
		comp();
	}

	/**
	 * Tạo khung giao diện
	 */
	@Override
	public void innit() {
		// Set title
		setTitle(TITLE);
		// set kích thước giao diện
		setSize(W_FRAME, H_FRAME);
		// Set vị trí giữa màn hình
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		// phóng to
		setResizable(true);
		getContentPane().setBackground(Color.BLUE);
		// setLayout
		setLayout(new CardLayout());

		try {
			UIManager.setLookAndFeel(NimbusLookAndFeel.class.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void event() {
//		WindowAdapter adapter = new WindowAdapter() {
//			@Override
//			public void windowClosing(WindowEvent arg0) {
//				int rs = JOptionPane.showConfirmDialog(GUI.this, "Bạn có muốn tắt không?", "Xác nhận thoát",
//						JOptionPane.YES_NO_OPTION);
//				if (rs == JOptionPane.YES_OPTION) {
//					dispose();
//				}
//			}
//		};
//		addWindowListener(adapter);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	}

	@Override
	public void comp() {
		mainPanel = new MainPanel();
		add(mainPanel);
	}

}
