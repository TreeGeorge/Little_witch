package little_witch.thread;

import little_witch.Starter;

	public class Mana_poison implements Runnable {

		int time = 0; // 마나독의 시간/9

		@Override
		public void run() {

			while (!Thread.currentThread().isInterrupted()) {	// 중단상태가 아닐때까지만 반복해라
				try {			
					for ( time = 0 ; time <= 8 ; time ++) {
						if ( Starter.player.mp <= Starter.player.max_mp/20 ) {	// 플레이어의 마나가 최대 마나의 5%보다 적다면
							Starter.player.mp = 0;
							System.out.println("=============================");
							System.out.println();
							System.out.println("마나 독에 의해 마나가 모두 소진되었습니다.");
							System.out.println("마나 독의 남은 지속시간은 " + (180-(time*20)) + "초 입니다.");
							System.out.println("※ 온천에 가서 상태이상을 회복할 수 있습니다.");
							System.out.println();
						} else if ( Starter.player.mp > Starter.player.max_mp/20 ) {	// 플레이어의 마나가 최대 마나의 5%보다 많다면
							Starter.player.mp -= Starter.player.max_mp/20;	// 플레이어의 마나를 최대 마나의 5%만큼 깎는다
							System.out.println("=============================");
							System.out.println();
							System.out.println("마나 독에 중독당했습니다! 20초마다 마나가 최대마나의 5%만큼 깎입니다. 현재 마나 : " + Starter.player.mp);
							System.out.println("마나 독의 남은 지속시간은 " + (180-(time*20)) + "초 입니다.");
							System.out.println("※ 온천에 가서 상태이상을 회복할 수 있습니다.");
							System.out.println();
						}
						Thread.sleep(20000);	// 20초
					}
					if ( time == 9 ) {	// 마나독의 지속시간이 다 됐을때
						System.out.println("=============================");
						System.out.println();
						System.out.println("마나 독의 지속시간이 끝나 마나 독이 해독됩니다.");
						System.out.println();
						Thread.currentThread().interrupt();	//중단상태로 만들어서 와일문을 false로 만듬
						break;
					}
				} catch (InterruptedException e) {	// 중단되면
					System.out.println("=============================");
					System.out.println();
					System.out.println("마나 독이 해독 됐습니다.");
					System.out.println();
					break;	
				}
			}
		}
	}
