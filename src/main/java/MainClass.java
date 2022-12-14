
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DBConn db = new DBConn(); // static 이기 때문에 안해도 된다.
        UserDto udto = new UserDto();
        GameDto gdto = new GameDto();

        System.out.println("=======================================");
        System.out.println("데이터 베이스");
        System.out.println("오징어 게임에 오신것을 환영합니다.");

        while(true) {
            System.out.println("=======================================");
            System.out.print("아이디가 있습니까?(Y/N) >> ");
            String IDWhether = sc.next();

            if (IDWhether.equalsIgnoreCase("N")) {
                // 회원가입
                System.out.println("=======================================");
                System.out.println("회원가입을 진행합니다.");
                System.out.println("=======================================");
                System.out.print("아이디 입력: ");
                String userId = sc.next();
                System.out.println("=======================================");
                System.out.print("패스워드 입력: ");
                String userPw = sc.next();
                System.out.println("=======================================");
                System.out.print("이름 입력: ");
                String name = sc.next();

                db.save(userId, userPw, name); // 회원가입 함수
                break; // 아이디 여부 break;

            } else if (IDWhether.equalsIgnoreCase("Y")) {
                // 로그인
                while (true) {
                    System.out.println("=======================================");
                    System.out.println("로그인을 진행합니다.");
                    System.out.println("=======================================");
                    System.out.print("아이디 입력: ");
                    String login_userId = sc.next();
                    System.out.println("=======================================");
                    System.out.print("패스워드 입력: ");
                    String login_userPw = sc.next();

                    udto = db.login(login_userId, login_userPw); // 로그인 함수
                    if (udto.getName() == null) { // 로그인 실패 시
                        System.out.println("로그인을 실패하였습니다.");
                    } else { // 로그인 성공 시
                        System.out.println(udto.getName() + " 님은 현재 " + udto.getGusl() + " 개의 구슬을 소지하고 계십니다.");
                        break;
                    }
                }
                break; // 아이디 여부 break;
            }
            System.out.println("Y/N 중 하나를 선택해 주시기 바랍니다.");
        }

        OddEven game = new OddEven();
        System.out.println("=======================================");
        System.out.println("게임을 시작합니다.");

        game.intro(10, 10); // 구슬 게임
        while(game.gameOver()) {
            game.betting();
            game.correctAnswer();
        }

        // 게임 종료 후 저장 여부
        while(true) {
            System.out.print("게임 데이터를 저장 하시겠습니까? (Y/N) >> ");
            String marbleSave = sc.next();

            if (marbleSave.equalsIgnoreCase("Y")) {
                db.gusl_update(gdto.getUserGu(), udto.getId());
                System.out.println("게임 종료합니다.");
                break;

            } else if (marbleSave.equalsIgnoreCase("N")) {
                System.out.println("게임 종료합니다.");
                break;

            }

            System.out.println("Y/N 중 하나를 선택해 주시기 바랍니다.");
        }

        // 만약에 y이면 아이디 입력으로
        // 아니면 신규 가입
        // 아이디 입력 받고, 패스워드 입력받고, 이름 입력받고 DB에 저장하고 난뒤
        // 아이디 입력 (아래 프로세스)

        // 아이디 입력을 받고 패스워드도 입력을 받고
        // DB에 id, pw 가 있는지 확인해서 있으면 게임 시작
        // 없으면 다시 입력

        // 한번 게임을 하고 게임 데이터를 저장을 할건지 물어보고
        // y/n 해서 y 이면 저장하고 게임 종료
        // 다시 게임을 실행하면 자신의 아이디에 따라 구슬 갯수 정보가 나와야 함
    }
}
