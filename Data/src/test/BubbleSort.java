package test;

import java.util.Arrays;

public class BubbleSort {

	public static void main(String[] args) {
		int[] data = {3,4,5,1};
		int size = data.length;
		int temp;
		System.out.println("<<초기상태>>"+Arrays.toString(data));
		for(int i = 1; i<size; i++) {
			for(int j = 0; j < size-i; j++) {
				if(data[j] > data[j+1]) {
					temp = data[j];
					data[j] = data[j+1];
					data[j+1] = temp;
				}
			}
			System.out.println(i+"단계 : "+Arrays.toString(data));
		}
		System.out.println("<<정렬 상태>>"+Arrays.toString(data));

	}

}
