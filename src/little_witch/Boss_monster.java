package little_witch;

import java.lang.Thread.State;

import little_witch.thread.Burn;
import little_witch.thread.Mana_poison;
import little_witch.thread.Paralysis;

public class Boss_monster extends Monster {
	public Boss_monster( String name, int hp, int max_hp, int mp, int max_mp, int defense, int gold, int max_damage, int min_damage, int magic_power, int exp , String attack_name, String type, Skill skill ) {
		super(name, hp, max_hp, mp, max_mp, defense, gold, max_damage, min_damage, magic_power, exp, attack_name, type, skill);
	}

	// 보스몬스터의 속성 변화 시키기
	public void chameleon() { 
		int type_probability = (int)(Math.random()*3 + 1);		// 랜덤값 1~3을 정해서
		if ( !this.is_dead() ) {
			// 1/3확률로 선택지 진입하게 하기
			// 1. 불속성으로 변환  , 2. 물속성으로 변환 , 3. 풀속성으로 변환 (type과 스킬의 변화)
			if ( type_probability == 1 ) {
				this.type = "불속성";
				this.skill = fire_root;
			} else if ( type_probability == 2 ) {
				this.type = "물속성";
				this.skill = water_root;
			} else if ( type_probability == 3 ) {
				this.type = "풀속성";
				this.skill = grass_root;
			} System.out.println("=============================");
			System.out.println();
			System.out.println( name + "이 무지갯빛으로 빛나며 " + this.type + "으로 변했습니다!!");
			System.out.println();
		}
	}

	// 보스몬스터의 특수 능력을 적용시키기위해 오버라이드 함
	@Override
	public void attack( Player player ) {	// 몬스터가 player에게 일반 공격을 사용
		int real_damage = (int) (Math.random() * (this.max_damage - this.min_damage )) + this.min_damage; // 최소 ~ 최대 공격력 사이의 랜덤한 데미지
		if ( !this.is_dead() && player.defense < real_damage ) {	// 플레이어의 방어력이 몬스터의 real_damage 보다 낮을때
			System.out.println("=============================");
			System.out.println();
			System.out.println( this.name + "의 " + this.attack_name + "!!!!");
			System.out.println( this.name + "은(는) " + real_damage + "만큼의 피해를 주었습니다.");
			System.out.println();
			player.hp -= real_damage - player.defense;	// real_damage에서 플레이어의 방어력을 뺀 만큼을 플레이어의 체력만큼 빼준다
			System.out.println( player.name + "은(는) " + player.defense + "만큼의 피해를 버텨내었습니다!");
			System.out.println();
		} else if ( !this.is_dead() && player. defense >= real_damage ) {// 플레이어의 방어력이 몬스터의 real_damage 보다 높을때
			System.out.println();
			System.out.println( player.name + "은(는) " + this.name + "의 공격을 온전히 막아내었습니다!");
			System.out.println();
		} this.rage(); this.restore(); this.chameleon();	// 특수능력을 적용시켜준 부분
	}

