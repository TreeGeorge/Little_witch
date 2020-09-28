package little_witch;

import java.util.ArrayList;
import java.util.Scanner;

import little_witch.thread.Monster_attack;
import little_witch.thread.Night_and_day;

public class Player extends Character {
	
	Scanner sc = new Scanner(System.in);
	int level;	// 플레이어의 레벨
	int max_exp;	// 플레이어의 최대 경험치
	Weapon weapon;	// 플레이어의 무기
	Armor armor;	// 플레이어의 방어구
	Accessory accessory;	// 플레이어의 장신구
	int item_min_damage;	// 아이템 최소 공격력
	int item_max_damage;	// 아이템 최대 공격력
	int item_defense;	// 아이템 방어력
	int item_magic_power;	// 아이템 마법 공격력
	ArrayList<Item> inventory = new ArrayList<>();	// 플레이어의 인벤토리
	ArrayList<Skill> skills = new ArrayList<>();	// 플레이어의 스킬
	boolean enter = true;	// 입장한다를 알아보기 쉽게 이름지어준것

	// 기본 장비들을 실체화 해준것
	static Weapon humble_staff = new Humble_staff();
	static Armor humble_robe = new Humble_robe();
	static Accessory humble_ring = new Humble_ring();

	public Player(String name, int hp, int max_hp, int mp, int max_mp, int defense, int gold, int max_damage, int min_damage, int magic_power, int exp , String attack_name, int level, int max_exp, Weapon weapon, Armor armor, Accessory accessory) {
		super(name, hp, max_hp, mp, max_mp, defense, gold, max_damage, min_damage, magic_power, exp, attack_name);
		this.level = level;
		this.max_exp = max_exp;
		this.weapon = weapon;
		this.armor = armor;
		this.accessory = accessory;
	}

	// 숫자 입력값만 받기 위해 사용하는 메소드
	public void input_only_number_check() {
		while( !sc.hasNextInt() ) { 	// 입력값이 숫자인지 판별한다.
			sc.next();	// 다음 scanner에 입력해라. ( 와일문을 돈다. 숫자를 입력하면 와일문 조건이 false가 되기 때문에 sc.nextInt(); 값으로 들어간다 )
			System.err.print("입력값 오류 입니다.  숫자로 입력해주세요. \n입력 :  ");
		}
	}

	// 간소화된 스테이터스창
	public void status() {
		System.out.println("-----------------------------------------------------");
		System.out.println();
		System.out.println("이름 : " + name);
		System.out.println("레벨 : " + level + "                  경험치 : " + exp + "/" + max_exp);
		System.out.println("체력 : " + hp + "/" + max_hp + "              마나 : " + mp + "/" + max_mp);
		System.out.println("공격력 : " + (item_min_damage + min_damage) + "~" + (item_max_damage + max_damage) + "               마법 공격력 : " + (item_magic_power + magic_power));
		System.out.println("방어력 : " + (item_defense + defense) + "                 보유 골드 : " + gold);
		// 밤 낮의 시간을 판단해서 텍스트 출력
		if ( Night_and_day.getTime() == 0 || Night_and_day.getTime() == 1 ) {
			System.out.println("현재 시간 : 밤");
		} else if ( Night_and_day.getTime() == 2 || Night_and_day.getTime() == 3 ) {
			System.out.println("현재 시간 : 낮");
		}
		System.out.println();
		System.out.println("-----------------------------------------------------");
	}

