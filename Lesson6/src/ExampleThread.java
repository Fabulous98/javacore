/**
 * Copyright(C) 2019 LuvinaSoftware
 * ExampleThread.java, Dec 23, 2019, MDung
 */

/**
 * Ví dụ về Thread
 * @author MDung
 */
public class ExampleThread extends Thread {

	/**
	 * Main
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExampleThread et = new ExampleThread();
		System.out.println("Start!");
		et.thread1();
		et.thread2();
		et.thread3();
	}
	/**
	 * Thread1 in ra 1-->20 dừng 1s mỗi lần in
	 * @throws InterruptedException
	 */
	public void thread1() {
		Runnable run1 = new Runnable() {
		
			@Override
			public void run() {
				for (int i=0; i<20; i++) {
					System.out.println(i+1);
					try {
						Thread.sleep(1000);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		};
		
		Thread th = new Thread(run1);
		th.run();
	}
	
	/**
	 * Thread2 in ra các số chia hết cho 10, 1-->200 dừng 3s mỗi lần in
	 * @throws InterruptedException
	 */
	public void thread2() {
		Runnable run2 = new Runnable() {
		
			@Override
			public void run() {
				for (int i=10; i<=200; i+=10) {
					System.out.println(i);
					try {
						Thread.sleep(3000);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		};
		
		Thread th = new Thread(run2);
		th.run();
	}
	
	/**
	 * Thread3 in ra các số lẻ 1-->20 dừng 5s mỗi lần in
	 * @throws InterruptedException
	 */
	public void thread3() {
		Runnable run3 = new Runnable() {
		
			@Override
			public void run() {
				for (int i=1; i<20; i+=2) {
					System.out.println(i);
					try {
						Thread.sleep(5000);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		};
		
		Thread th = new Thread(run3);
		th.run();
	}

}
