package game;

import java.util.Random;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class MainGame {
	private GraphicsContext gc;
	private int gap = 5; //블럭과 블럭간의 사이 크기
	private int size = 30; // 블럭의 가로세로 사이즈
	private int color = 4; //색상간의 간격
	
	private Integer[][] board;
	private int[][] reveal; //밝히다
	
	private boolean debug = false;
	
	private int mineCnt = 10;
	
	private int flagCnt = 0;// 현재 꽃힌 깃발의 갯수를 센다.
	private boolean gameover = false;
	
	public MainGame(GraphicsContext gc) {
		this.gc = gc;
		board = new Integer[10][10]; //보드판 할당
		reveal = new int[10][10]; //밝혀진 판 
		initGame(); //게임 초기화
	}
	
	public void initGame() {

		for(int i = 0; i <board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				board[i][j] = 0;
				reveal[i][j] = MineStaus.LOCKED;
			}
		}
		
		//지뢰배치
		int[] minePos = new int[100];
		for(int i = 0; i < minePos.length; i++) {
			minePos[i] = i;
		}
		Random rnd = new Random();
		for(int i = 0; i < mineCnt; i++) {
			int idx = rnd.nextInt(100 - i);
			int pos = minePos[idx];
			minePos[idx] = minePos[100-i-1];
			
			int y = pos / 10;
			int x = pos % 10;
			
			board[x][y] = -1; //-1은 지뢰를 뜻함.
		}
		//각 칸의 숫자 계산
		for(int i = 0; i< board.length; i++) {
			for(int j = 0; j<board[i].length; j++) {
				if(board[i][j] != -1) {
					board[i][j] = checkCount(i, j);
				}
			}
		}
		
	}

	private int checkCount(int y, int x) {
		int cnt = 0;
		
		for(int i = -1; i<=1; i++) {
			for(int j = -1; j <= 1; j++) {
				if(x+j < 0 || x+j >= 10 ||
						y+i<0|| y+i >= 10 ||( i==0 && j==0) ) {
					continue;
				}
				if(board[y+i][x+j] == -1) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	public void render() {
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.setStroke(Color.rgb(240, 240, 240));
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				int x = gap + (size+gap) * j;
				int y = gap + (size+gap) * i;
				
				gc.setFill(Color.rgb(86, 98, 112));
				gc.fillRect(x, y, size, size);
				
				gc.setFill(Color.rgb(165, 147, 224));
				gc.fillRect(x + color, y + color,
							size - 2*color, size - 2*color);
				
				if(debug) {
					gc.strokeText(board[i][j].toString(), x + size/2, y + size/2);
				} else if(reveal[i][j] == MineStaus.REVEAL) {
					gc.strokeText(board[i][j].toString(), x + size/2, y + size/2);
				} else if(reveal[i][j] == MineStaus.FLAGED) {
					gc.setFill(Color.rgb(40, 89, 67));
					gc.fillRoundRect(x+color, y+color, size - 2 * color, size - 2*color, 4,4);
				}
			}
		}
	}
	
	public void clickHandle(MouseEvent e) {
		if(gameover) return;
		double mouseX = e.getX();
		double mouseY = e.getY();
		
		int bs = gap + size; //블록 크기

		if(mouseX % bs < gap || mouseY % bs < gap) {
			//갭을 클릭하면 무시한다.
			return;
		}
		
		int x = (int) (mouseX / bs);
		int y = (int) (mouseY / bs);
		
		if(x >= 10 || y >= 10) {
			//게임판 밖은 무시한다.
			return;
		}
		
		MouseButton btn = e.getButton();
		if(btn == MouseButton.SECONDARY) {
			rightClick(y,x);
		} else if(btn==MouseButton.PRIMARY) {
			leftClick(y,x);
		}
		
		render(); //마우스 버튼 클릭 뒤에는 반듯이 판이 새로 그려지도록 함.
	}
	private void rightClick(int y, int x) {
		if(reveal[y][x] == MineStaus.FLAGED) {
			reveal[y][x] = MineStaus.LOCKED;
			flagCnt--;
			return;
		}
		if(flagCnt >= mineCnt) {
			System.out.println("설정할 수 이쓴 최대 깃발 수 초과");
			return;
		}
		reveal[y][x] = MineStaus.FLAGED;
		flagCnt++;
		
		if(checkGame()) {
			gameover = true;
			System.out.println("게임 클리어");
		}
	}
	private boolean checkGame() {
		int cnt = 0;
		for(int i = 0; i<board.length; i++) {
			for(int j = 0; j<board[i].length; j++) {
				if(board[i][j] == -1 && reveal[i][j]==MineStaus.FLAGED) {
					cnt++;
				}
			}
		}
		return cnt == mineCnt;
	}
	
	private void leftClick(int y, int x) {
		if(reveal[y][x] == MineStaus.REVEAL || reveal[y][x] == MineStaus.FLAGED) {
			return;
		}
		reveal[y][x] = MineStaus.REVEAL;
		if(board[y][x] == -1) {
			System.out.println("게임오버");
			gameover = true;
		}
	}
}
