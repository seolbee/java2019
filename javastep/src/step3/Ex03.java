package step3;

import java.util.ArrayList;
import java.util.Random;

/*
 * # 랜덤학생[문제]
 * 1. 10회 반복을 한다.
 * 2. 1~100 사이의 랜덤 숫자를 저장한다.
 * 3. 위 숫자가 60점 이상이면, 합격생이다.
 * ------------------------------
 * 4. 10명 학생의 총점과 평균을 출력한다.
 * 5. 합격생 수를 출력한다.
 * 6. 1등학생의 번호와 성적을 출력한다.
 * 
 * 예) 87 11 92 42 100 23 68 33 8 75
 * [1] 총점 = 539점
 * [2] 평균 = 53.9점
 * [3] 합격생 수 = 5명
 * [4] 1등 = 100점(5번)
 */

public class Ex03 {
	public static void main(String[] args) {
		Random ran = new Random();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		
		for(int i = 0; i<10; i++) {
			int num = ran.nextInt(99) + 1;
			arr.add(num);
		}
		
		int sum = 0;
		int cnt = 0;
		int max = 0;
		for(Integer num : arr) {
			sum += num;
			if(num >= 60) {
				cnt++;
			}
			
			if(num >= max) {
				max = num;
			}
		}
		System.out.println(arr.toString());
		System.out.println("총점  : "+sum+"점");
		System.out.println("평균 : "+(float) sum/arr.size()+"점");
		System.out.println("합격생 수 : "+cnt+"명");
		System.out.println("1등 :"+max+"점 ("+(arr.indexOf(max) + 1)+"번)");
	}
}
