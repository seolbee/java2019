package step4;

import java.util.Arrays;
import java.util.Scanner;

/*
 * # ATM(3단계)[문제]
 * 1. 가입
 * . 계좌번호와 비밀번호를 입력받아 가입
 * . 계좌번호 중복검사
 * . 5명이상 입력시 배열의 크기를 자동으로 늘려줌
 * 2. 탈퇴
 * . 계좌번호를 입력받아 탈퇴
 * 
 * 주의:리스트 사용금지
 */

public class Ex04 {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int[] accs = {1001, 1002, 0, 0, 0}; //계좌번호 배열
		int[] pws  = {1111, 2222, 0, 0, 0}; //비밀번호 배열
		
		int cnt = 2;
		
		boolean run = true;
		while(run) {
			
			System.out.println("1.가입");
			System.out.println("2.탈퇴");
			
			System.out.print("메뉴 선택 : ");
			int sel = scan.nextInt();
			
			if(sel == 1) {
				System.out.println("가입할 계좌번호를 입력하세요");
				int acc = scan.nextInt();
				boolean is = false;
				for(int num : accs) {
					if(num == acc) {
						is = true;
						break;
					}
				}
				if(is) {
					System.out.println("이미 있는 계좌번호 입니다.");
					continue;
				}
				System.out.println("가입할 비밀번호를 입력하세요");
				int pw = scan.nextInt();
				if(cnt >= accs.length) {
					int[] arr = new int[accs.length+1];
					int[] arr2 = new int[accs.length+1];
					for(int i = 0; i<accs.length; i++) {
						arr[i] = accs[i];
						arr2[i] = pws[i];
					}
					arr[cnt] = acc;
					arr2[cnt] = pw;
					accs = arr;
					pws = arr2;
				} else {
					accs[cnt] = acc;
					pws[cnt] = pw;
				}
				cnt++;
				System.out.println(Arrays.toString(accs));
				System.out.println(Arrays.toString(pws));
				System.out.println("추가되었습니다.");
				
			}
			else if(sel == 2) {
				System.out.println("계좌번호를 입력하세요");
				int acc = scan.nextInt();
				int idx = -1;
				for(int i = 0; i<accs.length; i++) {
					if(accs[i] == acc) {
						idx = i;
						break;
					}
				}
				if(idx <0) {
					System.out.println("없는 계좌번호 입니다.");
					continue;
				}
				int[] ar = Arrays.copyOf(accs, idx);
				int[] ry = Arrays.copyOfRange(accs, idx+1, accs.length);
				int[] array = new int[accs.length-1];
				System.arraycopy(ar, 0, array, 0, ar.length);
				System.arraycopy(ry, 0, array, idx, ry.length);
				accs = array;
				ar = Arrays.copyOf(pws, idx);
				ry = Arrays.copyOfRange(pws, idx+1, pws.length);
				array = new int[pws.length-1];
				System.arraycopy(ar, 0, array, 0, ar.length);
				System.arraycopy(ry, 0, array, idx, ry.length);
				pws = array;
				System.out.println(Arrays.toString(accs));
				System.out.println(Arrays.toString(pws));
			}
			
		}
		
		scan.close();
		
	}
}
