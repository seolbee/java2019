package step1;

import java.util.Scanner;

public class Ex01 {
	public static void main(String[] args) {

		double kor, eng, math;
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("국어 점수를 입력하세요");
		kor = in.nextDouble();
		
		System.out.println("영어 점수를 입력하세요");
		eng = in.nextDouble();
		
		System.out.println("수학 점수를 입력하세요");
		math = in.nextDouble();
		
		double a = (kor+eng+math) / 3;
		
		if((kor >=50 && eng >= 50 && math >= 50) && a >= 60) {
			System.out.println("합격");
		} else {
			System.out.println("불합격");
		}
		
	}
}
