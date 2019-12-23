/**
 * Copyright(C) 2019 LuvinaSoftware
 * TwoThread.java, Dec 23, 2019, MDung
 */

import java.util.*;

/**
 * Chương trình test 2 thread theo 2 cách runnable và extends Thread
 * @author MDung
 */
public class TwoThread {

	/**
	 * Main
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Khởi tạo 2 thread
		Thread1 th1 = new Thread1();
		Thread2 th2 = new Thread2();
		
		// set values cho th2
		th2.setThread2();
		// chạy th2
		th2.start();
		
		// kiểm tra xem th2 sống hay chết
		if (th2.isAlive()) {
			System.out.println("+++Thread 2 còn sống trong khi run!");
		}else {
			System.out.println("+++Thread 2 chet trong khi run!");
		}
		
		// chạy th1
		th1.thread1();
		// kiểm tra th1 sau khi hoàn thành chết hay sống, in ra thông báo tương ứng
		th2.thread2();
	}
}

	


