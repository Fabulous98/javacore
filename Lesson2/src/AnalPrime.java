/**
 * Copyright(C) 2019 LuvinaSoftware
 * AnalPrime.java, Nov 28, 2019, MDung
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Chuong trinh phan tich thua so nguyen to
 * @author MDung
 */
public class AnalPrime {

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
        // phan tich n
        List<Integer> listNumbers = analysisInt(n);
        // in ket qua ra man hinh
        System.out.printf("Kết quả sau khi phân tích: "+n+" = ");
        int size = listNumbers.size();
        for (int i = 0; i < size - 1; i++) {
            System.out.print(listNumbers.get(i) + "*");
        }
        System.out.print(listNumbers.get(size - 1));
        scanner.close();
	}
	/**
	 * Ham phan tich thua so nguyen to (primes)
	 *
	 * @param n
	 * @return list primes
	 */
	public static List<Integer> analysisInt(int n) {
        int i = 2;
        List<Integer> listNumbers = new ArrayList<Integer>();
        // phan tich
        while (n > 1) {
            if (n % i == 0) {
                n = n / i;
                listNumbers.add(i);
            } else {
                i++;
            }
        }
        // neu listNumbers trong thi them n vao listNumbers
        if (listNumbers.isEmpty()) {
            listNumbers.add(n);
        }
        return listNumbers;
    }

}
