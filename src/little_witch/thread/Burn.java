package little_witch.thread;

import little_witch.Starter;

public class Burn implements Runnable {

	int time = 0; // 화상의 시간/9

	@Override
	public void run() {
//TODO time = 9 를 9부분을 변수명으로 설정한다
		while (!Thread.currentThread().isInterrupted()) {	// 중단상태가 아닐때까지만 반복해라
			try {			
				for ( time = 0 ; time <= 8 ; time ++) {
					if ( Starter.player.hp > Starter.player.max_hp/20 ) {	// 플레이어의 체력이 최대 체력의 5%보다 많다면
						Starter.player.hp -= Starter.player.max_hp/20;
						System.out.println("=============================");
						System.out.println();
						System.out.println("화상을 입었습니다! 20초마다 체력이 최대체력의 5%만큼 깎입니다. 현재 체력 : " + Starter.player.hp);
						System.out.println("화상의 남은 지속시간은 " + (180-(time*20)) + "초 입니다.");
						System.out.println("※ 온천에 가서 상태이상을 회복할 수 있습니다.");
						System.out.println();
					} else if ( Starter.player.hp <= Starter.player.max_hp/20 ) {	// 플레이어의 체력이 최대 체력의 5%보다 적다면
						Starter.player.hp = 1;
						System.out.println("=============================");
						System.out.println();
						System.out.println("화상피해는 체력이 1 미만으로 떨어지지 않습니다.");
						System.out.println("화상의 남은 지속시간은 " + (180-(time*20)) + "초 입니다.");
						System.out.println("※ 온천에 가서 상태이상을 회복할 수 있습니다.");
						System.out.println();
					}
					Thread.sleep(20000);	// 20초
				}
				int end_time = 9;
				if ( time == end_time ) {	// 지속시간이 다되면
					System.out.println("=============================");
					System.out.println();
					System.out.println("화상의 지속시간이 끝나 화상상태가 해제됩니다.");
					System.out.println();
					Thread.currentThread().interrupt();	//중단상태로 만들어서 와일문을 false로 만듬
					break;
				}
			} catch (InterruptedException e) {	// 중단되면
				System.out.println("=============================");
				System.out.println();
				System.out.println("화상상태가 해제됐습니다.");
				System.out.println();
				break;		
			}
		}
	}
}