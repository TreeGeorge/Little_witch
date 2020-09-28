package little_witch;

import java.util.ArrayList;
import java.util.Scanner;


import little_witch.thread.Music;
import little_witch.thread.Night_and_day_music_stop;

public class Place {

	Scanner sc = new Scanner(System.in);
	String name;	// 장소 이름


	// 숫자 입력값만 받기 위해 사용하는 메소드
	public void input_only_number_check() {
		while( !sc.hasNextInt() ) { 	// 입력값이 숫자인지 판별한다.
			sc.next();	// 다음 scanner에 입력해라. ( 와일문을 돈다. 숫자를 입력하면 와일문 조건이 false가 되기 때문에 sc.nextInt(); 값으로 들어간다 )
			System.out.print("입력값 오류 입니다.  숫자로 입력해주세요. \n입력 :  ");
		}
	}

	public Place (String name) {
		this.name = name;
	}
}

class Home extends Place {

	// 마법을 저장하기 위한 리스트
	ArrayList<Skill> skills = new ArrayList<Skill>(); 

	// 각 마법들 실체화
	Skill fire_ball = new Fire_ball();
	Skill water_ball = new Water_ball();
	Skill grass_wooden_needle = new Grass_wooden_needle();
	Skill fire_spear = new Fire_spear();
	Skill water_spear = new Water_spear();
	Skill grass_wooden_spear = new Grass_wooden_spear();
	Skill fire_storm = new Fire_storm();
	Skill water_storm = new Water_storm();
	Skill grass_leaf_storm = new Grass_leaf_storm();

	// 마법을 한가지씩 배우게 하기 위해서 불리언 사용
	boolean ball = true;
	boolean spear = true;
	boolean storm = true;

	public Home() {
		super("마녀의 집");
	}