	// 자세한 스테이터스창
	public void detailed_status() {
		System.out.println("-----------------------상태창-----------------------");
		System.out.println();
		System.out.println("이름 : " + name);
		System.out.println("레벨 : " + level);
		System.out.println("경험치 : " + exp + "/" + max_exp);
		System.out.println("체력 : " + hp + "/" + max_hp);
		System.out.println("마나 : " + mp + "/" + max_mp);
		System.out.println("공격력 : " + (item_min_damage + min_damage) + "~" + (item_max_damage + max_damage));
		System.out.println("마법 공격력 : " + (item_magic_power + magic_power));
		System.out.println("방어력 : " + (item_defense + defense));
		System.out.println("보유 골드 : " + gold);
		 // weapon이나 armor 악세사리 등등 null 값일때 이름을 가져올 수 없어서 에러가 나기때문에 null 일때 값을 불러오지 않음
		if ( weapon != null ) {
			System.out.println("장착중인 무기 : " + weapon.name);
		} else if ( weapon == null ) {	
			System.out.println("장착중인 무기 : 길바닥에서 주운 나무 막대기" );
		} if ( armor != null ) {
			System.out.println("장착중인 방어구 : " + armor.name);
		} else if ( armor == null ) {
			System.out.println("장착중인 방어구 : 평상복");
		} if ( accessory != null ) {
			System.out.println("장착중인 장신구 : " + accessory.name);
		} else if ( accessory == null ) {
			System.out.println("장착중인 장신구 : 맨손");
		}
		System.out.println();
		System.out.println("-----------------------------------------------------");
		System.out.println("=============================");
		System.out.println("가방에 보유중인 아이템");
		if ( inventory.isEmpty() ) { 		// 인벤토리가 비어있을때
			System.out.println();
			System.out.println("가방에 아이템이 없습니다.");
			System.out.println();
		} else if ( !inventory.isEmpty() ) {	// 인벤토리에 아이템이 존재할때
			System.out.println();
			// player에 존재하는 inventory의 리스트를 나오게 하기. inventory의 범위까지만
			for ( int index_number = 0 ; inventory.size() > index_number ; index_number ++) {
				System.out.println( (index_number + 1) + ". " + inventory.get( index_number ));
			}
			System.out.println();
		}
		System.out.println("=============================");
		System.out.println("현재 보유중인 마법 목록");
		if ( skills.isEmpty() ) {			// skills 가 비어있을때
			System.out.println();
			System.out.println("보유중인 마법이 없습니다.");
			System.out.println();
		} else if ( !skills.isEmpty() ) {
			System.out.println();
			// player에 존재하는 skills의 리스트를 나오게 하기. skills의 범위까지만
			for ( int index_number = 0 ; skills.size() > index_number ; index_number ++) {
				System.out.println( (index_number + 1) + ". " + skills.get( index_number ));
			}
			System.out.println();
		}
		System.out.println("=============================");
	}

