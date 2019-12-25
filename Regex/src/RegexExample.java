/**
 * Copyright(C) 2019 LuvinaSoff
 * RegexExample.java, Dec 24, 2019, MDung
 */

import java.util.regex.*;

/**
 * Vi du ve Regex mat khau, sdt
 * @author MDung
 */
public class RegexExample {

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String PASSWORD_PATTERN ="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$";
		String PASSWORD_PATTERN2 ="[A-Za-z\\d@$!%*?&]{6,}$";
		String pass1 = "12346";
		String pass2 = "A1aB4@";
		//String SDT2_PATTERN ="^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
		String SDT_PATTERN = "^0[0-9]{9}" ;
		String sdt1 = "1234567897";
		String sdt2 = "0985479364";
		
		System.out.println(pass1 + ":" + Pattern.matches(PASSWORD_PATTERN,pass1));
		System.out.println(pass2 + ":" + Pattern.matches(PASSWORD_PATTERN,pass2));
		System.out.println(sdt1 + ":" + Pattern.matches(SDT_PATTERN,sdt1));
		System.out.println(sdt2 + ":" + Pattern.matches(SDT_PATTERN,sdt2));
		
	}

}
