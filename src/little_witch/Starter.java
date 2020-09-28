package little_witch;

import java.util.Scanner;

import little_witch.thread.Burn;
import little_witch.thread.Mana_poison;
import little_witch.thread.Music;
import little_witch.thread.Night_and_day;
import little_witch.thread.Paralysis;
import little_witch.thread.Title;


public class Starter {

	// 공백 부분 삽입을 위해 만들어줌
	public static void console_clear() {
		for (int i = 0; i < 60; i++) {
			System.out.println();
		}
	}

	static Scanner sc = new Scanner(System.in);

	// 숫자 입력값만 받기 위해 사용하는 메소드
	public static void input_only_number_check() {
		while( !sc.hasNextInt() ) { // 입력값이 숫자인지 판별한다.
			sc.next(); // 다음 scanner에 입력해라. ( 와일문을 돈다. 숫자를 입력하면 와일문 조건이 false가 되기 때문에 sc.nextInt(); 값으로 들어간다 )
			System.out.print("입력값 오류 입니다.  숫자로 입력해주세요. \n입력 :  ");
		}
	}
	
	public static Thread night_day = new Thread(new Night_and_day());	// 밤낮을 바뀌게 해주는 쓰레드
	public static int player_attack_delay = 1; // player의 공격 딜레이(지연)값
	public static int monster_attack_delay = 1; // monster의 공격 딜레이(지연)값
	static Thread burn = new Thread(new Burn()); // burn을(화상 상태) 하나로 설정해줌  // (자꾸만 새로 만들어서 하니까 새로운 몬스터에게 걸렸을때 A슬라임과 B슬라임에게 걸린 화상이 비교 불가능해서 중첩되어 걸림)
	static Thread mana_poison = new Thread(new Mana_poison()); // mana_poison을(마나독 상태) 하나로 설정해줌
	static Thread paralysis = new Thread(new Paralysis()); // paralysis를(마비) 하나로 설정해줌
	public static Player player;	// player의 값을 null 로 저장한뒤 뒤에 선택지에서 값을 입력시켜줌
	public static Monster monster;	// monster의 값을 null 로 저장한뒤 뒤에 선택지에서 값을 입력시켜줌
	static boolean is_boss = true; // 보스 클리어 여부
	static boolean is_quest_boss = true; // 보스 클리어 후 정말 잘했어요 도장 여부
	static boolean is_mini_game_ten = true; // 미니게임 숫자 맞추기 클리어 여부
	static boolean is_quest_ten = true; // 미니게임 숫자 맞추기 클리어 후 참 잘했어요 도장 여부
	static boolean is_mini_game_odd_even = true; // 미니게임 홀짝 맞추기 클리어 여부
	static boolean is_quest_odd_even = true; // 미니게임 홀짝 맞추기 클리어 후 참 잘했어요 도장 여부
	static int stamp = 0;	// 잘했어요 도장 개수

