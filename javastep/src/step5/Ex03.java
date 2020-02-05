package step5;

import java.util.Scanner;

// # 관리비[문제]
//문제 1) 각층별 관리비 합 출력
		// 정답 1) 4400, 7100, 5400
		
		// 문제 2) 호를 입력하면 관리비 출력
		// 예    2) 입력 : 202	관리비 출력 : 2000
		
		// 문제 3) 관리비가 가장 많이 나온 집, 적게 나온 집 출력
		// 정답 3) 가장 많이 나온 집(201), 가장 적게 나온 집(303)
		
		// 문제 4) 호 2개를 입력하면 관리비 교체
public class Ex03 {
	public static void main(String[] args) {

		int[][] apt = {
			{101, 102, 103},	
			{201, 202, 203},	
			{301, 302, 303}	
		};
		Scanner in = new Scanner(System.in);
			
		int[][] pay = {
			{1000, 2100, 1300},	
			{4100, 2000, 1000},	
			{3000, 1600,  800}
		};
		
		for(int i = 0; i<apt.length; i++) {
			int sum = 0;
			for(int j = 0; j<apt[i].length; j++) {
				sum+= pay[i][j];
			}
			System.out.print(sum+", ");
		}
		System.out.println();
		System.out.print("호를 입력하세요 ");
		int number = in.nextInt();
		int payMoney = -1;
		for(int i = 0; i<apt.length; i++) {
			for(int j = 0; j<apt[i].length; j++) {
				if(apt[i][j] == number) {
					payMoney = pay[i][j];
				}
			}
		}
		if(payMoney >= 0) {
			System.out.println("관리비는 : "+payMoney+"입니다.");
		} else {
			System.out.println("없는 호 입니다.");
		}
		int max = 0;
		int maxNum = 0;
		int min = 10000;
		int minNum = 0;
		for(int i = 0; i < apt.length; i++) {
			for(int j = 0; j<apt[i].length; j++) {
				if(max < pay[i][j]) {
					max = pay[i][j];
					maxNum = apt[i][j];
				}
				
				if(min > pay[i][j]) {
					min = pay[i][j];
					minNum = apt[i][j];
				}
			}
		}
		
		System.out.println("관리비가 많이 나온 곳은 " + maxNum + "이고, 적게 나온 곳은 " + minNum);
		
		System.out.println("관리비를 교체할 호를 입력하세요");
		
		int num1 = in.nextInt();
		
		System.out.println("교체 당할 나머지 호를 입력하세요");
		int num2 = in.nextInt();
		
		int idx = -1;
		int jdx = -1;
		int idx2 = -1;
		int jdx2 = -1;
		
		for(int i = 0; i<apt.length; i++) {
			for(int j = 0; j<apt[i].length; j++) {
				if(num1 == apt[i][j]) {
					idx = i;
					jdx = j;
				}
				
				if(num2 == apt[i][j]) {
					idx2 = i;
					jdx2 = j;
				}
			}
		}
		
		if(idx < 0 || jdx < 0 || idx2 < 0|| jdx2 < 0) {
			System.out.println("없는 호가 있습니다.");
		} else {
			int temp = pay[idx][jdx];
			pay[idx][jdx] = pay[idx2][jdx2];
			pay[idx2][jdx2] = temp;
			System.out.println(apt[idx][jdx] + "호의 관리비 : " +pay[idx][jdx]);
			System.out.println(apt[idx2][jdx2] + "호의 관리비 : " +pay[idx2][jdx2]);
		}
	}
}
