package step3;

import java.util.Random;
import java.util.Scanner;

/*
 * # 구구단 게임(3단계)[문제]
 * 1. 1부터 9까지의 랜덤 숫자를 2개 뽑아내서,
 * 2. 곱셈 문제를 5회 출제한다.  
 * 3. 한 문제당 20점으로 게임 종료 후 게임성적을 출력한다.
 * 
 * 문제의 제시는 "5 X 4 = ?" 형식으로 제시
 * 
 * 문제가 제시되고 숫자를 입력하면  입력값에 따라 "맞음" 또는 "틀림" 을 출력해주고 최종 5번문제를 풀고나면
 * 최종 점수는 몇점인지를 표시한다.
 */

public class Ex01 {
	public static void main(String[] args) {
		Random ran = new Random();
		Scanner in = new Scanner(System.in);
		int score = 0;
		for(int i = 0; i<5; i++) {
			int num1 = ran.nextInt(9) + 1;
			int num2 = ran.nextInt(9) + 1;
			System.out.println(num1 + " X " + num2 + "= ?");
			int gap = in.nextInt();
			if(num1 * num2 == gap) {
				System.out.println("맞음");
				score += 20;
			} else {
				System.out.println("틀림");
			}
		}
		
		System.out.println("당신의 점수는 " + score + "점");
		
	}
}
