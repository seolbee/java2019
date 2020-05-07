package test;

public class SimpleMin {

	public static void main(String[] args) {
		int[] data = {6,2,9,8,1,4,17,5};//배열 선언
		int size = data.length-1;//배열의 인덱스
		int min = 100;//최댓값을 저장하는 변수
		for(int i = 0; i<=size; i++) {
			if(data[i] < min)
				min = data[i];
		}
		System.out.println("최솟값은 "+min+"입니다.");
	}

}
