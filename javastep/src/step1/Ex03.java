package step1;

import java.util.Scanner;

/*
 * # 화폐매수[문제]
 * 금액을 입력받아 해당 금액의 화폐 종류 별 화폐 매수를 출력한다.
 * 예시)
 * 만약 87900원이 입력되었다면 다음과 같이 출력된다. (화폐단위는 한국을 기준으로 하며 없는 단위의 매수는 출력되지 않는다.)
 * 5만원 : 1장
 * 1만원 : 3장
 * 5천원 : 1장
 * 1천원 : 2장
 * 5백원 : 1개
 * 1백원 : 4개
 */

public class Ex03 {
	public static void main(String[] args) {

		int money;
		Scanner in = new Scanner(System.in);
		
		System.out.println("금액을 입력받으세요");
		money = in.nextInt();
		
		int[] arr = {50000, 10000, 5000, 1000, 500, 100, 10, 5, 1};
		for(int i = 0; i<arr.length; i++) {
			int cnt = money / arr[i];
			if(cnt > 0) {
				System.out.println(arr[i] + "원 : " + cnt+"장");
				money = money % arr[i];
			}
		}
	}
}
