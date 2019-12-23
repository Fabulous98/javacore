/**
 * Copyright(C) 2019 LuvinaSoftware
 * Thread1.java, Dec 23, 2019, MDung
 */

/**
 * Thread1 tạo theo kiểu Runnable
 * @author MDung
 */
public class Thread1 {
	/**
	 * Tạo biến runnable và override run()
	 * @throws InterruptedException
	 */
	Runnable rb = new Runnable() {
		@Override
		public void run() {
			//in ra giá trị i
			for (int i = 0; i < 10; i++) {
				System.out.println("---Runnable: " + i);
				try {
					Thread.sleep(200);// Delay 0.2s
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};
	
	/**
	 * Tạo thread, chạy thread, kiểm tra thread sống hay chết, in ra tên, mức độ ưu tiên thread
	 * @throws InterruptedException
	 */
	public void thread1(){
		
		Thread th = new Thread(rb);
		th.setName("One");
		System.out.println("---Tên Thread 1: "+th.getName());
		th.setPriority(5);
		System.out.println("---Mức độ ưu tiên của Thread 1: "+th.getPriority());
		// chạy thread
		th.start();
		// kiểm tra thread live hay dead
		if (th.isAlive()) {
			System.out.println("---Thread 1 còn sống trong khi run!");
		}else {
			System.out.println("---Thread 1 chet trong khi run!");
		}
		
		try {
		// chờ 6s cho thread chạy xong
		th.join(6000);
		
		// Khi thread chạy xong thì báo thread die
		if (th.isAlive()) {
			System.out.println("---Thread 1 còn sống sau khi run!");
		}else {
			System.out.println("---Thread 1 chet sau khi run!");
		}
		
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