	public void home( Player player ) {

		// 각 스킬들을 skills array list에 담는다.
		skills.add(fire_ball);
		skills.add(water_ball);
		skills.add(grass_wooden_needle);
		skills.add(fire_spear);
		skills.add(water_spear);
		skills.add(grass_wooden_spear);
		skills.add(fire_storm);
		skills.add(water_storm);
		skills.add(grass_leaf_storm);

		// 마녀의 집 입장
		while(true) {
			player.status();
			System.out.println( "=============================");
			System.out.println();
			System.out.println( "이곳은 " + name + "입니다." );
			System.out.println( "헤카테 : 어머 벌써 돌아왔니? 항상 몸 조심해야한단다 아가..");
			System.out.println();
			System.out.println( "=============================");
			System.out.println();
			System.out.println( "1. 새로운 마법을 배우고싶어요!  2. 도장 2개를 모두 모아왔어요!  3. 다시 나가볼게요..." );
			System.out.println();
			System.out.print("입력 :  ");

			input_only_number_check();
			int home_select = sc.nextInt();
			Starter.console_clear();

			if ( home_select == 1 ) {	// 마법을 설명해주고 배우는 부분
				while(true) {
					player.status();
					System.out.println();
					System.out.println("헤카테 : 그래그래 알려줄테니 너무 걱정하지 마려므나 어떤 마법을 배우고 싶니?");
					System.out.println("헤카테 : 하지만 주의사항이 있단다! 4레벨 마법, 7레벨 마법, 10레벨 마법 모두 각각 한개씩밖에 배울 수 없단다.");
					System.out.println("헤카테 : 한번 배우고나면 되돌릴 수 없으니까 신중하게 선택하도록해~");
					System.out.println();
					System.out.println("헤카테 : 그럼 마법에 대해 설명해줄게! 마법 데미지는 마법 계수 x 마법 공격력이야.");
					System.out.println("헤카테 : 볼 마법의 마나 소모량은 40 이고 마법 계수는 1이야!");
					System.out.println("헤카테 : 스피어 마법의 마나 소모량은 400 이고 마법 계수는 1.2야!");
					System.out.println("헤카테 : 스톰 마법의 마나 소모량은 4000 이고 마법 계수는 1.5야!");
					System.out.println("헤카테 : 각 마법마다 속성이 있는데 몬스터 속성에 따라 데미지를 다르게 줄 수 있으니까 그건 직접 알아보도록해~");
					System.out.println();
					System.out.println( "=============================");
					System.out.println();
					System.out.println("1. 파이어 볼 ( 레벨 4 이상 )        2. 워터 볼 ( 레벨 4 이상 )         3. 우든 니들 ( 레벨 4 이상 )");
					System.out.println("4. 파이어 스피어 ( 레벨 7 이상 )     5. 워터 스피어 ( 레벨 7 이상 )      6. 우드 스피어 ( 레벨 7 이상 )");
					System.out.println("7. 파이어 스톰 ( 레벨 10 )         8. 워터 스톰 ( 레벨 10 )         9. 리프 스톰 ( 레벨 10 )");
					System.out.println();
					System.out.println( "=============================");
					System.out.println();
					System.out.println("0. 되돌아가기");
					System.out.println();
					System.out.println("※주의사항※ 각 레벨에 맞는 마법은 한가지씩밖에 배울 수 없으며 배운 뒤에는 되돌이킬수 없습니다.");
					System.out.println();
					System.out.print("입력 :  ");

					input_only_number_check();
					int learn_skill = sc.nextInt();
					Starter.console_clear();

					if ( learn_skill == 1 && player.level >= 4 ) { // 4레벨 이상만 배울 수 있게 함
						if (ball) { // boolean을 통해 마법을 배우면 false로 전환. 다음에는 입장을 못하게 하여 마법을 배울 수 없게 만듬
							System.out.println( "=============================");
							System.out.println();
							System.out.println("파이어 볼 : 불속성 , 마나 소모량 : 40 , 마법 계수 : 1");
							System.out.println();
							System.out.println( "=============================");
							System.out.println();
							System.out.println("정말로 \"파이어 볼\"을 배우시겠습니까? \"파이어 볼\"을 배우시면 \"워터 볼\"과 \"우든 니들\"을 배울 수 없습니다.");
							System.out.println();
							System.out.println("1. 배운다  2. 되돌아간다");
							System.out.println();
							System.out.print("입력 :  ");

							input_only_number_check();
							int select_fire_ball = sc.nextInt();
							Starter.console_clear();

							if ( select_fire_ball == 1 ) {
								player.skills.add(fire_ball);
								System.out.println( "=============================");
								System.out.println();
								System.out.println("파이어 볼을 배우셨습니다.");
								System.out.println("\"워터 볼\"과 \"우든 니들\"을 배울 수 없습니다.");
								System.out.println();
								ball = false; // boolean을 통해 마법을 배우면 false로 전환. 다음에는 입장을 못하게 하여 마법을 배울 수 없게 만듬
								continue;
							} else if ( select_fire_ball == 2 ) {
								System.out.println( "=============================");
								System.out.println();
								System.out.println("상위 메뉴로 되돌아갑니다.");
								System.out.println();
								continue;
							}  else {
								System.out.println( "=============================");
								System.out.println();
								System.out.println("잘못 입력하셨습니다. 상위 메뉴로 되돌아갑니다.");
								System.out.println();
								continue;
							}
						} else if (!ball) {
							System.out.println( "=============================");
							System.out.println();
							System.out.println("볼 마법은 한가지만 배울 수 있습니다.");
							System.out.println();
							continue;
						}
					} else if ( learn_skill == 2 && player.level >= 4 ) {	// 4레벨 이상만 배울 수 있게함
						if (ball) {	// boolean을 통해 마법을 배우면 false로 전환. 다음에는 입장을 못하게 하여 마법을 배울 수 없게 만듬
							System.out.println( "=============================");
							System.out.println();
							System.out.println("워터 볼 : 물속성 , 마나 소모량 : 40 , 마법 계수 : 1");
							System.out.println();
							System.out.println( "=============================");
							System.out.println();
							System.out.println("정말로 \"워터 볼\"을 배우시겠습니까? \"워터 볼\"을 배우시면 \"파이어 볼\"과 \"우든 니들\"을 배울 수 없습니다.");
							System.out.println();
							System.out.println("1. 배운다  2. 되돌아간다");
							System.out.println();
							System.out.print("입력 :  ");

							input_only_number_check();
							int select_water_ball = sc.nextInt();
							Starter.console_clear();

							if ( select_water_ball == 1 ) {
								player.skills.add(water_ball);
								System.out.println( "=============================");
								System.out.println();
								System.out.println("워터 볼을 배우셨습니다.");
								System.out.println("\"파이어 볼\"과 \"우든 니들\"을 배울 수 없습니다.");
								System.out.println();
								ball = false; 	// boolean을 통해 마법을 배우면 false로 전환. 다음에는 입장을 못하게 하여 마법을 배울 수 없게 만듬
								continue;
							} else if ( select_water_ball == 2 ) {
								System.out.println( "=============================");
								System.out.println();
								System.out.println("상위 메뉴로 되돌아갑니다.");
								System.out.println();
								continue;
							}  else {
								System.out.println( "=============================");
								System.out.println();
								System.out.println("잘못 입력하셨습니다. 상위 메뉴로 되돌아갑니다.");
								System.out.println();
								continue;
							}
						} else if (!ball) {
							System.out.println( "=============================");
							System.out.println();
							System.out.println("볼 마법은 한가지만 배울 수 있습니다.");
							System.out.println();
							continue;
						} 
					} else if ( learn_skill == 3 && player.level >= 4 ) { // 4레벨 이상만 배울 수 있게함
						if (ball) {	// boolean을 통해 마법을 배우면 false로 전환. 다음에는 입장을 못하게 하여 마법을 배울 수 없게 만듬
							System.out.println( "=============================");
							System.out.println();
							System.out.println("우든 니들 : 풀속성 , 마나 소모량 : 40 , 마법 계수 : 1");
							System.out.println();
							System.out.println( "=============================");
							System.out.println();
							System.out.println("정말로 \"우든 니들\"을 배우시겠습니까? \"우든 니들\"을 배우시면 \"파이어 볼\"과 \"워터 볼\"을 배울 수 없습니다.");
							System.out.println();
							System.out.println("1. 배운다  2. 되돌아간다");
							System.out.println();
							System.out.print("입력 :  ");

							input_only_number_check();
							int select_wooden_needle = sc.nextInt();
							Starter.console_clear();

							if ( select_wooden_needle == 1 ) {
								player.skills.add(grass_wooden_needle);
								System.out.println( "=============================");
								System.out.println();
								System.out.println("우든 니들을 배우셨습니다.");
								System.out.println("\"파이어 볼\"과 \"워터 볼\"을 배울 수 없습니다.");
								System.out.println();
								ball = false;	// boolean을 통해 마법을 배우면 false로 전환. 다음에는 입장을 못하게 하여 마법을 배울 수 없게 만듬
								continue;
							} else if ( select_wooden_needle == 2 ) {
								System.out.println( "=============================");
								System.out.println();
								System.out.println("상위 메뉴로 되돌아갑니다.");
								System.out.println();
								continue;
							}  else {
								System.out.println( "=============================");
								System.out.println();
								System.out.println("잘못 입력하셨습니다. 상위 메뉴로 되돌아갑니다.");
								System.out.println();
								continue;
							}
						} else if (!ball) {
							System.out.println( "=============================");
							System.out.println();
							System.out.println("볼 마법은 한가지만 배울 수 있습니다.");
							System.out.println();
							continue;
						} 
					} else if ( learn_skill == 4 && player.level >= 7 ) { // 7레벨 이상만 배울 수 있게함
						if (spear) {	// boolean을 통해 마법을 배우면 false로 전환. 다음에는 입장을 못하게 하여 마법을 배울 수 없게 만듬
							System.out.println( "=============================");
							System.out.println();
							System.out.println("파이어 스피어 : 불속성 , 마나 소모량 : 400 , 마법 계수 : 1.2");
							System.out.println();
							System.out.println( "=============================");
							System.out.println();
							System.out.println("정말로 \"파이어 스피어\"를 배우시겠습니까? \"파이어 스피어\"를 배우시면 \"워터 스피어\"와 \"우든 스피어\"를 배울 수 없습니다.");
							System.out.println();
							System.out.println("1. 배운다  2. 되돌아간다");
							System.out.println();
							System.out.print("입력 :  ");

							input_only_number_check();
							int select_fire_spear = sc.nextInt();
							Starter.console_clear();

							if ( select_fire_spear == 1 ) {
								player.skills.add(fire_spear);
								System.out.println( "=============================");
								System.out.println();
								System.out.println("파이어 스피어를 배우셨습니다.");
								System.out.println("\"워터 스피어\"와 \"우든 스피어\"를 배울 수 없습니다.");
								System.out.println();
								spear = false;	// boolean을 통해 마법을 배우면 false로 전환. 다음에는 입장을 못하게 하여 마법을 배울 수 없게 만듬
								continue;
							} else if ( select_fire_spear == 2 ) {
								System.out.println( "=============================");
								System.out.println();
								System.out.println("상위 메뉴로 되돌아갑니다.");
								System.out.println();
								continue;
							} else {
								System.out.println( "=============================");
								System.out.println();
								System.out.println("잘못 입력하셨습니다. 상위 메뉴로 되돌아갑니다.");
								System.out.println();
								continue;
							}
						} else if (!spear) {
							System.out.println( "=============================");
							System.out.println();
							System.out.println("스피어 마법은 한가지만 배울 수 있습니다.");
							System.out.println();
							continue;
						} 
					} else if ( learn_skill == 5 && player.level >= 7 ) {	// 7레벨 이상만 배울 수 있게함
						if (spear) {		// boolean을 통해 마법을 배우면 false로 전환. 다음에는 입장을 못하게 하여 마법을 배울 수 없게 만듬
							System.out.println( "=============================");
							System.out.println();
							System.out.println("워터 스피어 : 물속성 , 마나 소모량 : 400 , 마법 계수 : 1.2");
							System.out.println();
							System.out.println( "=============================");
							System.out.println();
							System.out.println("정말로 \"워터 스피어\"를 배우시겠습니까? \"워터 스피어\"를 배우시면 \"파이어 스피어\"와 \"우든 스피어\"를 배울 수 없습니다.");
							System.out.println();
							System.out.println("1. 배운다  2. 되돌아간다");
							System.out.println();
							System.out.print("입력 :  ");

							input_only_number_check();
							int select_water_spear = sc.nextInt();
							Starter.console_clear();

							if ( select_water_spear == 1 ) {
								player.skills.add(water_spear);
								System.out.println( "=============================");
								System.out.println();
								System.out.println("워터 스피어를 배우셨습니다.");
								System.out.println("\"파이어 스피어\"와 \"우든 스피어\"를 배울 수 없습니다.");
								System.out.println();
								spear = false;	// boolean을 통해 마법을 배우면 false로 전환. 다음에는 입장을 못하게 하여 마법을 배울 수 없게 만듬
								continue;
							} else if ( select_water_spear == 2 ) {
								System.out.println( "=============================");
								System.out.println();
								System.out.println("상위 메뉴로 되돌아갑니다.");
								System.out.println();
								continue;
							} else {
								System.out.println( "=============================");
								System.out.println();
								System.out.println("잘못 입력하셨습니다. 상위 메뉴로 되돌아갑니다.");
								System.out.println();
								continue;
							}
						} else if (!spear) {
							System.out.println( "=============================");
							System.out.println();
							System.out.println("스피어 마법은 한가지만 배울 수 있습니다.");
							System.out.println();
							continue;
						} 
					} else if ( learn_skill == 6 && player.level >= 7 ) {	// 7레벨 이상만 배울 수 있게함
						if (spear) {		// boolean을 통해 마법을 배우면 false로 전환. 다음에는 입장을 못하게 하여 마법을 배울 수 없게 만듬
							System.out.println( "=============================");
							System.out.println();
							System.out.println("우든 스피어 : 풀속성 , 마나 소모량 : 400 , 마법 계수 : 1.2");
							System.out.println();
							System.out.println( "=============================");
							System.out.println();
							System.out.println("정말로 \"우든 스피어\"를 배우시겠습니까? \"우든 스피어\"를 배우시면 \"파이어 스피어\"와 \"워터 스피어\"를 배울 수 없습니다.");
							System.out.println();
							System.out.println("1. 배운다  2. 되돌아간다");
							System.out.println();
							System.out.print("입력 :  ");

							input_only_number_check();
							int select_wooden_spear = sc.nextInt();
							Starter.console_clear();

							if ( select_wooden_spear == 1 ) {
								player.skills.add(grass_wooden_spear);
								System.out.println( "=============================");
								System.out.println();
								System.out.println("우든 스피어를 배우셨습니다.");
								System.out.println("\"파이어 스피어\"와 \"워터 스피어\"를 배울 수 없습니다.");
								System.out.println();
								spear = false;	// boolean을 통해 마법을 배우면 false로 전환. 다음에는 입장을 못하게 하여 마법을 배울 수 없게 만듬
								continue;
							} else if ( select_wooden_spear == 2 ) {
								System.out.println( "=============================");
								System.out.println();
								System.out.println("상위 메뉴로 되돌아갑니다.");
								System.out.println();
								continue;
							} else {
								System.out.println( "=============================");
								System.out.println();
								System.out.println("잘못 입력하셨습니다. 상위 메뉴로 되돌아갑니다.");
								System.out.println();
								continue;
							}
						} else if (!spear) {
							System.out.println( "=============================");
							System.out.println();
							System.out.println("스피어 마법은 한가지만 배울 수 있습니다.");
							System.out.println();
							continue;
						} 
					} else if ( learn_skill == 7 && player.level >= 10 ) { // 10레벨 이상만 배울 수 있게함
						if (storm) {	// boolean을 통해 마법을 배우면 false로 전환. 다음에는 입장을 못하게 하여 마법을 배울 수 없게 만듬
							System.out.println( "=============================");
							System.out.println();
							System.out.println("파이어 스톰 : 불속성 , 마나 소모량 : 4000 , 마법 계수 : 1.5");
							System.out.println();
							System.out.println( "=============================");
							System.out.println();
							System.out.println("정말로 \"파이어 스톰\"을 배우시겠습니까? \"파이어 스톰\"을 배우시면 \"워터 스톰\"과 \"리프 스톰\"을 배울 수 없습니다.");
							System.out.println();
							System.out.println("1. 배운다  2. 되돌아간다");
							System.out.println();
							System.out.print("입력 :  ");

							input_only_number_check();
							int select_fire_storm = sc.nextInt();
							Starter.console_clear();

							if ( select_fire_storm == 1 ) {
								player.skills.add(fire_storm);
								System.out.println( "=============================");
								System.out.println();
								System.out.println("파이어 스톰을 배우셨습니다.");
								System.out.println("\"워터 스톰\"과 \"리프 스톰\"을 배울 수 없습니다.");
								System.out.println();
								storm = false;	// boolean을 통해 마법을 배우면 false로 전환. 다음에는 입장을 못하게 하여 마법을 배울 수 없게 만듬
								continue;
							} else if ( select_fire_storm == 2 ) {
								System.out.println( "=============================");
								System.out.println();
								System.out.println("상위 메뉴로 되돌아갑니다.");
								System.out.println();
								continue;
							} else {
								System.out.println( "=============================");
								System.out.println();
								System.out.println("잘못 입력하셨습니다. 상위 메뉴로 되돌아갑니다.");
								System.out.println();
								continue;
							}
						} else if (!storm) {
							System.out.println( "=============================");
							System.out.println();
							System.out.println("스톰 마법은 한가지만 배울 수 있습니다.");
							System.out.println();
							continue;
						} 
					} else if ( learn_skill == 8 && player.level >= 10 ) {		// 10레벨 이상만 배울 수 있게함
						if (storm) {	// boolean을 통해 마법을 배우면 false로 전환. 다음에는 입장을 못하게 하여 마법을 배울 수 없게 만듬
							System.out.println( "=============================");
							System.out.println();
							System.out.println("워터 스톰 : 물속성 , 마나 소모량 : 4000 , 마법 계수 : 1.5");
							System.out.println();
							System.out.println( "=============================");
							System.out.println();
							System.out.println("정말로 \"워터 스톰\"을 배우시겠습니까? \"워터 스톰\"을 배우시면 \"파이어 스톰\"과 \"리프 스톰\"을 배울 수 없습니다.");
							System.out.println();
							System.out.println("1. 배운다  2. 되돌아간다");
							System.out.println();
							System.out.print("입력 :  ");

							input_only_number_check();
							int select_water_storm = sc.nextInt();
							Starter.console_clear();

							if ( select_water_storm == 1 ) {
								player.skills.add(water_storm);
								System.out.println( "=============================");
								System.out.println();
								System.out.println("워터 스톰을 배우셨습니다.");
								System.out.println("\"파이어 스톰\"과 \"리프 스톰\"을 배울 수 없습니다.");
								System.out.println();
								storm = false;	// boolean을 통해 마법을 배우면 false로 전환. 다음에는 입장을 못하게 하여 마법을 배울 수 없게 만듬
								continue;
							} else if ( select_water_storm == 2 ) {
								System.out.println( "=============================");
								System.out.println();
								System.out.println("상위 메뉴로 되돌아갑니다.");
								System.out.println();
								continue;
							} else {
								System.out.println( "=============================");
								System.out.println();
								System.out.println("잘못 입력하셨습니다. 상위 메뉴로 되돌아갑니다.");
								System.out.println();
								continue;
							}
						} else if (!storm) {
							System.out.println( "=============================");
							System.out.println();
							System.out.println("스톰 마법은 한가지만 배울 수 있습니다.");
							System.out.println();
							continue;
						} 
					} else if ( learn_skill == 9 && player.level >= 10 ) {		// 10레벨 이상만 배울 수 있게함
						if (storm) {	// boolean을 통해 마법을 배우면 false로 전환. 다음에는 입장을 못하게 하여 마법을 배울 수 없게 만듬
							System.out.println( "=============================");
							System.out.println();
							System.out.println("리프 스톰 : 풀속성 , 마나 소모량 : 4000 , 마법 계수 : 1.5");
							System.out.println();
							System.out.println( "=============================");
							System.out.println();
							System.out.println("정말로 \"리프 스톰\"을 배우시겠습니까? \"리프 스톰\"을 배우시면 \"파이어 스톰\"과 \"워터 스톰\"을 배울 수 없습니다.");
							System.out.println();
							System.out.println("1. 배운다  2. 되돌아간다");
							System.out.println();
							System.out.print("입력 :  ");

							input_only_number_check();
							int select_leaf_storm = sc.nextInt();
							Starter.console_clear();

							if ( select_leaf_storm == 1 ) {
								player.skills.add(grass_leaf_storm);
								System.out.println( "=============================");
								System.out.println();
								System.out.println("리프 스톰을 배우셨습니다.");
								System.out.println("\"파이어 스톰\"과 \"워터 스톰\"을 배울 수 없습니다.");
								System.out.println();
								storm = false;	// boolean을 통해 마법을 배우면 false로 전환. 다음에는 입장을 못하게 하여 마법을 배울 수 없게 만듬
								continue;
							} else if ( select_leaf_storm == 2 ) {
								System.out.println( "=============================");
								System.out.println();
								System.out.println("상위 메뉴로 되돌아갑니다.");
								System.out.println();
								continue;
							} else {
								System.out.println( "=============================");
								System.out.println();
								System.out.println("잘못 입력하셨습니다. 상위 메뉴로 되돌아갑니다.");
								System.out.println();
								continue;
							}
						} else if (!storm) {
							System.out.println( "=============================");
							System.out.println();
							System.out.println("스톰 마법은 한가지만 배울 수 있습니다.");
							System.out.println();
							continue;
						} 
					} else if ( learn_skill == 0 ) {
						System.out.println( "=============================");
						System.out.println();
						System.out.println("상위 메뉴로 되돌아 갑니다.");
						System.out.println();
						break;
					} else {
						System.out.println( "=============================");
						System.out.println();
						System.out.println("잘못 입력하셨거나 레벨이 모자릅니다. 다시 입력해주세요.");
						System.out.println();
						continue;
					}
				}
			} else if ( home_select == 2 ) {	// 도장을 모두 모으고 스승님에게 돌아갔을때 아웃트로
				if ( !Starter.is_quest_boss && !Starter.is_quest_ten && !Starter.is_quest_odd_even ) {	// 도장을 모두 모았을 때
					Starter.night_day.interrupt();	// 게임이 끝났으니 밤낮 종료
					Music ending = new Music("ending.mp3", true);	// 엔딩 송
					ending.setDaemon(true);
					ending.start();

					String outro = "헤카테 : 드디어 도장을 모두 모았구나! 축하해! 사실 이건 초급마녀가 되기 위한 시험이었단다.\n\n"+
							player.name + " : 네..?? 그럼 저는 초급마녀가 된건가요?\n\n"+
							"헤카테 : 그럼~ 가슴을 피고 자랑스러워해도 된단다.\n\n"+
							player.name + " : 엣-헴 (으쓱 으쓱)\n\n"+
							"헤카테 : 이녀석!!!!!!!!!! 너무 귀여운거 아니야?!?!\n\n"+
							player.name + " : 으악!! 스승님 뭐하시...........\n\n\n\n\n\n"+
							"                                                                         ███████╗██╗███╗   ██╗\n"+
							"                                                                         ██╔════╝██║████╗  ██║\n"+
							"                                                                         █████╗  ██║██╔██╗ ██║\n"+
							"                                                                         ██╔══╝  ██║██║╚██╗██║\n"+
							"                                                            ██╗██╗██╗    ██║     ██║██║ ╚████║\n"+
							"                                                            ╚═╝╚═╝╚═╝    ╚═╝     ╚═╝╚═╝  ╚═══╝\n"+
							"                                                                                                                                                 이렇게 꼬마마녀와 스승님은 행복하게 오래오래 살았답니다~\n\n\n"+ 
							"                                      Little Which를 즐겨주셔서 감사합니다.\n\n\n\n"+
							"                                       Enter키를 입력하시면 게임이 종료됩니다.\n\n\n\n";


					for ( int i = 0 ; i < outro.length() ; i++ ) { // outro의 길이만큼 i회 반복해라
						System.out.print(outro.charAt(i));	// String은 char의 배열이니까 char의 배열값을 나타내라
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					sc.nextLine();
					sc.nextLine();
					System.exit(0);

				} else {	// 도장을 모두 모으지 못했을때
					System.out.println( "=============================");
					System.out.println();
					System.out.println("헤카테 : 아직 도장을 다 모으지 못했구나! 금방 모을 수 있을거야! 화이팅!");
					System.out.println();
				}
			} else if ( home_select == 3 ) {	// 돌아간다 선택시
				System.out.println( "=============================");
				System.out.println();
				System.out.println("헤카테 : 그래~ 항상 몸 조심하구! 언제나 응원하고 있단다!");
				System.out.println();
				System.out.println( "=============================");
				System.out.println();
				System.out.println("장소 메뉴로 되돌아갑니다.");
				System.out.println();
				break;
			} else {
				System.out.println( "=============================");
				System.out.println();
				System.out.println( "잘못 입력하셨습니다. 1 또는 2 또는 3 을 입력해주세요." );
				System.out.println();
				continue;
			}
		}
	}
}

class Village extends Place {

