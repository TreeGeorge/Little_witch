package little_witch.thread;

import little_witch.Starter;

public class Monster_attack implements Runnable {

	public void run() {
		try { 			
			Thread.sleep(5000);	// 몬스터 조우 후 5초 이후부터 플레이어 공격을 위해 사용
			while(!Thread.currentThread().isInterrupted()) {	// 중단상태가 아닐때까지만 반복해라
				while( !(Starter.player.is_dead()) && !(Starter.monster.is_dead()) ) {	// 플레이어와 몬스터가 죽음 상태가 아닐때까지 반복해라
					Starter.console_clear();	// 화면 전환시 화면 전체 클리어
					Starter.monster.random_attack_by_monster(Starter.player);	// 몬스터가 플레이어에게 랜덤하게 스킬과 일반공격을 한다
					if ( Starter.player.is_dead() ) {	// 플레이어의 체력이 - 가 되는것을 방지
						Starter.player.hp = 0;
					}
					Starter.player.status();
					Starter.monster.status();
					if ( Starter.player.hp == 0 ) {	// 플레이어 사망시
						System.out.println("=============================");
						System.out.println();
						System.out.println("전투에서 패배하셨습니다..");
						System.out.println("※ " + (Starter.monster_attack_delay + 4) + "초 넘게 게임 진행이 되지 않는다면 아무 숫자를 입력해주세요.");	// delay는 변수값으로 언제든지 바뀔 수 있지만 기본값이 1이다.
						System.out.println();
						Thread.currentThread().interrupt(); // while문의 조건을 true로 바꾼다
						break;
					}
					System.out.println();
					System.out.println( Starter.monster.name + "은(는) " + (Starter.monster_attack_delay + 4) + "초간 움직일 수 없습니다.");	// delay는 변수값으로 언제든지 바뀔 수 있지만 기본값이 1이다.
					System.out.println();
					System.out.println( Starter.player.name + " : 흠... 어떻게 하지.....?");
					System.out.println();
					System.out.println("1. 일반 공격  2. 마법 공격  3. 명상하기  4. 도망가기");
					System.out.println();
					System.out.println("※ 캐릭터가 움직일 수 없을시 작동하지 않습니다.");
					System.out.println();
					System.out.print("입력 :  ");
					Thread.sleep( (Starter.monster_attack_delay*1000) + 4000);	// 
					// 만약 슬립중에 몬스터나 플레이어가 죽으면 와일문을 벗어나라. (자동공격 stop)
					if ( Starter.monster.is_dead() || Starter.player.is_dead() ) { // 사실 플레이어의 사망은 불가능하나 추후 추가될 수 있으니 넣어놓음 ( 도트데미지를 입고 죽는다던가 )
						Thread.currentThread().interrupt();
						break;
					}
				}
			}	
		}catch (InterruptedException e) {	
		}
	}
}

