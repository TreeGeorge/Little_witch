package little_witch.thread;

import little_witch.Starter;

public class Night_and_day implements Runnable {
	
	public static int time = 0; // 시간
	public static Music time_day_music = new Music("time_day.mp3", true);	// Night_and_day_stop 에서 사용하기 위해서
	public static Music time_night_music = new Music("time_night.mp3", true);	// Night_and_day_stop 에서 사용하기 위해서
	
	public static int getTime() {
		return time;
	}

	public static void setTime(int time) {
		Night_and_day.time = time;
	}



	public void run() {
		
		while (!Thread.currentThread().isInterrupted()) {	// 중단상태가 아닐때까지만 반복해라
			try {	
				// 밤이 시작되면 밤 노래 재생
				time_night_music = new Music("time_night.mp3", true);
				time_night_music.setDaemon(true);
				time_night_music.start();
				Starter.player.setMax_mp(Starter.player.getMax_mp()*2);	// 밤이되면 플레이어의 최대 마나가 두배가 된다
				Starter.player.setMp(Starter.player.getMp()*2);	// 밤이되면 플레이어의 마나가 두배가 된다
				Starter.player.setMagic_power(Starter.player.getMagic_power()*2);	// 밤이되면 플레이어의 마법 공격력이 두배가 된다
				for ( time = 0 ; time <= 1 ; time ++) {
					System.out.println();
					System.out.println("=============================");
					System.out.println();
					System.out.println("현재 시간은 밤입니다. " + Starter.player.getName() + "의 마나와 마법 공격력이 2배 강해집니다!");
					System.out.println("낮까지 남은 시간은 " + (2-time) + "분 입니다.");
					System.out.println();
					Thread.sleep(60000);		// 1분			
				}
				// 밤이 끝나면 밤 노래를 종료하고 낮 노래 재생
				time_night_music.close();
				time_day_music = new Music("time_day.mp3", true);
				time_day_music.setDaemon(true);
				time_day_music.start();
				Starter.player.setMax_mp(Starter.player.getMax_mp()/2);	// 낮이되면 두배가 된 최대 마나를 원래대로 복구함
				Starter.player.setMp(Starter.player.getMp()/2);	// 낮이되면 두배가 된 플레이어의 마나를 원래대로 복구함
				Starter.player.setMagic_power(Starter.player.getMagic_power()/2);	// 낮이되면 두배가된 플레이어의 마법 공격력을 원래대로 복구함
				for ( time = 2 ; time <= 3 ; time ++) {
					System.out.println();
					System.out.println("=============================");
					System.out.println();
					System.out.println("현재 시간은 낮입니다. " + Starter.player.getName() + "의 마나와 마법 공격력이 원상태로 돌아왔습니다.");
					System.out.println("밤까지 남은 시간은 " + (4-time) + "분 입니다.");
					System.out.println();
					Thread.sleep(60000);		// 1분			
				}
				time_day_music.close();
			} catch (InterruptedException e) {	// 중단되면
				// 중단될 일이 없어서 코드를 더 짜놓지는 않았지만 time을 이용해 낮인지 밤인지를 판단해서 스텟을 조정하는 코드 필요 ( 밤이면 낮추고 낮이면 그대로하기 위해서. 즉 초기상태로 만들어줄 필요가 있다는 소리임)
				time_night_music.close();
				time_day_music.close();
				break;
			} 
		}
	}
}
