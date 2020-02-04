package step4;

import java.util.Arrays;
import java.util.Scanner;

/*
 * # 배열 컨트롤러(1단계)[문제]
 * 1) 추가 : 맨 끝에 삽입
 * 2) 삭제 : 삭제를 원하는 인덱스를 입력하면 삭제
 * 3) 삽입 : 삽입을 원하는 인덱스와 값을 입력하면 삽입
 * 
 * 주의 : 리스트 사용금지!
 *  
*/

public class Ex03 {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int[] arr = {10, 20, 0, 0, 0};
		int cnt = 2;
		
		boolean run = true;
		while(run) {
			
			System.out.println("[1]추가");
			System.out.println("[2]삭제");
			System.out.println("[3]삽입");
			System.out.println("[0]종료");
			
			System.out.print("메뉴 선택 : ");
			int sel = scan.nextInt();
			
			if(sel == 1) {
				System.out.print("추가할 값 입력 : ");
				int data = scan.nextInt();
				if(cnt >= arr.length) {
					int[] array = new int[arr.length+1];
					for(int i = 0; i<arr.length; i++) {
						array[i] = arr[i];
					}
					array[cnt] = data;
					arr = array;
				} else {
					arr[cnt] = data;
				}
				cnt++;
				System.out.println(Arrays.toString(arr));
			}
			else if(sel == 2) {
				System.out.print("삭제할 값 입력 : ");
				int data = scan.nextInt();
				if(data >= arr.length) {
					System.out.println("없는 인덱스 입니다");
					continue;
				} else {
					arr[data] = -1;
					int[] array = new int[arr.length-1];
					int[] ar = Arrays.copyOf(arr, data);
					int[] ry = Arrays.copyOfRange(arr, data+1, arr.length);
					System.arraycopy(ar, 0, array, 0, ar.length);
					System.arraycopy(ry, 0, array, data, ry.length);
					arr = array;
					System.out.println(Arrays.toString(arr));
				}
				
			}
			else if(sel == 3) {
				
				System.out.print("삽입할 위치 입력 : ");
				int idx = scan.nextInt();
				
				if(idx >= arr.length) {
					System.out.println("배열의 크기를 벗어난 인덱스 입니다.");
					continue;
				}
				
				System.out.print("삽입할 값 입력 : ");
				int data = scan.nextInt();
				
				int[] array = new int[arr.length+1];
				int[] ar = Arrays.copyOf(arr, idx+1);
				int[] ry = Arrays.copyOfRange(arr, idx, arr.length);
				for(int i = 0; i<ar.length; i++) {
					array[i] = ar[i];
				}
				array[idx] = data;
				System.arraycopy(ry, 0, array, idx+1, ry.length);
				System.out.println(Arrays.toString(array));
				cnt++;
			}
			else if(sel == 0) {
				run = false;
				System.out.println("프로그램 종료");
			}
			
		}
		
		scan.close();
		
	}
}
