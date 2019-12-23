/**
 * Copyright(C) 2019 LuvinaSoftware
 * ListPrime.java, Nov 29, 2019, MDung
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Description
 * @author MDung
 */
public class ListPrime {

	/**
	 * Main
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Nhap so nguyen duong n
		System.out.print("Nhập số nguyên dương n = ");
		Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        // Tim cac so nguyen to nho hon n
        List<Integer> listNumbers = analysisInt(n);
        // in ket qua ra man hinh
        System.out.printf("Các số nguyên tố nhỏ hơn %d: ", n);
        int size = listNumbers.size();
        for (int i = 0; i < size - 1; i++) {
            System.out.print(listNumbers.get(i) + ", ");
        }
        System.out.print(listNumbers.get(size - 1));
        scanner.close();
	}
	
	/**
	 * Ham tim cac so nguyen to (primes) nho hon n
	 *
	 * @param n
	 * @return list primes<n
	 */
	public static List<Integer> analysisInt(int n) {
        int i = 2;
        List<Integer> listNumbers = new ArrayList<Integer>();
        // lay cac so nguyen to vao list
        boolean jump=false;
        while (n > 1) {
            if (n % i == 0) {
                n = n / i;
                // bien jump dam bao de so nguyen to khong trung nhau trong list
                if (jump==false) listNumbers.add(i);
                jump=true;
            } else {
                i++;
                jump=false;
            }
        }
        // neu listNumbers trong thi them n vao listNumbers
        if (listNumbers.isEmpty()) {
            listNumbers.add(n);
        }
        return listNumbers;
    }

}