	// 보스몬스터의 특수 능력을 적용시키기위해 오버라이드 함
	@Override
	public void use_skill( Player player ) {	// 몬스터가 player에게 마법을 사용
		if ( !this.is_dead() && this.mp >= skill.mp_consumption ) {	// 몬스터가 죽지 않고 몬스터의 마나가 마법의 요구 마나량보다 크거나 같을 때
			System.out.println("=============================");
			System.out.println();
			System.out.println( name + "의 " + skill.name + "!!!!");
			System.out.println( name + "은(는) " + (int)( magic_power * skill.coefficient ) + "만큼의 피해를 주었습니다.");
			System.out.println();
			this.mp -= skill.mp_consumption;	// 몬스터의 마나는 마법의 요구 마나량만큼 깎고
			player.hp -= (int)( magic_power * skill.coefficient );	// 플레이어의 체력은 몬스터의 마법공격력 * 마법의 계수 만큼 깎인다.
			this.rage(); this.restore(); this.chameleon();	// 특수능력을 적용시켜준 부분
			if ( skill.type == "불속성" ) {	// 몬스터의 스킬 타입이 불속성이라면
				//				int burn_probability = (int)(Math.random() + 1);	// 확률에 따른 상태이상을 부여하고 싶을때 사용 (시연을 위해 100%로 설정)
				//				if ( burn_probability == 1 ) {
				if ( Starter.burn.getState() == State.NEW ) {	// burn이 실행되지 않았다면
					Starter.burn.setDaemon(true);
					Starter.burn.start();	// burn을 실행하라
					Starter.burn.setPriority(6);	// 메인의 텍스트보다 먼저 나오게 하려고 사용 (메인은 5)
					// burn이 실행 대기상태이거나 일시 정지 상태일경우 (이미 화상에 걸려있는경우) 이미 걸렸다고 알려줌
				} else if ( Starter.burn.getState() == State.RUNNABLE || Starter.burn.getState() == State.TIMED_WAITING ) {
					System.out.println("=============================");
					System.out.println();
					System.out.println("이미 화상 상태입니다.");
					System.out.println();
				} else if ( Starter.burn.getState() == State.TERMINATED ) {	// burn이 끝났다면 새걸로 만들고 실행함.
					Starter.burn = new Thread(new Burn());
					Starter.burn.setDaemon(true);
					Starter.burn.start();	// burn을 실행하라
					Starter.burn.setPriority(2);	// 메인의 텍스트보다 늦게 나오게 하려고 사용 (메인은 5)
				}
				//				}
			} else if ( skill.type == "물속성" ) {	// 몬스터의 스킬 타입이 물속성이라면
				//				int mana_poisoned_probability = (int)(Math.random() + 1);	// 확률에 따른 상태이상을 부여하고 싶을때 사용 (시연을 위해 100%로 설정)
				//				if ( mana_poisoned_probability == 1 ) {
				if ( Starter.mana_poison.getState() == State.NEW ) {	// mana_poison이 새것이라면
					Starter.mana_poison.setDaemon(true);
					Starter.mana_poison.start();	// mana_poison을 실행해라
					Starter.mana_poison.setPriority(6);	// 메인의 텍스트보다 먼저 나오게 하려고 사용 (메인은 5)
					// mana_poison이 실행 대기상태이거나 일시 정지 상태일경우 (이미 마나 독에 걸려있는경우) 이미 걸렸다고 알려줌
				} else if ( Starter.mana_poison.getState() == State.RUNNABLE || Starter.mana_poison.getState() == State.TIMED_WAITING ) {
					System.out.println("=============================");
					System.out.println();
					System.out.println("이미 마나독에 중독된 상태입니다.");
					System.out.println();
				} else if ( Starter.mana_poison.getState() == State.TERMINATED ) {	// mana_poison이 끝났다면 새걸로 만들고 실행함.
					Starter.mana_poison = new Thread(new Mana_poison());
					Starter.mana_poison.setDaemon(true);
					Starter.mana_poison.start();
					Starter.mana_poison.setPriority(3);	// 메인의 텍스트보다 늦게 나오게 하려고 사용 (메인은 5)
				}
				//				}
			} else if ( skill.type == "풀속성" ) {	// 몬스터의 스킬 타입이 풀속성이라면
				//				int paralysis_probability = (int)(Math.random() + 1);	// 확률에 따른 상태이상을 부여하고 싶을때 사용 (시연을 위해 100%로 설정)
				//				if ( paralysis_probability == 1 ) {
				if ( Starter.paralysis.getState() == State.NEW ) {	// paralysis가 새것이라면
					Starter.paralysis.setDaemon(true);
					Starter.paralysis.start();	// paralysis를 실행해라
					Starter.paralysis.setPriority(6);	// 메인의 텍스트보다 먼저 나오게 하려고 사용 (메인은 5)
					// paralysis이 실행 대기상태이거나 일시 정지 상태일경우 (이미 마비에 걸려있는경우) 이미 걸렸다고 알려줌
				} else if ( Starter.paralysis.getState() == State.RUNNABLE || Starter.paralysis.getState() == State.TIMED_WAITING ) {
					System.out.println("=============================");
					System.out.println();
					System.out.println("이미 마비에 걸린 상태입니다.");
					System.out.println();
				} else if ( Starter.paralysis.getState() == State.TERMINATED ) {	// paralysis가 끝났다면 새걸로 만들고 실행함.
					Starter.paralysis = new Thread(new Paralysis());
					Starter.paralysis.setDaemon(true);
					Starter.paralysis.start();	// paralysis를 실행해라
					Starter.paralysis.setPriority(4);	// 메인의 텍스트보다 늦게 나오게 하려고 사용 (메인은 5)
				}
				//				}
			}
		} else if ( !this.is_dead() && this.mp < skill.mp_consumption ) { // 몬스터의 마나가 부족할시 일반공격이 나가게 함
			this.attack(player);
		}
	}

	public void rage() {
		if ( hp <= max_hp/2 && hp > max_hp/4) {	// 보스 몬스터의 체력이 25~50% 사이라면 보스 몬스터의 공격력을 1000씩 증가시킴
			max_damage += 1000;
			min_damage += 1000;
			System.out.println("=============================");
			System.out.println();
			System.out.println(name + "은 크게 소리지르며 몸이 푸르게 빛나기 시작합니다.");
			System.out.println(name + "의 최소 공격력과 최대 공격력이 1000만큼 상승하였습니다!");
			System.out.println();
		}
	}

	public void restore() {
		if ( hp > 0 && hp <= max_hp/4 ) {		// 보스 몬스터의 체력이 0~25% 사이라면 보스 몬스터의 체력을 10%씩 회복시킴
			hp += max_hp/10;
			System.out.println("=============================");
			System.out.println();
			System.out.println(name + "은 땅 속에 뿌리를 깊게 박으며 태세를 굳건하게 다집니다.");
			System.out.println(name + "의 체력이 10%만큼 회복됩니다!");
			System.out.println();
		}
	}
}


class Stump_king extends Boss_monster {
	public Stump_king() {
		super( "스텀프 킹", 20000, 20000, 5000, 5000, 1000, 25000, 4000, 2000, 2500, 1000, "뿌리 휘감기" , "풀속성", grass_root );
	}
}