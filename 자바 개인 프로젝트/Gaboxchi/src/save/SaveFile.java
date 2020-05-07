package save;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class SaveFile {
	private Double ex;
	private Double weight;
	private String Ident;
	private String image;
	
	public SaveFile(Double ex, Double weight, String Ident, String image) {
		File file = new File(getClass().getResource("data/userSave.txt").getFile());
		this.ex = ex;
		this.weight = weight;
		this.Ident = Ident;
		this.image = image;
		try {
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			
			bw.write("\n몸무게 : "+this.weight+"\n랭킹 : "+ this.Ident + "\n이미지 : " + this.image + "\n경험치 : "+this.ex);
			bw.flush();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("파일이 없습니다.");
		}
	}
}
