package test;

public class RecursiveFibo {
	private static int fibonacci(int num) {
		if(num == 1) return 1;
		else if(num == 2) return 2;
		else return fibonacci(num - 1) + fibonacci(num - 2);
	}
	
	public static void main(String[] args) {
		System.out.println("피보나치 수열의 4번째 원소는 "+fibonacci(4)+"입니다.");
	}

}
