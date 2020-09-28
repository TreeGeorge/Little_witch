package little_witch;
	
public class Skill {
	
	String name; // 마법 이름
	String type; // 마법 타입
	int mp_consumption; // 마나 소모량
	double coefficient; // 마법 계수
	
	public Skill (String name, String type, int mp_consumption, double coefficient) {
		this.name = name;
		this.type = type;
		this.mp_consumption = mp_consumption;
		this.coefficient = coefficient;
	}
	
	// 주소가 아니라 객체의 내부 정보를 가져오기위해 재정의 해줌
	@Override
	public String toString() {
		String result = "";
		result += this.name + ",  스킬 속성 : " + this.type + ",  마나 소모량 : " + this.mp_consumption + ",  스킬 계수 " + this.coefficient; 
		return result;
	}
}
	

// 불속성 마법
class Fire_skill extends Skill {
	public Fire_skill (String name, int mp_consumption, double coefficient) {
		super(name, "불속성", mp_consumption, coefficient);
	}
}
// 플레이어 마법들
class Fire_ball extends Fire_skill {
	public Fire_ball () {
		super("파이어 볼", 40, 1);
	}
}

class Fire_spear extends Fire_skill {
	public Fire_spear() {
		super("파이어 스피어", 400, 1.2);
	}
}

class Fire_storm extends Fire_skill {
	public Fire_storm() {
		super("파이어 스톰", 4000, 1.5);
	}
}
// 몬스터 마법들
class Fire_rush extends Fire_skill {
	public Fire_rush () {
		super("불꽃 몸통 박치기", 5, 1);
	}
}

class Fire_dust extends Fire_skill {
	public Fire_dust() {
		super("불꽃 포자 뿌리기", 40, 1.2);
	}
}

class Fire_beam extends Fire_skill {
	public Fire_beam() {
		super("불꽃 빔", 320, 1.5);
	}
}

class Fire_root extends Fire_skill {
	public Fire_root() {
		super("불꽃 뿌리 휘감기", 1500, 1.5);
	}
}

// 물속성 마법
class Water_skill extends Skill {
	public Water_skill (String name, int mp_consumption, double coefficient) {
		super(name, "물속성", mp_consumption, coefficient);
	}
}
// 플레이어 마법들
class Water_ball extends Water_skill {
	public Water_ball () {
		super("워터 볼", 40, 1);
	}
}

class Water_spear extends Water_skill {
	public Water_spear() {
		super("워터 스피어", 400, 1.2);
	}
}

class Water_storm extends Water_skill {
	public Water_storm() {
		super("워터 스톰", 4000, 1.5);
	}
}
// 몬스터 마법들
class Water_rush extends Water_skill {
	public Water_rush () {
		super("맹독 몸통 박치기", 5, 1);
	}
}

class Water_dust extends Water_skill {
	public Water_dust() {
		super("맹독 포자 뿌리기", 40, 1.2);
	}
}

class Water_beam extends Water_skill {
	public Water_beam() {
		super("맹독 빔", 320, 1.5);
	}
}

class Water_root extends Water_skill {
	public Water_root() {
		super("맹독 뿌리 휘감기", 1500, 1.5);
	}
}

// 풀속성 마법
class Grass_skill extends Skill {
	public Grass_skill (String name, int mp_consumption, double coefficient) {
		super(name, "풀속성", mp_consumption, coefficient);
	}
}
// 플레이어 마법들
class Grass_wooden_needle extends Grass_skill {
	public Grass_wooden_needle () {
		super("우든 니들", 40, 1);
	}
}

class Grass_wooden_spear extends Grass_skill {
	public Grass_wooden_spear() {
		super("우든 스피어", 400, 1.2);
	}
}

class Grass_leaf_storm extends Grass_skill {
	public Grass_leaf_storm() {
		super("리프 스톰", 4000, 1.5);
	}
}
//몬스터 마법들
class Grass_rush extends Grass_skill {
	public Grass_rush () {
		super("마비 몸통 박치기", 5, 1);
	}
}

class Grass_dust extends Grass_skill {
	public Grass_dust() {
		super("맹독 포자 뿌리기", 40, 1.2);
	}
}

class Grass_beam extends Grass_skill {
	public Grass_beam() {
		super("맹독 빔", 320, 1.5);
	}
}

class Grass_root extends Grass_skill {
	public Grass_root() {
		super("맹독 뿌리 휘감기", 1500, 1.5);
	}
}














