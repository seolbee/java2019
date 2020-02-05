package step5;

import java.util.Arrays;
import java.util.Scanner;

/*
 * # 최대값 구하기(3단계)[문제]
 * 1. 가장 큰 값을 찾아 입력한다.
 * 2. 정답이면 해당 값을 0으로 변경한다.
 * 3. arr배열의 모든 값이 0으로 변경되면 프로그램은 종료된다.
 * 예)
 * 11, 87, 42, 100, 24
 * 입력 : 100
 * 
 * 11, 87, 42, 0, 24
 * 입력 : 87
 * 
 * 11, 0, 42, 0, 24
 */

public class Ex01 {
	public static void main(String[] args) {
		
		int[] arr = {11, 87, 42, 100, 24};
		
		boolean run = true;
		while(run) {
			Scanner in = new Scanner(System.in);
			System.out.print("입력 : ");
			int num = in.nextInt();
			int max = 0;
			for(int i = 0; i<arr.length; i++) {
				if(arr[i] > arr[max]) {
					max = i;
				}
			}
			
			if(num == arr[max]) {
				arr[max] = 0;
			} else {
				System.out.println("가장 큰 숫자가 아닙니다. 확인하세요");
			}
			
			boolean is = false;
			for(int anum : arr) {
				if(anum > 0) {
					is = true;
				}
			}
			if(!is) run = false;
			System.out.println(Arrays.toString(arr));
		}
	}
}