	public Village() {
		super("마을");
	}

	public void village(Player player) {
		// 마을 입장
		while(true) {
			player.status();
			System.out.println("=============================");
			System.out.println();
			System.out.println("마을에 입장하셨습니다. 어디로 가시겠습니까?");
			System.out.println();
			System.out.println("1. 여관  2. 상점  3. 온천 ( 상태이상 회복 )  4. 마을회관  5. 되돌아가기");
			System.out.println();
			System.out.print("입력 :  ");

			input_only_number_check();
			int select_village = sc.nextInt();
			Starter.console_clear();

			if ( select_village == 1 ) {	// 여관 부분
				new Motel().motel(player);
			} else if ( select_village == 2 ) {	// 상점 부분
				new Shop().shop(player);
			} else if ( select_village == 3 ) {	// 온천 부분
				new Hot_spring().hot_spring(player);
			} else if ( select_village == 4 ) {	// 마을 회관 부분
				new Village_hall().village_hall(player);
			} else if ( select_village == 5 ) {
				System.out.println("=============================");
				System.out.println();
				System.out.println("장소 메뉴로 되돌아갑니다.");
				System.out.println();
				break;
			} else {
				System.out.println("=============================");
				System.out.println();
				System.out.println("잘못 입력하셨습니다. 1 또는 2 또는 3 또는 4 또는 5 를 입력해주세요.");
				System.out.println();
				continue;
			}
		}
	}
}

class Motel extends Place {

	public Motel() {
		super("마을 여관");
	}

	public void motel(Player player) {
		// 여관 입장
		while(true) {
			player.status();
			System.out.println("=============================");
			System.out.println();
			System.out.println(this.name + "에 입장하셨습니다. 이곳은 돈을 내고 잠시 쉬어갈 수 있는 장소입니다.");
			System.out.println();
			System.out.println("여관 주인 : 귀여운 꼬마 아가씨구만! 어때 잠시 쉬었다 갈래? 내가 특별하게 200골드만 받고 쉬게 해줄게!");
			System.out.println();
			System.out.println("=============================");
			System.out.println();
			System.out.println( player.name + " : 음.. 어떻게 할까??");
			System.out.println();
			System.out.println("1. 200골드를 내고 잠시 쉬고간다. ( 체력/마나 모두 회복 )  2. 되돌아 간다.");
			System.out.println();
			System.out.print("입력 :  ");

			input_only_number_check();
			int select_motel = sc.nextInt();
			Starter.console_clear();

			// 쉬고간다 선택시
			if ( select_motel == 1 ) {
				if ( player.hp == player.max_hp && player.mp == player.max_mp ) {	// 플레이어의 체력과 마나가 가득 찼다면
					System.out.println("=============================");
					System.out.println();
					System.out.println("여관 주인 : 응?? 아가씨는 이 여관에서 쉴 필요가 없어보이는데? 돈은 소중하니까 아껴서 사용하는게 좋아");
					System.out.println();
					System.out.println("마을로 되돌아갑니다.");
					System.out.println();
					break;
				} else if ( player.hp == 1 && player.level <= 3) {	// 플레이어의 체력이 1이면서 레벨이 3 이하일때는 무료로 사용 가능
					System.out.println("=============================");
					System.out.println();
					System.out.println("여관 주인 : 아이고 마을로 나온지 얼마 되지 않은 아이에게까지 돈을 받고싶지는 않아");
					System.out.println("여관 주인 : 조금더 강해지면 그때부터 돈을 받을게!");
					System.out.println();
					System.out.println(player.name + "의 체력과 마나가 모두 회복되었습니다!");
					System.out.println();
					player.hp = player.max_hp;
					player.mp = player.max_mp;
					System.out.println("=============================");
					System.out.println();
					System.out.println(player.name + " : 정말 너무너무 고맙습니다 주인아저씨!");
					System.out.println();
				} else if ( player.gold >= 200 ) {		// (위의 조건을 만족하지 않고) 플레이어의 골드가 200골드 이상이라면
					System.out.println("=============================");
					System.out.println();
					System.out.println("여관 주인 : 그래 잘생각했다구! 푹 쉬다가 가라구!");
					System.out.println();
					System.out.println(player.name + "의 체력과 마나가 모두 회복되었습니다!");
					System.out.println();
					player.hp = player.max_hp;
					player.mp = player.max_mp;
					player.gold -= 200;
					System.out.println("=============================");
					System.out.println();
					System.out.println(player.name + " : 정말 잘 쉬었어요! 다음에 또 올게요~");
					System.out.println();
					System.out.println("200골드를 내고 여관을 떠났습니다. 마을로 되돌아갑니다.");
					System.out.println();
					break;
				} else if ( player.gold < 200 ) {		// 플레이어의 골드가 200골드 미만이라면
					System.out.println("=============================");
					System.out.println();
					System.out.println("여관 주인 : 아이고 이거 미안해서 어쩌나.. 200골드도 충분히 싸게 해준건데..");
					System.out.println("여관 주인 : 아무리 그래도 공짜로 쉬게해줄순 없다구 아가씨.. 골드가 생기면 다시 찾아와~");
					System.out.println();
					System.out.println("=============================");
					System.out.println();
					System.out.println("마을로 되돌아갑니다.");
					System.out.println();
					break;
				}
			} else if ( select_motel == 2 ) {
				System.out.println("=============================");
				System.out.println();
				System.out.println("마을로 되돌아갑니다.");
				System.out.println();
				break;
			} else {
				System.out.println("=============================");
				System.out.println();
				System.out.println("잘못 입력하셨습니다. 1 또는 2를 입력해주세요.");
				System.out.println();
				continue;
			}
		}
	}
}

class Shop extends Place {

