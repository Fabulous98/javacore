/**
 * Copyright(C) 2020 LuvinaSoff
 * MainPanel.java, Jan 6, 2020, MDung
 */
package Cau1;

import java.awt.CardLayout;
import Cau1.gui.*;

/**
 * Description
 * @author MDung
 */
public class MainPanel extends BasePanel{
	private Student stu;

	@Override
	public void innit() {
		setLayout(new CardLayout());
	}

	@Override
	public void event() {

	}

	@Override
	public void comp() {
		stu = new Student();
		add(stu);
	
	}

}
