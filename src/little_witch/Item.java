package little_witch;

public class Item {
	String name;	// 아이템 이름
	int price;	// 아이템 가격

	public Item(String name, int price) {
		this.name = name;
		this.price = price;
	}
}

// 무기
class Weapon extends Item {
	
	int max_damage;	// 최대 공격력
	int min_damage;	// 최소 공격력

	public Weapon(String name, int price, int max_damage, int min_damage) {
		super(name, price);
		this.max_damage = max_damage;
		this.min_damage = min_damage;
	}
	
	// 주소가 아니라 객체의 내부 정보를 가져오기위해 재정의 해줌
	@Override
	public String toString() {
		String result = "";
		result += this.name + ",  종류 : 무기,  가격 : " + this.price + "골드,  최대 공격력 : " + this.max_damage + ",  최소 공격력 : " + this.min_damage; 
		return result;
	}
}

class Humble_staff extends Weapon {
	public Humble_staff() {
		super("허름한 지팡이", 100, 0, 0);
	}
}

class Star_staff extends Weapon {
	public Star_staff() {
		super("별의 지팡이", 200, 4, 2);
	}
}

class Sun_staff extends Weapon {
	 public Sun_staff() {
		super("태양의 지팡이", 1600, 80, 40);
	} 
}

class Moon_staff extends Weapon {
	public Moon_staff() {
		super("달의 지팡이", 12800, 500, 250);
	}
}

// 방어구
class Armor extends Item {
	
	int defense; // 방어구에는 방어력 추가

	public Armor(String name, int price, int defense) {
		super(name, price);
		this.defense = defense;
	}
	
	// 주소가 아니라 객체의 내부 정보를 가져오기위해 재정의 해줌
	@Override
	public String toString() {
		String result = "";
		result += this.name + ",  종류 : 방어구,  가격 : " + this.price + "골드,  방어력 : " + this.defense; 
		return result;
	}
}

class Humble_robe extends Armor {
	public Humble_robe() {
		super("허름한 로브", 100, 0);
	}
}

class Star_robe extends Armor {
	public Star_robe() {
		super("별의 로브", 200, 2);
	}
}

class Sun_robe extends Armor {
	public Sun_robe() {
		super("태양의 로브", 1600, 40);
	}
}

class Moon_robe extends Armor {
	public Moon_robe() {
		super("달의 로브", 12800, 250);
	}
}

// 장신구
class Accessory extends Item {
	
	int magic_power; // 장신구에는 마법 공격력 추가
	
	public Accessory(String name, int price, int magic_power) {
		super(name, price);
		this.magic_power = magic_power;
	}
	
	// 주소가 아니라 객체의 내부 정보를 가져오기위해 재정의 해줌
	@Override
	public String toString() {
		String result = "";
		result += this.name + ",  종류 : 장신구,  가격 : " + this.price + "골드,  마법 공격력 : " + this.magic_power; 
		return result;
	}
}
	
class Humble_ring extends Accessory {
	public Humble_ring() {
		super("허름한 반지", 100, 0);
	}
}

class Star_ring extends Accessory {
	public Star_ring() {
		super("별의 반지", 200, 5);
	}
}

class Sun_ring extends Accessory {
	public Sun_ring() {
		super("태양의 반지", 1600, 40);
	}
}

class Moon_ring extends Accessory {
	public Moon_ring() {
		super("달의 반지", 12800, 250);
	}
}

