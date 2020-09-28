package little_witch;

public class Character {
	
	public String name;	// 캐릭터 이름
	public int hp;	// 캐릭터 체력
	public int max_hp;	// 캐릭터 최대 체력
	public int mp;	// 캐릭터 마나
	public int max_mp;	// 캐릭터 최대 마나
	int defense;	// 캐릭터 방어력
	int gold;	// 캐릭터 골드
	int max_damage;	// 캐릭터 최대 공격력
	int min_damage;	// 캐릭터 최소 공격력
	int magic_power;	// 캐릭터 마법 공격력
	int exp;	// 캐릭터 경험치
	String attack_name;	// 캐릭터 기본공격 이름

	public Character( String name, int hp, int max_hp, int mp, int max_mp, int defense, int gold, int max_damage, int min_damage, int magic_power, int exp , String attack_name ) {
		this.name = name;
		this.hp = hp;
		this.max_hp = max_hp;
		this.mp = mp;
		this.max_mp = max_mp;
		this.defense = defense;
		this.gold = gold;
		this.max_damage = max_damage;
		this.min_damage = min_damage;
		this.magic_power = magic_power;
		this.exp = exp;
		this.attack_name = attack_name;
	}
	
	// 캐릭터의 죽음을 판별해주는 메소드
	public boolean is_dead() {
		if ( hp <= 0 ) {	// hp가 0 이하가 되면 dead는 true 가 된다.
			return true;
		} else {	// 아니면 false
			return false;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMax_hp() {
		return max_hp;
	}

	public void setMax_hp(int max_hp) {
		this.max_hp = max_hp;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getMax_mp() {
		return max_mp;
	}

	public void setMax_mp(int max_mp) {
		this.max_mp = max_mp;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getMax_damage() {
		return max_damage;
	}

	public void setMax_damage(int max_damage) {
		this.max_damage = max_damage;
	}

	public int getMin_damage() {
		return min_damage;
	}

	public void setMin_damage(int min_damage) {
		this.min_damage = min_damage;
	}

	public int getMagic_power() {
		return magic_power;
	}

	public void setMagic_power(int magic_power) {
		this.magic_power = magic_power;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public String getAttack_name() {
		return attack_name;
	}

	public void setAttack_name(String attack_name) {
		this.attack_name = attack_name;
	}
	
}
