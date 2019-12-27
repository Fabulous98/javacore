/**
 * Copyright(C) 2019 LuvinaSoftware
 * Main.java, Dec 27, 2019, MDung
 */

/**
 * Description
 * @author MDung
 */
public class Main {

	/**
	 * Main
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Manager ma = new Manager();
		ma.enterDetails();
		ma.enterDetails();
		ma.printDetails();
		ma.writeEmployeeFile("../BuiManhDung.JavaBean.txt");
	}

}
