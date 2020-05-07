package test;
//욕심쟁이 기법 - 결과를 얻기 위해서 최솟값 또는 최댓값을 가진 데이터들만 추출
public class GetChange {
//거스름돈:큰 잔돈부터 나눠주는 방법
	public static int price;//거스름돈
	public static int count;//잔돈의 종류 세는 변수
	public static int[] coinArr = {10,6,4,1};//잔돈의 종류 선언

	public static void main(String[] args) {
		price = 128;
		System.out.println("거스름돈 : "+price+"원");
		for(int coin : coinArr) getCount(coin);
		System.out.println("잔돈의 종류 : "+count+"개");
	}
	
	public static void getCount(int coin) {
		int check = price / coin;
		//판별된 잔돈 종류 갯수 추가
		if(check != 0) {
			count++;
		}
		System.out.println("잔돈 : "+coin+"원 "+check+"개");
		//거스름돈에서 잔돈 종류를 빼서 남은 거스름돈을 변경함.
		price = price - (coin * check);
	}
}