	public void attack( Monster monster ) {	// 플레이어가 몬스터를 공격할때
		int real_damage = (int) (Math.random() * ((this.max_damage+item_max_damage) - (this.min_damage+item_min_damage)) + (this.min_damage+item_min_damage)); // 최소 ~ 최대 공격력 사이의 랜덤한 데미지
		if ( !this.is_dead() && monster.defense < real_damage ) {		// 플레이어가 죽지 않고 몬스터의 방어력이 플레이어의 실제 데미지보다 작을때
			System.out.println("=============================");
			System.out.println();
			System.out.println( this.name + "의 " + this.attack_name + "!!!!");
			System.out.println( this.name + "은(는) " + real_damage + "만큼의 피해를 주었습니다.");
			System.out.println();
			monster.hp -= real_damage - monster.defense;	// 몬스터의 체력이 플레이어의 실제 데미지 - 몬스터의 방어력만큼 깎인다.
			System.out.println( monster.name + "은(는) " + monster.defense + "만큼의 피해를 버텨내었습니다!");
			System.out.println();
		} else if ( !this.is_dead() && monster. defense >= real_damage ){		// 플레이어가 죽지 않고 몬스터의 방어력이 플레이어의 실제 데미지보다 클때
			System.out.println();
			System.out.println( this.name + "의 공격 피해인 " + real_damage + "보다 " + monster.name + "의 방어력이 높습니다!");
			System.out.println( monster.name + "은(는) " + this.name + "의 공격을 온전히 막아내었습니다!");
			System.out.println();
		}
	}
	public void use_skill( Monster monster ) {
		while (enter) {
			if ( skills.isEmpty() ) {		// skills가 비어있다면
				System.out.println("=============================");
				System.out.println();
				System.out.println("보유중인 마법이 없습니다.");
				System.out.println();
				break;
			} else if ( this.mp < 40 ) {	// player의 마나가 너무 적을시
				System.out.println("=============================");
				System.out.println();
				System.out.println("마법을 사용할 마나가 부족합니다.");
				System.out.println();
				break;
			}
			if ( !this.is_dead() ) {
				this.status();
				monster.status();
				System.out.println("=============================");
				System.out.println();
				System.out.println("어떤 마법을 사용하시겠습니까?");
				System.out.println();
				System.out.println("=============================");
				System.out.println("현재 보유중인 마법 목록");
				System.out.println();
				// player에 존재하는 skills의 리스트를 나오게 하기. skills의 범위까지만
				for ( int index_number = 0 ; skills.size() > index_number ; index_number ++) {
					if ( index_number < skills.size() ) {
						System.out.println( (index_number + 1) + ". " + skills.get( index_number ));
					}
				}
				System.out.println();
				System.out.println("=============================");
				System.out.println();
				System.out.println("사용하실 마법의 번호를 입력해주세요.");
				System.out.println();
				System.out.println("0. 마법 취소하기");
				System.out.println();
				System.out.print("입력 :  ");

				input_only_number_check();
				int select_skill = sc.nextInt() - 1; // 배열의 시작이 0 부터 시작이기 때문에 1을 빼주었다. (입력값을 1 늘린 수치가 보이도록 나타냈기 때문에)
				Starter.console_clear();

				if ( select_skill == -1 ) {
					System.out.println("=============================");
					System.out.println();
					System.out.println("마법 사용을 취소하였습니다. ");
					System.out.println();
					break;
				} else if ( skills.size() <= select_skill || select_skill < 0 ) {
					System.out.println("=============================");
					System.out.println();
					System.out.println("잘못 입력하셨습니다. 존재하는 마법의 번호를 입력해주세요.");
					System.out.println();
					continue;
				} else if ( skills.size() > select_skill && select_skill >=0 ) {		// skills 의 범위 안에있는 값 선택시
					if ( skills.get( select_skill ).mp_consumption > this.mp ) {	// 마나가 부족하면
						System.out.println("=============================");
						System.out.println();
						System.out.println("마법을 발동시킬 마나가 부족합니다.");
						System.out.println();
						continue;
					} else if ( skills.get( select_skill ).mp_consumption <= this.mp ) {	// 마나가 충분하면 마법과 몬스터의 속성에 따른 마법 데미지 증감
						System.out.println("=============================");
						System.out.println();
						System.out.println( this.name + "의 " + skills.get( select_skill ).name + "!!!!");
						System.out.println();
						if ( skills.get( select_skill ).type == "불속성" && monster.type == "풀속성") {
							System.out.println( this.name + "은(는) 유리한 상성의 마법을 사용해 " + (int)( (item_magic_power + this.magic_power) * skills.get( select_skill ).coefficient ) + "의 1.5배인 " +  (int)( (item_magic_power + this.magic_power) * skills.get( select_skill ).coefficient *1.5) + "만큼의 피해를 주었습니다.");
							monster.hp -= (int)( this.magic_power * skills.get( select_skill ).coefficient * 1.5);
							this.mp -= skills.get( select_skill ).mp_consumption;
						} else if ( skills.get( select_skill ).type == "물속성" && monster.type == "불속성") {
							System.out.println( this.name + "은(는) 유리한 상성의 마법을 사용해 " + (int)( (item_magic_power + this.magic_power) * skills.get( select_skill ).coefficient ) + "의 1.5배인 " +  (int)( (item_magic_power + this.magic_power) * skills.get( select_skill ).coefficient *1.5) + "만큼의 피해를 주었습니다.");
							monster.hp -= (int)( this.magic_power * skills.get( select_skill ).coefficient * 1.5);
							this.mp -= skills.get( select_skill ).mp_consumption;
						} else if ( skills.get( select_skill ).type == "풀속성" && monster.type == "물속성") {
							System.out.println( this.name + "은(는) 유리한 상성의 마법을 사용해 " + (int)( (item_magic_power + this.magic_power) * skills.get( select_skill ).coefficient ) + "의 1.5배인 " +  (int)( (item_magic_power + this.magic_power) * skills.get( select_skill ).coefficient *1.5) + "만큼의 피해를 주었습니다.");
							monster.hp -= (int)( this.magic_power * skills.get( select_skill ).coefficient * 1.5);
							this.mp -= skills.get( select_skill ).mp_consumption;
						} else if ( skills.get( select_skill ).type == "불속성" && monster.type == "물속성") {
							System.out.println( this.name + "은(는) 불리한 상성의 마법을 사용해 " + (int)( (item_magic_power + this.magic_power) * skills.get( select_skill ).coefficient ) + "의 0.5배인 " + (int)( (item_magic_power + this.magic_power) * skills.get( select_skill ).coefficient * 0.5) + "만큼의 피해를 주었습니다.");
							monster.hp -= (int)( this.magic_power * skills.get( select_skill ).coefficient * 0.5);
							this.mp -= skills.get( select_skill ).mp_consumption;
						} else if ( skills.get( select_skill ).type == "물속성" && monster.type == "풀속성") {
							System.out.println( this.name + "은(는) 불리한 상성의 마법을 사용해 " + (int)( (item_magic_power + this.magic_power) * skills.get( select_skill ).coefficient ) + "의 0.5배인 " + (int)( (item_magic_power + this.magic_power) * skills.get( select_skill ).coefficient * 0.5) + "만큼의 피해를 주었습니다.");
							monster.hp -= (int)( this.magic_power * skills.get( select_skill ).coefficient * 0.5);
							this.mp -= skills.get( select_skill ).mp_consumption;
						} else if ( skills.get( select_skill ).type == "풀속성" && monster.type == "불속성") {
							System.out.println( this.name + "은(는) 불리한 상성의 마법을 사용해 " + (int)( (item_magic_power + this.magic_power) * skills.get( select_skill ).coefficient ) + "의 0.5배인 " + (int)( (item_magic_power + this.magic_power) * skills.get( select_skill ).coefficient * 0.5) + "만큼의 피해를 주었습니다.");
							monster.hp -= (int)( this.magic_power * skills.get( select_skill ).coefficient * 0.5);
							this.mp -= skills.get( select_skill ).mp_consumption;
						} else {
							System.out.println( this.name + "은(는) 마법을 사용하여 " + (int)( (item_magic_power + this.magic_power) * skills.get( select_skill ).coefficient ) + "만큼의 피해를 주었습니다.");
							monster.hp -= (int)( this.magic_power * skills.get( select_skill ).coefficient );
							this.mp -= skills.get( select_skill ).mp_consumption;
						}
						System.out.println();
					} break;
				} 
			} 
		}	
	}

