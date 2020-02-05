package step6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//# 학생성적관리 프로그램(2단계)[문제]

class Student{
	private int code; //학번
	private int score; //점수
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "[" + code + " : " + score + "]";
	}	
}

class School{
	private List<Student> list = new ArrayList<>();
	
	public void addStudent(int code, int score) {
		//학번과 코드를 입력받아서 list에 넣어주는 매서드
		boolean is = false;
		for(Student st : list) {
			if(st.getCode() == code) is = true; 
		}
		if(is) {
			System.out.println("이미 있는 코드입니다. 확인하세요");
		} else {
			Student st = new Student();
			st.setScore(score);
			st.setCode(code);
			list.add(st);
			System.out.println("입력 완료");
		}
	}
	
	public void totalStatus() {
		//전교생의 총점과 평균을 다음과 같은 형식으로 출력
		// 성적데이터수 : 5개 
		// 총점 : 245점
		// 평균 : 49.00점
		// 1등 학생 : 1001번 (94점)
		// 꼴등 학생 : 1009번(7점)
		if(list.size() <=0) {
			System.out.println("데이터가 없습니다. 데이터를 넣어주세요");
			return;
		}
		int sum = 0;
		int first = -1;
		int max = 0;
		int last = -1;
		int min = 100;
		for(int i = 0; i<list.size(); i++) {
			sum+= list.get(i).getScore();
			if(list.get(i).getScore() > max) {
				max = list.get(i).getScore();
				first = i;
				
			}
			
			if(list.get(i).getScore() < min) {
				min = list.get(i).getScore();
				last = i;
			}
		}
		
		System.out.println("성적 데이터 수 : " + list.size() +"개");
		System.out.println("총점 : "+sum+"점");
		System.out.println("평균 : "+(float) sum / list.size() + "점");
		System.out.println("1등 학생 : "+list.get(first).getCode()+"번 (" + list.get(first).getScore()+ "점)");
		System.out.println("꼴등 학생 : "+list.get(last).getCode()+"번 (" + list.get(last).getScore()+"점)");
	}
	
	public void printSuccess() {
		//합격한 학생을 다음과 같은 양식으로 출력하는 매서드 
		// 학번 : 1001 , 점수 : 84 => 합격!
		// 학번 : 1003 , 점수 : 95 => 합격!
		// 학번 : 1007 , 점수 : 72 => 합격!
		// =============================
		// 총합격자수 : 3명
		int cnt = 0;
		for(Student st : list) {
			if(st.getScore() > 60) {
				System.out.println("학번 : "+st.getCode() +" , "+"점수 : " +st.getScore()+" => 합격!");
				cnt++;
			}
		}
		
		System.out.println("==============================");
		System.out.println("총 합격자 수 : "+cnt+"명");
	}
	
	public void printStudent(int code) {
		//학번을 입력받아서 해당 학생을 출력하는 매서드
		//Student의 toString을 활용할 것.
		Student t = null;
		for(Student st : list) {
			if(st.getCode() == code) {
				System.out.println(st.toString());
				t = st;
			}
		}
		
		if(t == null) {
			System.out.println("없는 학번 입니다");
		}
	}
	
	public void searchStudent(int score) {
		//점수를 입력받아 해당 점수보다 높은 학생을 모두 출력 출력은 Student의 toString을 활용
		for(Student st : list) {
			if(st.getScore() > score) {
				System.out.println(st.toString());
			}
		}
	}
}

public class Ex02 {
	public static void main(String[] args) {
		
		School school = new School();
		//while 루프를 이용하여 다음과 같은 메뉴를 구성할 것.
		// [학생 성적관리 프로그램]
		// [1. 학생 데이터 입력 ]
		// [2. 전교생 총점 및 평균 출력 ]
		// [3. 합격생 수 출력 ]
		// [4. 학생 정보 출력 ]
		// [5. 성적으로 검색 ]
		
		// 문제1) addStudent 매서드를 활용하여 학생 5명을 입력
		// 예   1) 학번 1001, 점수 20 등등 으로 5명 입력
		// 단 학번은 중복 불가로 입력할 것. 
		
		// 문제2) totalStatus 매서드를 이용해서 전교생의 총점과 평균 출력
		
		// 문제3) 성적이 60점 이상이면 합격. 합격생 수 출력
		// 예   3) 2명
		
		// 문제4) 학생의 학번을 입력하여 printStudent 매서드를 이용해서 학생 정보 출력
		// 예4) 학번 입력 : 1001
		// 단 없는 학번의 경우 예외처리를 한다.
		
		// 문제5) 성적을 입력받아 해당 점수보다 상위권의 학생을 출력
		// 예5) 성적 입력 : 70
		Scanner in = new Scanner(System.in);
		boolean run = true;
		while(run) {
			System.out.println("[학생 성적관리 프로그램]");
			System.out.println("[1. 학생 데이터 입력]");
			System.out.println("[2. 전교생 총점 및 평균 출력]");
			System.out.println("[3. 합격생 수 출력]");
			System.out.println("[4. 학생 정보 출력]");
			System.out.println("[5. 성적으로 검색]");
			
			System.out.print("메뉴를 입력하세요  ");
			int sel = in.nextInt();
			
			if(sel == 1) {
				System.out.println("학번을 입력하세요");
				int code = in.nextInt();
				System.out.println("점수를 입력하세요");
				int score = in.nextInt();
				
				school.addStudent(code, score);
			} else if( sel == 2) {
				school.totalStatus();
			} else if( sel == 3) {
				school.printSuccess();
			} else if(sel == 4) {
				System.out.println("학번을 입력하세요");
				int code = in.nextInt();
				school.printStudent(code);
			} else if(sel == 5) {
				System.out.println("검색할 점수를 입력하세요");
				int score = in.nextInt();
				school.searchStudent(score);
			}
		}
	}
}
