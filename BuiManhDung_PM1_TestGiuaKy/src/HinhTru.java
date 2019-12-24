/**
 * 
 */

/**
 * @author LA-PM
 *
 */
public class HinhTru extends HinhTron {
	int height;
	
	public HinhTru() {
		
	}
	
public HinhTru(int a, int b) {
		this.r = a;
		this.height = b;
	}

public int getHeight() {
	return this.height;
}

public float getTheTich() {
	return (float)(this.height*this.getDienTich());
}

}