	public void meditation() {	// 명상을 하면
		if ( mp <= max_mp/10*9 ) {
			mp = mp + (max_mp/10);
			System.out.println("=============================");
			System.out.println();
			System.out.println("명상을 통해 마나가 10%만큼 회복되었습니다.");
			System.out.println();
		} else if ( max_mp == mp ) {
			System.out.println("=============================");
			System.out.println();
			System.out.println("마나가 이미 가득 차있습니다. 최대 마나 이상으로 회복하실 수 없습니다.");
			System.out.println();
		} else if ( mp > max_mp/10*9 ) {
			mp = max_mp;
			System.out.println("=============================");
			System.out.println();
			System.out.println("명상을 통해 마나를 가득 채웠습니다. 최대 마나이상으로 회복하실 수 없습니다.");
			System.out.println();
		}
	}

	// 전리품 획득 메소드
	public void plunder(Monster monster) {
		// 몬스터 사망시
		if ( monster.is_dead() ) {
			System.out.println("=============================");
			System.out.println();
			System.out.println( monster.name + "과(와)의 전투에서 승리하였습니다!!");
			System.out.println();
			System.out.println("=============================");
			// 레벨에 상관없이 전리품으로 골드는 항상 획득
			this.gold += monster.gold;
			System.out.println();
			System.out.println("전리품으로 " + monster.gold + "만큼의 골드를 획득하였습니다.");
			System.out.println();
			// 레벨이 10 미만일때만 경험치 획득
			if ( this.level < 10) {
				this.exp += monster.exp;
				System.out.println();
				System.out.println("전리품으로 " + monster.exp + "만큼의 경험치를 획득하였습니다.");
				System.out.println();
				// 경험치가 최대 경험치와 같거나 크다면 레벨업
				if ( this.exp >= this.max_exp) {
					this.level_up();
					System.out.println();
					System.out.println("다음 레벨까지 필요한 경험치는 " + this.max_exp + "입니다.");
					System.out.println();
				}
			} else if ( this.level == 10 ) {	// 레벨이 10일때
				this.exp = 0;
				System.out.println("=============================");
				System.out.println();
				System.out.println("최대 레벨에 도달하여 더이상 경험치를 획득하실 수 없습니다.");
				System.out.println();
			}
			// 플레이어 사망시
		} else if ( this.is_dead() ) {
			System.out.println("=============================");
			System.out.println();
			System.out.println("스승님이 걸어주신 마법 [ save ] 로 인해 몸이 빛에 휩싸이며 안전지대로 이동됩니다!!");
			System.out.println("이 과정중에 가진 소지금의 일부를 잃어버렸습니다...");
			System.out.println();
			// 플레이어의 체력을 1, 마나를 0 , 10%의 골드를 잃어버리게 한뒤 몬스터와 조우하기 전으로 이동.
			this.hp = 1;
			this.mp = 0;
			this.gold = this.gold/10*9;
		}
	}
	// 플레이어 레벨업시
	public void level_up() {
		this.level++;
		this.exp = 0;
		this.max_exp = max_exp*2;
		this.max_hp = max_hp*2;
		this.max_mp = max_mp*2;
		this.max_damage = max_damage*2;
		this.min_damage = min_damage*2;
		this.defense = defense*2;
		this.magic_power = magic_power*2;
		System.out.println("=============================");
		System.out.println();
		System.out.println("축하드립니다! 레벨업을 하셨습니다!!");
		System.out.println();
	}
	// 플레이어와 몬스터가 싸우는 메소드
	public void battle( Monster monster ) {

		Thread monster_attack = new Thread(new Monster_attack());
		monster_attack.setDaemon(true);
		monster_attack.start();

		while ( !this.is_dead() && !monster.is_dead() ) {		// 둘중에 하나가 죽을때까지 반복해라
			Starter.console_clear();
			this.status();
			monster.status();
			System.out.println();
			System.out.println( monster.name + "이(가) 당신을 노려봅니다.");
			System.out.println();
			System.out.println( this.name + " : 흠... 어떻게 하지.....?");
			System.out.println();
			System.out.println("1. 일반 공격  2. 마법 공격  3. 명상하기  4. 도망가기");
			System.out.println();
			System.out.println("※ 현재 " + this.name + "는(은) 행동이 가능합니다!");
			System.out.println();
			System.out.print("입력 :  ");

			input_only_number_check();
			int battle_select = sc.nextInt();
			Starter.console_clear();

			// 일반 공격을 했을때
			if ( battle_select == 1 ) {	
				if ( !this.is_dead() ) {	// 플레이어의 행동을 선택하기 전에 몬스터에 의해 사망했을경우 아래의 코드를 실행하지 않기 위해서	 (캐릭터가 죽었는데 공격하면 물리적으로 불가능하므로)
					Starter.console_clear();	// 화면 전환시 화면 전체 클리어
					this.attack(monster);
					if ( monster.is_dead() ) {	// 몬스터 체력이 - 가 되는것을 방지
						monster.hp = 0;
						monster_attack.interrupt(); // 몬스터를 한방에 죽였을때 몬스터 자동공격 중단
						break;
					}
					this.status();
					monster.status();
					System.out.println();
					System.out.println( (Starter.player_attack_delay + 2) + "초동안 움직이실 수 없습니다.");	// delay의 초기값은 1이다 
					System.out.println();
					try {
						Thread.sleep((Starter.player_attack_delay*1000) + 2000);	// delay의 초기값은 1이다 
					} catch (Exception e) {
					} 
				}
			// 마법을 사용했을때
			} else if ( battle_select == 2 ) {
				if ( !this.is_dead() ) {	// 플레이어의 행동을 선택하기 전에 몬스터에 의해 사망했을경우 아래의 코드를 실행하지 않기 위해서	 (캐릭터가 죽었는데 공격하면 물리적으로 불가능하므로)
					Starter.console_clear();
					this.use_skill(monster);
					if ( monster.is_dead() ) {	// 몬스터 체력이 - 가 되는것을 방지
						monster.hp = 0;
						monster_attack.interrupt(); // 몬스터를 한방에 죽였을때 몬스터 자동공격 중단
						break;
					}
					this.status();
					monster.status();
					System.out.println();
					System.out.println( (Starter.player_attack_delay + 2) + "초동안 움직이실 수 없습니다.");
					System.out.println();
					try {
						Thread.sleep((Starter.player_attack_delay*1000) + 2000);	// delay의 초기값은 1이다 
					} catch (Exception e) {
					}
				}
			} else if ( battle_select == 3 ) {	// 명상을 사용했을때
				if ( !this.is_dead() ) {	// 선택하기 전에 몬스터에 의해 사망했을경우 아래의 코드를 실행하지 않기 위해서
					Starter.console_clear();	// 화면 전환시 화면 전체 클리어
					this.meditation();
					this.status();
					monster.status();
					System.out.println();
					System.out.println( (Starter.player_attack_delay + 2) + "초동안 움직이실 수 없습니다.");
					System.out.println();
					try {
						Thread.sleep((Starter.player_attack_delay*1000) + 2000);
					} catch (Exception e) {
					}
				}
			} else if ( battle_select == 4 ) {	// 도망갈때
				if ( !this.is_dead() ) {	// 선택하기 전에 몬스터에 의해 사망했을경우 아래의 코드를 실행하지 않기 위해서
					monster_attack.interrupt(); // 도망치면 몬스터의 공격을 중단시킴
					System.out.println( "=============================");
					System.out.println();
					System.out.println("당신은 무사히 도망쳤습니다...");
					System.out.println();
					break;
				}
			} else {
				if ( !this.is_dead() ) {	// 선택하기 전에 몬스터에 의해 사망했을경우 아래의 코드를 실행하지 않기 위해서
					System.out.println( "=============================");
					System.out.println();
					System.out.println("잘못 입력하셨습니다. 1 또는 2 또는 3 또는 4 를 입력해주세요.");
					System.out.println();
					continue;
				}
			}
		} this.plunder(monster);	// 전투 보상 획득
	}

