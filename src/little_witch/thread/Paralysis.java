package little_witch.thread;

import little_witch.Starter;

public class Paralysis implements Runnable {

	int time = 0; // 마비의 시간/5 

	@Override
	public void run() {

		while (!Thread.currentThread().isInterrupted()) {	// 중단상태가 아닐때까지만 반복해라
			try {	
				for ( time = 0 ; time <= 4 ; time ++) {
					Starter.player_attack_delay = 2;
					System.out.println("=============================");
					System.out.println();
					System.out.println("풀속성 기술에 당했습니다! 20초동안 마비상태가 됩니다. 현재 공격 딜레이 : " + (Starter.player_attack_delay + 2) + "초");
					System.out.println("마비의 남은 지속시간은 " + (20-(time*4)) + "초 입니다.");
					System.out.println("※ 온천에 가서 상태이상을 회복할 수 있습니다.");
					System.out.println();
					Thread.sleep(4000);		// 4초마다 반복			
				}
				if ( time == 5 ) {	// 지속시간이 다 됐을때
					Starter.player_attack_delay = 1;
					Thread.currentThread().interrupt();	//중단상태로 만들어서 와일문을 false로 만듬
					System.out.println("=============================");
					System.out.println();
					System.out.println("마비의 지속시간이 끝나 마비가 해제됩니다.");
					System.out.println();
					break;
				}
			} catch (InterruptedException e) {	// 중단되면
				Starter.player_attack_delay = 1;
				System.out.println("=============================");
				System.out.println();
				System.out.println("마비가 해제 됐습니다.");
				System.out.println();
				break;
			} 
		}
	}
}


