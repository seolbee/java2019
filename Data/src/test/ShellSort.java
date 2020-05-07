package test;

import java.util.Arrays;

public class ShellSort {

	public static void main(String[] args) {
		int[] data = {54,34,41,89,67,16,52,23};
		int size = data.length;
		int temp = 0, d= size / 2;
		System.out.println("<<정렬 전>>");
		System.out.println(Arrays.toString(data));
		while(d>0) {
			System.out.println(d+"-정렬");
			for(int i = d; i<size; i++) {
				for(int j = i; j>=d && data[j-d] > data[j]; j-=d) {
					temp = data[j-d];
					data[j-d] = data[j];
					data[j] = temp;
				}
			}
			d = d/2;
		}
		System.out.println("\n<<정렬 후>>");
		System.out.println(Arrays.toString(data));
	}

}