	public void buy_item( Item item ) {
		// 소지 골드가 아이템 가격 이상이라면
		if ( this.gold >= item.price ) {
			this.gold -= item.price;
			System.out.println("=============================");
			System.out.println();
			System.out.println( item.name + "을(를) 구매하였습니다. " + item.price + "골드를 사용하였습니다. 현재 골드 : " + this.gold );
			System.out.println();
			inventory.add( item );
		} else {
			System.out.println("=============================");
			System.out.println();
			System.out.println( item.name + "을(를) 구매하시기에는 " + (item.price - this.gold) +"만큼의 골드가 모자릅니다.");
			System.out.println();
		}
	}


	public void sell_item () {
		while (enter) {
			if (this.inventory.isEmpty()) {	// 인벤토리가 비었다면
				System.out.println("=============================");
				System.out.println();
				System.out.println("가방에 아이템이 없습니다.");
				System.out.println();
				break;
			} else {
				System.out.println("=============================");
				System.out.println();
				System.out.println("현재 보유중인 아이템을 판매합니다. (구매 가격의 절반 가격에 판매합니다.) 현재 골드 : " + this.gold );
				System.out.println();
				System.out.println("=============================");
				System.out.println();
				// player에 존재하는 inventory의 리스트를 나오게 하기. inventory의 범위까지만
				for ( int index_number = 0 ; inventory.size() > index_number ; index_number ++) {
					if ( index_number < inventory.size() ) {
						System.out.println( (index_number + 1) + ". " + inventory.get( index_number ));			
						System.out.println();
					}
				} System.out.println("=============================");
				System.out.println();
				System.out.println("판매하실 아이템의 번호를 입력해주세요.");
				System.out.println();
				System.out.println("0. 되돌아가기");
				System.out.println();
				System.out.print("입력 :  ");

				input_only_number_check();
				int select_item = sc.nextInt() -1;
				Starter.console_clear();

				if ( select_item < this.inventory.size() && select_item >= 0) {	// 인벤토리의 배열 범위 안쪽 값일때
					System.out.println("=============================");
					System.out.println();
					System.out.println( this.inventory.get(select_item).name + "을 판매하셨습니다. " + (this.inventory.get(select_item).price/2) + "골드를 획득하였습니다.");
					System.out.println();
					this.gold += (this.inventory.get(select_item).price/2);
					inventory.remove( select_item );
				} else if ( select_item == - 1 ) {
					System.out.println("=============================");
					System.out.println();
					System.out.println("상위 메뉴로 되돌아갑니다.");
					System.out.println();
					break;
				} else {
					System.out.println("=============================");
					System.out.println();
					System.out.println("잘못 입력하셨습니다. 알맞은 번호를 입력해주세요.");
					System.out.println();
					continue;
				}
			}
		}
	}


