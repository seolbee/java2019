package test;

public class RecursiveTest {

	public static void main(String[] args) {
		System.out.println("재귀함수란? 자신을 호출하는 형태로 만들어진 함수");
		Recursive(3);
	}
	
	public static void Recursive(int num) {
		//종료값
		if(num <= 0) {
			System.out.println("재귀함수 호출 종료");
			return;
		} else {
			System.out.println("재귀함수 호출(파라매터 값) : " + num);
			Recursive(num-1);
			System.out.println("리턴 완료"+num);
		}
	}

}
