/**
 * Copyright(C) 2019 LuvinaSoftware
 * Thread2.java, Dec 23, 2019, MDung
 */

/**
 * Thread2 theo kiểu extends Thread
 * @author MDung
 */

	public class Thread2 extends Thread {

		/**
		 * Override run() in ra 10 số nguyên đầu tiên
		 * @throws InterruptedException
		 */
		
		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				System.out.println("+++extends Thread: " + i);
				try {
					Thread.sleep(200);//Delay 0.2s
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	
		}
		
		/**
		 * set một số giá trị mặc định như tên, mức ưu tiên, Daemon cho Thread
		 */
		
		public void setThread2() {
			this.setDaemon(true); //Set Daemon
			this.setName("Two"); //Set tên Thread
			System.out.println("+++Tên Thread 2: "+this.getName());//In ra tên Thread
			this.setPriority(8);//Set mức ưu tiên
			System.out.println("+++Mức độ ưu tiên của Thread 2: "+this.getPriority());//In ra mức ưu tiên
		}
		
		/**
		 * check xem sau khi hoàn thành thread còn sống không, in ra thông báo tương ứng
		 * @throws InterruptedException
		 */
		public void thread2() {
			try {
				this.join(6000);
				
				if (this.isAlive()) {
					System.out.println("+++" + Thread.currentThread().getName() + " còn sống trong khi run!");
				}else {
					System.out.println("+++Thread 2 chet sau khi run!");
				}
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
		}
	}
