package main;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class Timer extends Thread{
	
	public Integer hour;
	
	public Integer minute;
	
	public Integer second;
	
	public int sec;
	
	public Label lblHour;
	
	public Label lblminute;
	
	public Label lblsecond;

	private boolean start = false;
	
	private boolean stop = false;
	
	private boolean quit = false;
	
	public Timer(Label hour, Label minute, Label second) {
		this.lblHour = hour;
		this.lblminute = minute;
		this.lblsecond = second;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(10000);
				sec++;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("쓰레드 오류");
			}
			
			hour = sec / 3600;
			minute = sec % 3600/60;
			second = sec % 3600;
			
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					lblHour.setText(hour.toString());
					lblminute.setText(minute.toString());
					lblsecond.setText(second.toString());
				}
			});
		}
	}
}