	public static void main(String[] args) {
		
		
		Music opening_music = new Music("opening.mp3", true);
		opening_music.setDaemon(true);
		opening_music.start();
		
		// 시작화면
		Thread title = new Thread(new Title());
		title.setDaemon(true);
		title.start();
		
		sc.nextLine();	// 엔터를 치면 (정확히는 엔터를 포함한 값을 입력하면)
		title.interrupt();	// 시작화면 종료
		Starter.console_clear();
		
		// 게임 설명
		String tips = "                                [Tips]\n\n\n\n"+
				"반갑습니다. 이 게임은\n"+
				" ~ 숲속에 살던 꼬마마녀가 세상공부를 위해 스승마녀에게 도움을 받으며 마을에 도움을 주며 성장하는 이야기 ~ 입니다.\n"+
				" 이 게임의 최종 목표는 마을 주민을 도와주고  \"잘했어요!\" 도장을 모두 모아 스승마녀에게 되돌아가는것 입니다.\n\n\n"+
				"게임 시스템에 대해 설명해드리겠습니다.\n\n\n"+
				"1. 캐릭터 상세 정보 : 캐릭터의 현재 상태 및 장비를 해제 및 착용할 수 있습니다.\n\n"+
				"2. 장소 : 마녀의 집, 마을, 사냥터에 입장할 수 있습니다.\n\n"+
				"2-1. 마녀의집 : 각종 마법들을 배울 수 있으며 도장을 모두 모아 이곳으로 되돌아오면 게임이 클리어가 됩니다.\n\n"+
				"2-2. 마을 : 여관에서 체력 및 마나회복, 상점에서 장비구매 및 장비판매, 온천에서 상태이상 회복, 마을 회관에서 도장을 얻을 수 있습니다.\n\n"+
				"2-3. 사냥터 : 레벨에 맞는 사냥터가 존재하며 최종 보스는 단 한번만 처치가 가능합니다.\n\n"+
				"3. 도장개수 확인 및 퀘스트 진행상황 : 현재 가지고있는 도장개수와 퀘스트의 진행 상황을 볼 수 있습니다.\n\n"+
				"4. 밤과 낮이 존재하며 4분간격으로 하루가 반복되며 밤일시에 캐릭터의 마나와 마법 공격력이 강해집니다.\n\n\n\n\n"+
				"                                 캐릭터를 생성하시려면 Enter키를 눌러주세요.\n\n\n\n";

		for ( int i = 0 ; i < tips.length() ; i++ ) { // tips의 길이만큼 i회 반복해라
			System.out.print(tips.charAt(i));	// String은 char의 배열이니까 char의 배열값을 나타내라
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		sc.nextLine();
		Starter.console_clear();

		// 캐릭터 선택
		while (player == null) {
			System.out.println("=============================");
			System.out.println();
			System.out.println("당신의 캐릭터를 선택해주세요.");
			System.out.println();
			System.out.println("1. 메디아 ( 체력 보너스 )  2. 키르케 ( 마법 공격력 보너스 )  3. 프레이야 ( 기본 공격력 보너스 )");
			System.out.println();
			System.out.print("입력 :  ");

			Starter.input_only_number_check();
			int select_character = sc.nextInt();
			Starter.console_clear();

			if ( select_character == 1 ) {			// 메디아를 선택했을시
				player  = new Medeia();
				player.detailed_status();
				player = null;
				System.out.println();
				System.out.println("메디아는 다른 캐릭터들에 비해 체력이 20% 높습니다.");
				System.out.println();
				System.out.println("정말로 이 캐릭터를 선택하시겠습니까?");
				System.out.println();
				System.out.println("1. 네 선택하겠습니다.  2. 아니요.");
				System.out.println();
				System.out.print("입력 :  ");

				Starter.input_only_number_check();
				int select_decide = sc.nextInt();
				Starter.console_clear();
				
				// 캐릭터를 확정 지을것인가
				if ( select_decide == 1 ) { 
					player = new Medeia(); 
					System.out.println("=============================");
					System.out.println();
					System.out.println("당신은 지금부터 메디아로 게임을 진행하게 됩니다. 즐거운 시간 보내시길...");
					System.out.println();
				} else if ( select_decide == 2 ) { 
					System.out.println("=============================");
					System.out.println();
					System.out.println("캐릭터 선택창으로 되돌아갑니다.");
					System.out.println();
				} else { 
					System.out.println("=============================");
					System.out.println();
					System.out.println("잘못 입력하셨습니다. 캐릭터 선택창으로 되돌아갑니다.");
					System.out.println();
				} 
			} else if ( select_character == 2 ) {			// 키르케를 선택했을시
				player  = new Circe();
				player.detailed_status();
				player = null;
				System.out.println();
				System.out.println("키르케는 다른 캐릭터들에 비해 마법 공격력이 20% 높습니다.");
				System.out.println();
				System.out.println("정말로 이 캐릭터를 선택하시겠습니까?");
				System.out.println();
				System.out.println("1. 네 선택하겠습니다.  2. 아니요.");
				System.out.println();
				System.out.print("입력 :  ");

				Starter.input_only_number_check();
				int select_decide = sc.nextInt();
				Starter.console_clear();

				// 캐릭터를 확정 지을것인가
				if ( select_decide == 1 ) { 
					player = new Circe(); 
					System.out.println("=============================");
					System.out.println();
					System.out.println("당신은 지금부터 키르케로 게임을 진행하게 됩니다. 즐거운 시간 보내시길...");
					System.out.println(); 
				} else if ( select_decide == 2 ) { 
					System.out.println("=============================");
					System.out.println();
					System.out.println("캐릭터 선택창으로 되돌아갑니다.");
					System.out.println();
				} else { 
					System.out.println("=============================");
					System.out.println();
					System.out.println("잘못 입력하셨습니다. 캐릭터 선택창으로 되돌아갑니다.");
					System.out.println();
				} 
			} else if ( select_character == 3 ) {			// 프레이야를 선택했을시
				player  = new Freyja();
				player.detailed_status();
				player = null;
				System.out.println();
				System.out.println("프레이야는 다른 캐릭터들에 비해 최대 공격력이 12.5%, 최소 공격력이 25% 높습니다.");
				System.out.println();
				System.out.println("정말로 이 캐릭터를 선택하시겠습니까?");
				System.out.println();
				System.out.println("1. 네 선택하겠습니다.  2. 아니요.");
				System.out.println();
				System.out.print("입력 :  ");

				Starter.input_only_number_check();
				int select_decide = sc.nextInt();
				Starter.console_clear();

				// 캐릭터를 확정 지을것인가
				if ( select_decide == 1 ) { 
					player = new Freyja(); 
					System.out.println("=============================");
					System.out.println();
					System.out.println("당신은 지금부터 프레이야로 게임을 진행하게 됩니다. 즐거운 시간 보내시길...");
					System.out.println();
				} else if ( select_decide == 2 ) { 
					System.out.println("=============================");
					System.out.println();
					System.out.println("캐릭터 선택창으로 되돌아갑니다.");
					System.out.println();
				} else { 
					System.out.println("=============================");
					System.out.println();
					System.out.println("잘못 입력하셨습니다. 캐릭터 선택창으로 되돌아갑니다.");
					System.out.println();
				} 
			} else if ( select_character == 99 ) {	// 시연용 숨겨진 선택지
				player = new Test();	// 시연용 캐릭터
			} else {
				System.out.println("=============================");
				System.out.println();
				System.out.println("잘못 입력하셨습니다. 1 또는 2 또는 3 을 입력해주세요.");
				System.out.println();
			}
		}
		sc.nextLine();
		
		// 게임 스토리
		String intro = "                                    [마녀의 집]\n\n\n"+
		"헤카테 : " + player.name + "야... 밖에는 많은 몬스터들이 도사리고 있어서 지금까지는 못나가게 했는데 지금까지 많이 답답했지??\n\n"+
		player.name + " : 네.. 너무너무 답답했어요..\n\n"+
		"헤카테 : 이제 너도 어느정도 성장을 했으니까 마을에 한번 나가볼래?\n\n"+
		player.name + " : 와 정말 나가도 돼요?\n\n"+
		"헤카테 : 그래... 그래도 아직 많이 걱정이 되니까 네가 위험해지면 안전한 장소로 보내주는 주문을 걸어줄게! [ Save ]\n"+
		"헤카테 : 아 참! 그리고 마을 주민분들을 도와주면 잘했어요 도장을 얻을 수 있을거야!\n"+
		"헤카테 : 잘했어요 도장을 얻는 법은 마을 회관에 촌장님이 알려주실거야! 되도록 먼저 찾아가보는게 좋을거야~\n\n"+
		player.name + " : 열심히 모아올게요! 그런데 몇개나 모으면 되나요??\n\n"+
		"헤카테 : 어머.. 내 정신좀봐.. 중요한걸 이야기 안해줬구나 미안해.. 잘했어요 도장은 총 2개란다\n\n"+
		player.name + " : 알겠습니다! 잘했어요 도장 두개라.. 금방 모아올게요!\n\n"+
		"헤카테 : 그럼 잘 다녀오구! 언제든지 돌아와도 좋아! 레벨이 오르고 찾아오면 새로운 마법을 알려줄게!\n\n"+
		player.name + " : 다녀오겠습니다!\n\n\n"+
		"                                      게임을 진행하시려면 Enter키를 눌러주세요.\n\n\n";
		
		for ( int i = 0 ; i < intro.length() ; i++ ) { // intro의 길이만큼 i회 반복해라
			System.out.print(intro.charAt(i));	// String은 char의 배열이니까 char의 배열값을 나타내라
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		sc.nextLine();
		opening_music.close();
		Starter.console_clear();
		boolean is_one_time = true;	// 와일문 안에서 단 한번만 밤낮 스레드를 실행시키기 위해서 ( 미리 실행시키고 들어가면 게임 시작도 전에 밤이라는 텍스트가 나와버리기 때문 )
									// 와일문 안에서 true로 설정시 계속 트루로 바뀌기 때문
		// 메뉴 부분 (앞으로의 게임 진행을 모두 이곳에서 함)
		while(true) {
			// 밤, 낮 카운트 및 밤낮 노래 시작
			if (is_one_time) {	// 와일문 안에서 밤낮 스레드를 단 한번만 실행시키기 위하여 만든 조건문
				is_one_time = false;
				night_day.setDaemon(true);
				night_day.start();
			}
			player.status();
			System.out.println("=============메뉴=============");
			System.out.println();
			System.out.println("1. 캐릭터 상세 정보");
			System.out.println("2. 장소 선택");
			System.out.println("3. 도장개수 확인 및 남은 퀘스트 조건");
			System.out.println("4. 치트키 ( 레벨, 돈 )");
			System.out.println("5. 게임 종료");
			System.out.println();
			System.out.print("입력 :  ");

			Starter.input_only_number_check();
			int select_menu = sc.nextInt();
			Starter.console_clear();

			//캐릭터 상세 정보 부분
			if ( select_menu == 1 ) {
				while(true) {
					player.detailed_status();
					System.out.println();
					System.out.println("1. 무기 장착  2. 방어구 장착  3. 장신구 장착  4. 되돌아가기");
					System.out.println();
					System.out.print("입력 :  ");

					Starter.input_only_number_check();
					int status_select = sc.nextInt();
					Starter.console_clear();

					// 아이템 장착을 할것인지
					if ( status_select == 1 ) {
						player.equip_weapon(player);
					} else if ( status_select == 2 ) {
						player.equip_armor(player);
					} else if ( status_select == 3 ) {
						player.equip_accessory(player);
					} else if ( status_select == 4 ){
						System.out.println("=============================");
						System.out.println();
						System.out.println("메뉴로 되돌아갑니다.");
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
			} else if ( select_menu == 2 ) { 			// 장소 부분
				while(true) {
					player.status();
					System.out.println();
					System.out.println("1. 마녀의 집으로 간다.  2. 마을로 간다.  3. 사냥터로 간다.  4. 되돌아가기");
					System.out.println();
					System.out.print("입력 :  ");

					Starter.input_only_number_check();
					int select_place = sc.nextInt();
					Starter.console_clear();

					if ( select_place == 1 ) {	// 집 부분
						new Home().home( player ); 		// Home home = new Home(); home.home(player); 이것과 같다.
					} else if ( select_place == 2 ) {	// 마을 부분
						new Village().village( player );
					} else if ( select_place == 3 ) {	// 사냥터 부분
						new Hunting_ground().hunting_ground( player );
					} else if ( select_place == 4 ) {
						System.out.println("=============================");
						System.out.println();
						System.out.println("메뉴로 되돌아갑니다.");
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
			} else if ( select_menu == 3 ) {	// 도장 개수와 아직 클리어하지 못한 퀘스트 확인하기
				if ( Starter.is_quest_boss && Starter.is_quest_ten && Starter.is_quest_odd_even ) {	// if Main.quest_boss 일때 와 !일때를 나눠서 밑에 두개를 나누는게 더 좋다.
					System.out.println("=============================");
					System.out.println();
					System.out.println("현재 모은 도장의 개수 : " + stamp );
					System.out.println("총 모을 수 있는 도장의 개수 : 2");
					System.out.println("도장을 모을 수 있는 퀘스트 : 스텀프 킹 처치 , 마을 아이들과 놀아주기 ( 숫자 맞추기, 홀짝 맞추기 )");
					System.out.println();
				} else if ( !Starter.is_quest_boss && Starter.is_quest_ten && Starter.is_quest_odd_even ) {
					System.out.println("=============================");
					System.out.println();
					System.out.println("현재 모은 도장의 개수 : " + stamp );
					System.out.println("총 모을 수 있는 도장의 개수 : 2");
					System.out.println("도장을 모을 수 있는 퀘스트 : 마을 아이들과 놀아주기 ( 숫자 맞추기, 홀짝 맞추기 )");
					System.out.println();
				} else if ( !Starter.is_quest_boss && !Starter.is_quest_ten && Starter.is_quest_odd_even ) {
					System.out.println("=============================");
					System.out.println();
					System.out.println("현재 모은 도장의 개수 : " + stamp );
					System.out.println("총 모을 수 있는 도장의 개수 : 2");
					System.out.println("도장을 모을 수 있는 퀘스트 : 마을 아이들과 놀아주기 ( 홀짝 맞추기 )");
					System.out.println();
				} else if ( !Starter.is_quest_boss && !Starter.is_quest_ten && !Starter.is_quest_odd_even ) {
					System.out.println("=============================");
					System.out.println();
					System.out.println("현재 모은 도장의 개수 : " + stamp );
					System.out.println("총 모을 수 있는 도장의 개수 : 2");
					System.out.println("모든 도장을 모았습니다. 집으로 돌아가서 스승님에게 보고하세요!");
					System.out.println();
				} else if ( !Starter.is_quest_boss && Starter.is_quest_ten && !Starter.is_quest_odd_even ) {
					System.out.println("=============================");
					System.out.println();
					System.out.println("현재 모은 도장의 개수 : " + stamp );
					System.out.println("총 모을 수 있는 도장의 개수 : 2");
					System.out.println("도장을 모을 수 있는 퀘스트 : 마을 아이들과 놀아주기 ( 숫자 맞추기 )");
					System.out.println();
				} else if ( Starter.is_quest_boss && !Starter.is_quest_ten && Starter.is_quest_odd_even ) {
					System.out.println("=============================");
					System.out.println();
					System.out.println("현재 모은 도장의 개수 : " + stamp );
					System.out.println("총 모을 수 있는 도장의 개수 : 2");
					System.out.println("도장을 모을 수 있는 퀘스트 : 스텀프 킹 처치 , 마을 아이들과 놀아주기 ( 홀짝 맞추기 )");
					System.out.println();
				} else if ( Starter.is_quest_boss && Starter.is_quest_ten && !Starter.is_quest_odd_even ) {
					System.out.println("=============================");
					System.out.println();
					System.out.println("현재 모은 도장의 개수 : " + stamp );
					System.out.println("총 모을 수 있는 도장의 개수 : 2");
					System.out.println("도장을 모을 수 있는 퀘스트 : 스텀프 킹 처치 , 마을 아이들과 놀아주기 ( 숫자 맞추기 )");
					System.out.println();
				} else if ( Starter.is_quest_boss && !Starter.is_quest_ten && !Starter.is_quest_odd_even ) {
					System.out.println("=============================");
					System.out.println();
					System.out.println("현재 모은 도장의 개수 : " + stamp );
					System.out.println("총 모을 수 있는 도장의 개수 : 2");
					System.out.println("도장을 모을 수 있는 퀘스트 : 스텀프 킹 처치");
					System.out.println();
				}
			} else if ( select_menu == 4 ) {	// 치트키 부분
				System.out.println("=============================");
				System.out.println();
				System.out.println("100000골드를 획득하였습니다.");
				System.out.println();
				player.gold += 100000;
				if ( player.level < 10 ) {	// 레벨이 10 이하라면
					player.level_up();	// 레벨업을 시켜라
					player.hp = player.max_hp;	// 체력 회복
					player.mp = player.max_mp;	// 마나 회복
				} else if ( player.level == 10) {	// 레벨이 10 이라면 출력만 해라
					System.out.println("이미 캐릭터가 최고 레벨에 도달하여 레벨이 오르지 않습니다.");
					System.out.println();
				}
			} else if ( select_menu == 5 ) {
				System.out.println("게임을 종료합니다.");
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
