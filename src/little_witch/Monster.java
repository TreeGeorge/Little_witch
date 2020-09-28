package little_witch;



import java.lang.Thread.State;

import little_witch.thread.Burn;
import little_witch.thread.Mana_poison;
import little_witch.thread.Paralysis;

public class Monster extends Character  {

	String type;	// 몬스터 타입
	Skill skill;	// 몬스터 마법

	// 몬스터들 마법 실체화
	static Skill fire_rush = new Fire_rush();
	static Skill water_rush = new Water_rush();
	static Skill grass_rush = new Grass_rush();
	static Skill fire_dust = new Fire_dust();
	static Skill water_dust = new Water_dust();
	static Skill grass_dust = new Grass_dust();
	static Skill fire_beam = new Fire_beam();
	static Skill water_beam = new Water_beam();
	static Skill grass_beam = new Grass_beam();
	static Skill fire_root = new Fire_root();
	static Skill water_root = new Water_root();
	static Skill grass_root = new Grass_root();

	public Monster( String name, int hp, int max_hp, int mp, int max_mp, int defense, int gold, int max_damage, int min_damage, int magic_power, int exp , String attack_name, String type, Skill skill ) {
		super(name, hp, max_hp, mp, max_mp, defense, gold, max_damage, min_damage, magic_power, exp , attack_name );
		this.type = type;
		this.skill = skill;
	}

	public void status() {
		System.out.println("-----------------------------------------------------");
		System.out.println();
		System.out.println("이름 : " + name + "                           속성 : " + type);
		System.out.println("체력 : " + hp + "/" + max_hp + "                마나 : " + mp + "/" + max_mp);
		System.out.println("공격력 : " + min_damage + "~" + max_damage + "                 마법 공격력 : " + magic_power);
		System.out.println("방어력 : " + defense + "                   보유 골드 : " + gold);
		System.out.println("보유 경험치 : " + exp);
		System.out.println();
		System.out.println("-----------------------------------------------------");
	}


	public void attack( Player player ) {	// 몬스터가 player를 공격
		int real_damage = (int) (Math.random() * (this.max_damage - this.min_damage )) + this.min_damage; // 최소 ~ 최대 공격력 사이의 랜덤한 데미지
		if ( !this.is_dead() && player.defense < real_damage ) {		// 몬스터가 죽지 않고 플레이어의 방어력이 몬스터의 실제 데미지보다 작을때
			System.out.println("=============================");
			System.out.println();
			System.out.println( this.name + "의 " + this.attack_name + "!!!!");
			System.out.println( this.name + "은(는) " + real_damage + "만큼의 피해를 주었습니다.");
			System.out.println();
			player.hp -= real_damage - player.defense;	// 플레이어의 체력이 몬스터의 실제 데미지 - 플레이어의 방어력만큼 깎인다.
			System.out.println( player.name + "은(는) " + player.defense + "만큼의 피해를 버텨내었습니다!");
			System.out.println();
		} else if ( !this.is_dead() && player.defense >= real_damage ){	// 플레이어의 방어력이 몬스터의 실제 데미지 보다 높거나 같다면
			System.out.println();
			System.out.println( this.name + "의 공격 피해인 " + real_damage + "보다 " + player.name + "의 방어력이 높습니다!");
			System.out.println( player.name + "은(는) " + this.name + "의 공격을 온전히 막아내었습니다!");
			System.out.println();
		}
	}