	// 무기 장착 메소드
	public void equip_weapon (Player player) {
		while(enter) {
			if ( weapon == null ) {
				System.out.println("=============================");
				System.out.println();
				System.out.println("어떤 무기를 장착하시겠습니까?");
				System.out.println();
				System.out.println("=============================");
				System.out.println("가방에 보유중인 아이템");
				if ( inventory.isEmpty() ) {
					System.out.println();
					System.out.println("가방에 아이템이 없습니다.");
				} else if ( !inventory.isEmpty() ) {
					System.out.println();
					// player에 존재하는 inventory의 리스트를 나오게 하기. inventory의 범위까지만
					for ( int index_number = 0 ; inventory.size() > index_number ; index_number ++) {
						System.out.println( (index_number + 1) + ". " + inventory.get( index_number ));
					}
				}
				System.out.println();
				System.out.println("장착하실 무기의 번호를 입력해주세요.");
				System.out.println();
				System.out.println("0. 되돌아가기");
				System.out.println();
				System.out.print("입력 :  ");

				input_only_number_check();
				int index_number = sc.nextInt() -1;
				Starter.console_clear();

				// 인벤토리의 아이템이 웨폰타입이라면
				if ( index_number == - 1 ) {
					System.out.println("=============================");
					System.out.println();
					System.out.println("상위 메뉴로 되돌아갑니다.");
					System.out.println();
					break;
				} else if ( inventory.size() <= index_number || index_number < 0) {
					System.out.println("=============================");
					System.out.println();
					System.out.println("잘못 입력하셨습니다. 존재하는 아이템의 번호를 입력해주세요.");
					System.out.println();
					continue;
					//인벤토리의 아이템이 웨폰타입이 아니라면
				} else if ( !(inventory.get( index_number ) instanceof Weapon) ) {
					System.out.println("=============================");
					System.out.println();
					System.out.println( "선택하신 아이템은 무기가 아닙니다." );
					System.out.println();
					continue;
					// 인벤토리의 아이템이 웨폰타입이라면
				} else if ( inventory.get( index_number ) instanceof Weapon  ) {
					this.weapon = (Weapon) inventory.get( index_number ); // 무기 추가
					this.item_max_damage += weapon.max_damage; // 맥뎀 증가
					this.item_min_damage += weapon.min_damage; // 민뎀 증가
					System.out.println("=============================");
					System.out.println();
					System.out.println(weapon.name + "을(를) 장착하셔서 " + weapon.min_damage + "~" + weapon.max_damage + " 만큼의 추가 공격력을 얻으셨습니다.");
					System.out.println();
					inventory.remove( index_number );
					break;
				}
			} else if ( weapon != null ) {
				System.out.println("=============================");
				System.out.println();
				System.out.println("이미 장착중이신 무기가 있습니다. 해제하시겠습니까?");
				System.out.println();
				System.out.println("1. 네  2. 아니요");
				System.out.println();
				System.out.print("입력 :  ");

				input_only_number_check();
				int select_weapon_equip = sc.nextInt();
				Starter.console_clear();

				if ( select_weapon_equip == 1) {	// 장착중인 무기를 해제하면 플레이어의 공격력이 그 무기만큼의 공격력이 삭감된다.
					inventory.add( weapon );
					System.out.println("=============================");
					System.out.println();
					System.out.println(weapon.name + "이(가) 해제되었습니다. 무기의 공격력인 " + weapon.min_damage + "~" + weapon.max_damage +" 만큼의 추가 공격력을 잃으셨습니다.");
					System.out.println();
					this.item_max_damage -= weapon.max_damage;
					this.item_min_damage -= weapon.min_damage;
					weapon = null;
					continue;
				} else if ( select_weapon_equip == 2) {
					System.out.println("=============================");
					System.out.println();
					System.out.println("기존의 장착하고 있던 무기를 그대로 사용합니다.");
					System.out.println();
					break;
				} else {
					System.out.println("=============================");
					System.out.println();
					System.out.println("잘못 입력하셨습니다. 1 또는 2 를 입력해주세요.");
					System.out.println();
				}
			}
		}
	}

