package little_witch.thread;

// 밤 낮의 시간은 흘러가게하고 노래만 중단시키기 위해서 만든 쓰레드
public class Night_and_day_music_stop implements Runnable {

	@Override
	public void run() {

		try {
			while (!Thread.currentThread().isInterrupted()) {	// 중단상태가 아닐때까지만 반복해라
				Night_and_day.time_day_music.close();
				Night_and_day.time_night_music.close();
				Thread.sleep(1);	// 중단 상황을 이용하려면 필요했음
			}
		} catch (InterruptedException e) {
			if ( Night_and_day.time <= 1 ) {	// 시간이 밤이라면 밤노래 재생
				Night_and_day.time_night_music = new Music("time_night.mp3", true);
				Night_and_day.time_night_music.setDaemon(true);
				Night_and_day.time_night_music.start();
			} else {	// 밤이 아니라면 낮노래 재생
				Night_and_day.time_day_music = new Music("time_day.mp3", true);
				Night_and_day.time_day_music.setDaemon(true);
				Night_and_day.time_day_music.start();
			}
		}
	}
}