/**
 * 
 */

/**
 * @author LA-PM
 *
 */
public class HinhTron {
	
	int r;
	
	public HinhTron() {
		
	}
	
	public HinhTron(int a) {
		this.r = a;
	}
	
	public int getRadian() {
		return this.r;
	}
	
	public float getDienTich() {
		return (float)(r*r*3.14);
	}
	
	public String toString() {
		return "ban kinh: " + this.r + " dien tich " + this.getDienTich();
	}
	

}