	public void equip_armor (Player player) {

		while(enter) {
			if ( armor == null ) {
				System.out.println("=============================");
				System.out.println();
				System.out.println("어떤 방어구를 장착하시겠습니까?");
				System.out.println();
				System.out.println("=============================");
				System.out.println("가방에 보유중인 아이템");
				if ( inventory.isEmpty() ) {
					System.out.println();
					System.out.println("가방에 아이템이 없습니다.");
				} else if ( !inventory.isEmpty() ) {
					System.out.println();
					// player에 존재하는 inventory의 리스트를 나오게 하기. inventory의 범위까지만
					for ( int index_number = 0 ; inventory.size() > index_number ; index_number ++) {
						System.out.println( (index_number + 1) + ". " + inventory.get( index_number ));
					}
				}
				System.out.println();
				System.out.println("장착하실 방어구의 번호를 입력해주세요.");
				System.out.println();
				System.out.println("0. 되돌아가기");
				System.out.println();
				System.out.print("입력 :  ");

				input_only_number_check();
				int index_number = sc.nextInt() -1;
				Starter.console_clear();

				// 인벤토리의 아이템이 방어구타입이라면
				if ( index_number == - 1 ) {
					System.out.println("=============================");
					System.out.println();
					System.out.println("상위 메뉴로 되돌아갑니다.");
					System.out.println();
					break;
				} else if ( inventory.size() <= index_number || index_number < 0) {
					System.out.println("=============================");
					System.out.println();
					System.out.println("잘못 입력하셨습니다. 존재하는 아이템의 번호를 입력해주세요.");
					System.out.println();
					continue;
					//인벤토리의 아이템이 아머타입이 아니라면
				} else if ( !(inventory.get( index_number ) instanceof Armor) ) {
					System.out.println("=============================");
					System.out.println();
					System.out.println( "선택하신 아이템은 방어구가 아닙니다." );
					System.out.println();
					continue;
					// 인벤토리의 아이템이 아머타입이라면
				} else if ( inventory.get( index_number ) instanceof Armor  ) {
					this.armor = (Armor) inventory.get( index_number ); // 방어구 추가
					this.item_defense += armor.defense; // 방어력 증가
					System.out.println("=============================");
					System.out.println();
					System.out.println(armor.name + "을(를) 장착하셔서 " + armor.defense + " 만큼의 추가 방어력을 얻으셨습니다.");
					System.out.println();
					inventory.remove( index_number );
					break;
				}
			} else if ( armor != null ) {
				System.out.println("=============================");
				System.out.println();
				System.out.println("이미 장착중이신 방어구가 있습니다. 해제하시겠습니까?");
				System.out.println();
				System.out.println();
				System.out.println("1. 네  2. 아니요");
				System.out.println();
				System.out.print("입력 :  ");

				input_only_number_check();
				int select_armor_equip = sc.nextInt();
				Starter.console_clear();

				if ( select_armor_equip == 1) {	// 장착중인 방어구를 해제하면 플레이어의 공격력이 그 방어구만큼의 방어력이 삭감된다.
					inventory.add( armor );
					System.out.println("=============================");
					System.out.println();
					System.out.println(armor.name + "이(가) 해제되었습니다. 방어구의 방어력인 " + armor.defense +" 만큼의 추가 방어력을 잃으셨습니다.");
					System.out.println();
					this.item_defense -= armor.defense;
					armor = null;
					continue;
				} else if ( select_armor_equip == 2) {
					System.out.println("=============================");
					System.out.println();
					System.out.println("기존의 장착하고 있던 방어구를 그대로 사용합니다.");
					System.out.println();
					break;
				} else {
					System.out.println("=============================");
					System.out.println();
					System.out.println("잘못 입력하셨습니다. 1 또는 2 를 입력해주세요.");
					System.out.println();
				}
			}
		}
	}

