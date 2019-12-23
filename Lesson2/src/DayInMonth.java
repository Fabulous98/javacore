/**
 * Copyright(C) 2019 LuvinaSoftware
 * DayInMonth.java, Nov 29, 2019, MDung
 */

import java.util.Scanner;

/**
 * Description
 * @author MDung
 */
public class DayInMonth {

	/**
	 * Main
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int month; int year;
        Scanner scanner = new Scanner(System.in);
         
        System.out.println("Nhập tháng: ");
        month = scanner.nextInt();
        System.out.println("Nhập năm: ");
        year = scanner.nextInt();
         
        int Days = 0;
        Days = showDay(month,year);
        if (Days!=0) {
        	System.out.println("Tháng " + month + " năm " + year + " có " + Days + " ngày.");
        }else {
        	System.out.println("Nhập tháng hoặc năm không hợp lệ.");
        }
        
	}
	
	/**
	 * Ham tinh so ngay
	 *
	 * @param month
	 * @param year
	 * @return so ngay cua thang
	 */
	public static int showDay(int month, int year) {
		int Day=0;
         
        switch (month) {
            // các có 31 ngày.
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                Day = 31;
                break;
             
            // các tháng có 30 ngày
            case 4:
            case 6:
            case 9:
            case 11:
                Day = 30;
                break;
                 
            // Trương hợp tháng 2 (28 hoặc 29 ngày)
            case 2:
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    Day = 29;
                } else {
                    Day = 28;
                }
                break;
            default:
                Day = 0;
            }
        return Day;
	}

}
