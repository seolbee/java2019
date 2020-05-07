package test;
//배열에서 최댓값을 찾는 검색 알고리즘 구현 : 반복문
public class SimpleSearch {
	public static void main(String[] args) {
		int[] data = {6,2,9,8,1,4,17,5};//배열 선언
		int size = data.length-1;//배열의 인덱스
		int result = 0;//최댓값을 저장하는 변수
		for(int i = 0; i<=size; i++) {
			if(data[i] > result)
				result = data[i];
		}
		System.out.println("최댓값은 "+result+"입니다.");
	}	
}
