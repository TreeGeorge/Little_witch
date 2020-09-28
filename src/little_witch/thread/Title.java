package little_witch.thread;

import little_witch.Starter;

public class Title implements Runnable {

	@Override
	public void run() {

		try {		
			while (!Thread.currentThread().isInterrupted()) {	// 중단상태가 아닐때까지만 반복해라
				Starter.console_clear();
				System.out.println("                                              .=====!~                    ");
				System.out.println("                            -;;~-;;;;;;,~;;:;-           ~;~;:             ▄█        ▄█      ███         ███      ▄█          ▄████████");
				System.out.println("                       ~:*.                                     ;*-.       ███       ███  ▀█████████▄ ▀█████████▄ ███         ███    ███");
				System.out.println("                   .~!                                      ..,:#$~ !      ███       ███▌    ▀███▀▀██    ▀███▀▀██ ███         ███    █▀ ");
				System.out.println("                  =                                     =#.                ███       ███▌     ███   ▀     ███   ▀ ███        ▄███▄▄▄    ");
				System.out.println("                :-                                  =,- ,= =               ███       ███▌     ███         ███     ███       ▀▀███▀▀▀    ");
				System.out.println("               !                                 ;;     #   =              ███       ███      ███         ███     ███         ███    █▄ ");
				System.out.println("              =                               ~*       .-    *             ███▌    ▄ ███      ███         ███     ███▌    ▄   ███    ███");
				System.out.println("             !                              ~!         #      =            █████▄▄██ █▀      ▄████▀      ▄████▀   █████▄▄██   ██████████");
				System.out.println("            .=           :* #              !          $        $           ▀                                      ▀                     ");
				System.out.println("            :          :,   =            =            @         :          ");
				System.out.println("            =        !:     ~           $            *          !          ");
				System.out.println("           .       ,!       .          =            ,-          !          ");
				System.out.println("           :      =-        .         #             =           !                                ▄█     █▄     ▄█    █▄     ▄█   ▄████████    ▄█    █▄    ");
				System.out.println("           :     #          .        *             @           $                                 ███     ███   ███    ███   ███  ███    ███   ███    ███ ");
				System.out.println("           :    -           .       ,,            =           $                                  ███     ███   ███    ███   ███▌ ███    █▀    ███    ███   ");
				System.out.println("           :   -!           $       *            .          !~                                   ███     ███  ▄███▄▄▄▄███▄▄ ███▌ ███         ▄███▄▄▄▄███▄▄");
				System.out.println("           .-  !            .:     #=           ,;       :!                                      ███     ███ ▀▀███▀▀▀▀███▀  ███▌ ███        ▀▀███▀▀▀▀███▀ ");
				System.out.println("            *  ~             !    = ;           :,-- *=-                                         ███     ███   ███    ███   ███  ███    █▄    ███    ███  ");
				System.out.println("            $ .               *  ..  :         @                                                 ███ ▄█▄ ███   ███    ███   ███  ███    ███   ███    ███  ");
				System.out.println("            ; .                ~-#   #        @                                                   ▀███▀███▀    ███    █▀    █▀   ████████▀    ███    █▀ ");
				System.out.println("            ,,.                 ~.   #      ,=                             ");
				System.out.println("             $.                      *     -~                              ");
				System.out.println("              =;                    ,,    !                                ");
				System.out.println("              $!                    ~   ,=                                 ");
				System.out.println("            -:  -~                  $  $                        Press Enter! 엔터키를 눌러 시작하세요!           ");
				System.out.println("             ,::;                  ,*,                                     ");
				System.out.println("                                   @~                                      ");
				System.out.println("\n\n\n");

				Thread.sleep(1000);

				Starter.console_clear();
				System.out.println("                                              .=====!~                    ");
				System.out.println("                            -;;~-;;;;;;,~;;:;-           ~;~;:             ▄█        ▄█      ███         ███      ▄█          ▄████████");
				System.out.println("                       ~:*.                                     ;*-.       ███       ███  ▀█████████▄ ▀█████████▄ ███         ███    ███");
				System.out.println("                   .~!                                      ..,:#$~ !      ███       ███▌    ▀███▀▀██    ▀███▀▀██ ███         ███    █▀ ");
				System.out.println("                  =                                     =#.                ███       ███▌     ███   ▀     ███   ▀ ███        ▄███▄▄▄    ");
				System.out.println("                :-                                  =,- ,= =               ███       ███▌     ███         ███     ███       ▀▀███▀▀▀    ");
				System.out.println("               !                                 ;;     #   =              ███       ███      ███         ███     ███         ███    █▄ ");
				System.out.println("              =                               ~*       .-    *             ███▌    ▄ ███      ███         ███     ███▌    ▄   ███    ███");
				System.out.println("             !                              ~!         #      =            █████▄▄██ █▀      ▄████▀      ▄████▀   █████▄▄██   ██████████");
				System.out.println("            .=           :* #              !          $        $           ▀                                      ▀                     ");
				System.out.println("            :          :,   =            =            @         :          ");
				System.out.println("            =        !:     ~           $            *          !          ");
				System.out.println("           .       ,!       .          =            ,-          !          ");
				System.out.println("           :      =-        .         #             =           !                                ▄█     █▄     ▄█    █▄     ▄█   ▄████████    ▄█    █▄    ");
				System.out.println("           :     #          .        *             @           $                                 ███     ███   ███    ███   ███  ███    ███   ███    ███ ");
				System.out.println("           :    -           .       ,,            =           $                                  ███     ███   ███    ███   ███▌ ███    █▀    ███    ███   ");
				System.out.println("           :   -!           $       *            .          !~                                   ███     ███  ▄███▄▄▄▄███▄▄ ███▌ ███         ▄███▄▄▄▄███▄▄");
				System.out.println("           .-  !            .:     #=           ,;       :!                                      ███     ███ ▀▀███▀▀▀▀███▀  ███▌ ███        ▀▀███▀▀▀▀███▀ ");
				System.out.println("            *  ~             !    = ;           :,-- *=-                                         ███     ███   ███    ███   ███  ███    █▄    ███    ███  ");
				System.out.println("            $ .               *  ..  :         @                                                 ███ ▄█▄ ███   ███    ███   ███  ███    ███   ███    ███  ");
				System.out.println("            ; .                ~-#   #        @                                                   ▀███▀███▀    ███    █▀    █▀   ████████▀    ███    █▀ ");
				System.out.println("            ,,.                 ~.   #      ,=                             ");
				System.out.println("             $.                      *     -~                              ");
				System.out.println("              =;                    ,,    !                                ");
				System.out.println("              $!                    ~   ,=                                 ");
				System.out.println("            -:  -~                  $  $                                   ");
				System.out.println("             ,::;                  ,*,                                     ");
				System.out.println("                                   @~                                      ");
				System.out.println("\n\n\n");

				Thread.sleep(1000);
				
			}
		} catch (InterruptedException e) {
		}
	}
}

