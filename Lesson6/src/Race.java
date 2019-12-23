/**
 * Copyright(C) 2019 LuvinaSoftware
 * Race.java, Dec 23, 2019, MDung
 */

import java.util.Scanner;

/**
 * Chương trình giả lập đua xe, trong đó mỗi xe là một RaceCar
 * @author MDung
 */
public class Race {

	/**
	 * Main
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhập giá trị finish:");
		int finish = sc.nextInt();
		sc.nextLine();
		
		// Tạo mảng 5 phần tử RaceCar
		RaceCar arrRaceCar[] = new RaceCar[5];
		
		// Duyệt vòng for gán giá trị cho mảng
		for (int i = 0; i < arrRaceCar.length; i++) {
			System.out.println("Nhap ten xe thu " + (i + 1) + ":");
			
			//Gán giá trị tên cho xe
			String name = sc.nextLine();
			arrRaceCar[i] = new RaceCar(name, finish);
		}
		
		// Duyệt vòng for gọi hàm start() của mỗi Thread
		for (int i = 0; i < arrRaceCar.length; i++) {
			arrRaceCar[i].start();
		}
	}

}
