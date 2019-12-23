/**
 * Copyright(C) 2019 LuvinaSoftware
 * RaceCar.java, Dec 23, 2019, MDung
 */

import java.util.*;

/**
 * Class RaceCar kế thừa lớp Thread, có 2 thuộc tính name và finish
 * @author MDung
 */
public class RaceCar extends Thread {
	private String name;
	private int finish;
	
	// Constructor khởi tạo
	public RaceCar(String name, int finish) {
		this.name = name;
		this.finish = finish;
	}

	/**
	 * Phương thức duyệt vòng for in ra tên và giá trị mỗi vòng
	 * @throws InterruptedException
	 */
	public void run() {
		Random rd = new Random();
		
		// in ra giá trị tên và thứ tự vòng lặp 
		// mỗi vòng sleep ngẫu nhiên 0s -> 5s
		
		for (int i = 0; i < finish; i++) {
			System.out.println(name + ": " + (i + 1));
			// Vòng cuối thì in ra tên + finished
			if (i == finish - 1) {
				System.out.println(name + " finished!");
			}
			
			// Lấy ngẫu nhiên 1 số từ 1-->5
			int delay = rd.nextInt(6);
			
			// Sleep ngẫu nhiên từ 0s-->5s
			try {
				Thread.sleep(1000 * delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
