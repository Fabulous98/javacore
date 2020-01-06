/**
 * Copyright(C) 2020 LuvinaSoftware
 * Main.java, Jan 6, 2020, MDung
 */

/**
 * Description
 * @author MDung
 */
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main extends JFrame{
	JPanel northPane = new JPanel(),
			centerPane = new JPanel(),
			leftCenterPane = new JPanel(),
			rightCenterPane = new JPanel();
	public void view() {
		this.add(northPane, BorderLayout.NORTH);
		this.add(centerPane, BorderLayout.CENTER);
		centerPane.add(leftCenterPane, BorderLayout.WEST);
		centerPane.add(rightCenterPane, BorderLayout.EAST);
		
		this.setTitle("Panels");
		this.setSize(400, 300);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public static void main(String[] args) {
		new Main().view();
	}

}