	// 상점 아이템 리스트들을 객체화
	ArrayList<Weapon> weapons = new ArrayList<Weapon>();
	ArrayList<Armor> armors = new ArrayList<Armor>();
	ArrayList<Accessory> accessories = new ArrayList<Accessory>();

	// 상점 아이템 각각을 객체화
	Weapon star_staff = new Star_staff();
	Weapon sun_staff = new Sun_staff();
	Weapon moon_staff = new Moon_staff();
	Armor star_robe = new Star_robe(); 
	Armor sun_robe = new Sun_robe();
	Armor moon_robe = new Moon_robe();
	Accessory star_ring = new Star_ring();
	Accessory sun_ring = new Sun_ring();
	Accessory moon_ring = new Moon_ring();

	public Shop() {
		super( "마을 상점" );
	}


	public void shop( Player player ) {
		// 상점에 아이템 추가
		weapons.add(star_staff);
		weapons.add(sun_staff);
		weapons.add(moon_staff);
		armors.add(star_robe);
		armors.add(sun_robe);
		armors.add(moon_robe);
		accessories.add(star_ring);
		accessories.add(sun_ring);
		accessories.add(moon_ring);

		// 상점에 입장
		while(true) {
			player.detailed_status();
			System.out.println("=============================");
			System.out.println();
			System.out.println( this.name + "에 입장하셨습니다. 이곳은 아이템을 구매하거나 판매할수 있는 장소입니다.");
			System.out.println();
			System.out.println("상점 주인 : 이봐 꼬맹이 돈은 있는거야...? 난 바쁜몸이라 장난칠거면 나가주겠니?");
			System.out.println();
			System.out.println("=============================");
			System.out.println();
			System.out.println( player.name + " : 음.. 어떻게 할까??");
			System.out.println();
			System.out.println("1. 아이템을 구매한다.  2. 아이템을 판매한다.  3. 되돌아간다.");
			System.out.println();
			System.out.print("입력 :  ");

			input_only_number_check();
			int select_shop = sc.nextInt();
			Starter.console_clear();

			if ( select_shop == 1) {	// 아이템을 구매한다 선택시
				while (true) {
					player.detailed_status();
					System.out.println("=============================");
					System.out.println();
					System.out.println("상점 주인 : 진짜로 돈이 있는거야?? 훔친돈은 아니지??");
					System.out.println();
					System.out.println("=============================");
					System.out.println();
					System.out.println("1. 무기를 구매한다.  2. 방어구를 구매한다.  3. 장신구를 구매한다.  4. 되돌아간다.");
					System.out.println();
					System.out.print("입력 :  ");

					input_only_number_check();
					int select_buy_item = sc.nextInt();
					Starter.console_clear();

					if ( select_buy_item == 1 ) {	// 무기 구매하기 선택시
						while(true) {
							System.out.println("=============================");
							System.out.println();
							System.out.println("                         무기 목록");
							System.out.println();
							System.out.println("=============================");
							System.out.println();
							// 상점에 존재하는 weapons의 리스트를 나오게 하기. weapons의 범위까지만
							for ( int index_number = 0 ; weapons.size() > index_number ; index_number ++) {
								System.out.println( (index_number + 1) + ". " + weapons.get( index_number ));
								System.out.println();
							} System.out.println("=============================");
							System.out.println();
							System.out.println("0. 되돌아가기");
							System.out.println();
							System.out.print("입력 :  ");

							input_only_number_check();
							int select_weapon = sc.nextInt() -1;
							Starter.console_clear();
							// weapons의 범위 값 안에 있을때만 작동하게하여 에러를 안나게함. ( 범위 바깥의 정보를 get 해오려고하면 에러가 발생함. 없는 정보이기 때문 )
							if ( select_weapon < weapons.size() && select_weapon >= 0) {
								player.buy_item(weapons.get(select_weapon));
							} else if ( select_weapon == -1 ) {
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
					} else if ( select_buy_item == 2 ) {	// 방어구 구매 선택시
						while(true) {
							System.out.println("=============================");
							System.out.println();
							System.out.println("                      방어구 목록");
							System.out.println();
							System.out.println("=============================");
							System.out.println();
							// 상점에 존재하는 armors의 리스트를 나오게 하기. armors의 범위까지만
							for ( int index_number = 0 ; armors.size() > index_number ; index_number ++) {
								System.out.println( (index_number + 1) + ". " + armors.get( index_number ));
								System.out.println();
							} System.out.println("=============================");
							System.out.println();
							System.out.println("0. 되돌아가기");
							System.out.println();
							System.out.print("입력 :  ");

							input_only_number_check();
							int select_armor = sc.nextInt() -1;
							Starter.console_clear();
							// armors의 범위 값 안에 있을때만 작동하게하여 에러를 안나게함. ( 범위 바깥의 정보를 get 해오려고하면 에러가 발생함. 없는 정보이기 때문 )
							if ( select_armor < armors.size() && select_armor >= 0) {
								player.buy_item(armors.get(select_armor));
							} else if ( select_armor == -1 ) {
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
					} else if ( select_buy_item == 3 ) {	// 장신구 구매 선택시
						while(true) {
							System.out.println("=============================");
							System.out.println();
							System.out.println("                      장신구 목록");
							System.out.println();
							System.out.println("=============================");
							System.out.println();
							// 상점에 존재하는 accessories의 리스트를 나오게 하기. accessories의 범위까지만
							for ( int index_number = 0 ; accessories.size() > index_number ; index_number ++) {
								System.out.println( (index_number + 1) + ". " + accessories.get( index_number ));
								System.out.println();
							} System.out.println("=============================");
							System.out.println();
							System.out.println("0. 되돌아가기");
							System.out.println();
							System.out.print("입력 :  ");

							input_only_number_check();
							int select_accessory = sc.nextInt() -1;
							Starter.console_clear();
							// accessories의 범위 값 안에 있을때만 작동하게하여 에러를 안나게함. ( 범위 바깥의 정보를 get 해오려고하면 에러가 발생함. 없는 정보이기 때문 )
							if ( select_accessory < accessories.size() && select_accessory >= 0) {
								player.buy_item(accessories.get(select_accessory));
							} else if ( select_accessory == -1 ) {
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
					} else if ( select_buy_item == 4 ) {
						System.out.println("=============================");
						System.out.println();
						System.out.println("상위 메뉴로 되돌아갑니다.");
						System.out.println();
						break;
					} else {
						System.out.println("=============================");
						System.out.println();
						System.out.println("잘못 입력하셨습니다. 1 또는 2 또는 3 또는 4 를 입력해주세요.");
						System.out.println();
						continue;
					}
				}
			} else if ( select_shop == 2 ) {	// 아이템 판매 선택시
				player.sell_item();
			} else if ( select_shop == 3 ) {
				System.out.println("=============================");
				System.out.println();
				System.out.println("상위 메뉴로 되돌아갑니다.");
				System.out.println();
				break;
			} else {
				System.out.println("=============================");
				System.out.println();
				System.out.println("잘못 입력하셨습니다. 1 또는 2 또는 3 을 입력해주세요.");
				System.out.println();
				continue;
			}
		}
	}
}

class Hot_spring extends Place {

	public Hot_spring() {
		super("온천");
	}

	public void hot_spring(Player player) {
		// 온천 입장
		System.out.println("=============================");
		System.out.println();
		System.out.println(this.name + "에 입장하셨습니다. 이곳은 무료로 개방된 장소입니다.");
		System.out.println();
		System.out.println(player.name + " : 후아... 새로 태어나는 기분이야!");
		System.out.println();
		System.out.println("=============================");
		//상태이상 중단!
		Starter.burn.interrupt();
		try {
			Starter.burn.join();	//선택지와 온천 말풍선 사이에 나오게 하고싶어서 join으로 먼저 종료시킬때까지 다른것들은 나오지 않게함
		} catch (InterruptedException e) {
		}
		Starter.mana_poison.interrupt();
		try {
			Starter.mana_poison.join();	//선택지와 온천 말풍선 사이에 나오게 하고싶어서 join으로 먼저 종료시킬때까지 다른것들은 나오지 않게함
		} catch (InterruptedException e) {
		}
		Starter.paralysis.interrupt();			
		try {
			Starter.paralysis.join();	//선택지와 온천 말풍선 사이에 나오게 하고싶어서 join으로 먼저 종료시킬때까지 다른것들은 나오지 않게함
		} catch (InterruptedException e) {
		}
	} 

}

class Village_hall extends Place {

	Thread night_and_day_music_stop = new Thread(new Night_and_day_music_stop());
	Music kid_battle_music = new Music("kid_battle.mp3", true);

	public Village_hall() {
		super("마을 회관");
	}

	public void village_hall(Player player) {
		// 마을 회관 입장
		while (true) {
			System.out.println("=============================");
			System.out.println();
			System.out.println( this.name + "에 입장하셨습니다. 이곳은 마을 촌장님이 계시고, 잘했어요 도장을 받을 수 있는 장소입니다.");
			System.out.println();
			System.out.println("마을 촌장 : 아아 자네가 헤카테님께서 이야기하신 아이로구나! 내가 무엇을 도와주면 될까??");
			System.out.println();
			System.out.println("=============================");
			System.out.println();
			System.out.println("1. 정말 잘했어요! 도장을 받는다.  2. 참 잘했어요! 도장을 받는다.  3. 아이들과 미니게임하기  4. 되돌아간다.");	// 정말 잘했어요는 스텀프킹 , 참 잘했어요는 미니게임
			System.out.println();
			System.out.print("입력 :  ");

			input_only_number_check();
			int select_village_hall = sc.nextInt();
			Starter.console_clear();

			if ( select_village_hall == 1 ) {
				if ( Starter.is_boss ) {	// 스텀프 킹 처치 전 출력값
					System.out.println("=============================");
					System.out.println();
					System.out.println("마을 촌장 : 그래그래 " + player.name + "야.. 정말 잘했어요! 도장을 받고싶다고??");
					System.out.println("마을 촌장 : 그럼 마을에 큰 피해를 주는 \"스텀프 킹\"을 처치하고 오면 \"정말 잘했어요!\" 도장을 줄게!");
					System.out.println("마을 촌장 : \"스텀프 킹\"은 정말정말 강하니까 꼭꼭 몸조심해야해! 기다리고 있을게~~");
					System.out.println();
					System.out.println("=============================");
					System.out.println();
					System.out.println("※ \"정말 잘했어요!\" 도장을 받기 위해서 \"스텀프 킹\"을 쓰러뜨리고 돌아오자!");
					System.out.println();
					continue;
				} else if ( !Starter.is_boss && Starter.is_quest_boss ) { // 스텀프 킹 처치 후 출력값
					System.out.println("=============================");
					System.out.println();
					System.out.println("마을 촌장 : 오오오오 정말로 네가 해냈구나 " + player.name + "야! 정말 힘들었을텐데..");
					System.out.println("마을 촌장 : 어디 다친곳은 없니?? 너무 무리한 부탁을 한게 아닌가 싶었는데 정말 다행이구나");
					System.out.println("마을 촌장 : 자 여기 \"정말 잘했어요!\" 도장을 찍어줄게! 스텀프 킹을 쓰러뜨려줘서 정말 고맙다.");
					System.out.println("마을 촌장 : 고생 많았을텐데 마을 여관에서라도 잠시 쉬었다 오는게 어떻겠니?? 여관비는 내가 주도록 하마");
					System.out.println();
					System.out.println("=============================");
					System.out.println("※ 200골드를 얻었다!");
					player.gold += 200;
					System.out.println();
					System.out.println("마을 촌장 : 그럼 다음에 또 보자꾸나! 헤카테님한테 안부도 전해주고~");
					System.out.println();
					Starter.is_quest_boss = false;
					Starter.stamp += 1;
					continue;
				} else if ( !Starter.is_quest_boss ) { // 스텀프 킹 처치 후 출력값을 본 뒤 에 출력되는 출력값
					System.out.println("=============================");
					System.out.println();
					System.out.println("마을 촌장 : 응? 이미 \"정말 잘했어요!\" 도장은 받아간걸로 기억하는데..");
					System.out.println("마을 촌장 : 허허 혹시 내가 보고 싶어서 돌아온거니?? 마음만은 고맙지만 내가 지금 좀 바쁘구나");
					System.out.println("마을 촌장 : 다음에 시간이 날때 보자꾸나 아이야.");
					System.out.println();
					System.out.println("=============================");
					System.out.println();
					System.out.println("※ " + player.name + "는(은) 촌장님과 더 이야기 할 수 없었습니다.");
					System.out.println();
					continue;
				}
			} else if ( select_village_hall == 2 ) {
				if ( Starter.is_mini_game_ten || Starter.is_mini_game_odd_even ) {	// 미니게임 클리어 전 출력값
					System.out.println("=============================");
					System.out.println();
					System.out.println("마을 촌장 : 아이고 요즘 마을 사람들이 워낙에 바쁘다보니 아이들과 놀아줄 시간이 없구나");
					System.out.println("마을 촌장 : 혹시 시간 괜찮다면 아이들과 함께 놀아 줄 수 있겠니?? 아이들이 너무 외로워 보여 안쓰러워서 그래");
					System.out.println("마을 촌장 : 아이들과 함께 놀아준다면 \"참 잘했어요!\"도장을 찍어주도록 할게! 꼭 좀 부탁할게~");
					System.out.println();
					System.out.println("=============================");
					System.out.println();
					System.out.println("※ \"참 잘했어요!\" 도장을 받기 위해서 마을 아이들과 미니게임에서 승리하자!");
					System.out.println();
					continue;
				} else if ( !Starter.is_mini_game_ten && !Starter.is_mini_game_odd_even & Starter.is_quest_ten && Starter.is_quest_odd_even ) {	// 미니게임 클리어 후 출력값
					System.out.println("=============================");
					System.out.println();
					System.out.println("마을 촌장 : 이야~ 정말 고마워 아이들이 너무 기뻐하더라 새로운 친구 " + player.name + "가(이) 생겼다고 얼마나 자랑하던지");
					System.out.println("마을 촌장 : 요즘에 어른들이 모두 바쁘다고 아이들하고 못놀아줘서 마음이 심란했는데.. 덕분에 한시름 놓았구나");
					System.out.println("마을 촌장 : 자 여기 \"참 잘했어요!\" 도장을 찍어줄게! 아이들과 함께 즐겁게 놀아줘서 정말 고맙다.");
					System.out.println("마을 촌장 : 고생 많았을텐데 용돈이라도 조금 주마! 앞으로도 우리 아이들과 사이좋게 지내주면 좋겠네");
					System.out.println();
					System.out.println("=============================");
					System.out.println();
					System.out.println("※ 200골드를 얻었다!");
					player.gold += 200;
					System.out.println();
					System.out.println("마을 촌장 : 그럼 다음에 또 보자꾸나! 헤카테님한테 안부도 전해주고~");
					System.out.println();
					Starter.is_quest_ten = false;
					Starter.is_quest_odd_even = false;
					Starter.stamp += 1;
					continue;
				} else if ( !Starter.is_quest_ten && !Starter.is_quest_odd_even ) {		// 미니게임 클리어 후 출력값을 본 뒤 에 출력되는 출력값
					System.out.println("=============================");
					System.out.println();
					System.out.println("마을 촌장 : 응? 이미 \"참 잘했어요!\" 도장은 받아간걸로 기억하는데..");
					System.out.println("마을 촌장 : 아이들과 조금 더 놀고싶어서 찾아온거니?? 하지만 아이들은 지금 많이 피곤해보이던데..");
					System.out.println("마을 촌장 : 다음에 아이들이 놀고싶어할때 다시 놀아주러 찾아와 줬으면 좋겠구나.");
					System.out.println("마을 촌장 : 그리고 내가 지금 좀 바빠서.. 이야기 할 시간이 없구나. 미안하지만 돌아가주겠니?");
					System.out.println();
					System.out.println("=============================");
					System.out.println();
					System.out.println("※ " + player.name + "는(은) 촌장님과 더 이야기 할 수 없었습니다.");
					System.out.println();
					continue;
				}
			} else if ( select_village_hall == 3 ) {
				while(true) {
					if ( Starter.is_mini_game_ten || Starter.is_mini_game_odd_even ) {	// 미니게임 클리어 전 출력값
						System.out.println("=============================");
						System.out.println();
						System.out.println("마을 아이 A : 네가 촌장님이 말씀하신 " + player.name + "야..? 우리랑 놀아줄거라고 하던데..");
						System.out.println("마을 아이 B : 요즘에 어른들이 모두 바쁘다고 안놀아 주셔서 이제 우리들끼리만 노는것도 질렸어!");
						System.out.println("마을 아이 A : 맞아.. 그래서 그런데 우리랑 같이 간단한 놀이라도 하지 않을래??");
						System.out.println("마을 아이 B : 제발~~ 너무 심심하단 말이야~~ 오래 붙잡고 있지는 않을테니까 꼭 같이 놀아주라~~");
						System.out.println();
						System.out.println("=============================");
						System.out.println();
						System.out.println("1. 열고개 숫자맞추기 게임  2. 주사위 굴려 홀짝맞추기 게임  3. 되돌아가기");
						System.out.println();
						System.out.print("입력 :  ");

						input_only_number_check();
						int select_mini_game = sc.nextInt();
						Starter.console_clear();

						if ( select_mini_game == 1 ) {
							while(true) {
								if ( Starter.is_mini_game_ten ) {	// 미니게임(숫자맞추기) 클리어 전 출력
									System.out.println("=============================");
									System.out.println();
									System.out.println("마을 아이 A : 좋았어! 내가 좋아하는 숫자 맞추기 게임이야!");
									System.out.println("마을 아이 B : 우와... 부럽다..");
									System.out.println("마을 아이 A : 이거 끝나고 B가 좋아하는 게임도 같이 해달라고 하자~");
									System.out.println("마을 아이 B : 그래 좋아! 일단 그럼 게임 설명부터 해주자!");
									System.out.println();
									System.out.println("=============================");
									System.out.println();
									System.out.println("1. 시작하기  2.  되돌아가기");
									System.out.println();
									System.out.println("※ 게임 방법은 간단합니다. 1~1000 사이의 랜덤한 숫자를 10번 이내에 맞추시면 됩니다.");
									System.out.println("※ 숫자를 말하면 마을아이들이 숫자가 더 낮아야 하는지 더 높아야 하는지 알려줍니다.");
									System.out.println("※ 정답을 맞추면 승리하게되고 10번 이내에 맞추지 못하면 재도전이 가능합니다.");
									System.out.println();
									System.out.print("입력 :  ");

									input_only_number_check();
									int select_ten = sc.nextInt();
									Starter.console_clear();
									// 열고개 숫자맞추기 시작
									if ( select_ten == 1 ) {
										// 밤 낮의 시간은 흘러가게하고 노래만 중단시키기 위해서 만든 쓰레드
										night_and_day_music_stop = new Thread(new Night_and_day_music_stop());	
										night_and_day_music_stop.setDaemon(true);
										night_and_day_music_stop.start();
										// 아이와 대결 노래 시작
										kid_battle_music = new Music("kid_battle.mp3", true);
										kid_battle_music.setDaemon(true);
										kid_battle_music.start();
										while( Starter.is_mini_game_ten ) {
											System.out.println("=============================");
											System.out.println();
											System.out.println("마을 아이 A : 좋아.. 1~1000 까지 숫자중에 어떤 숫자로 정할까..");
											System.out.println("마을 아이 A : 정했어! 시작해도 좋아!");
											System.out.println();
											System.out.println("=============================");
											System.out.println();
											System.out.println("※ 1~1000 사이의 숫자를 입력해주세요!");
											System.out.println();

											int random = (int)(Math.random()*1000 + 1); // 마을 아이 A가 정한 1~1000 사이의 랜덤한 숫자

											for ( int count = 0 ; count < 10 ; count++ ) { 			// 플레이어가 정답을 맞출 기회를 10번으로 제한함
												if ( count < 10 ) { // 기회를 모두 잃었을때 표시하지 않게 하기 위해서
													System.out.println("=============================");
													System.out.println();
													System.out.println(player.name + " : 흠.. 이게 맞나..?? 앞으로 " + (10 - count) + "번의 횟수만큼 남았다. " );
													System.out.println();
													System.out.print("입력 :  ");
												}

												input_only_number_check();
												int answer = sc.nextInt();
												Starter.console_clear();

												if ( random == answer ) {		// 플레이어가 정답을 맞췄을 시
													System.out.println("※ 정답을 맞추셨습니다!! 정답은 " + random + "(이)였습니다.");
													System.out.println();
													System.out.println("=============================");
													System.out.println();
													System.out.println("마을 아이 A : 와 이걸 " + count + "번 만에 맞춘다고?? 굉장한걸?? 너무너무 즐거웠어!");
													System.out.println();
													Starter.is_mini_game_ten = false; 	// 미니게임(숫자 맞추기)의 입장을 false로 바꾸어 다시 못들어가게 함
													break;
												} else if ( random > answer ) {		// 적은 숫자가 정답보다 작을때
													System.out.println("=============================");
													System.out.println();
													System.out.println("마을 아이 A : 뿌뿌-- 틀렸다구! 내가 생각한 숫자가 더 \"큰\" 숫자야!  ※시연을 위한 정답 : " + random);
													System.out.println();
												} else if ( random < answer ) {		// 적은 숫자가 정답보다 클때
													System.out.println("=============================");
													System.out.println();
													System.out.println("마을 아이 A : 뿌뿌-- 틀렸다구! 내가 생각한 숫자가 더 \"작은\" 숫자야!  ※시연을 위한 정답 : " + random);
													System.out.println();
												} if ( count == 9 ) {
													System.out.println("=============================");
													System.out.println();
													System.out.println("마을 아이 A : 후후 어림도 없지! 10번안에 못맞췄구나? 하지만 다시 도전하게 해줄게!");
													System.out.println("마을 아이 A : 내가 생각한 숫자는 \"" + random + "\"이었어!");
													System.out.println();
												}
											}
										}
									} else if ( select_ten == 2 ) {
										System.out.println("=============================");
										System.out.println();
										System.out.println("상위 메뉴로 되돌아갑니다.");
										System.out.println();
										break;
									} else {
										System.out.println("=============================");
										System.out.println();
										System.out.println("잘못 입력하셨습니다. 1 또는 2 를 입력해주세요.");
										System.out.println();
										continue;
									}
								} else if ( !Starter.is_mini_game_ten ) {	// 미니게임(숫자 맞추기) 클리어 후 출력값
									kid_battle_music.close();
									night_and_day_music_stop.interrupt();	// 밤과 낮의 노래를 종료시켜주는 스레드 종료

									System.out.println("=============================");
									System.out.println();
									System.out.println("마을 아이 A : 아까는 즐거웠어! 너 생각보다 잘하더라..");
									System.out.println("마을 아이 A : 오늘은 피곤해서 숫자 맞추기는 그만하고 다음에 놀자~ 안녕~~");
									System.out.println();
									System.out.println("=============================");
									System.out.println();
									System.out.println("※ 마을 아이 A는 지쳐서 다음에 놀기를 원하고 있습니다.");
									System.out.println();
									break;
								}
							}
						} else if ( select_mini_game == 2 ) {

							int player_count = 0; // 플레이어의 정답 수 
							int kid_count = 0;	// 마을 아이의 정답 수

							while(true) {
								if ( Starter.is_mini_game_odd_even ) {	// 미니게임(홀짝 맞추기) 클리어 전 출력
									System.out.println("=============================");
									System.out.println();
									System.out.println("마을 아이 B : 좋았어! 이번엔 내가 원하는 게임을 할 차례야!");
									System.out.println("마을 아이 A : 으.. 난 이거 별로 안좋아 하는데...");
									System.out.println("마을 아이 B : 뭐!? 그러기 있어!? 너무해.. 아냐 그래도 " + player.name + "는(은) 처음하니까 괜찮을거야!");
									System.out.println("마을 아이 A : 그러..려나...?? 그래 뭐 어때 한번 해보자");
									System.out.println("마을 아이 B : 후후 난 운이 좋으니까! 게임 설명을 먼저 해줄게!");
									System.out.println();
									System.out.println("=============================");
									System.out.println();
									System.out.println("1. 시작하기  2.  되돌아가기");
									System.out.println();
									System.out.println("※ 게임 방법은 간단합니다. 서로 주사위를 굴려 홀인지 짝인지 맞추는 단순한 게임입니다.");
									System.out.println("※ 상대방보다 먼저 정답을 5회 맞추면 승리하게 됩니다.");
									System.out.println("※ 지극히 운이 필요한 게임이며 운이 나쁘면 계속해서 성공하지 못할 수 있습니다.");
									System.out.println("※ 행운을 빕니다.");
									System.out.println();
									System.out.print("입력 :  ");

									input_only_number_check();
									int select_odd_even = sc.nextInt();
									Starter.console_clear();
									// 홀짝 맞추기 시작
									if ( select_odd_even == 1 ) {
										// 밤 낮의 시간은 흘러가게하고 노래만 중단시키기 위해서 만든 쓰레드
										night_and_day_music_stop = new Thread(new Night_and_day_music_stop());	
										night_and_day_music_stop.setDaemon(true);
										night_and_day_music_stop.start();
										// 아이와 대결 노래 시작
										kid_battle_music = new Music("kid_battle.mp3", true);
										kid_battle_music.setDaemon(true);
										kid_battle_music.start();
										
										while( Starter.is_mini_game_odd_even ) { // Starter.is_mini_game_odd_even가 트루인동안 반복해라( 이길때까지 하라는 소리)
											System.out.println("=============================");
											System.out.println();
											System.out.println("마을 아이 B : 좋았어... 내가 운하나는 기가막히게 좋거든? 긴장하는게 좋을거야");
											System.out.println("마을 아이 B : 그럼 내가 굴릴테니까 홀인지 짝인지 맞춰봐!");
											System.out.println();
											System.out.println("=============================");
											System.out.println();
											System.out.println("1. 홀  2. 짝");
											System.out.println();
											System.out.print("입력 :  ");

											while( Starter.is_mini_game_odd_even ) {

												int random = (int)(Math.random()*6 + 1); // 랜덤한 주사위 숫자가 나오게함
												input_only_number_check();
												int player_answer = sc.nextInt();
												Starter.console_clear();

												if ( player_answer == 1 && random % 2 == 1 ) { // 홀 선택 홀 결과
													player_count++;	// 플레이어가 정답을 맞추면 플레이어 점수가 1점씩 오름
													if ( player_count < 5 ) {
														System.out.println("=============================");
														System.out.println();
														System.out.println( player.name + " : 음... 난 홀! 홀로 할래!");
														System.out.println();
														System.out.println("마을 아이 B : 음.. 내 주사위에서 나온 숫자는.. " + random + "이야!");
														System.out.println();
														System.out.println( player.name + " : 후후 맞춰버렸네?? 이제 " + player_count + "번 맞췄으니.. 긴장하라구!");
														System.out.println();
														break;
													} else if ( player_count == 5 ) {	// 5점 쌓이면 승리
														System.out.println("=============================");
														System.out.println();
														System.out.println( player.name + " : 음... 난 홀! 홀로 할래!");
														System.out.println();
														System.out.println("마을 아이 B : 음.. 내 주사위에서 나온 숫자는.. " + random + "이야!");
														System.out.println();
														System.out.println( player.name + " : 행운의 여신이 나에게 미소를 지었나보네~ 벌써 5번! 내 승리야 소년!");
														System.out.println();
														System.out.println("마을 아이 B : 이럴수가... 사기가 아니고서야.. 아니야.. 그래 내가 졌어.. 축하해! 오늘 너무 즐거웠어!");
														System.out.println("마을 아이 B : 다음에 또 같이 놀자!");
														System.out.println();
														Starter.is_mini_game_odd_even = false;		// 최종적으로 플레이어가 승리했을때 미니게임(홀짝 맞추기)의 입장을 false로 바꾸어 못들어가게 함
														break;
													}
												} else if ( player_answer == 1 && random % 2 == 0 ) {	// 홀 선택 짝 결과
													System.out.println("=============================");
													System.out.println();
													System.out.println( player.name + " : 음... 난 홀! 홀로 할래!");
													System.out.println();
													System.out.println("마을 아이 B : 음.. 내 주사위에서 나온 숫자는.. " + random + "이야!");
													System.out.println();
													System.out.println( player.name + " : 엑.. 틀렸잖아.. 우씨 다음엔 맞추겠어!");
													System.out.println();
													break;
												} else if ( player_answer == 2 && random % 2 == 1 ) {	// 짝 선택 홀 결과
													System.out.println("=============================");
													System.out.println();
													System.out.println( player.name + " : 음... 난 짝! 짝으로 할래!");
													System.out.println();
													System.out.println("마을 아이 B : 음.. 내 주사위에서 나온 숫자는.. " + random + "이야!");
													System.out.println();
													System.out.println( player.name + " : 엑.. 틀렸잖아.. 우씨 다음엔 맞추겠어!");
													System.out.println();
													break;
												} else if ( player_answer == 2 && random % 2 == 0 ) {	// 짝 선택 짝 결과
													player_count++;	// 플레이어가 정답을 맞추면 플레이어 점수가 1점씩 오름
													if ( player_count < 5 ) {
														System.out.println("=============================");
														System.out.println();
														System.out.println( player.name + " : 음... 난 짝! 짝으로 할래!");
														System.out.println();
														System.out.println("마을 아이 B : 음.. 내 주사위에서 나온 숫자는.. " + random + "이야!");
														System.out.println();
														System.out.println( player.name + " : 후후 맞춰버렸네?? 이제 " + player_count + "번 맞췄으니.. 긴장하라구!");
														System.out.println();
														break;
													} else if ( player_count == 5 ) {	// 5점 쌓으면 승리
														System.out.println("=============================");
														System.out.println();
														System.out.println( player.name + " : 음... 난 짝! 짝으로 할래!");
														System.out.println();
														System.out.println("마을 아이 B : 음.. 내 주사위에서 나온 숫자는.. " + random + "이야!");
														System.out.println();
														System.out.println( player.name + " : 행운의 여신이 나에게 미소를 지었나보네~ 벌써 5번! 내 승리야 소년!");
														System.out.println();
														System.out.println("마을 아이 B : 이럴수가... 사기가 아니고서야.. 아니야.. 그래 내가 졌어.. 축하해! 오늘 너무 즐거웠어!");
														System.out.println("마을 아이 B : 다음에 또 같이 놀자!");
														System.out.println();
														Starter.is_mini_game_odd_even = false;		// 최종적으로 플레이어가 승리했을때 미니게임(홀짝 맞추기)의 입장을 false로 바꾸어 못들어가게 함
														break;
													} else {
														System.out.println("=============================");
														System.out.println();
														System.out.println("잘못 입력하셨습니다. 1 또는 2 를 입력해주세요");
														System.out.println();
														continue;
													}
												}
											}
											while ( Starter.is_mini_game_odd_even ) {	// 마을 아이들의 맞추기 턴. player가 홀짝을 정한다.
												System.out.println("=============================");
												System.out.println();
												System.out.println("당신이 주사위를 굴릴 차례입니다.");
												System.out.println();
												System.out.println("※ 주사위 숫자를 입력해주세요. (1~6 사이의 숫자로 입력해주세요)");
												System.out.println();
												System.out.print("입력 :  ");

												int kid_answer = (int)(Math.random()*2 + 1); // 마을 아이 B의 대답은 1 또는 2로 랜덤값 부여. (1은 홀수 ,2는 짝수)
												input_only_number_check();
												int player_dice = sc.nextInt(); // 플레이어가 주사위 숫자를 정함
												Starter.console_clear();

												if ( player_dice < 1 || player_dice > 6 ) {			// 1~6까지만 입력하게하고 그 외의값은 안되게 함 주사위 숫자를 나타내는 player_dice 가 사용되기 때문. 주사위가 200이면 이상하니까
													System.out.println("=============================");
													System.out.println();
													System.out.println("잘못 입력하셨습니다. 1~6 사이의 숫자를 입력해주세요.");
													System.out.println();
													continue;
												} if ( kid_answer == 1 && player_dice % 2 == 1 ) {
													kid_count++;	// 마을 아이가 정답을 맞추면 마을 아이의 점수가 1점씩 오름
													if ( kid_count < 5 ) {
														System.out.println("=============================");
														System.out.println();
														System.out.println("마을 아이 B : 좋아.. 이제 내차례지? 나는.. 그래 홀이야!");
														System.out.println();
														System.out.println( player.name + " : 내 주사위는 " + player_dice + "이(가) 나왔어..");
														System.out.println();
														System.out.println("마을 아이 B : 히히 이거봐 역시 난 운이 좋다니까?? " + kid_count + "번째 성공!");
														System.out.println();
														break;
													} else if ( kid_count == 5 ) {	// 마을아이 승리시 정답수 초기화 후 다시 시작
														System.out.println("=============================");
														System.out.println();
														System.out.println("마을 아이 B : 좋아.. 이제 내차례지? 나는.. 그래 홀이야!");
														System.out.println();
														System.out.println( player.name + " : 내 주사위는 " + player_dice + "이(가) 나왔어..");
														System.out.println();
														System.out.println("마을 아이 B : 하하하하 이거봐 역시 난 운이 좋아! 벌써 5번! 어때? 재도전할 기회를 주지!");
														System.out.println();
														System.out.println( player.name + " : .... 그래 고맙다..");
														System.out.println();
														kid_count = 0;		// 마을 아이가 최종적으로 이기면 플레이어와 마을아이의 점수를 0 점으로 초기화 하고 홀짝 맞추기 게임을 재 시작함
														player_count = 0;
														break;
													}
												} else if ( kid_answer == 1 && player_dice % 2 == 0 ) {
													System.out.println("=============================");
													System.out.println();
													System.out.println("마을 아이 B : 좋아.. 이제 내차례지? 나는.. 그래 홀이야!");
													System.out.println();
													System.out.println( player.name + " : 내 주사위는 " + player_dice + "이(가) 나왔어..");
													System.out.println();
													System.out.println("마을 아이 B : 뭐?? 거짓말 하는거 아니야?? 내가 틀렸다니! 다음엔 맞춰주겠어..");
													System.out.println();
													break;
												} else if ( kid_answer == 2 && player_dice % 2 == 1 ) {
													System.out.println("=============================");
													System.out.println();
													System.out.println("마을 아이 B : 좋아.. 이제 내차례지? 나는.. 그래 짝이야!");
													System.out.println();
													System.out.println( player.name + " : 내 주사위는 " + player_dice + "이(가) 나왔어..");
													System.out.println();
													System.out.println("마을 아이 B : 뭐?? 거짓말 하는거 아니야?? 내가 틀렸다니! 다음엔 맞춰주겠어..");
													System.out.println();
													break;
												} else if ( kid_answer == 2 && player_dice % 2 == 0 ) {
													kid_count++;	// 마을 아이가 정답을 맞추면 마을 아이의 점수가 1점씩 오름
													if ( kid_count < 5 ) {
														System.out.println("=============================");
														System.out.println();
														System.out.println("마을 아이 B : 좋아.. 이제 내차례지? 나는.. 그래 짝이야!");
														System.out.println();
														System.out.println( player.name + " : 내 주사위는 " + player_dice + "가 나왔어..");
														System.out.println();
														System.out.println("마을 아이 B : 히히 이거봐 역시 난 운이 좋다니까?? " + kid_count + "번째 성공!");
														System.out.println();
														break;
													} else if ( kid_count == 5 ) {
														System.out.println("=============================");
														System.out.println();
														System.out.println("마을 아이 B : 좋아.. 이제 내차례지? 나는.. 그래 짝이야!");
														System.out.println();
														System.out.println( player.name + " : 내 주사위는 " + player_dice + "가 나왔어..");
														System.out.println();
														System.out.println("마을 아이 B : 하하하하 이거봐 역시 난 운이 좋아! 벌써 5번! 어때? 재도전할 기회를 주지!");
														System.out.println();
														System.out.println( player.name + " : .... 그래 고맙다..");
														System.out.println();
														kid_count = 0;		// 마을 아이가 최종적으로 이기면 플레이어와 마을아이의 점수를 0 점으로 초기화 하고 홀짝 맞추기 게임을 재 시작함
														player_count = 0;
														break;
													}
												}
											}
										} 
									} else if ( select_odd_even == 2 ) { 
										System.out.println("=============================");
										System.out.println();
										System.out.println("상위 메뉴로 되돌아갑니다.");
										System.out.println();
										break;
									} else {
										System.out.println("=============================");
										System.out.println();
										System.out.println("잘못 입력하셨습니다. 1 또는 2 를 입력해주세요.");
										System.out.println();
										continue;
									}
								} else if ( !Starter.is_mini_game_odd_even ) {		// 미니게임(홀짝 맞추기) 클리어 후 출력값
									kid_battle_music.close();	// 아이와 배틀 노래 종료
									night_and_day_music_stop.interrupt();	// 밤과 낮의 노래를 종료시켜주는 스레드 종료

									System.out.println("=============================");
									System.out.println();
									System.out.println("마을 아이 B : 아까는 즐거웠어! 너 생각보다 운이 좋더라..");
									System.out.println("마을 아이 B : 오늘은 피곤해서 홀짝 맞추기는 그만하고 다음에 놀자~ 안녕~~");
									System.out.println();
									System.out.println("=============================");
									System.out.println();
									System.out.println("※ 마을 아이 B는 지쳐서 다음에 놀기를 원하고 있습니다.");
									System.out.println();
									break;
								}
							}
						} else if ( select_mini_game == 3 ) {
							System.out.println("=============================");
							System.out.println();
							System.out.println("상위 메뉴로 되돌아갑니다.");
							System.out.println();
							break;
						} else {
							System.out.println("=============================");
							System.out.println();
							System.out.println("잘못 입력하셨습니다. 1 또는 2 또는 3 을 입력해주세요.");
							System.out.println();
							continue;
						}
					} else {	// 미니게임 (홀짝,숫자 맞추기) 모두 클리어 후 출력값
						System.out.println("=============================");
						System.out.println();
						System.out.println("마을 아이 A : 이야~ 즐거웠었어~~");
						System.out.println("마을 아이 B : 맞아! 우리랑 함께 놀아줘서 고마웠었어!");
						System.out.println("마을 아이 A : 우리 친구가 된거 맞지?? 다음에도 꼭 다시 같이 놀아줘야해~");
						System.out.println("마을 아이 B : 좋아 이몸이 또 너와 놀아줄테니까 또 놀러 오라구!");
						System.out.println();
						System.out.println("=============================");
						System.out.println();
						System.out.println("※ " + player.name + "는(은) 아이들과의 승부를 모두 이겼습니다.");
						System.out.println("※ 아이들은 모두 지쳐서 다음에 놀기를 원하고 있습니다.");
						System.out.println();
						break;
					}
				}
			} else if ( select_village_hall == 4 ) {
				System.out.println("=============================");
				System.out.println();
				System.out.println("마을로 되돌아갑니다.");
				System.out.println();
				break;
			} else {
				System.out.println("=============================");
				System.out.println();
				System.out.println("잘못 입력하셨습니다. 1 또는 2 또는 3 또는 4 를 입력해주세요.");
				System.out.println();
				continue;
			}
		}
	}
}

class Hunting_ground extends Place {
	public Hunting_ground() {
		super("사냥터");
	}

	public void hunting_ground ( Player player ) {
		// 사냥터 입장
		while(true) { 
			System.out.println("=============================");
			System.out.println();
			System.out.println("사냥터는 다음과 같은 사냥터가 존재합니다.");
			System.out.println();
			System.out.println("1. 슬라임 늪지대 (level 1이상 입장가능)");
			System.out.println("2. 버섯 군락지 (level 4이상 입장가능)");
			System.out.println("3. 골렘의 사원 (level 7이상 입장가능)");
			System.out.println("4. 스텀프킹의 서식지 (level 10 입장가능)");
			System.out.println("5. 되돌아가기");
			System.out.println();
			System.out.println("=============================");
			System.out.println();
			System.out.print("입력 :  ");

			input_only_number_check();
			int select_hunting_ground = sc.nextInt();
			Starter.console_clear();

			if ( select_hunting_ground == 1 ) {	// 슬라임 서식지 부분
				(new Slime_cienaga()).slime_cienaga(player);
			} else if ( select_hunting_ground == 2 ) {	// 버섯 군락지 부분
				(new Mushroom_colony()).mushroom_colony(player);
			} else if ( select_hunting_ground == 3 ) {	// 골렘의 사원 부분
				(new Golem_temple()).golem_temple(player);
			} else if ( select_hunting_ground == 4 ) {	// 스텀프 킹 부분
				(new Stump_king_residence()).stump_king_residence(player);
			} else if ( select_hunting_ground == 5 ) {
				System.out.println("=============================");
				System.out.println();
				System.out.println("메뉴로 되돌아갑니다.");
				System.out.println();
				break;
			} else {
				System.out.println("=============================");
				System.out.println();
				System.out.println("잘못 입력하셨습니다. 1 또는 2 또는 3 또는 4 또는 5 를 입력해주세요.");
				System.out.println();
				continue;
			}
		}
	}
}

class Slime_cienaga extends Place {
	public Slime_cienaga() {
		super("슬라임 늪지대");
	}
	// 플레이어를 슬라임 늪지대에 입장시킨다.
	public void slime_cienaga( Player player ) {
		while(true) {

			player.status();
			System.out.println( "=============================");
			System.out.println();
			System.out.println( "이곳은 " + name + "입니다." );
			System.out.println( "주변에서 음산한 소리가 들려오는것 같습니다...");
			System.out.println();
			System.out.println( "=============================");
			System.out.println();
			System.out.println( "몬스터를 탐색하시겠습니까?");
			System.out.println();
			System.out.println( "1. 예  2. 아니요, 되돌아가겠습니다." );
			System.out.println();
			System.out.print("입력 :  ");

			input_only_number_check();
			int search_select = sc.nextInt();
			Starter.console_clear();
			// 탐색한다를 선택했을시
			if ( search_select == 1 ) {
				// 랜덤 확률로 각 속성 몬스터 조우하기.
				int encounter_probability = (int)(Math.random()*3 + 1);
				//	1/3 확률로 이 조건문에 진입
				if ( encounter_probability == 1 ) {
					// 몬스터를 파이어 슬라임이라는 새로운 객체로 만들어줌
					Starter.monster = new Fire_slime();
					// 전투페이즈 돌입
					player.battle(Starter.monster);
				} else if ( encounter_probability == 2 ) {	// 위와 동일
					Starter.monster = new Water_slime();
					player.battle(Starter.monster);
				} else if ( encounter_probability == 3 ) {	// 위와 동일
					Starter.monster = new Grass_slime();
					player.battle(Starter.monster);
				}
			} else if ( search_select == 2 ) {
				System.out.println("=============================");
				System.out.println();
				System.out.println( "메뉴로 되돌아갑니다." );
				System.out.println();
				break;
			} else {
				System.out.println("=============================");
				System.out.println();
				System.out.println( "잘못 입력하셨습니다. 1또는 2를 입력해주세요." );
				System.out.println();
				continue;
			}
		}
	}
}

class Mushroom_colony extends Place {
	public Mushroom_colony() {
		super("버섯 군락지");
	}
	// 플레이어를 버섯 군락지에 입장시킨다.
	public void mushroom_colony( Player player ) {
		while(true) {
			// 레벨에 따른 입장제한 걸기
			if ( player.level < 4 ) {
				System.out.println("=============================");
				System.out.println();
				System.out.println(player.name + "의 레벨이 부족합니다. 레벨을 올리신 다음 입장해주세요.");
				System.out.println();
				break;
			} else if ( player.level >= 4 ) {

				player.status();
				System.out.println( "=============================");
				System.out.println();
				System.out.println( "이곳은 " + name + "입니다." );
				System.out.println( "주변에서 음산한 소리가 들려오는것 같습니다...");
				System.out.println();
				System.out.println( "=============================");
				System.out.println();
				System.out.println( "몬스터를 탐색하시겠습니까?");
				System.out.println();
				System.out.println( "1. 예  2. 아니요, 되돌아가겠습니다." );
				System.out.println();
				System.out.print("입력 :  ");

				input_only_number_check();
				int search_select = sc.nextInt();
				Starter.console_clear();
				// 탐색한다를 선택했을시
				if ( search_select == 1 ) {
					// 랜덤 확률로 각 속성 몬스터 조우하기.
					int encounter_probability = (int)(Math.random()*3 + 1);
					//	1/3 확률로 이 조건문에 진입
					if ( encounter_probability == 1 ) {
						// 몬스터를 파이어 머슈룸이라는 새로운 객체로 만들어줌
						Starter.monster = new Fire_mushroom();
						// 전투페이즈 돌입
						player.battle(Starter.monster);
					} else if ( encounter_probability == 2 ) {	// 위와 동일
						Starter.monster = new Water_mushroom();
						player.battle(Starter.monster);
					} else if ( encounter_probability == 3 ) {	// 위와 동일
						Starter.monster = new Grass_mushroom();
						player.battle(Starter.monster);
					}
				} else if ( search_select == 2 ) {
					System.out.println("=============================");
					System.out.println();
					System.out.println( "메뉴로 되돌아갑니다." );
					System.out.println();
					break;
				} else {
					System.out.println("=============================");
					System.out.println();
					System.out.println( "잘못 입력하셨습니다. 1또는 2를 입력해주세요." );
					System.out.println();
					continue;
				}
			}
		}
	}
}

class Golem_temple extends Place {
	public Golem_temple() {
		super("골렘의 사원");
	}
	// 플레이어를 골렘의 사원에 입장시킨다.
	public void golem_temple( Player player ) {
		while(true) {
			// 레벨에 따른 입장제한 걸기
			if ( player.level < 7 ) {
				System.out.println("=============================");
				System.out.println();
				System.out.println(player.name + "의 레벨이 부족합니다. 레벨을 올리신 다음 입장해주세요.");
				System.out.println();
				break;
			} else if ( player.level >= 7 ) {

				player.status();
				System.out.println( "=============================");
				System.out.println();
				System.out.println( "이곳은 " + name + "입니다." );
				System.out.println( "주변에서 음산한 소리가 들려오는것 같습니다...");
				System.out.println();
				System.out.println( "=============================");
				System.out.println();
				System.out.println( "몬스터를 탐색하시겠습니까?");
				System.out.println();
				System.out.println( "1. 예  2. 아니요, 되돌아가겠습니다." );
				System.out.println();
				System.out.print("입력 :  ");

				input_only_number_check();
				int search_select = sc.nextInt();
				Starter.console_clear();
				// 탐색한다를 선택했을시
				if ( search_select == 1 ) {
					// 랜덤 확률로 각 속성 몬스터 조우하기.
					int encounter_probability = (int)(Math.random()*3 + 1);
					//	1/3 확률로 이 조건문에 진입
					if ( encounter_probability == 1 ) {
						// 몬스터를 파이어 골렘이라는 새로운 객체로 만들어줌
						Starter.monster = new Fire_golem();
						// 전투페이즈 돌입
						player.battle(Starter.monster);
					} else if ( encounter_probability == 2 ) {	//위와 동일
						Starter.monster = new Water_golem();
						player.battle(Starter.monster);
					} else if ( encounter_probability == 3 ) {	// 위와 동일
						Starter.monster = new Grass_golem();
						player.battle(Starter.monster);
					}

					// 되돌아간다 선택시
				} else if ( search_select == 2 ) {
					System.out.println("=============================");
					System.out.println();
					System.out.println( "메뉴로 되돌아갑니다." );
					System.out.println();
					break;

					// 잘못 입력했을시
				} else {
					System.out.println("=============================");
					System.out.println();
					System.out.println( "잘못 입력하셨습니다. 1또는 2를 입력해주세요." );
					System.out.println();
					continue;
				}
			}
		}
	}
}

class Stump_king_residence extends Place {

	public Stump_king_residence() {
		super("스텀프 킹의 거처");
	}
	// 플레이어를 스텀프 킹의 거처에 입장시킨다.
	public void stump_king_residence( Player player ) {
		while(true) {
			// 한번 클리어 하면 다시 입장할수 없게 만들기 위한 도구
			if (Starter.is_boss) {
				// 레벨에 따른 입장제한 걸기
				if ( player.level < 10 ) {
					System.out.println("=============================");
					System.out.println();
					System.out.println(player.name + "의 레벨이 부족합니다. 레벨을 올리신 다음 입장해주세요.");
					System.out.println();
					break;
				} else if ( player.level >= 10 ) {
					// 몬스터를 스텀프킹으로 정의 ( 위의 hunting_ground와 다른이유는 몬스터가 하나밖에 없기 때문)
					Starter.monster = new Stump_king();

					player.detailed_status();
					System.out.println( "=============================");
					System.out.println();
					System.out.println( "이곳은 " + name + "입니다." );
					System.out.println( "주변에서 음산한 소리가 들려오는것 같습니다...");
					System.out.println();
					System.out.println( "=============================");
					System.out.println();
					System.out.println( "근처에서 " + Starter.monster.name + "의 울음소리가 들립니다! 전투를 하시겠습니까?");
					System.out.println();
					System.out.println("※ 주의 ! 매우 강한 적입니다. 스킬을 배우고 오는것을 추천합니다.");
					System.out.println();
					System.out.println( "1. 예  2. 아니요, 되돌아가겠습니다." );
					System.out.println();
					System.out.print("입력 :  ");

					input_only_number_check();
					int search_select = sc.nextInt();
					Starter.console_clear();
					// 전투한다를 선택했을시
					if ( search_select == 1 ) {
						Thread night_and_day_music_stop = new Thread(new Night_and_day_music_stop());	// 밤과 낮의 노래를 계속 종료시켜주는 스레드 
						night_and_day_music_stop.setDaemon(true);					// 밤 낮의 시간은 흘러가게하고 노래만 중단시키기 위해서 만든 쓰레드
						night_and_day_music_stop.start();
						// 보스 배틀 노래 시작
						Music boss_battle_music = new Music("boss_battle.mp3", true);	// 전투에서 도망가거나 패배하고 돌아왔을때 들어올때마다 새로 시작해야 하므로 새로 만들어준다
						boss_battle_music.setDaemon(true);
						boss_battle_music.start();

						// 전투페이즈 돌입
						player.battle(Starter.monster);
						boss_battle_music.close();	// 전투종료 후 보스 배틀 노래 종료
						night_and_day_music_stop.interrupt();	// 밤과 낮의 노래를 종료시켜주는 스레드 종료
						if ( Starter.monster.is_dead() ) {
							Starter.is_boss = false;	// 스텀프 킹이 죽음 상태가 되면 입장조건을 false 상태로 만들어 재입장 불가능하게 만듬.
							break;
						}
					} else if ( search_select == 2 ) {
						System.out.println("=============================");
						System.out.println();
						System.out.println( "메뉴로 되돌아갑니다." );
						System.out.println();
						break;
					} else {
						System.out.println("=============================");
						System.out.println();
						System.out.println( "잘못 입력하셨습니다. 1또는 2를 입력해주세요." );
						System.out.println();
						continue;
					}
				}
			} else if ( !Starter.is_boss ) {	// 스텀프 킹을 처치하고 조건이 false가 되어 이 문장이 출력됨
				System.out.println("=============================");
				System.out.println();
				System.out.println("축하드립니다. 스텀프 킹을 클리어 하셨습니다. 마을 회관에 들러서 정말 잘했어요! 도장을 수령해주세요.");
				System.out.println();
				break;
			}
		}
	}
}