	public void use_skill( Player player ) {	// 몬스터가 player에게 마법을 사용
		if ( !this.is_dead() && this.mp >= skill.mp_consumption ) {	// 몬스터가 죽지 않고 몬스터의 마나가 마법의 요구 마나량보다 크거나 같을 때
			System.out.println("=============================");
			System.out.println();
			System.out.println( name + "의 " + skill.name + "!!!!");
			System.out.println( name + "은(는) " + (int)( magic_power * skill.coefficient ) + "만큼의 피해를 주었습니다.");
			System.out.println();
			this.mp -= skill.mp_consumption;	// 몬스터의 마나는 마법의 요구 마나량만큼 깎고
			player.hp -= (int)( magic_power * skill.coefficient );	// 플레이어의 체력은 몬스터의 마법공격력 * 마법의 계수 만큼 깎인다.
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

	// 몬스터가 마법공격을 할지 일반 공격을 할지 랜덤하게 설정해주는 메소드
	public void random_attack_by_monster(Player player) {	
		int attack_probability = (int)(Math.random()*3 + 1);
		if ( attack_probability == 1 || attack_probability == 2 ) { // 2/3 확률로 일반공격
			this.attack(player);
		} else if ( attack_probability == 3 ) { // 1/3 확률로 마법공격
			this.use_skill(player);
		}
	}
}

class Fire extends Monster {
	public Fire( String name, int hp, int max_hp, int mp, int max_mp, int defense, int gold, int max_damage, int min_damage, int magic_power, int exp , String attack_name, Skill skill ) {
		super( name, hp, max_hp, mp, max_mp, defense, gold, max_damage, min_damage, magic_power, exp, attack_name, "불속성", skill );
	}
}

class Fire_slime extends Fire {
	public Fire_slime() {
		super( "레드 슬라임", 40, 40, 10, 10, 2, 50, 8, 3, 5, 2 , "몸통 박치기", fire_rush);
	}
}

class Fire_mushroom extends Fire {
	public Fire_mushroom() {
		super( "레드 머슈룸", 320, 320, 80, 80, 16, 400, 64, 32, 40, 16, "포자 뿌리기", fire_dust);
	}
}

class Fire_golem extends Fire {
	public Fire_golem() {
		super( "레드 골렘", 2560, 2560, 640, 640, 128, 3200, 512, 256, 320, 128 , "레이저 빔", fire_beam);
	}
}

class Water extends Monster {

	public Water( String name, int hp, int max_hp, int mp, int max_mp, int defense, int gold, int max_damage, int min_damage, int magic_power, int exp , String attack_name, Skill skill ) {
		super(name, hp, max_hp, mp, max_mp, defense, gold, max_damage, min_damage, magic_power, exp , attack_name, "물속성", skill );
	}
}

class Water_slime extends Water {
	public Water_slime() {
		super( "블루 슬라임", 40, 40, 10, 10, 2, 50, 8, 3, 5, 2, "몸통 박치기", water_rush );
	}
}

class Water_mushroom extends Water {
	public Water_mushroom() {
		super( "블루 머슈룸", 320, 320, 80, 80, 16, 400, 64, 32, 40, 16,"포자 뿌리기", water_dust);
	}
}

class Water_golem extends Water {
	public Water_golem() {
		super( "블루 골렘", 2560, 2560, 640, 640, 128, 3200, 512, 256, 320, 128, "레이저 빔", water_beam );
	}
}

class Grass extends Monster {

	public Grass(  String name, int hp, int max_hp, int mp, int max_mp, int defense, int gold, int max_damage, int min_damage, int magic_power, int exp , String attack_name, Skill skill ) {
		super(name, hp, max_hp, mp, max_mp, defense, gold, max_damage, min_damage, magic_power, exp , attack_name , "풀속성", skill);
	}
}

class Grass_slime extends Grass {
	public Grass_slime() {
		super( "그린 슬라임", 40, 40, 10, 10, 2, 50, 8, 3, 5, 2, "몸통 박치기", grass_rush );
	}
}

class Grass_mushroom extends Grass {
	public Grass_mushroom() {
		super( "그린 머슈룸", 320, 320, 80, 80, 16, 400, 64, 32, 40, 16,"포자 뿌리기", grass_dust );
	}
}

class Grass_golem extends Grass {
	public Grass_golem() {
		super( "그린 골렘", 2560, 2560, 640, 640, 128, 3200, 512, 256, 320, 128, "레이저 빔", grass_beam );
	}
}








