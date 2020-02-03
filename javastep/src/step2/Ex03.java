package step2;

import java.util.Scanner;

/*
 * # ATM[문제]
 * [1]로그인
 * . 계좌번호와 비밀번호를 입력받아 로그인을 처리한다.
 * . 이미 로그인 된 상태에서 다시 이용할 수 없다.
 * [2]로그아웃
 * . 로그아웃 상태에서 이용할 수 없다.
 * [3]입금
 * . 로그인 상태에서 이용할 수 있다.
 * . 입금할 금액을 입력받아 입금을 진행한다.
 * [4]출금
 * . 로그인 상태에서 이용할 수 있다.
 * . 출금할 금액이 계좌잔액을 초과할 경우 출금을 진행할 수 없다.
 * [5]이체
 * . 로그인 상태에서 이용할 수 있다.
 * . 이체할 계좌번호를 입력받아 처리한다.
 * . 이체할 금액이 계좌잔액을 초과할 경우 이체를 진행할 수 없다.
 * [6]잔액조회
 * . 로그인 상태에서 이용할 수 있다.
 * . 로그인 된 회원의 계좌잔액을 출력한다.
 */


public class Ex03 {
	
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		String acc1 = "gondr";
		String pw1 = "1234";
		int money1 = 10000;
		
		String acc2 = "yydh";
		String pw2 = "1234";
		int money2 = 20000;
		
		/*
		 * 이곳에 필요한 변수를 선언하세요.
		 * (Hint. 로그인상태를 저장하거나 누가 로그인되었는지를 저장하는 변수가 필요합니다.)
		 */
		
		boolean status = false;
		String userId= null;
		String userPw = null;
		int userMoney = 0;
		
		while(true) {
			System.out.println("[MEGA ATM]");
			System.out.println("[1]로그인");
			System.out.println("[2]로그아웃");
			System.out.println("[3]입금");
			System.out.println("[4]출금");
			System.out.println("[5]이체");
			System.out.println("[6]잔액조회");
			System.out.println("[0]종료");
			
			System.out.print("메뉴를 선택하세요 : ");
			int sel = scan.nextInt();
			
			/* 아래의 중괄호에 알맞은 코드를 작성하세요 */
			if(sel == 1) {
				if(status) {
					System.out.println("이미 로그인했습니다.");
					continue;
				}
				System.out.println("계좌번호 입력");
				String an = scan.next();
				System.out.println("비밀번호 입력");
				String pw = scan.next();
				if(!an.contains(acc1) && !an.contains(acc2)) {
					System.out.println("계좌번호가 틀렸습니다. 다시 확인해보세요");
					continue;
				}
				
				if(!pw.contains(pw1) || !pw.contains(pw2)) {
					System.out.println("비밀번호가 틀렸습니다. 확인해보세요");
					continue;
				}
				
				status = true;
				userId = an;
				userPw = pw;
				userMoney = userId.contains(acc1) ? money1 : money2;
				System.out.println("로그인 완료");
				continue;
			}
			else if(sel == 2) {
				if(!status) {
					System.out.println("로그인 되지 않았습니다.");
					continue;
				}
				userId = null;
				userPw = null;
				userMoney = 0;
				status = false;
				System.out.println("로그아웃");
			}
			else if(sel == 3) {
				if(!status) {
					System.out.println("로그인 되지 않았습니다.");
					continue;
				}
				System.out.println("입금 금액을 입력하세요");
				int money = scan.nextInt();
				userMoney += money;
				continue;
			}
			else if(sel == 4) {
				if(!status) {
					System.out.println("로그인 되지 않았습니다.");
					continue;
				}
				System.out.println("출금할 금액을 입력하세요");
				int money = scan.nextInt();
				if(userMoney < money) {
					System.out.println("현재 잔액보다 출금 금액이 더 적습니다.");
					continue;
				}
				userMoney -= money;
				System.out.println("출금 완료");
				continue;
			}
			else if(sel == 5) {
				if(!status) {
					System.out.println("로그인 되지 않았습니다.");
					continue;
				}
				
				System.out.println("이체할 계좌번호를 입력하세요");
				String acc = scan.next();
				
				if(!acc.contains(acc1) && !acc.contains(acc2)) {
					System.out.println("없는 계좌번호 입니다.");
					continue;
				}
				
				System.out.println("보낼 금액을 입력하세요");
				int money = scan.nextInt();
				
				if(userMoney < money) {
					System.out.println("계좌에 있는 금액보다 이체 금액이 더 큽니다.");
					continue;
				}
				
				userMoney -= money;
				
				if(acc.contains(acc1)) {
					money1 += money;
				} else {
					money2 += money;
				}
				System.out.println("이체 완료");
				continue;
			}
			else if(sel == 6) {
				if(!status) {
					System.out.println("로그인 되지 않았습니다.");
					continue;
				}
				System.out.println("현재 고객님의 계좌 잔액은 "+userMoney+"원 입니다.");
			}
			else if(sel == 0) {
				System.out.println("[메세지]프로그램 종료");
				break;
			}
			
		}
		
		scan.close();
	}
}
