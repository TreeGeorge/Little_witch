package little_witch.thread;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import little_witch.Starter;
import javazoom.jl.player.Player;

public class Music extends Thread {
	
	private Player player;	// 내가만든 클래스가 아닌 위에 import javazoom의 Player 클래스를 가져온것!
	private boolean is_loop; //한번만 들려주고 말지 계속 반복해서 재생할지 설정해주는 값
	private File file;							//
	private FileInputStream fis;				// 동영상보고 따라하긴 했는데 솔직히 뭔지 잘 모르겠음;; 추가 공부 필요
	private BufferedInputStream bis;			// (https://www.youtube.com/watch?v=WspD6v6CK4A)

	public Music (String name , boolean is_loop ) {	// 곡의 제목과 그곡의 무한반복 여부를 입력받을 매개변수
		try {
			this.is_loop = is_loop;	// 입력값을 받을때 정해짐 >> 매개변수(파라메터)값이 이즈 루프로 들어감
			file = new File(Starter.class.getResource("../Music/" + name).toURI()); // 해당 파일의 위치를 가져올 수 있도록 함. (.은 뒤로간다라는 뜻)
			fis = new FileInputStream(file);	
			bis = new BufferedInputStream(fis);
			player = new Player(bis); // 해당 파일을 담을 수 있도록 해준것
		} catch (Exception e) {
//			System.out.println(e.getMessage());
		}
	}
	
	public void close() {
		is_loop = false;	// 무한 반복을 시키지 않게 만듬.
		player.close();
		this.interrupt();
	}
	
	@Override
	public void run() {
		try {
			do {
				player.play();	// 곡을 실행시킨다
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} while ( is_loop );	// is_loop가 true라면 무한반복을 시킨다.
		} catch (Exception e) {
//			System.out.println(e.getMessage());
		}
	}
}