	public void equip_accessory (Player player) {

		while(enter) {
			if ( accessory == null ) {
				System.out.println("=============================");
				System.out.println();
				System.out.println("어떤 장신구를 장착하시겠습니까?");
				System.out.println();
				System.out.println("=============================");
				if ( inventory.isEmpty() ) {
					System.out.println();
					System.out.println("가방에 아이템이 없습니다.");
				} else if ( !inventory.isEmpty() ) {
					System.out.println();
					// player에 존재하는 inventory의 리스트를 나오게 하기. inventory의 범위까지만
					for ( int index_number = 0 ; inventory.size() > index_number ; index_number ++) {
						System.out.println( (index_number + 1) + ". " + inventory.get( index_number ));
					}
				}
				System.out.println();
				System.out.println("장착하실 장신구의 번호를 입력해주세요.");
				System.out.println();
				System.out.println("0. 되돌아가기");
				System.out.println();
				System.out.print("입력 :  ");

				input_only_number_check();
				int index_number = sc.nextInt() -1;
				Starter.console_clear();

				if ( index_number == - 1 ) {
					System.out.println("=============================");
					System.out.println();
					System.out.println("상위 메뉴로 되돌아갑니다.");
					System.out.println();
					break;
				} else if ( inventory.size() <= index_number || index_number < 0) {
					System.out.println("=============================");
					System.out.println();
					System.out.println("잘못 입력하셨습니다. 존재하는 아이템의 번호를 입력해주세요.");
					System.out.println();
					continue;
					//인벤토리의 아이템이 장신구타입이 아니라면
				} else if ( !(inventory.get( index_number ) instanceof Accessory) ) {
					System.out.println("=============================");
					System.out.println();
					System.out.println( "선택하신 아이템은 장신구가 아닙니다." );
					System.out.println();
					continue;
					// 인벤토리의 아이템이 장신구타입이라면
				} else if ( inventory.get( index_number ) instanceof Accessory  ) {
					this.accessory = (Accessory) inventory.get( index_number ); // 장신구 추가
					this.item_magic_power += accessory.magic_power; // 마법공격력 증가
					System.out.println("=============================");
					System.out.println();
					System.out.println(accessory.name + "을(를) 장착하셔서 " + accessory.magic_power + " 만큼의 추가 마법 공격력을 얻으셨습니다.");
					System.out.println();
					inventory.remove( index_number );
					break;
				}
			} else if ( accessory != null ) {
				System.out.println("=============================");
				System.out.println();
				System.out.println("이미 장착중이신 장신구가 있습니다. 해제하시겠습니까?");
				System.out.println();
				System.out.println("1. 네  2. 아니요");
				System.out.println();
				System.out.print("입력 :  ");

				input_only_number_check();
				int select_accessory_equip = sc.nextInt();
				Starter.console_clear();

				if ( select_accessory_equip == 1) {	// 장착중인 장신구를 해제하면 플레이어의 마법 공격력이 그 장신구만큼의 마법 공격력이 삭감된다.
					inventory.add( accessory );
					System.out.println("=============================");
					System.out.println();
					System.out.println(accessory.name + "이(가) 해제되었습니다. 장신구의 마법공격력인 " + accessory.magic_power +" 만큼의 추가 마법 공격력을 잃으셨습니다.");
					System.out.println();
					this.item_magic_power -= accessory.magic_power;
					accessory = null;
					continue;
				} else if ( select_accessory_equip == 2) {
					System.out.println("=============================");
					System.out.println();
					System.out.println("기존의 장착하고 있던 장신구를 그대로 사용합니다.");
					System.out.println();
					break;
				} else {
					System.out.println("=============================");
					System.out.println();
					System.out.println("잘못 입력하셨습니다. 1 또는 2 를 입력해주세요.");
					System.out.println();
				}
			}
		}
	}
}

class Medeia extends Player {
	// 체력이 조금 높다
	public Medeia() {
		super("메디아", 60, 60, 20, 20, 2, 300, 8, 4, 5, 0, "지팡이 휘두르기", 1, 2 , humble_staff  , humble_robe , humble_ring);
	}
}

class Circe extends Player {
	// 마법공격력이 조금 높다
	public Circe() {
		super("키르케", 50, 50, 20, 20, 2, 300, 8, 4, 6, 0, "지팡이 휘두르기", 1, 2 , humble_staff  , humble_robe , humble_ring);
	}
}

class Freyja extends Player {
	// 기본공격력이 조금 높다
	public Freyja() {
		super("프레이야", 50, 50, 20, 20, 2, 300, 9, 5, 5, 0, "지팡이 휘두르기", 1, 2, humble_staff  , humble_robe , humble_ring);
	}
}

class Test extends Player {
	// 시연용 캐릭터
	public Test() {
		super("시연용 캐릭터", 500, 500, 200, 200, 200, 300, 10, 10, 5, 0, "지팡이 휘두르기", 1, 2, humble_staff  , humble_robe , humble_ring);
	}
}
