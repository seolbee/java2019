package test;

public class RecursiveFact {
	public static int factorial(int num) {
		if(num == 1) return 1;
		return num * factorial(num - 1);
	}
	
	public static void main(String[] args) {
		System.out.println("4 팩토리얼은 "+factorial(4)+"입니다.");
	}

}
